# Testing Airlines details with CRUD

Minimal project setup running with Webpack and Babel for a frontend application.
CRUD is build in JPA SpringBoot.

### REST
end point | method | details
------------ | ------------- | ------------
**/airline-details** | GET | `List<AirlineEntity>` get all airline list.
**/airline-details/names** | GET | `List<String>` get all airline list by name.
**/airline-details/{iata}** | GET | `AirlineEntity` get specific airline by iata.
**/airline-details** | POST | `void` deserialize airline by json post and create it.
**/airline-details/{iata}**  | PUT | `void` deserialize airline by json post and create it.
**/airline-details/{iata}** | DELETE | `void` remove airline register.
**/logger/front** | POST | `void` log error stack trace to send Kibana or Grafana platforms.

### Payload data
`com.airlines.entities.AirlineEntity`

### Status client
- 201 - request to service success
- 405 - malformed header http request, send application/json

### Deploy Application
* npm install
* npm start
* `http://localhost:8080`

### Deploy API service
* mvn clean install
* mvn tomcat7:run
