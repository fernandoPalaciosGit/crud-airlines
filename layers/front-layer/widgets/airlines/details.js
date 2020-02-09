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
        this.airlines = details;
    }

    getDetails() {
        return http.get(AirlineDetails.SERVICE).json()
            .then(manageServiceResponse.bind(this),);
    }

    getAirlines() {
        return this.airlines;
    }

    getDetail(iata) {
        return http.get(`${AirlineDetails.SERVICE}/${iata}`).json()
            .then(manageServiceResponse.bind(this), onErrorDetailsService.bind(this));
    }

    updateDetails(iata, details) {
        return http.put(`${AirlineDetails.SERVICE}/${iata}`, details).json()
            .then(manageServiceResponse.bind(this), onErrorDetailsService.bind(this));
    }

    createDetails(details) {
        return http.post(AirlineDetails.SERVICE, details).json()
            .then(manageServiceResponse.bind(this), onErrorDetailsService.bind(this));
    }

    deleteDetails(iata) {
        return http.delete(`${AirlineDetails.SERVICE}/${iata}`).text()
            .then(manageServiceResponse.bind(this), onErrorDetailsService.bind(this));
    }

    cleanErrors() {
        this.airlines.errors = [];
    }

    getErrors() {
        return this.airlines.errors || [];
    }

    extend(...details) {
        return Object.assign(this.airlines, ...details);
    }
}
