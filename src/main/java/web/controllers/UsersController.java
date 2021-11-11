package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDaoImpl;
import web.dao.UserDaoImpl;
import web.model.User;

import java.security.Principal;
import java.util.ArrayList;

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
    public String index(Model model){
        model.addAttribute("users",userDaoImpl.index());
        return "index";
    }

    @GetMapping("/admin/{id}")
    public String show(@PathVariable("id") Long id, Model model, Principal principal){
        model.addAttribute("users",userDaoImpl.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model){
        model.addAttribute("role",roleDaoImpl.getAllRoles());
        return "new";
    }

    @PostMapping("/adduser")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("role") ArrayList<Long> role){
        user.setRoles(roleDaoImpl.getByIdRoles(role));
        userDaoImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("users",userDaoImpl.show(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("role") ArrayList<Long> role,
                         @PathVariable("id") Long id){
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
        model.addAttribute("users", userDaoImpl.getUserByName(principalName));
        return "show_user";
    }
}

