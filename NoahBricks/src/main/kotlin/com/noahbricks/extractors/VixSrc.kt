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

open class VixSrc : ExtractorApi() {
    override val name = "VixSrc"
    override val mainUrl = "https://vixsrc.to"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        var apiPath = url.substringAfter(mainUrl).trimStart('/')
        if (!apiPath.startsWith("api/")) {
            apiPath = "api/$apiPath"
        }

        val apiResText = app.get("$mainUrl/$apiPath", headers = mapOf("X-Requested-With" to "XMLHttpRequest")).text
        val embedPath = JSONObject(apiResText).optString("src", "").trimStart('/')
        if (embedPath.isBlank()) return

        val embedHtml = app.get("$mainUrl/$embedPath", headers = mapOf("Referer" to mainUrl)).text
        val doc = Jsoup.parse(embedHtml)
        val scriptText = doc.selectFirst("script")?.data() ?: ""

        val videoId = scriptText.substringAfter("id: '", "").substringBefore("',").trim()
        val token = scriptText.substringAfter("'token': '", "").substringBefore("',").trim()
        val expires = scriptText.substringAfter("'expires': '", "").substringBefore("',").trim()

        if (videoId.isBlank() || token.isBlank()) return

        val playlistUrl = "$mainUrl/playlist/$videoId?token=$token&expires=$expires"

        callback.invoke(
            newExtractorLink(
                source = name,
                name = name,
                url = playlistUrl,
                type = ExtractorLinkType.M3U8
            ) {
                this.referer = "$mainUrl/$embedPath"
                this.quality = Qualities.Unknown.value
            }
        )
    }
}
