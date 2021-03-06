
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
import springmvc.domene.Supplier;
import org.springframework.dao.DuplicateKeyException;
import springmvc.domene.SupplierPerson;
import springmvc.service.PersonService;
import springmvc.service.SupplierService;
//import org.springframework.dao.

@Controller
public class RegisterSupplierController {
    
    @Autowired
    private SupplierService supplierService;
    
    @Autowired
    private PersonService personService;
    
    //Sørger for å gi en feilside når feil oppstår, merk at vi godt kunne hatt
    //flere slike feilhåndterere og håndtert ulike feil mer spesifikt
    //Denne metoden håndterer og "SQL-exceptions" (bortsett fra DuplicateKeyException)
    //om vi bruker SupplierDatabaseJdbcTemplateRepositoryImpl som repository i stedet for 
    //SupplierDatabaseRepositoryImpl. Dette avgjøres i springmvc.konfig.Konfigurasjon.respoistory().
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
        mav.addObject("melding", "feilmelding.prim.nokkel");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
       
    @RequestMapping(value = "/registerSupplier" , method=RequestMethod.GET)
    public String supplier(@ModelAttribute Supplier supplier, 
            @ModelAttribute("supplierPerson") SupplierPerson supplierPerson, HttpSession session) {
        if (personService.getPermission(session.getAttribute("email").toString()) != 0) {
            return "index";
        }
        return "registerSupplier";
    }

    @RequestMapping(value = "RegisterSupplier" , method=RequestMethod.POST)
    public String svarside(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult error, Model modell) {
        
        if (supplierService.registerSupplier(supplier)) {
            modell.addAttribute("melding","Supplier " + supplier.getSupplierName() + " er registrert");
            return "svarside";
        } else {
            modell.addAttribute("melding","feilmelding.reg.supplier");//DENNE LINJEN ER ENDRET SIDEN VIDEO BLE LAGET
            return "error";
        }
    }
    
    
    @RequestMapping(value = "registerSupplierPerson" , method=RequestMethod.POST)
    public String svarside(@Valid @ModelAttribute("supplierPerson") SupplierPerson suppPers, BindingResult err, Model modell){
        if(supplierService.registerSupplierPerson(suppPers)){
            modell.addAttribute("melding","SupplierPerson " + suppPers.toString() + " er registrert");
            return "svarside";
        } else {
            modell.addAttribute("melding","feilmelding.reg.customer");//DENNE LINJEN ER ENDRET SIDEN VIDEO BLE LAGET
            return "error";
        }
        
    }
}
