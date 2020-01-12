const markdown = require('markdown-it');
module.exports = function (src) {
    const md = markdown({
        html: true,
        typographer: true
    });
    const html = md.render(src);
    return (
        `<template><div class="markdown">${html}</div></template>`
    );
};
