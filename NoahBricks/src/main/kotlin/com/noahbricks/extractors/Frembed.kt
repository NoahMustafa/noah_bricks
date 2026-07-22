package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject

open class Frembed : ExtractorApi() {
    override val name = "Frembed"
    override val mainUrl = "https://frembed.click"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val jsonText = app.get(embedUrl, headers = mapOf("Content-Type" to "application/json")).text
        val json = JSONObject(jsonText)

        val linkKeys = listOf(
            "link1", "link2", "link3", "link4", "link5", "link6", "link7",
            "link1vostfr", "link2vostfr", "link3vostfr", "link4vostfr", "link5vostfr", "link6vostfr", "link7vostfr",
            "link1vo", "link2vo", "link3vo", "link4vo", "link5vo", "link6vo", "link7vo"
        )

        for (k in linkKeys) {
            val streamPath = json.optString(k, "")
            if (streamPath.isNotBlank()) {
                val fullUrl = if (streamPath.startsWith("/")) mainUrl + streamPath else streamPath
                val type = if (fullUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
                callback.invoke(
                    newExtractorLink(
                        source = "$name - $k",
                        name = "$name - $k",
                        url = fullUrl,
                        type = type
                    ) {
                        this.referer = embedUrl
                        this.quality = Qualities.Unknown.value
                    }
                )
            }
        }
    }
}
