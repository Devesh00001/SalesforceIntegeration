package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.example.demo.Connection.getResponse;

@RestController
public class LeadConverted {

    static String endpoint = "/services/apexrest/leadsconverted";

    @GetMapping(value = "/leadsconverted")
    public String data() throws Exception {
        return Objects.requireNonNull(getResponse(endpoint)).toString();
    }
}