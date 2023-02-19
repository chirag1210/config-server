package com.microservices.limitservice.contoller;

import com.microservices.limitservice.beans.Limit;
import com.microservices.limitservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limit getLimits() {
        return new Limit(configuration.getMin(), configuration.getMax());
    }
}
