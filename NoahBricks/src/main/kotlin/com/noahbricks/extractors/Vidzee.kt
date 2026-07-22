package com.noahbricks.extractors

import android.util.Base64
import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

open class Vidzee : ExtractorApi() {
    override val name = "Vidzee"
    override val mainUrl = "https://player.vidzee.wtf"
    override val requiresReferer = true
    private val coreApi = "https://core.vidzee.wtf"
    private val staticPass = "4f2a9c7d1e8b3a6f0d5c2e9a7b1f4d8c"

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val masterKey = getMasterKey() ?: return

        val headers = mapOf(
            "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36",
            "Origin" to mainUrl,
            "Referer" to "$mainUrl/"
        )

        val resText = app.get(embedUrl, headers = headers).text
        val json = JSONObject(resText)
        val urlArray = json.optJSONArray("url") ?: return
        if (urlArray.length() == 0) return

        val content = urlArray.getJSONObject(0)
        val encryptedLink = content.optString("link")
        if (encryptedLink.isEmpty()) return

        val decryptedUrl = decryptLink(encryptedLink, masterKey) ?: return

        val tracks = json.optJSONArray("tracks")
        if (tracks != null) {
            for (i in 0 until tracks.length()) {
                val track = tracks.getJSONObject(i)
                val subUrl = track.optString("url")
                val lang = track.optString("lang", "Unknown")
                if (subUrl.isNotEmpty()) {
                    subtitleCallback.invoke(SubtitleFile(lang, subUrl))
                }
            }
        }

        val isDuke = embedUrl.contains("sr=1")
        val type = if (isDuke) ExtractorLinkType.VIDEO else ExtractorLinkType.M3U8

        callback.invoke(
            newExtractorLink(
                source = name,
                name = name,
                url = decryptedUrl,
                type = type
            ) {
                this.referer = mainUrl
                this.quality = Qualities.Unknown.value
            }
        )
    }

    private suspend fun getMasterKey(): String? {
        return try {
            val headers = mapOf(
                "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36",
                "Origin" to mainUrl,
                "Referer" to "$mainUrl/"
            )
            val b64Data = app.get("$coreApi/api-key", headers = headers).text
            val data = Base64.decode(b64Data, Base64.DEFAULT)

            val iv = data.sliceArray(0 until 12)
            val tag = data.sliceArray(12 until 28)
            val ciphertext = data.sliceArray(28 until data.size)

            val key = MessageDigest.getInstance("SHA-256").digest(staticPass.toByteArray(Charsets.UTF_8))
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            val spec = GCMParameterSpec(128, iv)
            val secretKey = SecretKeySpec(key, "AES")

            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
            val combined = ciphertext + tag
            val decrypted = cipher.doFinal(combined)
            String(decrypted, Charsets.UTF_8)
        } catch (_: Exception) {
            null
        }
    }

    private fun decryptLink(encLink: String, masterKey: String): String? {
        return try {
            val decodedRaw = String(Base64.decode(encLink, Base64.DEFAULT), Charsets.UTF_8)
            val parts = decodedRaw.split(":")
            val iv = Base64.decode(parts[0], Base64.DEFAULT)
            val ciphertext = Base64.decode(parts[1], Base64.DEFAULT)

            val keyBytes = masterKey.toByteArray(Charsets.UTF_8)
            val paddedKey = ByteArray(32) { i -> if (i < keyBytes.size) keyBytes[i] else 0 }

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val secretKey = SecretKeySpec(paddedKey, "AES")
            val ivSpec = IvParameterSpec(iv)

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
            val decrypted = cipher.doFinal(ciphertext)
            String(decrypted, Charsets.UTF_8)
        } catch (_: Exception) {
            null
        }
    }
}
