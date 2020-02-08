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

export const getAirline = (id = '') => getAirlinesDetails()
    .then((airlines) => airlines.getDetail(id));

export const createAirline = (details = {}) => getAirlinesDetails()
    .then((airlines) => airlines.createDetails(details));

export const updateAirlines = ({id = '', details = {}}) => getAirlinesDetails()
    .then((airlines) => airlines.updateDetails(id, details));

export const deleteAirlines = ({id = ''}) => getAirlinesDetails()
    .then((airlines) => airlines.deleteDetails(id));
