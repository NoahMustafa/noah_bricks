package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject
import org.jsoup.Jsoup

open class HDRezka : ExtractorApi() {
    override val name = "HDrezka"
    override val mainUrl = "https://rezka.ag"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val headers = mapOf(
            "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
            "Referer" to "$mainUrl/",
            "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
        )

        val html = app.get(embedUrl, headers = headers).text
        val doc = Jsoup.parse(html)

        val rezkaId = Regex("""/(\d+)-""").find(embedUrl)?.groupValues?.get(1) ?: return
        val favs = doc.selectFirst("input#ctrl_favs")?.attr("value") ?: ""

        val translatorEl = doc.selectFirst("li[data-translator_id]")
        val translatorId = translatorEl?.attr("data-translator_id") ?: "1"

        val cdnResText = app.post(
            "$mainUrl/ajax/get_cdn_series/?t=${System.currentTimeMillis()}",
            data = mapOf(
                "id" to rezkaId,
                "translator_id" to translatorId,
                "favs" to favs,
                "action" to "get_movie"
            ),
            headers = mapOf(
                "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
                "Referer" to embedUrl,
                "X-Requested-With" to "XMLHttpRequest"
            )
        ).text

        val json = JSONObject(cdnResText)
        if (!json.optBoolean("success", false)) return

        val rawUrlData = json.optString("url", "")
        val rawSubData = json.optString("subtitle", "")

        if (rawSubData.isNotBlank()) {
            val subMatches = Regex("""\[([^\]]+)\]([^,]+)""").findAll(rawSubData)
            for (m in subMatches) {
                val lang = m.groupValues[1]
                val subUrl = m.groupValues[2].trim()
                if (subUrl.isNotBlank()) {
                    subtitleCallback.invoke(SubtitleFile(lang, subUrl))
                }
            }
        }

        if (rawUrlData.isNotBlank()) {
            val matches = Regex("""\[([^\]]+)\]([^,]+)""").findAll(rawUrlData)
            for (m in matches) {
                val label = m.groupValues[1]
                if (label.contains("Ultra") || label.contains("2K") || label.contains("4K")) continue
                val variants = m.groupValues[2].split(" or ")
                for (v in variants) {
                    val streamUrl = v.trim()
                    if (streamUrl.isNotBlank()) {
                        val type = if (streamUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
                        val qualityInt = label.replace("p", "").toIntOrNull() ?: Qualities.Unknown.value

                        callback.invoke(
                            newExtractorLink(
                                source = name,
                                name = "$name - $label",
                                url = streamUrl,
                                type = type
                            ) {
                                this.referer = "$mainUrl/"
                                this.quality = qualityInt
                            }
                        )
                    }
                }
            }
        }
    }
}
