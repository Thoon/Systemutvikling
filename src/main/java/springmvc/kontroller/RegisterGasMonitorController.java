
package springmvc.kontroller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domene.GasMonitor;
import springmvc.service.GasMonitorService;

@Controller
public class RegisterGasMonitorController {
    
    @Autowired
    private GasMonitorService gasMonitorService;
    
    @ExceptionHandler({Exception.class})
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        System.out.println("Feil i RegisterPersonController.handleError " + exception);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("melding", "feilmelding.generell");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
    
    @ExceptionHandler({DuplicateKeyException.class})
    public ModelAndView handleDuplicateKey(HttpServletRequest req, Exception exception) {
        System.out.println("Feil i RegisterGasMonitorController.handleError " + exception);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("melding", "feilmelding.prim.nokkel");
        mav.addObject("unntak", exception);
        mav.setViewName("error");
        return mav;
    }
    
    @RequestMapping(value = "RegisterGasMonitor" , method=RequestMethod.GET)
    public String person(@ModelAttribute GasMonitor gasMonitor) {
        System.out.println(" ******   GasMonitor.controller.person() ");
        return "RegisterGasMonitor";
    }
    
}
