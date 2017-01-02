/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.kontroller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.domene.Person;
import springmvc.service.PersonService;

@Controller
@Scope("session")
public class loginController {
    
    @Autowired
    private PersonService personService;
    
    
    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String person(@ModelAttribute Person person, HttpSession session) {
        if (checkLogin(session)) {
            return "hovedside";
        } else {
            return "login";
        }
    }
    
    public static boolean checkLogin(HttpSession session){
        return session.getAttribute("email") != null;
    }
}
