package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDaoImpl;
import web.dao.UserDaoImpl;
import web.model.Role;
import web.model.User;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RestUsersController {

    private final UserDaoImpl userDaoImpl;
    private final RoleDaoImpl roleDaoImpl;

    @Autowired
    public RestUsersController(UserDaoImpl userDaoImpl, RoleDaoImpl roleDaoImpl) {

        this.userDaoImpl = userDaoImpl;
        this.roleDaoImpl = roleDaoImpl;
    }

    @GetMapping("/admin")
    public List<User> index() {
        List<User> indexUser = userDaoImpl.index();
        return indexUser;
    }

    @GetMapping("/admin/currentuser")
    public User indexCurrentUser(Principal principal){
        String nameCurrentUser = principal.getName();
        User indexCurUser = userDaoImpl.getUserByName(nameCurrentUser);
        return indexCurUser;
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> show(@PathVariable("id") Long id) {
        User user = userDaoImpl.show(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
    @PostMapping("admin")
    public String addUser(@RequestBody User user){
        System.out.println(user);
        userDaoImpl.save(user);
        return null;
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        userDaoImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("admin")
    public String updateUser(@RequestBody User user){
        userDaoImpl.update(user);
        return null;
    }
}

