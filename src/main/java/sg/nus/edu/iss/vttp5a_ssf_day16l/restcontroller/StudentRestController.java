package sg.nus.edu.iss.vttp5a_ssf_day16l.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.edu.iss.vttp5a_ssf_day16l.constant.Constant;
import sg.nus.edu.iss.vttp5a_ssf_day16l.model.Student;
import sg.nus.edu.iss.vttp5a_ssf_day16l.service.StudentService;

@RestController
@RequestMapping(path = "/api/students", produces = "application/json")
public class StudentRestController {

    @Autowired
    StudentService studentService;
    
    @PostMapping()
    public ResponseEntity<String> create(@RequestBody Student entity){
        studentService.add(entity);
        // return new ResponseEntity("true", HttpStatus.OK);
        return ResponseEntity.ok().body("true");
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        List<Student> students = studentService.findAll(Constant.STUDENTKEY);
        return ResponseEntity.ok().body(students);
    }    
}
