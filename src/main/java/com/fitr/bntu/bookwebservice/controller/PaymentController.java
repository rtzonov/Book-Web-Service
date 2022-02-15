package com.fitr.bntu.bookwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PaymentController {
    @GetMapping("/payment")
    public ModelAndView payment(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("payment");
        return mv;
    }

}
