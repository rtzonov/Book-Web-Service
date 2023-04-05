package com.fitr.bntu.bookwebservice.controller;

import com.fitr.bntu.bookwebservice.DTO.BookDTO;
import com.fitr.bntu.bookwebservice.DTO.UserDTO;
import com.fitr.bntu.bookwebservice.service.ServiceException;
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

    private static final String DEFAULT_REDIRECT = "redirect:/menu";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

/*    @GetMapping("/menu")
    public String menu(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return "main";
    }*/
    @PostMapping("/do_signup")
    public String doRegister( UserDTO registerData, HttpSession session) {
            UserDTO registered = userService.signUp(registerData.getLogin(), registerData.getPassword());
            session.setAttribute(SessionAttribute.USER, registered);
            session.setAttribute(SessionAttribute.CART, new ArrayList<BookDTO>());
        return "redirect:/menu";
    }

    @PostMapping("/do_login")
    public ModelAndView dologin(UserDTO loginData, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName(DEFAULT_REDIRECT);
            final UserDTO logedIn = userService.signIn(loginData.getLogin(), loginData.getPassword());
            session.setAttribute(SessionAttribute.USER, logedIn);
            session.setAttribute(SessionAttribute.CART, new ArrayList<BookDTO>());
        }catch (ServiceException e){
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @GetMapping("/logOut")
    public String logOut(HttpSession session){
        if (session != null) {
            UserDTO user = (UserDTO) session.getAttribute(SessionAttribute.USER);
            if (user != null) {
                session.invalidate();
            }
        }
        return "redirect:/menu";
    }
}
