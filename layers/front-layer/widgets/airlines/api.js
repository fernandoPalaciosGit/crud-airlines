import AirlineDetails from './details';

let promiseAirlines = null;

const getAirlinesDetails = () => {
    if (promiseAirlines === null) {
        const airlineDetails = new AirlineDetails();
        promiseAirlines = airlineDetails.getDetails().then(() => airlineDetails, () => airlineDetails);
    }
    return promiseAirlines;
};

export const getAirlines = () => getAirlinesDetails()
    .then((airlines) => airlines.getAirlines());

export const getAirline = (iata = '') => getAirlinesDetails()
    .then((airlines) => airlines.getDetail(iata));

export const createAirline = (details = {}) => getAirlinesDetails()
    .then((airlines) => airlines.createDetails(details));

export const updateAirlines = ({iata = '', details = {}}) => getAirlinesDetails()
    .then((airlines) => airlines.updateDetails(iata, details));

export const deleteAirlines = ({iata = ''}) => getAirlinesDetails()
    .then((airlines) => airlines.deleteDetails(iata));
