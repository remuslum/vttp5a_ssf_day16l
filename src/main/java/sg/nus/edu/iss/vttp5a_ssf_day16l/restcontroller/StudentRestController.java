package sg.nus.edu.iss.vttp5a_ssf_day16l.restcontroller;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.nus.edu.iss.vttp5a_ssf_day16l.constant.Constant;
import sg.nus.edu.iss.vttp5a_ssf_day16l.model.Student;
import sg.nus.edu.iss.vttp5a_ssf_day16l.service.StudentService;

@RestController
@RequestMapping(path = "/api/students", produces = "application/json")
public class StudentRestController {

    @Autowired
    StudentService studentService;

    @PostMapping(path = {"","/create"})
    public ResponseEntity<String> create(@RequestBody String entity){
        JsonReader jReader = Json.createReader(new StringReader(entity));
        JsonObject jObject = jReader.readObject();

        Student s = new Student();
        s.setId(jObject.getInt("id"));
        s.setFullName(jObject.getString("fullName"));
        s.setEmail(jObject.getString("email"));
        s.setPhoneNumber(jObject.getString("phoneNumber"));

        return ResponseEntity.ok().body("true");
    }
    
    // @PostMapping()
    // public ResponseEntity<String> create(@RequestBody Student entity){
    //     studentService.add(entity);
    //     // return new ResponseEntity("true", HttpStatus.OK);
    //     return ResponseEntity.ok().body("true");
    // }

    // @GetMapping
    // public ResponseEntity<List<Student>> findAll(){
    //     List<Student> students = studentService.findAll(Constant.STUDENTKEY);
    //     return ResponseEntity.ok().body(students);
    // }  
    
    @GetMapping
    public ResponseEntity<String> findAll(){
        List<Student> students = studentService.findAll(Constant.STUDENTKEY);

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for(Student s: students){
            JsonObject jsonObject = Json.createObjectBuilder().add("id",s.getId())
            .add("fullName", s.getFullName())
            .add("email", s.getEmail())
            .add("phoneNumber", s.getPhoneNumber()).build();
            jsonArrayBuilder.add(jsonObject);
        }
        return ResponseEntity.ok().body(jsonArrayBuilder.build().toString());
    }  
}
