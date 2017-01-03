package springmvc.kontroller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domene.Customer;
import springmvc.domene.GasMonitor;
import springmvc.domene.Person;
import springmvc.service.CustomerService;
import springmvc.service.GasMonitorService;
import springmvc.service.PersonService;
import springmvc.ui.CustomerFormBackingBean;
import springmvc.ui.GasMonitorFormBackingBean;
import springmvc.ui.PersonFormBackingBean;

@Controller
public class HovedKontroller {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private GasMonitorService gasMonitorService;
    
    @Autowired
    private CustomerService customerService;
    
    // brukes for å gjøre om de valgte personene fra tekst til Person-objekt
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Person.class, new PersonEditor(personService));
    }
    
    //Sørger for å gi en feilside når feil oppstår, merk at vi godt kunne hatt
    //flere slike feilhåndterere og håndtert ulike feil mer spesifikt
    //Denne metoden håndterer og "SQL-exceptions" om vi bruker 
    //PersonDatabaseJdbcTemplateRepositoryImpl som repository i stedet for 
    //PersonDatabaseRepositoryImpl. Dette avgjøres i springmvc.konfig.Konfigurasjon.respoistory().
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("melding", "feilmelding.generell");
        mav.addObject("unntak",exception);
        mav.setViewName("error");
        return mav;
    }
    
    @RequestMapping(value = "/manipulerPersoner")
    public String manipulerPersoner(@ModelAttribute PersonFormBackingBean backingBean) {
        backingBean.setEveryone(personService.getEveryone());
        System.out.println("** ControllerClass.person() ******");
        return "manipulerPersoner";
    }

    @RequestMapping(value = "/editPerson")
    public String editPerson(@Valid @ModelAttribute PersonFormBackingBean backingBean, BindingResult error, Model modell, HttpServletRequest request) {
        System.out.println("****************Start oversikt***********************");
           
        String deletePersons = request.getParameter("deletePersons");
      
            
        //Slett personer valgt i checkbox'er
        if (deletePersons != null) { 
            List<Person> selectedPersons = backingBean.getSelectedPersons();
            
            System.out.println("*** slett person **** ");
            if (selectedPersons != null) {
                if (personService.deletePersons(selectedPersons)){
                    backingBean.setEveryone(personService.getEveryone());//oppdaterer verdiene i backingBean
                    return "editPerson";
                }else{ //feil ved sletting
                    modell.addAttribute("melding","feilside.slett");//feilside.slett er kode. Tekst hentes fra message.properties.
                    return "error";
                }
            }
            
        // Oppdater (alle) personer valgt. Endringer gjort i tekstfelt.
        // Valg i checkbox'er er uten betydning her.
        } else { 
            if (error.hasErrors()){ //ikke oppdater grunnet valideringsfeil
                return "editPerson";
            }
                    
            if (personService.updatePersons(backingBean.getEveryone())){
                backingBean.setEveryone(personService.getEveryone());
                System.out.println("TEST");
                return "editPerson";
            }else{ //feil ved oppdatering
                modell.addAttribute("melding","feilside.oppdater");//feilside.oppdater er kode. Tekst hentes fra message.properties.
                return "error";
                
            }  
        }
        return "editPerson";
    }
    
    @RequestMapping(value = "/editGasMonitor")
    public String editGasMonitor(@Valid @ModelAttribute GasMonitorFormBackingBean backingBean, BindingResult error, Model modell, HttpServletRequest request) {
        System.out.println("****************Start oversikt2***********************");
           
        String deleteGasMonitors = request.getParameter("deleteGasMonitors");
      
            
        //Slett personer valgt i checkbox'er
        if (deleteGasMonitors != null) { 
            List<GasMonitor> selectGasMonitor = backingBean.getSelectedGasMonitors();
            
            System.out.println("*** slett gasmonitor **** ");
            if (selectGasMonitor != null) {
                if (gasMonitorService.deleteGasMonitors(selectGasMonitor)){
                    backingBean.setAllGasMonitors(gasMonitorService.getAllGasMonitors());//oppdaterer verdiene i backingBean
                    return "editGasMonitor";
                }else{ //feil ved sletting
                    modell.addAttribute("melding","feilside.slett");//feilside.slett er kode. Tekst hentes fra message.properties.
                    return "error";
                }
            }
            
        // Oppdater (alle) personer valgt. Endringer gjort i tekstfelt.
        // Valg i checkbox'er er uten betydning her.
        } else { 
            if (error.hasErrors()){ //ikke oppdater grunnet valideringsfeil
                return "editGasMonitor";
            }  
            if (gasMonitorService.updateGasMonitors(backingBean.getAllGasMonitors())){
                backingBean.setAllGasMonitors(gasMonitorService.getAllGasMonitors());
                System.out.println("TEST");
                return "editGasMonitor";
            }else{ //feil ved oppdatering
                modell.addAttribute("melding","feilside.oppdater");//feilside.oppdater er kode. Tekst hentes fra message.properties.
                return "error";
            }  
        }
        return "editGasMonitor";
    }
    
    @RequestMapping(value = "/editCustomer")
    public String editCustomer(@Valid @ModelAttribute CustomerFormBackingBean backingBean, BindingResult error, Model modell, HttpServletRequest request) {
        System.out.println("****************Start oversikt***********************");
           
        String deleteCustomers = request.getParameter("deleteCustomers");
      
            
        //Slett kunder valgt i checkbox'er
        if (deleteCustomers != null) { 
            List<Customer> selectedCustomers = backingBean.getSelectedCustomers();
            
            System.out.println("*** slett kunde **** ");
            if (selectedCustomers != null) {
                if (customerService.deleteCustomers(selectedCustomers)){
                    backingBean.setEveryone(customerService.getEveryone());//oppdaterer verdiene i backingBean
                    return "editCustomer";
                }else{ //feil ved sletting
                    modell.addAttribute("melding","feilside.slett");//feilside.slett er kode. Tekst hentes fra message.properties.
                    return "error";
                }
            }
            
        // Oppdater (alle) personer valgt. Endringer gjort i tekstfelt.
        // Valg i checkbox'er er uten betydning her.
        } else { 
            if (error.hasErrors()){ //ikke oppdater grunnet valideringsfeil
                return "editCustomer";
            }
                    
            if (customerService.updateCustomers(backingBean.getEveryone())){
                backingBean.setEveryone(customerService.getEveryone());
                System.out.println("TEST");
                return "editCustomer";
            }else{ //feil ved oppdatering
                modell.addAttribute("melding","feilside.oppdater");//feilside.oppdater er kode. Tekst hentes fra message.properties.
                return "error";
                
            }  
        }
        return "editCustomer";
    }

}