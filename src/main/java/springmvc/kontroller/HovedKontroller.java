package springmvc.kontroller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import springmvc.domene.Supplier;
import springmvc.domene.SupplierChain;
import springmvc.service.CustomerService;
import springmvc.service.GasMonitorService;
import springmvc.service.MonitorResultsService;
import springmvc.service.PersonService;
import springmvc.service.SupplierChainService;
import springmvc.service.SupplierService;
import springmvc.ui.CustomerFormBackingBean;
import springmvc.ui.GasMonitorFormBackingBean;
import springmvc.ui.MonitorResultsBackingBean;
import springmvc.ui.PersonFormBackingBean;
import springmvc.ui.SupplierChainFormBackingBean;
import springmvc.ui.SupplierFormBackingBean;

@Controller
public class HovedKontroller {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private GasMonitorService gasMonitorService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private SupplierService supplierService;
    
    @Autowired
    private SupplierChainService scService;
    
    @Autowired
    private MonitorResultsService mrService;
    
    // brukes for å gjøre om de valgte personene fra tekst til Person-objekt
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Person.class, new PersonEditor(personService));
        binder.registerCustomEditor(GasMonitor.class, new GasMonitorEditor(gasMonitorService));
        binder.registerCustomEditor(SupplierChain.class, new SupplierChainEditor(scService));
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
    
    @RequestMapping(value= "/*")
    public String redirect(){
        return "index";
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
        System.out.println("****************Start oversikt***********************");
           
        String deleteGasMonitors = request.getParameter("deleteGasMonitors");
      
            
        //Slett gassmonitorer valgt i checkbox'er
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
            
        // Oppdater (alle) monitorer valgt. Endringer gjort i tekstfelt.
        // Valg i checkbox'er er uten betydning her.
        } else { 
            if (error.hasErrors()){ //ikke oppdater grunnet valideringsfeil
                return "editGasMonitor";
            }  
            if (gasMonitorService.updateGasMonitors(backingBean.getAllGasMonitors())){
                backingBean.setAllGasMonitors(gasMonitorService.getAllGasMonitors());
                System.out.println("TEST2");
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
            
        // Oppdater (alle) kunder valgt. Endringer gjort i tekstfelt.
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
    

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String changepassword(ModelMap model, @ModelAttribute Person person, PersonFormBackingBean personBean, HttpSession session) {
        if (LoginController.checkLogin(session)) {
            return "settings";
        } else {
            model.addAttribute("melding", "Du ble logget av");
            return "login";
        }
    }
    
    @RequestMapping(value = "changepassword", method = RequestMethod.POST)
    public String endrePassord(@ModelAttribute("person") Person person, ModelMap model, HttpSession session) {
        if (LoginController.checkLogin(session)) {
        String pass2 = person.getPassword();
        String pass1 = person.getEmail();
        String epost = session.getAttribute("email").toString();

        if (pass1.length() < 8) {
            model.addAttribute("melding", "Passordet må minimum være 8 tegn langt");
            return "settings";
        }
        int letters = 0, numbers = 0, specialChars = 0;
        for (char c : pass1.toCharArray()) {
            if (c == ' ') {
                model.addAttribute("melding", "Ikke lov med mellomrom i passord");
                return "settings";
            } else if (Character.isUpperCase(c)) {
                ++letters;
            } else if (Character.isDigit(c)) {
                ++numbers;
            } else {
                ++specialChars;
            }

        }
        if (letters < 1 || numbers < 1 || specialChars < 1) {
            model.addAttribute("melding", "Passordet må minimum ha en store bokstav, ett spesialtegn og ett tall");
            return "settings";
        }

        if (pass1.equals(pass2) && !pass1.equals("")) {
            personService.changePassword(pass1, epost);
            model.addAttribute("melding", "Passordet ble endret");
            return "settings";
        } else {
            model.addAttribute("melding", "Passordene samsvarer ikke");
            return "settings";

        }
        } else {
            model.addAttribute("melding", "Du ble logget av");
            return "login";
        }

    }

    @RequestMapping(value = "/editSupplier")
    public String editSupplier(@Valid @ModelAttribute SupplierFormBackingBean backingBean, BindingResult error, Model modell, HttpServletRequest request) {
        System.out.println("****************Start oversikt***********************");
           
        String deleteSuppliers = request.getParameter("deleteSuppliers");
      
            
        //Slett forhandlere valgt i checkbox'er
        if (deleteSuppliers != null) { 
            List<Supplier> selectedSuppliers = backingBean.getSelectedSuppliers();
            
            System.out.println("*** slett forhandlers **** ");
            if (selectedSuppliers != null) {
                if (supplierService.deleteSuppliers(selectedSuppliers)){
                    backingBean.setEveryone(supplierService.getEveryone());//oppdaterer verdiene i backingBean
                    return "editSupplier";
                }else{ //feil ved sletting
                    modell.addAttribute("melding","feilside.slett");//feilside.slett er kode. Tekst hentes fra message.properties.
                    return "error";
                }
            }
            
        // Oppdater (alle) forhandlere valgt. Endringer gjort i tekstfelt.
        // Valg i checkbox'er er uten betydning her.
        } else { 
            if (error.hasErrors()){ //ikke oppdater grunnet valideringsfeil
                return "editSupplier";
            }
                    
            if (supplierService.updateSuppliers(backingBean.getEveryone())){
                backingBean.setEveryone(supplierService.getEveryone());
                System.out.println("TEST");
                return "editSupplier";
            }else{ //feil ved oppdatering
                modell.addAttribute("melding","feilside.oppdater");//feilside.oppdater er kode. Tekst hentes fra message.properties.
                return "error";
                
            }  
        }
        return "editSupplier";
    }

    @RequestMapping(value = "/editSupplierChain")
    public String editSupplierChain(@Valid @ModelAttribute SupplierChainFormBackingBean backingBean, BindingResult error, Model modell, HttpServletRequest request) {
        System.out.println("****************Start oversikt***********************");
           
        String deleteSupplierChains = request.getParameter("deleteSupplierChains");
      
            
        //Slett forhandlere valgt i checkbox'er
        if (deleteSupplierChains != null) { 
            List<SupplierChain> selectedSupplierChains = backingBean.getSelectedSupplierChains();
            
            System.out.println("*** slett forhandlerkjeder **** ");
            if (selectedSupplierChains != null) {
                if (scService.deleteSupplierChains(selectedSupplierChains)){
                    backingBean.setEveryone(scService.getEveryone());//oppdaterer verdiene i backingBean
                    return "editSupplierChain";
                }else{ //feil ved sletting
                    modell.addAttribute("melding","feilside.slett");//feilside.slett er kode. Tekst hentes fra message.properties.
                    return "error";
                }
            }
            
        // Oppdater (alle) forhandlerkjeder valgt. Endringer gjort i tekstfelt.
        // Valg i checkbox'er er uten betydning her.
        } else { 
            if (error.hasErrors()){ //ikke oppdater grunnet valideringsfeil
                return "editSupplierChain";
            }
                    
            if (scService.updateSupplierChains(backingBean.getEveryone())){
                backingBean.setEveryone(scService.getEveryone());
                System.out.println("TEST");
                return "editSupplierChain";
            }else{ //feil ved oppdatering
                modell.addAttribute("melding","feilside.oppdater");//feilside.oppdater er kode. Tekst hentes fra message.properties.
                return "error";
                
            }  
        }
        return "editSupplierChain";
    }
    
    @RequestMapping(value = "/myMonitors")
    public String myMonitors(@Valid @ModelAttribute MonitorResultsBackingBean backingBean, BindingResult error, Model modell, HttpServletRequest request, HttpSession s) {
        System.out.println("****************Start oversikt***********************");
        int userLevel = (int) s.getAttribute("permissions");
        System.out.println(userLevel);
        System.out.println("***Setter backingbeanverdier***");
        backingBean.setAllResults(mrService.getCalculatedResults(userLevel));
        return "myMonitors";
    }
}