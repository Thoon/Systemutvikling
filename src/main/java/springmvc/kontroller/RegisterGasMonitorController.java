
package springmvc.kontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import springmvc.service.GasMonitorService;
import springmvc.service.PersonService;

@Controller
public class RegisterGasMonitorController {
    
    @Autowired
    private GasMonitorService gasMonitorService;
    
    @Autowired
    private PersonService personService;
    
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(GasMonitor.class, new GasMonitorEditor(gasMonitorService));
    }
    
    @ExceptionHandler({Exception.class})
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("melding", "feilmelding.generell");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
    
    @ExceptionHandler({DuplicateKeyException.class})
    public ModelAndView handleDuplicateKey(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("melding", "feilmelding.prim.nokkel");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
    
    @RequestMapping(value = "/registerGasMonitor" , method=RequestMethod.GET)
    public String gasMonitor(@ModelAttribute GasMonitor gasMonitor, HttpSession session) {
        if (personService.getPermission(session.getAttribute("email").toString()) <= 2) {
            return "registerGasMonitor";
        }else{
            return "index";
        }
    }
    
    
    @RequestMapping(value = "RegisterGasMonitor" , method=RequestMethod.POST)
    public String svarside(@Valid @ModelAttribute("gasMonitor") GasMonitor gasMonitor, BindingResult error, Model modell) {
        if(error.hasErrors()){
            return "registerGasMonitor";
        }
        
        if (gasMonitorService.registerGasMonitor(gasMonitor)) {
            modell.addAttribute("melding","GasMonitor" + gasMonitor + " er registrert");
            return "svarside";
        } else {
            modell.addAttribute("melding","feilmelding.reg.gasmonitor");
            return "error";
        }
    }
    
}
