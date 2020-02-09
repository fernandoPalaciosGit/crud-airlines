import {getAirlines} from './api';
import React from 'react';

export default class Hello extends React.Component {
    constructor(props) {
        super(props);
    }

    async render() {
        const airlines = await getAirlines();

        airlines.forEach((airline) => {
            return (
                <div>
                    <div>{airline.iata}</div>
                    <div>{airline.icao}</div>
                    <div>{airline.name}</div>
                    <div>{airline.phoneNumber}</div>
                </div>
            );
        })
    }
}
