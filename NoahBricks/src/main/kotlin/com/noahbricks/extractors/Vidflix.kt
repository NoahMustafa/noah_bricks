package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject

open class Vidflix : ExtractorApi() {
    override val name = "Vidflix"
    override val mainUrl = "https://vidflix.club"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val jsonText = app.get(embedUrl, referer = referer ?: embedUrl.replace("/api/", "/")).text
        val json = JSONObject(jsonText)
        val videoUrl = json.optString("video_url")

        if (videoUrl.isNotBlank()) {
            val subs = json.optJSONArray("subtitles")
            if (subs != null) {
                for (i in 0 until subs.length()) {
                    val sub = subs.getJSONObject(i)
                    val label = sub.optString("label", "English")
                    val subUrl = sub.optString("url")
                    if (subUrl.isNotBlank()) {
                        subtitleCallback.invoke(SubtitleFile(label, subUrl))
                    }
                }
            }

            val type = if (videoUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
            callback.invoke(
                newExtractorLink(
                    source = name,
                    name = name,
                    url = videoUrl,
                    type = type
                ) {
                    this.referer = embedUrl
                    this.quality = Qualities.Unknown.value
                }
            )
        }
    }
}
