package com.fitr.bntu.bookwebservice.controller;

import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.service.UserService;
import com.fitr.bntu.bookwebservice.util.SessionAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/menu")
    public String menu(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return "main";
    }
    @PostMapping("/do_signup")
    public String doRegister( UserDTO registerData, HttpSession session) {
            UserDTO registered = userService.signUp(registerData.getLogin(), registerData.getPassword());
            session.setAttribute(SessionAttribute.USER, registered);
            session.setAttribute(SessionAttribute.CART, new ArrayList<BookDTO>());
        return "redirect:/book/menu";
    }
    @PostMapping("/do_login")
    public String dologin(UserDTO loginData, HttpSession session){
        final UserDTO logedIn = userService.signIn(loginData.getLogin(), loginData.getPassword());
        session.setAttribute(SessionAttribute.USER, logedIn);
        session.setAttribute(SessionAttribute.CART, new ArrayList<BookDTO>());
        return "redirect:/book/menu";
    }
    @GetMapping("/logOut")
    public String logOut(HttpSession session){
        if (session != null) {
            UserDTO user = (UserDTO) session.getAttribute(SessionAttribute.USER);
            if (user != null) {
                session.invalidate();
            }
        }
        return "redirect:/book/menu";
    }
}
