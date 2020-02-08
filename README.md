# Testing Airlines details with CRUD

Minimal project setup for a Vanilla JavaScript project running with Webpack and Babel for a frontend application.
CRUD is build in JAVA spring

## Installation

* `git clone git@github.com:fernandoPalaciosGit/crud-airlines.git`
* cd crud-airlines
* npm install
* npm start


## PATH RESOURCES
* http://localhost:8080


### URI RESOURCES
- **/airline-details -GET** -List&lt;Airlines&gt; getAllAirlines: get all airline list
- **/airline-details/{id} -GET** -Airline getAirlineById: get specific airline by id
- **/airline-details -POST** -void addAirline: deserialize airline by json post and create it.
- **/airline-details/{id} -PUT** -void addAirline: deserialize airline by json post and create it.
- **/airline-details/{id} -DELETE** -void addAirline: deserialize airline by json post and create it.

#### STATUS CLIENT
- 201 - request to service success
- 405 - malformed header http request, send application/json
