package com.airlines.services;

import com.airlines.models.LoggerFront;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logger/front")
public class loggerFrontService {

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity<?> logErrorsFrontLayer(@RequestBody LoggerFront loggerFront) {
        // todo: connect LogStash to kibana or Grafana services
        return new ResponseEntity<>(null, null, HttpStatus.ACCEPTED);
    }
}
