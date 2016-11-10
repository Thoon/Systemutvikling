
package springmvc.konfig;

import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import springmvc.respository.GasMonitorRepository;
import springmvc.respository.GasMonitorRepositoryImpl;


import springmvc.respository.PersonDatabaseJdbcTemplateRepositoryImpl;
import springmvc.respository.PersonRepository;
import springmvc.service.GasMonitorService;
import springmvc.service.GasMonitorServiceImpl;
import springmvc.service.PersonService;
import springmvc.service.PersonServiceImpl;

@Configuration
@EnableWebMvc  // mvc annotation
@ComponentScan(basePackages = {"springmvc.kontroller"}) // pakken der controllerne ligger
public class Konfigurasjon extends WebMvcConfigurationSupport {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        return new TilesConfigurer();
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        //tilesViewResolver.setOrder(0);
        return tilesViewResolver;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("/WEB-INF/messages");
        return source;
    }

    // equivalents for <mvc:resources/> tags
    // Hvor finnes statisk ressurser som bilder/ css/ js osv.
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
    }

    @Override
    @Bean
    public HandlerMapping resourceHandlerMapping() {
        AbstractHandlerMapping handlerMapping = (AbstractHandlerMapping) super.resourceHandlerMapping();
        handlerMapping.setOrder(-1);
        return handlerMapping;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    public DataSource dataSource(){
        String url = "jdbc:mysql://mysql.stud.iie.ntnu.no/g_tdat3022b";
        String username = "g_tdat3022b";
        String password = "Gs0qzpT8";
        BasicDataSource dmds = new BasicDataSource();
        dmds.setUrl(url);
        dmds.setUsername(username);
        dmds.setPassword(password);
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        try{
            Connection con = dmds.getConnection();
            System.out.println(" *********  Konfig " + con );
        }catch(Exception e){
            System.out.println(" Konfig.Feil ved henting av connection() " + e);
        }
        return dmds;
    }
    
    @Bean 
    public PersonRepository repository(){
        return new PersonDatabaseJdbcTemplateRepositoryImpl();
    } 
    
    @Bean
    public PersonService personService(){
        return new PersonServiceImpl();
    }
    
    @Bean
    public GasMonitorService gasMonitorService(){
        return new GasMonitorServiceImpl();
    }
    
    @Bean
    public GasMonitorRepository gasMonitorRepository(){
        return new GasMonitorRepositoryImpl();
    }    
}
