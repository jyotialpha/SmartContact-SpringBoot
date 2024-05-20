package com.project.SmartContactv1.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryPhoneCodeService {
    private static final String API_URL = "http://country.io/phone.json";


    private final RestTemplate restTemplate;

    public CountryPhoneCodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getCountryPhoneCodes() {
        Map<String, String> countryCodeToPhoneCode = restTemplate.getForObject(API_URL, Map.class);
        Map<String, String> phoneCodeToCountryCode = new HashMap<>();

        // Switch keys and values
        //i swip the key and value to get codes 
        for (Map.Entry<String, String> entry : countryCodeToPhoneCode.entrySet()) {
            phoneCodeToCountryCode.put(entry.getValue(), entry.getKey());
        }

        return phoneCodeToCountryCode;
    }
}