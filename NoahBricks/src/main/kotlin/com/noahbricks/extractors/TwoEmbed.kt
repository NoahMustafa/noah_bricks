package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.jsoup.Jsoup

open class TwoEmbed : ExtractorApi() {
    override val name = "2Embed"
    override val mainUrl = "https://www.2embed.cc"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val html = app.get(embedUrl, referer = referer ?: mainUrl).text
        val doc = Jsoup.parse(html)
        val iframeSrc = doc.selectFirst("iframe")?.attr("data-src") ?: return

        val id = iframeSrc.substringAfter("id=").substringBefore("&")
        val streamWishUrl = "https://uqloads.xyz/e/$id"

        val streamHtml = app.get(streamWishUrl, referer = iframeSrc).text
        val streamUrl = Regex("""file:\s*["']([^"']+\.m3u8[^"']*)["']""").find(streamHtml)?.groupValues?.get(1)

        if (streamUrl != null) {
            callback.invoke(
                newExtractorLink(
                    source = name,
                    name = name,
                    url = streamUrl,
                    type = ExtractorLinkType.M3U8
                ) {
                    this.referer = streamWishUrl
                    this.quality = Qualities.Unknown.value
                }
            )
        }
    }
}
