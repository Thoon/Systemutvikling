package springmvc.kontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domene.Person;
import org.springframework.dao.DuplicateKeyException;
import springmvc.service.PersonService;
//import org.springframework.dao.

@Controller
public class RegisterPersonController {
    
    @Autowired
    private PersonService personService;
    
    //Sørger for å gi en feilside når feil oppstår, merk at vi godt kunne hatt
    //flere slike feilhåndterere og håndtert ulike feil mer spesifikt
    //Denne metoden håndterer og "SQL-exceptions" (bortsett fra DuplicateKeyException)
    //om vi bruker PersonDatabaseJdbcTemplateRepositoryImpl som repository i stedet for 
    //PersonDatabaseRepositoryImpl. Dette avgjøres i springmvc.konfig.Konfigurasjon.respoistory().
    @ExceptionHandler({Exception.class})
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("melding", "feilmelding.generell");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
    
    //Håndterer "SQL"-unntaket DuplicateKeyException
    @ExceptionHandler({DuplicateKeyException.class})
    public ModelAndView handleDuplicateKey(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        //mav.addObject("melding", "To personer kan ikke ha samme personnr");
        mav.addObject("melding", "feilmelding.prim.nokkel");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
       
    @RequestMapping(value = "/registerPerson" , method=RequestMethod.GET)
    public String person(@ModelAttribute Person person, HttpSession session) {
         if (personService.getPermission(session.getAttribute("email").toString()) != 0) {
            return "index";
        }
        return "registerPerson";
    }

    @RequestMapping(value = "registerPerson" , method=RequestMethod.POST)
    public String svarside(@Valid @ModelAttribute("person") Person person, BindingResult error, Model modell) {
        
        if(error.hasErrors()){
            return "registerPerson";
        }
        
        if (personService.registerPerson(person)) {
            modell.addAttribute("melding","Person " + person.toString() + " er registrert");
            return "svarside";
        } else {
            modell.addAttribute("melding","feilmelding.reg.person");
            return "error";
        }
    }
}