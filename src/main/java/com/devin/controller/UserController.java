package com.devin.controller;


import com.devin.domain.Role;
import com.devin.domain.User;
import com.devin.service.RoleService;
import com.devin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/getUsers")
    public ModelAndView getUsers(){
        List<User> users = userService.findUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users",users);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/getRolesToAdd")
    public ModelAndView getRoles(ModelAndView modelAndView){
        List<Role> roles = roleService.findRoles();
        modelAndView.addObject("roles",roles);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }
    @RequestMapping("/saveUser")
    public String saveUser(User user,Long[] roleId){
        for(Long each:roleId){
            System.out.println(each);
        }
        userService.saveUser(user,roleId);
        return "redirect:/user/getUsers";
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(Long id){
        userService.deleteUser(id);
        return "redirect:/user/getUsers";
    }

}
