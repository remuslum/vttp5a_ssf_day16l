package sg.nus.edu.iss.vttp5a_ssf_day16l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.nus.edu.iss.vttp5a_ssf_day16l.model.Country;

@Service
public class CountryRestService {
    RestTemplate restTemplate = new RestTemplate();
    private final String countryUrl = "https://api.first.org/data/v1/countries";

    public List<Country> getCountries(){
        String countryData = restTemplate.getForObject(countryUrl, String.class);
        // System.out.println(countryData);

        JsonReader jReader = Json.createReader(new StringReader(countryData));
        JsonObject jObject = jReader.readObject();

        JsonObject jDataObject = jObject.getJsonObject("data");
        Set<Entry<String, JsonValue>> entries = jDataObject.entrySet();
        List<Country> countries = new ArrayList<>();
        
        for(Entry<String, JsonValue> entry:entries){
            Country c = new Country();
            c.setCode(entry.getKey());
            c.setName(entry.getValue().asJsonObject().getString("country"));
            countries.add(c);
        }
        return countries;
    }
}
