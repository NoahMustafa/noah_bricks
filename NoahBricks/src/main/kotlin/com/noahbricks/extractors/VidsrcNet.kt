package com.noahbricks.extractors

import android.util.Base64
import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import org.jsoup.Jsoup

open class VidsrcNet : ExtractorApi() {
    override val name = "Vidsrc.net"
    override val mainUrl = "https://vidsrc-embed.ru"
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
        val iframeSrc = doc.selectFirst("iframe#player_iframe")?.attr("src")
            ?.let { if (it.startsWith("//")) "https:$it" else it } ?: return

        val iframeDocText = app.get(iframeSrc, referer = embedUrl).text
        val prorcpPath = Regex("src: '(/prorcp/.*?)'").find(iframeDocText)?.groupValues?.get(1) ?: return
        val prorcpUrl = iframeSrc.substringBefore("/rcp") + prorcpPath

        val script = app.get(prorcpUrl, referer = iframeSrc).text
        val playerId = Regex("Playerjs.*file: ([a-zA-Z0-9]*?) ,").find(script)?.groupValues?.get(1) ?: ""

        val decryptedData = if (playerId.isNotBlank()) {
            val encryptedSource = Regex("""<div id="$playerId" style="display:none;">\s*(.*?)\s*</div>""")
                .find(script)?.groupValues?.get(1) ?: return
            decrypt(playerId, encryptedSource)
        } else {
            Regex("""Playerjs.*file: "([^"]*?)" ,""").find(script)?.groupValues?.get(1)
        }

        if (decryptedData.isNullOrBlank()) return

        val streamUrl = decryptedData.split(" or ")
            .firstOrNull()
            ?.replace(Regex("\\{[a-z]\\d+\\}"), "quibblezoomfable.com") ?: return

        val subtitlesRaw = Regex("""default_subtitles\s*=\s*["']([^"']+)["']""").find(script)?.groupValues?.get(1) ?: ""
        if (subtitlesRaw.isNotBlank()) {
            val host = iframeSrc.substringBefore("/rcp").substringBefore("/embed")
            subtitlesRaw.split(",").forEach { item ->
                val language = item.substringAfter("[").substringBefore("]")
                val subPath = item.substringAfter("]")
                if (subPath.startsWith("/")) {
                    subtitleCallback.invoke(SubtitleFile(language, "$host$subPath"))
                }
            }
        }

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

    private fun decrypt(id: String, encrypted: String): String {
        return when (id) {
            "NdonQLf1Tzyx7bMG" -> NdonQLf1Tzyx7bMG(encrypted)
            "sXnL9MQIry" -> sXnL9MQIry(encrypted)
            "IhWrImMIGL" -> IhWrImMIGL(encrypted)
            "xTyBxQyGTA" -> xTyBxQyGTA(encrypted)
            "ux8qjPHC66" -> ux8qjPHC66(encrypted)
            "eSfH1IRMyL" -> eSfH1IRMyL(encrypted)
            "KJHidj7det" -> KJHidj7det(encrypted)
            "o2VSUnjnZl" -> o2VSUnjnZl(encrypted)
            "Oi3v1dAlaM" -> Oi3v1dAlaM(encrypted)
            "TsA2KGDGux" -> TsA2KGDGux(encrypted)
            "JoAHUMCLXV" -> JoAHUMCLXV(encrypted)
            else -> encrypted
        }
    }

    private fun NdonQLf1Tzyx7bMG(a: String): String {
        val b = 3
        val c = mutableListOf<String>()
        for (d in a.indices step b) {
            c.add(a.substring(d, minOf(d + b, a.length)))
        }
        return c.reversed().joinToString("")
    }

    private fun sXnL9MQIry(a: String): String {
        val b = "pWB9V)[*4I`nJpp?ozyB~dbr9yt!_n4u"
        val d = a.chunked(2).map { it.toInt(16).toChar() }.joinToString("")
        var c = ""
        for (e in d.indices) {
            c += (d[e].code xor b[e % b.length].code).toChar()
        }
        var e = ""
        for (ch in c) {
            e += (ch.code - 3).toChar()
        }
        return String(Base64.decode(e, Base64.DEFAULT))
    }

    private fun IhWrImMIGL(a: String): String {
        val b = a.reversed()
        val c = b.map { ch ->
            when {
                (ch in 'a'..'m') || (ch in 'A'..'M') -> (ch.code + 13).toChar()
                (ch in 'n'..'z') || (ch in 'N'..'Z') -> (ch.code - 13).toChar()
                else -> ch
            }
        }.joinToString("")
        val d = c.reversed()
        return String(Base64.decode(d, Base64.DEFAULT))
    }

    private fun xTyBxQyGTA(a: String): String {
        val b = a.reversed()
        val c = b.filterIndexed { index, _ -> index % 2 == 0 }
        return Base64.decode(c, Base64.DEFAULT).toString(Charsets.UTF_8)
    }

    private fun ux8qjPHC66(a: String): String {
        val b = a.reversed()
        val c = "X9a(O;FMV2-7VO5x;Ao:dN1NoFs?j,"
        val d = b.chunked(2).map { it.toInt(16).toChar() }.joinToString("")
        var e = ""
        for (i in d.indices) {
            e += (d[i].code xor c[i % c.length].code).toChar()
        }
        return e
    }

    private fun eSfH1IRMyL(a: String): String {
        val b = a.reversed()
        val c = b.map { (it.code - 1).toChar() }.joinToString("")
        val d = c.chunked(2).map { it.toInt(16).toChar() }.joinToString("")
        return d
    }

    private fun KJHidj7det(a: String): String {
        val b = a.substring(10, a.length - 16)
        val c = "3SAY~#%Y(V%>5d/Yg\"\$G[Lh1rK4a;7ok"
        val d = String(Base64.decode(b, Base64.DEFAULT))
        val e = c.repeat((d.length + c.length - 1) / c.length).take(d.length)
        var f = ""
        for (i in d.indices) {
            f += (d[i].code xor e[i].code).toChar()
        }
        return f
    }

    private fun o2VSUnjnZl(a: String): String {
        val shift = 3
        return a.map { char ->
            when (char) {
                in 'a'..'z' -> {
                    val shifted = char - shift
                    if (shifted < 'a') shifted + 26 else shifted
                }
                in 'A'..'Z' -> {
                    val shifted = char - shift
                    if (shifted < 'A') shifted + 26 else shifted
                }
                else -> char
            }
        }.joinToString("")
    }

    private fun Oi3v1dAlaM(a: String): String {
        val b = a.reversed()
        val c = b.replace("-", "+").replace("_", "/")
        val d = String(Base64.decode(c, Base64.DEFAULT))
        var e = ""
        for (ch in d) {
            e += (ch.code - 5).toChar()
        }
        return e
    }

    private fun TsA2KGDGux(a: String): String {
        val b = a.reversed()
        val c = b.replace("-", "+").replace("_", "/")
        val d = String(Base64.decode(c, Base64.DEFAULT))
        var e = ""
        for (ch in d) {
            e += (ch.code - 7).toChar()
        }
        return e
    }

    private fun JoAHUMCLXV(a: String): String {
        val b = a.reversed()
        val c = b.replace("-", "+").replace("_", "/")
        val d = String(Base64.decode(c, Base64.DEFAULT))
        var e = ""
        for (ch in d) {
            e += (ch.code - 3).toChar()
        }
        return e
    }
}
