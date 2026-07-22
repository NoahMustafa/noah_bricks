package com.noahbricks

import com.lagradost.cloudstream3.ProviderType
import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.TvType
import com.lagradost.cloudstream3.metaproviders.TmdbLink
import com.lagradost.cloudstream3.metaproviders.TmdbProvider
import com.lagradost.cloudstream3.utils.AppUtils.parseJson
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.noahbricks.extractors.*

class NoahBricksProvider : TmdbProvider() {
    override var name = "NoahBricks"
    override var mainUrl = "https://www.themoviedb.org"
    override val hasMainPage = true
    override val useMetaLoadResponse = true

    // Setting this to DirectProvider allows it to show up on the home screen provider list!
    override val providerType = ProviderType.DirectProvider
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val link = try {
            parseJson<TmdbLink>(data)
        } catch (e: Exception) {
            return false
        }

        val tmdb = link.tmdbID ?: return false
        val s = link.season
        val e = link.episode
        val isTv = s != null && e != null

        val extractors = listOf(
            VidsrcNet() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            VidsrcTo() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            VidsrcRu() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            Moviesapi() to if (isTv) "tv/$tmdb/$s/$e" else "movie/$tmdb",
            VidLink() to if (isTv) "tv/$tmdb/$s/$e" else "movie/$tmdb",
            Moflix() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            TwoEmbed() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            Vidzee() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            VixSrc() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            Einschalten() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            Frembed() to if (isTv) "api/series/$tmdb/$s/$e" else "api/film/$tmdb",
            Vidflix() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            Vidrock() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            Videasy() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            PrimeSrc() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            AfterDark() to if (isTv) "embed/tv/$tmdb/$s/$e" else "embed/movie/$tmdb",
            Rivestream() to "id=$tmdb" + if(isTv) "&season=$s&episode=$e" else "",
            VidKing() to "tmdbId=$tmdb",
        )

        extractors.forEach { (extractor, path) ->
            try {
                extractor.getUrl(path, null, subtitleCallback, callback)
            } catch (ex: Exception) {
                // Ignore failure
            }
        }
        return true
    }
}
