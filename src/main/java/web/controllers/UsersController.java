package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDaoImpl;
import web.dao.UserDaoImpl;
import web.model.Role;
import web.model.User;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UsersController {

    private final UserDaoImpl userDaoImpl;
    private final RoleDaoImpl roleDaoImpl;

    @Autowired
    public UsersController(UserDaoImpl userDaoImpl, RoleDaoImpl roleDaoImpl) {

        this.userDaoImpl = userDaoImpl;
        this.roleDaoImpl = roleDaoImpl;
    }


    @GetMapping("/admin")
    public String index(@ModelAttribute("newUser") User user,Model model,Principal principal){
        String principalName = principal.getName();
//        User adminUser = new User("admin2",34,"admin2","admin","admin",roleDaoImpl.getByNameRoles("ROLE_ADMIN"));
//        User userUser = new User("admin2",34,"admin2","admin","admin",roleDaoImpl.getByNameRoles("ROLE_USER"));
//        userDaoImpl.save(adminUser);
//        userDaoImpl.save(userUser);
        model.addAttribute("userCurrent",userDaoImpl.getUserByName(principalName));
        model.addAttribute("users",userDaoImpl.index());
        model.addAttribute("role",roleDaoImpl.getAllRoles());
        return "index";
    }

    @PostMapping("/adduser")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("role") ArrayList<Long> role){
        user.setRoles(roleDaoImpl.getByIdRoles(role));
        userDaoImpl.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(name = "role") ArrayList<Long> role) {
//        user.setRoles(role.stream().map((r2) -> roleDaoImpl.getByIdRoles(r2)).collect(Collectors.toSet()));
        user.setRoles(roleDaoImpl.getByIdRoles(role));
        userDaoImpl.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userDaoImpl.delete(id);
        return "redirect:/admin";
    }
    @GetMapping("/user")
    public String showUser(Principal principal, Model model){
        String principalName = principal.getName();
        model.addAttribute("userCurrent",userDaoImpl.getUserByName(principalName));
        model.addAttribute("users",userDaoImpl.index());
        model.addAttribute("role",roleDaoImpl.getAllRoles());
        return "show_user";
    }
}

