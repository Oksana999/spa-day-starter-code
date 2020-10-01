package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Oksana
 */
@Controller
@RequestMapping("user")
public class UserController {

   @GetMapping("add")
   public String displayAddUserForm(){
      return "user/add";
   }

   @PostMapping("add")
   public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
      if (user.getPassword().equals(verify)) {
         UserData.addUser(user);
         model.addAttribute("user", user);
         model.addAttribute("users", UserData.getAll());
         return "user/index";
      } else {
         model.addAttribute("userName", user.getUsername());
         model.addAttribute("userEmail", user.getEmail());
         model.addAttribute("error", "Passwords should match");
         return "user/add";
      }
   }

   @GetMapping("userInfo/{id}")
   public String userPage(Model model, @PathVariable int id){
     User user = UserData.getById(id);
     model.addAttribute("user", user);
      return "user/userInfo";
   }


   @PostMapping("userInfo")
   public String getUserInfo(int id, Model model){
      User user = UserData.getById(id);
      model.addAttribute("user", user);
      return "redirect:";
   }

}
