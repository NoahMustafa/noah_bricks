async function inspectVidsrcTo() {
    try {
        const res = await fetch("https://vidsrc.to/embed/movie/278", {
            headers: {
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36',
                'Referer': 'https://vidsrc.to/'
            }
        });
        console.log("STATUS:", res.status);
        const html = await res.text();
        console.log("HTML snippet:", html.slice(0, 1000));
        console.log("Matches data-id:", html.match(/data-id="([^"]+)"/g));
        console.log("Matches id=:", html.match(/id="([^"]+)"/g));
        console.log("Matches data-hash:", html.match(/data-hash="([^"]+)"/g));
    } catch(e) {
        console.error(e);
    }
}
inspectVidsrcTo();
