package com.laudate.service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationService {


    public Map<String, String> getRegion() throws IOException {

        Map<String, String> regions = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file from the resources directory
        ClassPathResource resource = new ClassPathResource("location/regions.json");
        InputStream inputStream = resource.getInputStream();

        System.out.println(inputStream);

        JsonNode jsonNode = objectMapper.readTree(inputStream);

        for (JsonNode regionNode : jsonNode) {
            regions.put(regionNode.get("key").asText(), regionNode.get("name").asText() + " " + regionNode.get("long").asText());
        }

        return regions;
    }


    public void getCities(String province) throws IOException {

        Map<String, String> cities = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file from the resources directory
        ClassPathResource resource = new ClassPathResource("location/cities.json");
        InputStream inputStream = resource.getInputStream();


        JsonNode jsonNode = objectMapper.readTree(inputStream);

        for (JsonNode cityNode : jsonNode) {
            System.out.println(cityNode.get("name").asText());
        }

        //return cities;
    }



}
