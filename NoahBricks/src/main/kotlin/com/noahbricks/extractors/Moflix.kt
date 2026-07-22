package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject

open class Moflix : ExtractorApi() {
    override val name = "Moflix"
    override val mainUrl = "https://moflix-stream.xyz"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        if (embedUrl.contains(".m3u8") || embedUrl.contains("/playback")) {
            val sourceUrl = if (embedUrl.contains("/playback")) {
                try {
                    val res = app.get(embedUrl, referer = referer ?: mainUrl).text
                    JSONObject(res).optString("src", embedUrl)
                } catch (_: Exception) {
                    embedUrl
                }
            } else embedUrl

            callback.invoke(
                newExtractorLink(
                    source = name,
                    name = name,
                    url = sourceUrl,
                    type = ExtractorLinkType.M3U8
                ) {
                    this.referer = embedUrl
                    this.quality = Qualities.Unknown.value
                }
            )
        }
    }
}
