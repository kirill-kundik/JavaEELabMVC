package controllers;

import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.StudentsService;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {
    public static final int STUDENTS_ON_THE_PAGE = 25;

    private final StudentsService studentsService;

    @Autowired
    public HomeController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public ModelAndView showHomePage() {
        ModelAndView model = new ModelAndView("home");
        model.addObject("mainText", "Students List");
        model.addObject("showForm", true);
        model.addObject("studentsList", studentsService.getAllStudents());
        return model;
    }

    @PostMapping
    public ModelAndView createStudent(@RequestParam String pib, @RequestParam int course) {
        Student student = new Student();
        student.setPib(pib);
        student.setCourse(course);
        studentsService.addStudent(student);
        ModelAndView model = new ModelAndView("home");
        model.addObject("mainText", "Student Added");
        model.addObject("showForm", true);
        model.addObject("studentsList", studentsService.getAllStudents());
        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView showOneStudent(@PathVariable int id) {
        Student student = studentsService.getStudentById(id);
        ModelAndView model = new ModelAndView("home");
        model.addObject("mainText", student.getPib() + ", course: " + student.getCourse());
        return model;
    }
}
