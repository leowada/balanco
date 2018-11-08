package com.wada.balanco.login.controller;

import javax.validation.Valid;

import com.wada.balanco.login.service.UserService;
import com.wada.balanco.login.model.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/home", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UserCustom user = new UserCustom();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserCustom user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UserCustom userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Já existe um usuário cadastrado com o e-mail fornecido");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Usuário criado com sucesso");
            modelAndView.addObject("user", new UserCustom());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserCustom user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Bem vindo " + user.getNome() + " " + user.getSobrenome() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuários administradores");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }


}