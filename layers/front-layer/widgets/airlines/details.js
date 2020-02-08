import http from '../../modules/http_request';
import {apiRejected} from '../../modules/http_request/errors';

const detailsResolve = function(details = {}) {
    this.cleanErrors();
    return this.extend(details);
};

const detailsReject = function(details = {}) {
    this.extend(details);
    return Promise.reject(this.getErrors());
};

const onErrorDetailsService = function() {
    this.extend({errors: [apiRejected]});
    return detailsReject();
};

const manageServiceResponse = function(details) {
    return _.isEmpty(details.errors) ?
        detailsResolve.call(this, details) :
        detailsReject.call(this, details);
};

export default class AirlineDetails {
    static SERVICE = 'airline-details';

    constructor(details = {}) {
        this.details = details;
    }

    getDetails() {
        return http.get(AirlineDetails.SERVICE).text()
            .then(manageServiceResponse.bind(this),);
    }

    updateDetails(details) {
        return http.put(`${AirlineDetails.SERVICE}/update`, details).json()
            .then(manageServiceResponse.bind(this), onErrorDetailsService.bind(this));
    }

    createDetails(details) {
        return http.post(`${AirlineDetails.SERVICE}/create`, details).json()
            .then(manageServiceResponse.bind(this), onErrorDetailsService.bind(this));
    }

    deleteDetails(id) {
        return http.delete(`${AirlineDetails.SERVICE}/delete/${id}`).text()
            .then(manageServiceResponse.bind(this), onErrorDetailsService.bind(this));
    }

    cleanErrors() {
        this.details.errors = [];
    }

    getErrors() {
        return this.details.errors || [];
    }

    extend(...details) {
        return Object.assign(this.details, ...details);
    }
}
