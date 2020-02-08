import AirlineDetails from './details';

let promiseAirlines = null;

const getAirlinesDetails = () => {
    if (promiseAirlines === null) {
        const airlineDetails = new AirlineDetails();
        promiseAirlines = airlineDetails.createDetails().then(() => airlineDetails, () => airlineDetails);
    }
    return promiseAirlines;
};

const getAirlines = () => getAirlinesDetails()
    .then((airlines) => airlines.getDetails());

const updateAirlines = (details) => getAirlinesDetails()
    .then((airlines) => airlines.updateDetails(details));

const deleteAirlines = ({id = ''}) => getAirlinesDetails()
    .then((airlines) => airlines.deleteDetails(id));

export default {
    getAirlines,
    updateAirlines,
    deleteAirlines,
};
