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
import springmvc.domene.GasMonitor;
import springmvc.domene.Person;
import springmvc.service.GasMonitorService;
import springmvc.service.PersonService;
import springmvc.ui.GasMonitorFormBackingBean;
import springmvc.ui.PersonFormBackingBean;

@Controller
public class HovedKontroller {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private GasMonitorService gasMonitorService;
    
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
    public String endrePersoner(@Valid @ModelAttribute PersonFormBackingBean backingBean, BindingResult error, Model modell, HttpServletRequest request) {
        System.out.println("****************Start oversikt***********************");
           
        String slettPersoner = request.getParameter("slettPersoner");
        String hentPersoner = request.getParameter("hentPersoner");
               
        //Hent personer valgt i checkbox'er
        if (hentPersoner != null) { 
            if (backingBean.getSelectedPersons() != null && backingBean.getSelectedPersons().size() > 0) {
                return "utskrift";
            }else { //ingen valgt
                return "editPerson";
            }
            
        //Slett personer valgt i checkbox'er
        } else if (slettPersoner != null) { 
            List<Person> valgtePersoner = backingBean.getSelectedPersons();
            
            System.out.println("*** slett person **** ");
            if (valgtePersoner != null) {
                if (personService.deletePersons(valgtePersoner)){
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
                return "editPerson";
            }else{ //feil ved oppdatering
                modell.addAttribute("melding","feilside.oppdater");//feilside.oppdater er kode. Tekst hentes fra message.properties.
                return "error";
            }  
        }
        return "editPerson";
    }
    
    @RequestMapping(value = "/editGasMonitor", method = RequestMethod.POST)
    public String sokResultat(@ModelAttribute("gasMonitor") GasMonitor gasM, GasMonitorFormBackingBean gasMonitor){
        gasMonitor.getAllGasMonitors();
        return "editGasMonitor";
    }
}