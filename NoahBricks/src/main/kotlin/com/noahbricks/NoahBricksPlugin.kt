package com.noahbricks

import com.lagradost.cloudstream3.plugins.BasePlugin
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.noahbricks.extractors.*

@CloudstreamPlugin
class NoahBricksPlugin : BasePlugin() {
    override fun load() {
        registerExtractorAPI(VidLink())
        registerExtractorAPI(VidsrcNet())
        registerExtractorAPI(VidsrcTo())
        registerExtractorAPI(VidsrcRu())
        registerExtractorAPI(Moflix())
        registerExtractorAPI(Moviesapi())
        registerExtractorAPI(TwoEmbed())
        registerExtractorAPI(Vidzee())
        registerExtractorAPI(VixSrc())
        registerExtractorAPI(Einschalten())
        registerExtractorAPI(Frembed())
        registerExtractorAPI(Vidflix())
        registerExtractorAPI(Vidrock())
        registerExtractorAPI(Videasy())
        registerExtractorAPI(PrimeSrc())
        registerExtractorAPI(AfterDark())
        registerExtractorAPI(Rivestream())
        registerExtractorAPI(VidKing())
        registerExtractorAPI(HDRezka())
    }
}
