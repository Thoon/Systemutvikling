
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
import springmvc.domene.SupplierChain;
import org.springframework.dao.DuplicateKeyException;
import springmvc.domene.SupplierChainPerson;
import springmvc.service.PersonService;
import springmvc.service.SupplierChainService;
//import org.springframework.dao.

@Controller
public class RegisterSupplierChainController {
    
    @Autowired
    private SupplierChainService scService;
    
    @Autowired
    private PersonService personService;
    
    //Sørger for å gi en feilside når feil oppstår, merk at vi godt kunne hatt
    //flere slike feilhåndterere og håndtert ulike feil mer spesifikt
    //Denne metoden håndterer og "SQL-exceptions" (bortsett fra DuplicateKeyException)
    //om vi bruker SupplierChainDatabaseJdbcTemplateRepositoryImpl som repository i stedet for 
    //SupplierChainDatabaseRepositoryImpl. Dette avgjøres i springmvc.konfig.Konfigurasjon.respoistory().
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
        //mav.addObject("melding", "To forhandlerkjeder kan ikke ha samme forhandlerkjeden-Id");
        mav.addObject("melding", "feilmelding.prim.nokkel");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
       
    @RequestMapping(value = "/registerSupplierChain" , method=RequestMethod.GET)
    public String supplierChain(@ModelAttribute SupplierChain supplierChain,
            @ModelAttribute("supplierChainPerson") SupplierChainPerson supplierChainPerson, 
            HttpSession session) {
        if (personService.getPermission(session.getAttribute("email").toString()) != 0) {
            return "index";
        }        
        return "registerSupplierChain";
    }

    @RequestMapping(value = "RegisterSupplierChain" , method=RequestMethod.POST)
    public String svarside(@Valid @ModelAttribute("supplier") SupplierChain supplierChain, BindingResult error, Model modell) {
        if (scService.registerSupplierChain(supplierChain)) {
            modell.addAttribute("melding","SupplierChain " + supplierChain.getName() + " er registrert");
            return "svarside";
        } else {
            modell.addAttribute("melding","feilmelding.reg.supplierChain");
            return "error";
        }
    }
    @RequestMapping(value = "registerSupplierChainPerson" , method=RequestMethod.POST)
    public String svarside(@Valid @ModelAttribute("supplierChainPerson") SupplierChainPerson suppChPers, BindingResult err, Model modell){
        if(scService.registerSupplierPerson(suppChPers)){
            modell.addAttribute("melding","SupplierPerson " + suppChPers.toString() + " er registrert");
            return "svarside";
        } else {
            modell.addAttribute("melding","feilmelding.reg.customer");//DENNE LINJEN ER ENDRET SIDEN VIDEO BLE LAGET
            return "error";
        }
        
    }    
}
