package ua.kpi.lab1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.kpi.lab1.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StartPage {
    static Map<String, User> students = new HashMap<>();
    static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("Yevhenii", "Novikov", "nullelon",
                "Я староста! Поставьте много балов пожалуйста!!!!!!!!!!!"));
        userList.add(new User("Mykyta", "Onaskyi", "nikitochkaa",
                "Мукута Онацкуи"));
        userList.add(new User("Viktoriia", "Yakimenko", "blackberry_vv",
                "Люблю орешки"));

        students.put("nullelon", userList.get(0));
        students.put("nikitochkaa", userList.get(1));
        students.put("blackberry_vv", userList.get(2));
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("students", userList);
        return "index";
    }

    @GetMapping(value = "/user/{username}")
    public String profile(Model model, @PathVariable String username) {
        model.addAttribute("student", students.get(username));
        return "profile";
    }

}
