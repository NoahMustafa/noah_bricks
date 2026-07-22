package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject

open class VidKing : ExtractorApi() {
    override val name = "VidKing"
    override val mainUrl = "https://www.vidking.net"
    override val requiresReferer = true
    private val apiBase = "https://api.wingsdatabase.com"

    private val servers = listOf(
        Pair("Hydrogen", "cdn/sources-with-title"),
        Pair("Titanium", "tejo/sources-with-title"),
        Pair("Oxygen", "neon2/sources-with-title"),
        Pair("Lithium", "downloader2/sources-with-title"),
        Pair("Helium", "1movies/sources-with-title")
    )

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val tmdbId = url.substringAfter("tmdbId=").substringBefore("&").ifEmpty { url.substringAfterLast("/") }
        val headers = mapOf(
            "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
            "Referer" to "$mainUrl/",
            "Origin" to mainUrl
        )

        val seedText = app.get("$apiBase/seed?mediaId=$tmdbId", headers = headers).text
        val seed = JSONObject(seedText).optString("seed")
        if (seed.isBlank()) return

        for ((srvName, endpoint) in servers) {
            try {
                val reqUrl = "$apiBase/$endpoint?mediaType=movie&tmdbId=$tmdbId&enc=2&seed=$seed&_t=${System.currentTimeMillis()}"
                val encRes = app.get(reqUrl, headers = headers).text
                if (encRes.isBlank()) continue

                val decrypted = decryptPayload(encRes, seed, tmdbId) ?: continue
                val json = JSONObject(decrypted)
                val sources = json.optJSONArray("sources") ?: continue

                for (i in 0 until sources.length()) {
                    val src = sources.getJSONObject(i)
                    val streamUrl = src.optString("url")
                    val qualityStr = src.optString("quality", "1080p")
                    if (streamUrl.isNotBlank()) {
                        val type = if (streamUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
                        val qualityInt = qualityStr.replace("p", "").toIntOrNull() ?: Qualities.Unknown.value

                        callback.invoke(
                            newExtractorLink(
                                source = "$name ($srvName)",
                                name = "$name ($srvName)",
                                url = streamUrl,
                                type = type
                            ) {
                                this.referer = "$mainUrl/"
                                this.quality = qualityInt
                            }
                        )
                    }
                }
            } catch (_: Exception) {}
        }
    }

    private fun decryptPayload(encrypted: String, key: String, tmdbId: String): String? {
        return try {
            val padded = encrypted.replace("-", "+").replace("_", "/")
            val data = android.util.Base64.decode(padded, android.util.Base64.DEFAULT)
            if (data.size < 4) return null
            String(data.sliceArray(4 until data.size), Charsets.UTF_8)
        } catch (_: Exception) {
            null
        }
    }
}
