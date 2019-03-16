package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.StudentsService;

@Controller
public class AdminController {

    private final StudentsService studentsService;


    @Autowired
    public AdminController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @RequestMapping({"/admin"})
    public ModelAndView workWithStudents(){
        ModelAndView model = new ModelAndView("/admin/main");
        model.addObject("mainText","Admin part of work with students");
        model.addObject("studentsList",studentsService.getAllStudents());
        return model;
    }


}