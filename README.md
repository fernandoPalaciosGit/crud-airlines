# Testing Airlines details with CRUD

Minimal project setup for a Vanilla JavaScript project running with Webpack and Babel for a frontend application.
CRUD is build in JAVA spring

## Deploy App
* git clone git@github.com:fernandoPalaciosGit/crud-airlines.git
* cd crud-airlines
* npm install
* npm start
* `http://localhost:8080`

## Deploy API service
* $mvn clean install
* $mvn tomcat7:run

### URI RESOURCES
- **/airline-details -GET** -List&lt;Airlines&gt; getAllAirlines: get all airline list
- **/airline-details/names -GET** -List&lt;Airlines&gt; getAllAirlineNames: get all airline list by name.
- **/airline-details/{iata} -GET** -Airline getAirlineByIata: get specific airline by iata
- **/airline-details -POST** -void addAirline: deserialize airline by json post and create it.
- **/airline-details/{iata} -PUT** -void addAirline: deserialize airline by json post and create it.
- **/airline-details/{iata} -DELETE** -void deleteAirline: remove airline register.
- **/logger/front -POST** -void log error stack trace to send Kibana or Grafana platforms

#### PAYLOAD DATA
- `com.airlines.entities.AirlineEntity`

#### STATUS CLIENT
- 201 - request to service success
- 405 - malformed header http request, send application/json
