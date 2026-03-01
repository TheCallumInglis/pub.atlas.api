const fs = require("fs");
const parse = require("csv-parse/sync");
const axios = require("axios");

function getArg(name) {
  const arg = process.argv.find(a => a.startsWith(`--${name}=`));
  return arg ? arg.split("=")[1] : null;
}

const filePath = getArg("file");
const apiUrl = getArg("url");
const apiKey = getArg("key");

if (!filePath || !apiUrl || !apiKey) {
  console.error(`Usage: npm run import -- --file=pubs.csv --url=https://... --key=API_KEY`);
  process.exit(1);
}

const file = fs.readFileSync(filePath, "utf-8");

const records = parse.parse(file, {
  columns: true,
  skip_empty_lines: true
});

const wait = (seconds) => {
    return new Promise((resolve) => {
      setTimeout(resolve, seconds * 1000)
    });
};

async function run() {
  for (const row of records) {
    await wait(1.5);

    if (!row.Title) continue;

    const payload = {
      name: row.Title,
      area: "Edinburgh",
      latitude: null,
      longitude: null,
      visited: true,
      visitDate: new Date().toISOString().split("T")[0],
      googleMapsUrl: row.URL
    };

    try {
      await axios.post(apiUrl, payload, {
        headers: {
          "X-API-Key": apiKey
        }
      });

      console.log("Imported:", row.Title);
    } catch (err) {
      console.error("Failed:", row.Title);
    }
  }
}

run();