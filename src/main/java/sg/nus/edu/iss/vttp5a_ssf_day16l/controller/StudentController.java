package sg.nus.edu.iss.vttp5a_ssf_day16l.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.edu.iss.vttp5a_ssf_day16l.model.Student;
import sg.nus.edu.iss.vttp5a_ssf_day16l.service.StudentRestService;
import sg.nus.edu.iss.vttp5a_ssf_day16l.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    StudentRestService studentRestService;
    
    @GetMapping("")
    public String studentForm(Model model){
        Student s = new Student();
        model.addAttribute("student", s);
        return "studentform";
    }

    @PostMapping("")
    public String postStudentForm(@ModelAttribute Student entity, BindingResult bindingResult, Model model){
        
        return "redirect:/students/list";
    }

    // @GetMapping("/list")
    // public String getStudentList(Model model){
    //     List<Student> students = studentService.findAll(Constant.STUDENTKEY);
    //     model.addAttribute("students", students);
    //     return "studentlist";
    // }

    @GetMapping("/list")
    public String getStudentList(Model model){
        List<Student> students = new ArrayList<>();
        students = studentRestService.getAllStudents();

        model.addAttribute("students",students);
        return "studentlist";
    }
}
