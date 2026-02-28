# CSV Import Util

Extract your "Saved" list from Google Maps, via [Google Takeout](https://takeout.google.com/), and import it into Pub Atlas.

## Usage

```bash
npm install

npm run import -- \
  --file=pubs.csv \
  --url=https://pub-atlas-api.sandbox.sitenotfound.co.uk/pubs \
  --key=supersecret
```

## Notes
- Does not currently support long/lat coordinates, these will be `null` in the db.

A sample pub list is provided in [pubs.csv](./pubs.csv) for testing purposes.