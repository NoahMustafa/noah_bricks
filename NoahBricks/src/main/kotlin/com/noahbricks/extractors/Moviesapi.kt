package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.jsoup.Jsoup

open class Moviesapi : ExtractorApi() {
    override val name = "Moviesapi"
    override val mainUrl = "https://moviesapi.club"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val html = app.get(embedUrl, referer = referer ?: "https://pressplay.top/").text
        val doc = Jsoup.parse(html)
        val iframeSrc = doc.selectFirst("iframe")?.attr("src") ?: return

        if (iframeSrc.isNotBlank()) {
            val streamHtml = app.get(iframeSrc, referer = embedUrl).text
            val streamUrl = Regex("""https?://[^\s"'<>]+\.mp4[^\s"'<>]*""").find(streamHtml)?.value
                ?: Regex("""https?://[^\s"'<>]+\.m3u8[^\s"'<>]*""").find(streamHtml)?.value

            if (streamUrl != null) {
                val type = if (streamUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
                callback.invoke(
                    newExtractorLink(
                        source = name,
                        name = name,
                        url = streamUrl,
                        type = type
                    ) {
                        this.referer = iframeSrc
                        this.quality = Qualities.Unknown.value
                    }
                )
            }
        }
    }
}
