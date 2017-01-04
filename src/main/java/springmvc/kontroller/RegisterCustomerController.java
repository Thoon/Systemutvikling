/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.kontroller;

/**
 *
 * @author ganon
 */

import javax.servlet.http.HttpServletRequest;
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
import springmvc.domene.Customer;
import org.springframework.dao.DuplicateKeyException;
import springmvc.service.CustomerService;
//import org.springframework.dao.

@Controller
public class RegisterCustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    //Sørger for å gi en feilside når feil oppstår, merk at vi godt kunne hatt
    //flere slike feilhåndterere og håndtert ulike feil mer spesifikt
    //Denne metoden håndterer og "SQL-exceptions" (bortsett fra DuplicateKeyException)
    //om vi bruker CustomerDatabaseJdbcTemplateRepositoryImpl som repository i stedet for 
    //CustomerDatabaseRepositoryImpl. Dette avgjøres i springmvc.konfig.Konfigurasjon.respoistory().
    @ExceptionHandler({Exception.class})
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        System.out.println("Feil i RegisterCustomerController.handleError " + exception);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("melding", "feilmelding.generell");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
    
    //Håndterer "SQL"-unntaket DuplicateKeyException
    @ExceptionHandler({DuplicateKeyException.class})
    public ModelAndView handleDuplicateKey(HttpServletRequest req, Exception exception) {
        System.out.println("Feil i RegisterCustomerController.handleError " + exception);
        
        ModelAndView mav = new ModelAndView();
        //mav.addObject("melding", "To customerer kan ikke ha samme customernr");
        mav.addObject("melding", "feilmelding.prim.nokkel");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
       
    @RequestMapping(value = "/registerCustomer" , method=RequestMethod.GET)
    public String customer(@ModelAttribute Customer customer) {
        System.out.println(" ******   RegisterCustomer.controller.customer() ");
        return "registerCustomer";
    }

    @RequestMapping(value = "registerCustomer" , method=RequestMethod.POST)
    public String svarside(@Valid @ModelAttribute("customer") Customer customer, BindingResult error, Model modell) {
        
        if(error.hasErrors()){
            System.out.println(" Validering feilet **** ");
            //modell.addAttribute("melding", "Kunde-Id ikke fylt ut riktig"); // kun ibruk v return svarside
            return "registerCustomer";
        }
        
        System.out.println(" **** Customer verdi i RegisterCustomerController " + customer);
        
        if (customerService.registerCustomer(customer)) {
            modell.addAttribute("melding","Customer " + customer.toString() + " er registrert");
            return "svarside";
        } else {
            modell.addAttribute("melding","feilmelding.reg.customer");//DENNE LINJEN ER ENDRET SIDEN VIDEO BLE LAGET
            return "error";
        }
    }
}
