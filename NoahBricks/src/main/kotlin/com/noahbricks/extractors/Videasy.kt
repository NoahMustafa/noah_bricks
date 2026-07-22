package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject

open class Videasy : ExtractorApi() {
    override val name = "Videasy"
    override val mainUrl = "https://api.videasy.net"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val encData = app.get(url, headers = mapOf("User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")).text
        val tmdbId = url.split("tmdbId=").getOrNull(1)?.split("&")?.getOrNull(0) ?: ""

        val json = JSONObject()
        json.put("text", encData)
        json.put("id", tmdbId)

        val decBody = app.post("https://enc-dec.app/api/dec-videasy", json = mapOf("text" to encData, "id" to tmdbId)).text
        val decJson = JSONObject(decBody)
        val resultStr = decJson.optString("result")
        if (resultStr.isBlank()) return

        val resultJson = JSONObject(resultStr)
        val tracks = resultJson.optJSONArray("subtitles")
        if (tracks != null) {
            for (i in 0 until tracks.length()) {
                val track = tracks.getJSONObject(i)
                val lang = track.optString("lang", "Unknown")
                val subUrl = track.optString("url")
                if (subUrl.isNotBlank()) {
                    subtitleCallback.invoke(SubtitleFile(lang, subUrl))
                }
            }
        }

        val sources = resultJson.optJSONArray("sources")
        if (sources != null && sources.length() > 0) {
            val sourceObj = sources.getJSONObject(0)
            val streamUrl = sourceObj.optString("url")
            if (streamUrl.isNotBlank()) {
                val type = if (streamUrl.contains(".mp4")) ExtractorLinkType.VIDEO else ExtractorLinkType.M3U8
                callback.invoke(
                    newExtractorLink(
                        source = name,
                        name = name,
                        url = streamUrl,
                        type = type
                    ) {
                        this.referer = "https://player.videasy.net/"
                        this.quality = Qualities.Unknown.value
                    }
                )
            }
        }
    }
}
