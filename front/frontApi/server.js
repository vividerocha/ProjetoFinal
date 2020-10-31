const express = require('express');
const path = require('path');
const nomeApp = process.env.npm_package_name;
const app = express();
const PORT = process.env.PORT || 8081;

app.use(express.static(`${__dirname}/dist/frontApi`));

app.get('/*', (req, res) => {
res.sendFile(path.join(`${__dirname}/dist/frontApi/index.html`));
});

app.listen(PORT,()=>{
    console.log(`Servidor rodando na porta: ${PORT}`);
});