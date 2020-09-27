package org.launchcode.spaday.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class SpaDayController {

   // private static String name;

    public boolean checkSkinType(String skinType, String facialType) {
        if (skinType.equals("oily")) {
            if (facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating")) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (skinType.equals("combination")) {
            if (facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating") || facialType.equals("Enzyme Peel")) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (skinType.equals("normal")) {
            return true;
        }
        else if (skinType.equals("dry")) {
            if (facialType.equals("Rejuvenating") || facialType.equals("Hydrofacial")) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }

    @GetMapping(value="")
    @ResponseBody
    public String customerForm () {
        String html = "<form method = 'post' action=''>" +
                "Name: <br>" +
                "<input type = 'text' name = 'name'>" +
                "<br>Skin type: <br>" +
                "<select name = 'skintype'>" +
                "<option value = 'oily'>Oily</option>" +
                "<option value = 'combination'>Combination</option>" +
                "<option value = 'normal'>Normal</option>" +
                "<option value = 'dry'>Dry</option>" +
                "</select><br>" +
                "Manicure or Pedicure? <br>" +
                "<select name='manipedi' id='manipedi' multiple>" +
                "<option value = 'manicure'>Manicure</option>" +
                "<option value = 'pedicure'>Pedicure</option>" +
                "</select><br>" +
                "<input type = 'submit' value = 'Submit'>" +
                "</form>" +
        "<p>Hold down the Ctrl (windows) or Command (Mac) button to select multiple options.</p>";
        return html;
    }

    @PostMapping(value="")
    public String spaMenu(@RequestParam String name, @RequestParam String skintype, @RequestParam String[] manipedi, HttpSession session) {

        ArrayList<String> facials = new ArrayList<>();
        facials.add("Microdermabrasion");
        facials.add("Hydrofacial");
        facials.add("Rejuvenating");
        facials.add("Enzyme Peel");

        ArrayList<String> appropriateFacials = new ArrayList<String>();
        for (int i = 0; i < facials.size(); i ++) {
            if (checkSkinType(skintype,facials.get(i))) {
                appropriateFacials.add(facials.get(i));
            }
        }

        session.setAttribute("name", name);
        session.setAttribute("skintype", skintype);
        session.setAttribute("manipedi", manipedi);

        return "redirect:menu";
    }

    @GetMapping(value="/menu")
    public String renderMenu(HttpSession session, Model model){
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("skintype", session.getAttribute("skintype"));
        model.addAttribute("manipedi", session.getAttribute("manipedi"));

        model.addAttribute("appropriateFacials", session.getAttribute("appropriateFacials"));
        return "menu";
    }

//    @GetMapping("/p1")
//    public String p1(Model model){
//        model.addAttribute("msg", "MSG_1");
//        return "p1";
//    }
//
//    @PostMapping("/p1")
//    public String p1(@RequestParam String name, HttpSession session){
//        session.setAttribute("name", name);
//        return "redirect:p2";
//    }
//
//    @GetMapping("/p2")
//    public ModelAndView p2(HttpSession session){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("p2");
//        mv.addObject("name", session.getAttribute("name"));
//        return mv;
//    }
}
