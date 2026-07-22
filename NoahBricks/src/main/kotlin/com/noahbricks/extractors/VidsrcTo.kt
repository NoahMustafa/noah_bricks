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
import org.jsoup.Jsoup
import java.net.URLDecoder
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

open class VidsrcTo : ExtractorApi() {
    override val name = "Vidsrc.to"
    override val mainUrl = "https://vidsrc.to"
    override val requiresReferer = true
    private val keyUrl = "https://raw.githubusercontent.com/Ciarands/vidsrc-keys/main/keys.json"

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val html = app.get(embedUrl, referer = referer ?: mainUrl).text
        val doc = Jsoup.parse(html)

        val mediaId = doc.selectFirst("ul.episodes li a")?.attr("data-id") ?: return
        val keysJsonText = app.get(keyUrl).text
        val keysObj = JSONObject(keysJsonText)
        val encryptKeys = keysObj.getJSONArray("encrypt")
        val decryptKeys = keysObj.getJSONArray("decrypt")

        val encKey = encryptKeys.getString(0)
        val decKey = decryptKeys.getString(0)

        val token = encodeToken(encKey, mediaId)
        val sourcesText = app.get("$mainUrl/ajax/embed/episode/$mediaId/sources?token=$token").text
        val sourcesJson = JSONObject(sourcesText)
        val sources = sourcesJson.optJSONArray("result") ?: return

        for (i in 0 until sources.length()) {
            val source = sources.getJSONObject(i)
            val sourceId = source.getString("id")
            val sourceTitle = source.getString("title")

            val sourceToken = encodeToken(encKey, sourceId)
            val embedResText = app.get("$mainUrl/ajax/embed/source/$sourceId?token=$sourceToken").text
            val embedResJson = JSONObject(embedResText)
            val encUrl = embedResJson.optJSONObject("result")?.optString("url") ?: continue

            val finalUrl = decryptUrl(decKey, encUrl)
            if (finalUrl.isNotBlank() && finalUrl != encUrl) {
                val type = if (finalUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
                callback.invoke(
                    newExtractorLink(
                        source = "$name - $sourceTitle",
                        name = "$name - $sourceTitle",
                        url = finalUrl,
                        type = type
                    ) {
                        this.referer = embedUrl
                        this.quality = Qualities.Unknown.value
                    }
                )
            }
        }

        try {
            val subsText = app.get("$mainUrl/ajax/embed/episode/$mediaId/subtitles").text
            val subsArray = JSONObject(subsText).optJSONArray("result")
            if (subsArray != null) {
                for (i in 0 until subsArray.length()) {
                    val sub = subsArray.getJSONObject(i)
                    val label = sub.optString("label", "English")
                    val file = sub.optString("file")
                    if (file.isNotBlank()) {
                        subtitleCallback.invoke(SubtitleFile(label, file))
                    }
                }
            }
        } catch (_: Exception) {}
    }

    private fun decryptUrl(key: String, encUrl: String): String {
        return try {
            var data = Base64.decode(encUrl.toByteArray(), Base64.URL_SAFE)
            val rc4Key = SecretKeySpec(key.toByteArray(), "RC4")
            val cipher = Cipher.getInstance("RC4")
            cipher.init(Cipher.DECRYPT_MODE, rc4Key, cipher.parameters)
            data = cipher.doFinal(data)
            URLDecoder.decode(data.toString(Charsets.UTF_8), "utf-8")
        } catch (_: Exception) {
            ""
        }
    }

    private fun encodeToken(key: String, vId: String): String {
        val decodedId = decodeData(key, vId)
        val encodedBase64 = Base64.encode(decodedId, Base64.NO_WRAP).toString(Charsets.UTF_8)
        return encodedBase64.replace("/", "_").replace("+", "-")
    }

    private fun decodeData(key: String, data: String): ByteArray {
        val keyBytes = key.toByteArray(Charsets.UTF_8)
        val s = ByteArray(256) { it.toByte() }
        var j = 0
        for (i in 0 until 256) {
            j = (j + s[i].toInt() + keyBytes[i % keyBytes.size].toInt()) and 0xff
            s[i] = s[j].also { s[j] = s[i] }
        }
        val decoded = ByteArray(data.length)
        var i = 0
        var k = 0
        for (index in decoded.indices) {
            i = (i + 1) and 0xff
            k = (k + s[i].toInt()) and 0xff
            s[i] = s[k].also { s[k] = s[i] }
            val t = (s[i].toInt() + s[k].toInt()) and 0xff
            decoded[index] = (data[index].code xor s[t].toInt()).toByte()
        }
        return decoded
    }
}
