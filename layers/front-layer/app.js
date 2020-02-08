import Runtime from "regenerator-runtime";
import {getAirlines} from './widgets/airlines/api';

window.addEventListener('load', () => {
    getAirlines().then(console.info);
}, false);
