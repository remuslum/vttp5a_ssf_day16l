package sg.nus.edu.iss.vttp5a_ssf_day16l.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.vttp5a_ssf_day16l.constant.Constant;
import sg.nus.edu.iss.vttp5a_ssf_day16l.model.Student;
import sg.nus.edu.iss.vttp5a_ssf_day16l.repo.ListRepo;

@Service
public class StudentService {
    
    @Autowired
    ListRepo listRepo;

    public void add(Student student){
        listRepo.rightPush(Constant.STUDENTKEY, student.toString());
    }

    public List<Student> findAll(String redisKey){
        List<String> students = listRepo.getList(redisKey);
        List<Student> studentRecords = new ArrayList<>();
        // Implement JSON-P logic here
        // JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for(String raw:students){
            String[] data = raw.split(",");
        //     JsonObject jsonObject = Json.createObjectBuilder()
        //     .add("id", Integer.parseInt(data[0]))
        //     .add("fullName", data[1])
        //     .add("email", data[2])
        //     .add("phoneNumber", data[3]).build();

        //     jsonArrayBuilder.add(jsonObject);
            Student s = new Student(Integer.valueOf(data[0]), data[1], data[2], data[3]);
            studentRecords.add(s);
        }
        // jsonArrayBuilder.build();

        return studentRecords;
    }
}
