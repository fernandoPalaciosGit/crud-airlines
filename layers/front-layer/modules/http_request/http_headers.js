import logger from '../log_error/index';

const getJsonHeaders = () => ({
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
    },
});

const getTextHeaders = () => ({
    headers: {
        'Content-Type': 'text/html;charset=UTF-8',
    },
});

const checkStatus = (response, {body = ''}) => {
    if (response.ok) {
        return response;
    } else {
        const {url, status, statusText} = response;
        logger.message(`REQUEST: ${url}, STATUS: ${status}-${statusText}, PAYLOAD: ${body}`);
        return Promise.reject(response);
    }
};

const request = (url, params) => window.fetch(url, params)
    .then((response) => checkStatus(response, params));

export default class HttpRequest {
    constructor(url, httpParams = {}) {
        this.url = url;
        this.httpParams = httpParams;
    }

    json() {
        return request(this.url, {...this.httpParams, ...getJsonHeaders()})
            .then((response) => response.json());
    }

    text() {
        return request(this.url, {...this.httpParams, ...getTextHeaders()})
            .then((response) => response.text());
    }
}
