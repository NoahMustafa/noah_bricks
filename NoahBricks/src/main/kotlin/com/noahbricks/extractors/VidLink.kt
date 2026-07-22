package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject

open class VidLink : ExtractorApi() {
    override val name = "VidLink"
    override val mainUrl = "https://vidlink.pro"
    override val requiresReferer = true

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val embedUrl = if (url.startsWith("http")) url else "$mainUrl/$url"
        val html = app.get(embedUrl, referer = referer ?: mainUrl).text
        val apiPath = Regex("""/api/b/[a-zA-Z0-9_-]+""").find(html)?.value

        if (apiPath != null) {
            val jsonText = app.get("$mainUrl$apiPath", headers = mapOf("Referer" to embedUrl)).text
            val json = JSONObject(jsonText)
            if (json.has("stream")) {
                val stream = json.getJSONObject("stream")
                val playlist = stream.optString("playlist")
                if (playlist.isNotBlank()) {
                    val captions = stream.optJSONArray("captions")
                    if (captions != null) {
                        for (i in 0 until captions.length()) {
                            val cap = captions.getJSONObject(i)
                            val subUrl = cap.optString("id")
                            val lang = cap.optString("language", "English")
                            if (subUrl.isNotBlank()) {
                                subtitleCallback.invoke(SubtitleFile(lang, subUrl))
                            }
                        }
                    }

                    callback.invoke(
                        newExtractorLink(
                            source = name,
                            name = name,
                            url = playlist,
                            type = ExtractorLinkType.M3U8
                        ) {
                            this.referer = embedUrl
                            this.quality = Qualities.Unknown.value
                        }
                    )
                }
            }
        }
    }
}
