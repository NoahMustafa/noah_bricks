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
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

open class Vidrock : ExtractorApi() {
    override val name = "Vidrock"
    override val mainUrl = "https://vidrock.net"
    override val requiresReferer = true
    private val passphrase = "x7k9mPqT2rWvY8zA5bC3nF6hJ2lK4mN9"

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val serverName = url.substringAfter("#", "").takeIf { it != url }
        val apiLink = url.substringBefore("#")

        val resText = app.get(apiLink).text
        val json = JSONObject(resText)
        val keys = json.keys()

        while (keys.hasNext()) {
            val key = keys.next()
            if (serverName.isNullOrEmpty() || key.equals(serverName, ignoreCase = true)) {
                val serverObj = json.optJSONObject(key)
                val videoUrl = serverObj?.optString("url") ?: ""
                if (videoUrl.isNotBlank()) {
                    val type = if (videoUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
                    callback.invoke(
                        newExtractorLink(
                            source = "$name - $key",
                            name = "$name - $key",
                            url = videoUrl,
                            type = type
                        ) {
                            this.referer = mainUrl
                            this.quality = Qualities.Unknown.value
                        }
                    )
                }
            }
        }
    }
}
