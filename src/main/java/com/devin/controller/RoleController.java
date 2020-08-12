package com.devin.controller;

import com.devin.domain.Role;
import com.devin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService service;

    @RequestMapping("/getRoles")
    public ModelAndView getRoles(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = service.findRoles();
        modelAndView.addObject("roles",roles);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }
    @RequestMapping("/addRole") //不能使用ajax请求，因为ajax请求后转发和重定向失效
    public String addRole(Role role){
        service.addRole(role);
        return "redirect:/role/getRoles";
    }
    @RequestMapping("/deleteRole")
    public String deleteRole(Role role){
        service.deleteRole(role);
        return "forward:/role/getRoles";  //由于是ajax请求的所以不会真的重定向，可以在ajax回调函数中使用location实现重定向
    }

}
