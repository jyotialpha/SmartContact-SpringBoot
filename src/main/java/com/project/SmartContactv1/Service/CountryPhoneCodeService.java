package com.project.SmartContactv1.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountryPhoneCodeService {
    private static final String API_URL = "https://country.io/names.json";

    private final RestTemplate restTemplate;

    public CountryPhoneCodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
	public Map<String, String> getCountryPhoneCodes() {
        try {
            // Fetch data from the API
            return restTemplate.getForObject(API_URL, Map.class);
        } catch (Exception e) {
            // Handle any exceptions, such as network errors or invalid response
            e.printStackTrace();
            return new HashMap<>(); // Return an empty map or handle the error appropriately
        }
    }
}
