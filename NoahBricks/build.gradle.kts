import com.lagradost.cloudstream3.gradle.CloudstreamExtension

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(plugin = "com.lagradost.cloudstream3.gradle")

configure<CloudstreamExtension> {
    description = "NoahBricks Extractors"
    status = 1
    version = 3
    tvTypes = listOf("Movie", "TvSeries")
    setRepo("https://github.com/NoahMustafa/noah_bricks")
    buildBranch = "main"
}

android {
    namespace = "com.noahbricks"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf("-Xskip-metadata-version-check")
    }
}

dependencies {
    compileOnly("com.github.recloudstream:cloudstream:-SNAPSHOT")
    compileOnly("com.github.Blatzar:NiceHttp:0.4.18")
    compileOnly("com.squareup.okhttp3:okhttp:4.12.0")
    compileOnly("org.jsoup:jsoup:1.17.2")
    compileOnly("org.json:json:20231013")
}
