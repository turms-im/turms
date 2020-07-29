const fs = require('fs');
const path = require('path');
const express = require('express');
const compression = require('compression');
const history = require('connect-history-api-fallback');
const chalk = require('chalk');

const port = process.env.PORT || 7510;
const DIST_DIR = path.resolve(__dirname, '../../dist');
const html = fs.readFileSync(`${DIST_DIR}/index.html`);
const app = express();

app.use(compression());
app.use(history());
app.use('/', express.static(DIST_DIR));

app.get('/', function(req, res) {
    res.send(html);
});

app.listen(port, () => {
    console.log(chalk.yellow(`turms-admin is running on port ${port}`));
});
