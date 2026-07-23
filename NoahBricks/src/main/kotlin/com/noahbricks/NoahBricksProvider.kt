package com.noahbricks

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.AppUtils.parseJson
import com.lagradost.cloudstream3.utils.AppUtils.toJson
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.noahbricks.extractors.*
import org.json.JSONObject

class NoahBricksProvider : MainAPI() {
    override var name = "NoahBricks"
    override var mainUrl = "https://api.themoviedb.org/3"
    override val hasMainPage = true
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    private val tmdbApiKey = "e6333b32409e02a4a6eba6fb7ff866bb"

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val movies = fetchTmdb("/trending/movie/week", page)
        val shows = fetchTmdb("/trending/tv/week", page)

        return newHomePageResponse(
            listOf(
                HomePageList("Trending Movies", movies),
                HomePageList("Trending TV Shows", shows)
            ),
            false
        )
    }

    private suspend fun fetchTmdb(path: String, page: Int): List<SearchResponse> {
        val res = app.get("$mainUrl$path?api_key=$tmdbApiKey&page=$page").text
        val json = JSONObject(res)
        val results = json.optJSONArray("results") ?: return emptyList()

        val list = mutableListOf<SearchResponse>()
        for (i in 0 until results.length()) {
            val item = results.getJSONObject(i)
            val isTv = item.has("name")
            val title = item.optString("title", item.optString("name"))
            val id = item.optInt("id")
            val poster = item.optString("poster_path")

            list.add(
                if (isTv) {
                    newTvSeriesSearchResponse(title, "tmdb_tv_$id", TvType.TvSeries) {
                        this.posterUrl = "https://image.tmdb.org/t/p/w500$poster"
                    }
                } else {
                    newMovieSearchResponse(title, "tmdb_movie_$id", TvType.Movie) {
                        this.posterUrl = "https://image.tmdb.org/t/p/w500$poster"
                    }
                }
            )
        }
        return list
    }

    override suspend fun search(query: String): List<SearchResponse> {
        val res = app.get("$mainUrl/search/multi?api_key=$tmdbApiKey&query=$query").text
        val json = JSONObject(res)
        val results = json.optJSONArray("results") ?: return emptyList()

        val list = mutableListOf<SearchResponse>()
        for (i in 0 until results.length()) {
            val item = results.getJSONObject(i)
            val type = item.optString("media_type")
            if (type != "movie" && type != "tv") continue

            val isTv = type == "tv"
            val title = item.optString("title", item.optString("name"))
            val id = item.optInt("id")
            val poster = item.optString("poster_path")

            list.add(
                if (isTv) {
                    newTvSeriesSearchResponse(title, "tmdb_tv_$id", TvType.TvSeries) {
                        this.posterUrl = "https://image.tmdb.org/t/p/w500$poster"
                    }
                } else {
                    newMovieSearchResponse(title, "tmdb_movie_$id", TvType.Movie) {
                        this.posterUrl = "https://image.tmdb.org/t/p/w500$poster"
                    }
                }
            )
        }
        return list
    }

    override suspend fun load(url: String): LoadResponse {
        val isTv = url.startsWith("tmdb_tv_")
        val id = url.substringAfterLast("_").toInt()

        val typePath = if (isTv) "tv" else "movie"
        val res = app.get("$mainUrl/$typePath/$id?api_key=$tmdbApiKey").text
        val item = JSONObject(res)

        val title = item.optString("title", item.optString("name"))
        val poster = item.optString("poster_path")
        val plot = item.optString("overview")

        if (isTv) {
            val episodes = mutableListOf<Episode>()
            val seasons = item.optJSONArray("seasons")
            if (seasons != null) {
                for (i in 0 until seasons.length()) {
                    val s = seasons.getJSONObject(i)
                    val seasonNum = s.optInt("season_number")
                    if (seasonNum == 0) continue

                    val seasonRes = app.get("$mainUrl/tv/$id/season/$seasonNum?api_key=$tmdbApiKey").text
                    val seasonJson = JSONObject(seasonRes)
                    val eps = seasonJson.optJSONArray("episodes") ?: continue

                    for (j in 0 until eps.length()) {
                        val ep = eps.getJSONObject(j)
                        val epNum = ep.optInt("episode_number")
                        val epName = ep.optString("name")
                        episodes.add(
                            newEpisode("tmdb_play_${id}_${seasonNum}_$epNum") {
                                this.name = epName
                                this.season = seasonNum
                                this.episode = epNum
                            }
                        )
                    }
                }
            }
            return newTvSeriesLoadResponse(title, url, TvType.TvSeries, episodes) {
                this.posterUrl = "https://image.tmdb.org/t/p/w500$poster"
                this.plot = plot
            }
        } else {
            return newMovieLoadResponse(title, url, TvType.Movie, "tmdb_play_${id}_0_0") {
                this.posterUrl = "https://image.tmdb.org/t/p/w500$poster"
                this.plot = plot
            }
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        // Format: tmdb_play_ID_SEASON_EPISODE
        val parts = data.split("_")
        val tmdb = parts[2]
        val s = parts[3].toInt()
        val e = parts[4].toInt()
        val isTv = s != 0 && e != 0

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
            VidKing() to "tmdbId=$tmdb"
        )

        extractors.forEach { (extractor, path) ->
            try {
                extractor.getUrl(path, null, subtitleCallback, callback)
            } catch (ex: Exception) {}
        }
        return true
    }
}
