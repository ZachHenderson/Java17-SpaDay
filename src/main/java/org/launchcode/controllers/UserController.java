package org.launchcode.controllers;


import org.launchcode.data.UserData;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUser() {
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
        if (user.getPassword().equals(verify)) {
            UserData.add(user);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("allUsers", UserData.getAll());
            return "user/index";
        } else {
            model.addAttribute("error", "Passwords must match");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            return "user/add";
        }
    }

    @GetMapping("/detail")
    public String displayUserDetails(@RequestParam Integer userId, Model model) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/detail";
    }
}
