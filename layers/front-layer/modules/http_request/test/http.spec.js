import {describe, it, afterEach, beforeEach} from 'mocha';
import sinon from 'sinon';
import {expect} from 'chai';
import HttpRequest from '../http_headers';
import logger from '../../log_error/index';
import airlines from '../../../mocks/airlines'
import httpResponse from '../../../mocks/http_response'

describe('Test Http Request module', () => {
    const SERVICE = 'test';
    let request;
    let response;

    afterEach(() => {
        sinon.restore();
    });

    describe('Should request different Content Types', () => {
        beforeEach(() => {
            response = httpResponse(airlines);
            sinon.stub(window, 'fetch').resolves(response);
        });

        it('Should return a Promise that resolves json', async() => {
            sinon.spy(response, 'json');
            request = new HttpRequest(SERVICE);
            const result = await request.json();
            sinon.assert.calledOnceWithExactly(window.fetch, SERVICE, sinon.match.has("headers"));
            sinon.assert.calledOnce(response.json);
            expect(result).to.be.equal(airlines);
        });

        it('Should return a Promise that resolves USVString object (text)', async() => {
            sinon.spy(response, 'text');
            request = new HttpRequest(SERVICE);
            const result = await request.text();
            sinon.assert.calledOnceWithExactly(window.fetch, SERVICE, sinon.match.has("headers"));
            sinon.assert.calledOnce(response.text);
            expect(result).to.be.equal(airlines);
        });
    });

    describe('Should reject response when fails the request', () => {
        const ERROR_RESPONSE = {
            ok: false,
            url: SERVICE,
            status: 500,
            statusText: 'BROKEN_FLOW'
        };

        beforeEach(() => {
            sinon.stub(window, 'fetch').resolves(ERROR_RESPONSE);
            sinon.stub(logger, 'message');
        });

        it('should logger error service', async() => {
            request = new HttpRequest(SERVICE);
            await request.json().catch((error) => {
                sinon.assert.calledOnceWithExactly(logger.message, 'REQUEST: test, STATUS: 500-BROKEN_FLOW, PAYLOAD: ');
                expect(error).to.be.equals(ERROR_RESPONSE);
            });
        });
    });
});
