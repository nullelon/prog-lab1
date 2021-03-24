package ua.kpi.lab1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IfController {

    @GetMapping("/if")
    public String institute(Model model, @RequestParam(required = false) String yes) {
        if (yes != null && yes.equals("yes"))
            model.addAttribute("yes", true);
        else
            model.addAttribute("yes", false);
        return "if";
    }

}
