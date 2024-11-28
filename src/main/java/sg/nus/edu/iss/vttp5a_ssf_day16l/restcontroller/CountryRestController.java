package sg.nus.edu.iss.vttp5a_ssf_day16l.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.edu.iss.vttp5a_ssf_day16l.model.Country;
import sg.nus.edu.iss.vttp5a_ssf_day16l.service.CountryRestService;

@RestController
@RequestMapping("/api/countries")
public class CountryRestController {
    
    @Autowired
    CountryRestService countryRestService;

    @GetMapping()
    public ResponseEntity<List<Country>> getCountries(){
        List<Country> countries = countryRestService.getCountries();
        return ResponseEntity.ok().body(countries);
    }
}
