package com.noahbricks.extractors

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.json.JSONObject

open class Rivestream : ExtractorApi() {
    override val name = "Rivestream"
    override val mainUrl = "https://www.rivestream.app"
    override val requiresReferer = true

    private val lookup = listOf(
        "4Z7lUo","gwIVSMD","PLmz2elE2v","Z4OFV0","SZ6RZq6Zc","zhJEFYxrz8","FOm7b0","axHS3q4KDq","o9zuXQ","4Aebt",
        "wgjjWwKKx","rY4VIxqSN","kfjbnSo","2DyrFA1M","YUixDM9B","JQvgEj0","mcuFx6JIek","eoTKe26gL","qaI9EVO1rB","0xl33btZL",
        "1fszuAU","a7jnHzst6P","wQuJkX","cBNhTJlEOf","KNcFWhDvgT","XipDGjST","PeGZJlbHoyt","2AYnMZ4kd","HIpJh","KH0C3iztrG",
        "W81hjts92","rJhAT","NON7LKoMQ","NMdY3nsKzI","t4En5v","Qq5cOQ9H","Y9nwrp","VX3FYVfsf","cE0SJG","x1vj1",
        "HegbLe","zJ3nmt4OA","gt7rxW7DQ","clIEH9b","jyJ9g","B5jXKiCSx","cOzZBZTV","FTXGy","Dfh1q1","ny9jqZ2POI",
        "X3NnMn","MBtoyD","qz4Ilys7wB","68lbOMYr","jYUJnmxp","1fv5fmona","PlDvvXD7GA","ZarKfHCaPR","owORnX","AP1YU",
        "dVdkx","qgiK0E","cx9wQ","59Ga","7UjkKrp","Yvhrj","wYXez5Dg3","pGUGMU","MwMAu","rFRD5wlM"
    )

    override suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val tmdbId = url.substringAfter("id=").substringBefore("&").ifEmpty { url.substringAfterLast("/") }
        val isShow = url.contains("season=") || url.contains("/tv/")
        val reqId = if (isShow) "tvVideoProvider" else "movieVideoProvider"

        val providers = listOf("flowcast", "primevids", "asiacloud", "humpy", "shadow")

        for (svc in providers) {
            try {
                val secretKey = computeSecretKey(tmdbId)
                val apiUrl = "$mainUrl/api/backendfetch?requestID=$reqId&id=$tmdbId&secretKey=$secretKey&service=$svc"
                val resText = app.get(apiUrl, headers = mapOf("Referer" to "$mainUrl/", "Accept" to "application/json")).text
                val json = JSONObject(resText)
                val data = json.optJSONObject("data") ?: continue
                val sources = data.optJSONArray("sources") ?: continue

                for (i in 0 until sources.length()) {
                    val src = sources.getJSONObject(i)
                    val srcUrl = src.optString("url")
                    val qualityStr = src.optString("quality", "1080")
                    val format = src.optString("format", "mp4")

                    if (srcUrl.isNotBlank()) {
                        val type = if (format == "hls" || srcUrl.contains(".m3u8")) ExtractorLinkType.M3U8 else ExtractorLinkType.VIDEO
                        val qualityInt = qualityStr.replace("p", "").toIntOrNull() ?: Qualities.Unknown.value

                        callback.invoke(
                            newExtractorLink(
                                source = "$name - $svc",
                                name = "$name - $svc",
                                url = srcUrl,
                                type = type
                            ) {
                                this.referer = "$mainUrl/"
                                this.quality = qualityInt
                            }
                        )
                    }
                }

                val captions = data.optJSONArray("captions")
                if (captions != null) {
                    for (i in 0 until captions.length()) {
                        val cap = captions.getJSONObject(i)
                        val capUrl = cap.optString("file", cap.optString("url"))
                        val label = cap.optString("label", "English")
                        if (capUrl.isNotBlank()) {
                            subtitleCallback.invoke(SubtitleFile(label, capUrl))
                        }
                    }
                }
                if (sources.length() > 0) break
            } catch (_: Exception) {}
        }
    }

    private fun computeSecretKey(tmdbId: String): String {
        val e = tmdbId.toLongOrNull() ?: 0L
        val n = tmdbId
        val r = lookup.getOrNull((e % lookup.size).toInt()) ?: android.util.Base64.encodeToString(n.toByteArray(), android.util.Base64.NO_WRAP)
        val i = ((e % n.length) / 2).toInt()
        val combined = n.substring(0, i) + r + n.substring(i)
        val hashed = outerHash(innerHash(combined))
        return android.util.Base64.encodeToString(hashed.toByteArray(), android.util.Base64.NO_WRAP)
    }

    private fun innerHash(s: String): String {
        var t = 0L
        for (n in s.indices) {
            val r = s[n].code.toLong()
            val step1 = (r + (t shl 6) + (t shl 16) - t) and 0xFFFFFFFFL
            val i = ((step1 shl (n % 5)) or (step1 ushr (32 - (n % 5)))) and 0xFFFFFFFFL
            t = (t xor i xor (((r shl (n % 7)) or (r ushr (8 - (n % 7)))) and 0xFFFFFFFFL)) and 0xFFFFFFFFL
            t = (t + ((t ushr 11) xor (t shl 3))) and 0xFFFFFFFFL
        }
        t = t xor (t ushr 15)
        t = (94842L * (65535L and t) + ((49842L * (t ushr 16) and 65535L) shl 16)) and 0xFFFFFFFFL
        t = t xor (t ushr 13)
        t = (40503L * (65535L and t) + ((40503L * (t ushr 16) and 65535L) shl 16)) and 0xFFFFFFFFL
        t = t xor (t ushr 16)
        return t.toString(16).padStart(8, '0')
    }

    private fun outerHash(str: String): String {
        var t = (3735928559L xor str.length.toLong()) and 0xFFFFFFFFL
        for (n in str.indices) {
            var r = str[n].code.toLong()
            r = r xor (255L and ((0L * n + 89L) xor (r shl (n % 5))))
            t = (((t shl 7) or (t ushr 25)) and 0xFFFFFFFFL) xor r
            t = (((t * (65535L and t)) and 0xFFFFFFFFL) + ((3L * (t ushr 16)) shl 16 and 0xFFFFFFFFL)) and 0xFFFFFFFFL
            t = t xor (t ushr 11)
        }
        t = t xor (t ushr 15)
        t = ((t * (65535L and t)) + (3L * (t ushr 16) shl 16)) and 0xFFFFFFFFL
        t = t xor (t ushr 13)
        t = ((t * (65535L and t)) + (3L * (t ushr 16) shl 16)) and 0xFFFFFFFFL
        t = t xor (t ushr 16)
        t = ((t * (65535L and t)) + (3L * (t ushr 16) shl 16)) and 0xFFFFFFFFL
        t = t xor (t ushr 15)
        return t.toString(16).padStart(8, '0')
    }
}
