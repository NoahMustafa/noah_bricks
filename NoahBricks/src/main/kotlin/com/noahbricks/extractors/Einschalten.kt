package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject

open class Einschalten : ExtractorApi() {
    override val name = "Einschalten"
    override val mainUrl = "https://einschalten.in"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val jsonText = app.get(embedUrl, headers = mapOf("Content-Type" to "application/json")).text
        val streamUrl = JSONObject(jsonText).optString("streamUrl", "").trim()

        if (streamUrl.isNotBlank()) {
            val type = if (streamUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
            callback.invoke(
                newExtractorLink(
                    source = name,
                    name = name,
                    url = streamUrl,
                    type = type
                ) {
                    this.referer = embedUrl
                    this.quality = Qualities.Unknown.value
                }
            )
        }
    }
}
