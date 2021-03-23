package ua.kpi.lab1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Calculator {

    public class CalcData {
        public double a;
        public double b;
        public double c;
        public double d;
        public String result;

        CalcData(String cookieStr) {
            var splitCookie = cookieStr.split("_");
            a = Double.parseDouble(splitCookie[0]);
            b = Double.parseDouble(splitCookie[1]);
            c = Double.parseDouble(splitCookie[2]);
            d = Double.parseDouble(splitCookie[3]);
            result = splitCookie[4];
        }

        CalcData(String a, String b, String c, String d, String result) {
            this.a = Double.parseDouble(a);
            this.b = Double.parseDouble(b);
            this.c = Double.parseDouble(c);
            this.d = Double.parseDouble(d);
            this.result = result;
        }

        String toCookie() {
            return a + "_" + b + "_" + c + "_" + d + "_" + result;
        }
    }

    @GetMapping(value = "/calculator")
    public String calcGet(Model model,
                          @CookieValue(name = "calcLastData", defaultValue = "0_0_0_0_0") String calcLastData,
                          @CookieValue(name = "calcDataHistory", defaultValue = "") String calcDataHistory) {

        CalcData calcData = new CalcData(calcLastData);
        calcData.result = "Введи данные, типо дорогой пользователь)";

        if (!calcDataHistory.equals("")) {
            String[] historyCookies = calcDataHistory.split("\\|");

            CalcData[] history = new CalcData[historyCookies.length];
            for (int i = 0; i < historyCookies.length; i++) {
                history[i] = new CalcData(historyCookies[i]);
            }
            model.addAttribute("calcDataHistory", history);
        }

        model.addAttribute("calcLastData", calcData);
        return "calculator";
    }

    @PostMapping(value = "/calculator")
    public String calcPost(HttpServletResponse r, Model model,
                           @CookieValue(name = "calcDataHistory", defaultValue = "") String calcDataHistory,
                           @RequestParam String a, @RequestParam String b,
                           @RequestParam String c, @RequestParam String d) {
        try {
            CalcData calcData = new CalcData(a, b, c, d, "");
            calcData.result = calculate(calcData.a, calcData.b, calcData.c, calcData.d) + "";

            model.addAttribute("calcLastData", calcData);

            model.addAttribute("drawTable", true);

            Cookie cookie = new Cookie("calcLastData", calcData.toCookie());
            cookie.setMaxAge(60 * 60 * 24 * 2);
            r.addCookie(cookie);

            if (calcDataHistory.equals("")) {
                calcDataHistory = calcData.toCookie();
            } else {
                calcDataHistory += "|" + calcData.toCookie();
            }


        } catch (NumberFormatException e) {
            CalcData calcData = new CalcData("0","0","0","0", "введи нормальные числа, дорогой пользователь)");
            model.addAttribute("calcLastData", calcData);
        }
        Cookie cookie2 = new Cookie("calcDataHistory", calcDataHistory);
        cookie2.setMaxAge(60 * 60 * 24 * 2);
        r.addCookie(cookie2);

        var historyCookies = (calcDataHistory).split("\\|");
        CalcData[] history = new CalcData[historyCookies.length];
        for (int i = 0; i < historyCookies.length; i++) {
            history[i] = new CalcData(historyCookies[i]);
        }

        model.addAttribute("calcDataHistory", history);
        return "calculator";
    }

    private double calculate(double a, double b, double c, double d) {
        return (3 * a / (Math.cos(a))) + (Math.sqrt((Math.tanh(Math.abs(b) * c)) / Math.log1p(d)));
    }
}
