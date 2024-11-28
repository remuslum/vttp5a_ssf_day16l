package sg.nus.edu.iss.vttp5a_ssf_day16l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import sg.nus.edu.iss.vttp5a_ssf_day16l.model.Student;

@Service
public class StudentRestService {
    
    RestTemplate restTemplate = new RestTemplate();

    private static final String studentURL = "http://localhost:4000/api/students";

    public List<Student> getAllStudents(){
        ResponseEntity<String> data = restTemplate.getForEntity(studentURL, String.class);
        String payLoad = data.getBody();

        JsonReader jReader = Json.createReader(new StringReader(payLoad));
        JsonArray jArray = jReader.readArray();

        List<Student> students = new ArrayList<>();

        for(int i = 0; i < jArray.size(); i++){
            JsonObject jObject = jArray.getJsonObject(i);

            Student s = new Student();
            s.setId(jObject.getInt("id"));
            s.setFullName(jObject.getString("fullName"));
            s.setEmail(jObject.getString("email"));
            s.setPhoneNumber(jObject.getString("phoneNumber"));

            students.add(s);
        }
        return students;

    }

    public String createStudent(Student student){
        // convert to JSON string using JSON-P functions
        JsonObjectBuilder jObject = Json.createObjectBuilder();
        jObject.add("id",student.getId());
        jObject.add("fullName", student.getFullName());
        jObject.add("email", student.getEmail());
        jObject.add("phoneNumber", student.getPhoneNumber());
        String requestPayload = jObject.build().toString();

        RequestEntity requestEntity = RequestEntity.post(studentURL + "/create" + requestPayload).build();
        ResponseEntity<String> responseResult = restTemplate.exchange(requestEntity, String.class);
        return responseResult.getBody();
        

    }
}
