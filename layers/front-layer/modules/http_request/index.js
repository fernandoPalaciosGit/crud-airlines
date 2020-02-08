import HttpRequest from 'http_headers';

const get = () => ({
    credentials: 'same-origin',
    method: 'GET',
});

const post = (requestParams) => ({
    credentials: 'same-origin',
    method: 'POST',
    body: JSON.stringify(requestParams),
});

const put = (requestParams) => ({
    credentials: 'same-origin',
    method: 'PUT',
    body: JSON.stringify(requestParams),
});

export default {
    get(url) {
        return new HttpRequest(url, get());
    },

    post(url, requestParams = {}) {
        return new HttpRequest(url, post(requestParams));
    },

    put(url, requestParams = {}) {
        return new HttpRequest(url, put(requestParams));
    },
};
