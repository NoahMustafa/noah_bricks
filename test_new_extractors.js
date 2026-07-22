import crypto from 'crypto';

// --- RIVESTREAM TEST ---
const RIVE_APP = 'https://www.rivestream.app';
const LOOKUP = ["4Z7lUo","gwIVSMD","PLmz2elE2v","Z4OFV0","SZ6RZq6Zc","zhJEFYxrz8","FOm7b0","axHS3q4KDq","o9zuXQ","4Aebt","wgjjWwKKx","rY4VIxqSN","kfjbnSo","2DyrFA1M","YUixDM9B","JQvgEj0","mcuFx6JIek","eoTKe26gL","qaI9EVO1rB","0xl33btZL","1fszuAU","a7jnHzst6P","wQuJkX","cBNhTJlEOf","KNcFWhDvgT","XipDGjST","PeGZJlbHoyt","2AYnMZ4kd","HIpJh","KH0C3iztrG","W81hjts92","rJhAT","NON7LKoMQ","NMdY3nsKzI","t4En5v","Qq5cOQ9H","Y9nwrp","VX3FYVfsf","cE0SJG","x1vj1","HegbLe","zJ3nmt4OA","gt7rxW7DQ","clIEH9b","jyJ9g","B5jXKiCSx","cOzZBZTV","FTXGy","Dfh1q1","ny9jqZ2POI","X3NnMn","MBtoyD","qz4Ilys7wB","68lbOMYr","jYUJnmxp","1fv5fmona","PlDvvXD7GA","ZarKfHCaPR","owORnX","AP1YU","dVdkx","qgiK0E","cx9wQ","59Ga","7UjkKrp","Yvhrj","wYXez5Dg3","pGUGMU","MwMAu","rFRD5wlM"];

function innerHash(s) {
  let t = 0;
  for (let n = 0; n < s.length; n++) {
    const r = s.charCodeAt(n);
    const i = ((t = (r + (t << 6) + (t << 16) - t) >>> 0) << n % 5 | t >>> (32 - n % 5)) >>> 0;
    t = (t ^ i ^ ((r << n % 7 | r >>> (8 - n % 7)) >>> 0)) >>> 0;
    t = (t + (t >>> 11 ^ t << 3)) >>> 0;
  }
  t ^= t >>> 15;
  t = (94842 * (65535 & t) + ((49842 * (t >>> 16) & 65535) << 16)) >>> 0;
  t ^= t >>> 13;
  t = (40503 * (65535 & t) + ((40503 * (t >>> 16) & 65535) << 16)) >>> 0;
  t ^= t >>> 16;
  return t.toString(16).padStart(8, '0');
}

function outerHash(str) {
  let t = (3735928559 ^ str.length) >>> 0;
  for (let n = 0; n < str.length; n++) {
    let r = str.charCodeAt(n);
    r ^= 255 & (0 * n + 89 ^ r << n % 5);
    t = ((t << 7 | t >>> 25) >>> 0) ^ r;
    t = ((t * (65535 & t) >>> 0) + (3 * (t >>> 16) << 16 >>> 0)) >>> 0;
    t ^= t >>> 11;
  }
  t ^= t >>> 15;
  t = (t * (65535 & t) + (3 * (t >>> 16) << 16)) >>> 0;
  t ^= t >>> 13;
  t = (t * (65535 & t) + (3 * (t >>> 16) << 16)) >>> 0;
  t ^= t >>> 16;
  t = (t * (65535 & t) + (3 * (t >>> 16) << 16)) >>> 0;
  t ^= t >>> 15;
  return t.toString(16).padStart(8, '0');
}

function computeSecretKey(tmdbId) {
  const e = Number(tmdbId);
  const n = String(tmdbId);
  const r = LOOKUP[e % LOOKUP.length] ?? Buffer.from(n).toString('base64');
  const i = Math.floor((e % n.length) / 2);
  return Buffer.from(outerHash(innerHash(n.slice(0, i) + r + n.slice(i)))).toString('base64');
}

async function testRivestream() {
  const secretKey = computeSecretKey("278");
  const url = `${RIVE_APP}/api/backendfetch?requestID=movieVideoProvider&id=278&secretKey=${encodeURIComponent(secretKey)}&service=flowcast`;
  const res = await fetch(url, { headers: { Referer: `${RIVE_APP}/`, Accept: 'application/json' } });
  console.log("Rivestream Status:", res.status);
  const data = await res.json();
  console.log("Rivestream Sources:", data?.data?.sources);
}

testRivestream();
