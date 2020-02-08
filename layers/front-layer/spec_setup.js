import httpResponse from './mocks/http_response'
import Runtime from "regenerator-runtime";
import {JSDOM} from 'jsdom';

const jsdomConfig = {
    url: 'https://testing.org',
    referrer: 'https://testing.com',
    contentType: 'text/html',
    includeNodeLocations: true,
    storageQuota: 10000000,
};
const jsdom = new JSDOM(
    `<!DOCTYPE html><html lang=""><head><script></script><title></title></head><body><div id="widget"></div></body></html>`,
    jsdomConfig,
);
global.jsdomConfig = jsdomConfig;
global.navigator = {
    userAgent: 'node.js',
};
global.window = jsdom.window;
global.document = jsdom.window.document;
global.fetch = window.fetch = (response) => Promise.resolve(httpResponse());
