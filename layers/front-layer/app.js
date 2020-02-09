import React from "react";
import ReactDOM from 'react-dom';
import AirlinesList from './widgets/airlines/AirlinesList';
import Runtime from "regenerator-runtime";

window.addEventListener('load', () => {
    ReactDOM.render(<AirlinesList/>, document.getElementById('root'));
}, false);
