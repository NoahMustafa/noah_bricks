import crypto from 'crypto';

const TMDB_ID = "278";
const MOVIE_TITLE = "The Shawshank Redemption";
const MOVIE_YEAR = "1994";
const IMDB_ID = "tt0111161";

const HEADERS = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36',
    'Accept-Language': 'en-US,en;q=0.9',
};

async function testVidLink() {
    try {
        const embedUrl = `https://vidlink.pro/movie/${TMDB_ID}`;
        const res = await fetch(embedUrl, { headers: HEADERS });
        const html = await res.text();
        const apiPath = html.match(/\/api\/b\/[a-zA-Z0-9_-]+/)?.[0];
        if (!apiPath) return { success: false, reason: "No /api/b/ path found in HTML" };

        const apiRes = await fetch(`https://vidlink.pro${apiPath}`, { headers: { ...HEADERS, Referer: embedUrl } });
        const json = await apiRes.json();
        if (json.stream?.playlist) {
            return { success: true, url: json.stream.playlist };
        }
        return { success: false, reason: "No stream playlist in JSON" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testVidsrcNet() {
    try {
        const embedUrl = `https://vidsrc-embed.ru/embed/movie?tmdb=${TMDB_ID}`;
        const res = await fetch(embedUrl, { headers: HEADERS });
        const html = await res.text();
        const iframeSrc = html.match(/<iframe[^>]+id=["']player_iframe["'][^>]+src=["']([^"']+)/i)?.[1]
            || html.match(/src: '(\/\/vidsrc-embed\.ru\/rcp\/[^']+)'/)?.[1];
        if (!iframeSrc) return { success: false, reason: "No player_iframe found" };

        const fullIframe = iframeSrc.startsWith("//") ? `https:${iframeSrc}` : iframeSrc;
        const iframeRes = await fetch(fullIframe, { headers: { ...HEADERS, Referer: embedUrl } });
        const iframeHtml = await iframeRes.text();
        const prorcpPath = iframeHtml.match(/src: '(\/prorcp\/[^']+)'/)?.[1];
        if (!prorcpPath) return { success: false, reason: "No prorcp path found" };

        return { success: true, url: `${fullIframe.split('/rcp')[0]}${prorcpPath}` };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testVidsrcTo() {
    try {
        const keyRes = await fetch("https://raw.githubusercontent.com/Ciarands/vidsrc-keys/main/keys.json");
        const keys = await keyRes.json();
        const embedUrl = `https://vidsrc.to/embed/movie/${TMDB_ID}`;
        const res = await fetch(embedUrl, { headers: HEADERS });
        const html = await res.text();
        const mediaId = html.match(/data-id=["']([^"']+)["']/)?.[1];
        if (!mediaId) return { success: false, reason: "No mediaId data-id found" };

        return { success: true, info: `Found mediaId: ${mediaId}` };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testMoflix() {
    try {
        const id = Buffer.from(`tmdb|movie|${TMDB_ID}`).toString('base64');
        const url = `https://moflix-stream.xyz/api/v1/titles/${id}?loader=titlePage`;
        const res = await fetch(url, { headers: { ...HEADERS, Referer: "https://moflix-stream.xyz" } });
        if (!res.ok) return { success: false, reason: `HTTP ${res.status}` };
        const json = await res.json();
        const videos = json.videos || json.title?.videos || [];
        if (videos.length > 0) {
            return { success: true, count: videos.length, firstSrc: videos[0].src || videos[0].playback_resolve_url };
        }
        return { success: false, reason: "No videos array returned" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testMoviesapi() {
    try {
        const url = `https://moviesapi.club/movie/${TMDB_ID}`;
        const res = await fetch(url, { headers: { ...HEADERS, Referer: "https://pressplay.top/" } });
        const html = await res.text();
        const iframeSrc = html.match(/<iframe[^>]+src=["']([^"']+)/i)?.[1];
        if (iframeSrc) return { success: true, iframeSrc };
        return { success: false, reason: "No iframe found" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testTwoEmbed() {
    try {
        const url = `https://www.2embed.cc/embed/${TMDB_ID}`;
        const res = await fetch(url, { headers: HEADERS });
        const html = await res.text();
        const iframeSrc = html.match(/<iframe[^>]+data-src=["']([^"']+)/i)?.[1];
        if (iframeSrc) {
            const id = iframeSrc.split("id=")[1]?.split("&")[0];
            return { success: true, streamWishUrl: `https://uqloads.xyz/e/${id}` };
        }
        return { success: false, reason: "No iframe data-src found" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testVidzee() {
    try {
        const res = await fetch("https://core.vidzee.wtf/api-key", {
            headers: { ...HEADERS, Origin: "https://player.vidzee.wtf", Referer: "https://player.vidzee.wtf/" }
        });
        if (res.ok) {
            const serverUrl = `https://player.vidzee.wtf/api/server?id=${TMDB_ID}&sr=0`;
            const serverRes = await fetch(serverUrl, { headers: { ...HEADERS, Origin: "https://player.vidzee.wtf", Referer: "https://player.vidzee.wtf/" } });
            if (serverRes.ok) {
                return { success: true, status: "API Key + Server OK" };
            }
        }
        return { success: false, reason: `HTTP ${res.status}` };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testVixSrc() {
    try {
        const apiUrl = `https://vixsrc.to/api/movie/${TMDB_ID}?lang=en`;
        const res = await fetch(apiUrl, { headers: { ...HEADERS, 'X-Requested-With': 'XMLHttpRequest' } });
        if (!res.ok) return { success: false, reason: `HTTP ${res.status}` };
        const json = await res.json();
        if (json.src) return { success: true, src: json.src };
        return { success: false, reason: "No src in API response" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testEinschalten() {
    try {
        const url = `https://einschalten.in/api/movies/${TMDB_ID}/watch`;
        const res = await fetch(url, { headers: HEADERS });
        if (!res.ok) return { success: false, reason: `HTTP ${res.status}` };
        const json = await res.json();
        if (json.streamUrl) return { success: true, streamUrl: json.streamUrl };
        return { success: false, reason: "No streamUrl in JSON" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testFrembed() {
    try {
        const url = `https://frembed.click/api/films?id=${TMDB_ID}&idType=tmdb`;
        const res = await fetch(url, { headers: HEADERS });
        if (!res.ok) return { success: false, reason: `HTTP ${res.status}` };
        const json = await res.json();
        const keys = Object.keys(json).filter(k => json[k]);
        if (keys.length > 0) return { success: true, activeLinks: keys.length, links: json };
        return { success: false, reason: "No active links found" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testVidflix() {
    try {
        const url = `https://vidflix.club/api/movie/${TMDB_ID}`;
        const res = await fetch(url, { headers: { ...HEADERS, Referer: `https://vidflix.club/movie/${TMDB_ID}` } });
        if (!res.ok) return { success: false, reason: `HTTP ${res.status}` };
        const json = await res.json();
        if (json.video_url) return { success: true, videoUrl: json.video_url };
        return { success: false, reason: "No video_url in JSON" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testVidrock() {
    try {
        const passphrase = "x7k9mPqT2rWvY8zA5bC3nF6hJ2lK4mN9";
        const key = Buffer.from(passphrase, 'utf-8');
        const iv = key.subarray(0, 16);
        const cipher = crypto.createCipheriv('aes-256-cbc', key, iv);
        let encrypted = cipher.update(TMDB_ID, 'utf-8', 'base64');
        encrypted += cipher.final('base64');
        const encoded = encrypted.replace(/=/g, '').replace(/\+/g, '-').replace(/\//g, '_');

        const apiUrl = `https://vidrock.net/api/movie/${encoded}`;
        const res = await fetch(apiUrl, { headers: HEADERS });
        if (!res.ok) return { success: false, reason: `HTTP ${res.status}` };
        const json = await res.json();
        const servers = Object.keys(json);
        if (servers.length > 0) return { success: true, servers };
        return { success: false, reason: "No servers returned" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testVideasy() {
    try {
        const url = `https://api.videasy.net/mb-flix/sources-with-title?title=${encodeURIComponent(MOVIE_TITLE)}&mediaType=movie&year=${MOVIE_YEAR}&tmdbId=${TMDB_ID}&imdbId=${IMDB_ID}`;
        const res = await fetch(url, { headers: HEADERS });
        if (!res.ok) return { success: false, reason: `HTTP ${res.status}` };
        const encData = await res.text();
        if (!encData) return { success: false, reason: "Empty response from videasy" };

        const decRes = await fetch("https://enc-dec.app/api/dec-videasy", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ text: encData, id: TMDB_ID })
        });
        if (!decRes.ok) return { success: false, reason: `Decryption API HTTP ${decRes.status}` };
        const decJson = await decRes.json();
        if (decJson.result) {
            const parsed = JSON.parse(decJson.result);
            if (parsed.sources?.length > 0) {
                return { success: true, sourceUrl: parsed.sources[0].url };
            }
        }
        return { success: false, reason: "No sources after decryption" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function testPrimeSrc() {
    try {
        const apiUrl = `https://primesrc.me/api/v1/s?tmdb=${TMDB_ID}&type=movie`;
        const res = await fetch(apiUrl, { headers: HEADERS });
        if (!res.ok) return { success: false, reason: `HTTP ${res.status}` };
        const json = await res.json();
        if (json.servers?.length > 0) return { success: true, serversCount: json.servers.length, firstServer: json.servers[0].name };
        return { success: false, reason: "No servers array" };
    } catch (e) {
        return { success: false, reason: e.message };
    }
}

async function main() {
    console.log(`=== TESTING EXTRACTORS FOR TMDB ID ${TMDB_ID} (${MOVIE_TITLE}) ===\n`);

    const extractors = [
        ["VidLink", testVidLink],
        ["VidsrcNet", testVidsrcNet],
        ["VidsrcTo", testVidsrcTo],
        ["Moflix", testMoflix],
        ["Moviesapi", testMoviesapi],
        ["TwoEmbed", testTwoEmbed],
        ["Vidzee", testVidzee],
        ["VixSrc", testVixSrc],
        ["Einschalten", testEinschalten],
        ["Frembed", testFrembed],
        ["Vidflix", testVidflix],
        ["Vidrock", testVidrock],
        ["Videasy", testVideasy],
        ["PrimeSrc", testPrimeSrc],
    ];

    for (const [name, fn] of extractors) {
        process.stdout.write(`Testing ${name}... `);
        const res = await fn();
        if (res.success) {
            console.log(`✅ VALID`, res.url || res.src || res.sourceUrl || res.info || JSON.stringify(res));
        } else {
            console.log(`❌ FAILED: ${res.reason}`);
        }
    }
}

main();
