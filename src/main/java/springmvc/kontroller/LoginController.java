/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.kontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.domene.Person;
import springmvc.service.PersonService;

@Controller
@Scope("session")
public class LoginController {
    
    @Autowired
    private PersonService personService;
    
    
    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String person(@ModelAttribute Person person, HttpSession session) {
        if (checkLogin(session)) {
            return "index";
        } else {
            return "login";
        }
    }
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String svarside(@Valid @ModelAttribute("person") Person person, BindingResult error, Model model, HttpSession session){
        int returnValue = personService.checkLogin(person);
        if(returnValue == 5){
            Person pers = personService.getPerson(person.getEmail());
            session.setAttribute("email", pers.getEmail());
            session.setAttribute("permissions", pers.getPermission());
            return "index";
        }
        switch (returnValue) {
            case 0:
                model.addAttribute("melding", "Epost- og passordfelt er tomme.");
                break;
            case 1:
                model.addAttribute("melding", "Epostfelt er tomt.");
                break;
            case 2:
                model.addAttribute("melding", "Passordfelt er tomt.");
                break;
            case 3:
                model.addAttribute("melding", "Passord samsvarer ikke med bruker.");
                break;
            case 4:
                model.addAttribute("melding", "Epost er ikke registret.");
                break;
            default:
                break;
        }
        return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(@ModelAttribute Person person, HttpSession session){
        session.removeAttribute("email");
        session.removeAttribute("permission");
        return "login";
    }
    
    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String newUser(@ModelAttribute Person person){
        return "newuser";
    }
    
    @RequestMapping(value = "newuser", method = RequestMethod.POST)
    public String checkNewUser(@Valid @ModelAttribute("person") Person person, BindingResult error, Model model) {
        int returnValue = personService.checkNewUser(person);

        if (returnValue == 3) {
            System.out.println("3");
            model.addAttribute("melding", "Epost er sendt");
            return "login";
        }
        switch (returnValue) {
            case 0:
                System.out.println("0");
                model.addAttribute("melding", "E-post er ikke fylt ut.");
                break;
            case 1:
                System.out.println("1");
                model.addAttribute("melding", "Epost eksisterer ikke.");
                break;
            case 2:
                System.out.println("2");
                model.addAttribute("melding", "Epost er sendt");
                return "login";
            default:
                break;
        }
        return "newuser";
    }
    
    public static boolean checkLogin(HttpSession session){
        return session.getAttribute("email") != null;
    }
}