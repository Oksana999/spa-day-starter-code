package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
       model.addAttribute("title", "Add User");
        model.addAttribute(new User());
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute  @Valid User newUser , Errors errors, Model model) {

      User user = new User();
      user.setPassword(newUser.getPassword());
      user.setVerifyPassword(newUser.getVerifyPassword());
       if (errors.hasErrors()) {
          model.addAttribute("title", "Add User");
          return "user/add";
       }

          return "user/index";

    }
}

