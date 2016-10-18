
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

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springmvc.respository.PersonDatabaseJdbcTemplateRepositoryImpl;
import springmvc.respository.PersonRepository;
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
        String url = "jdbc:mysql://smartcylinders.com.mysql";
        String username = "smartcylinders_com";
        String password = "y5ifZnQ8";
        BasicDataSource dmds = new BasicDataSource();
        dmds.setUrl(url);
        dmds.setUsername(username);
        dmds.setPassword(password);
        //DriverManagerDataSource dmds = new DriverManagerDataSource(url, username, password);
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        try{
            Connection con = dmds.getConnection();
            System.out.println(" *********  Konfig " + con );
            //getAllePersoner(con); //brukes for testing av oppkobling
        }catch(Exception e){
            System.out.println(" Konfig.Feil ved henting av connection() " + e);
        }
        return dmds;
    }
    
    @Bean 
    public PersonRepository repository(){
        return new PersonDatabaseJdbcTemplateRepositoryImpl();
        //return new PersonDatabaseRepositoryImpl();
    } 
    
    @Bean
    public PersonService personService(){
        return new PersonServiceImpl();
    }
    
//    public ArrayList<Person> getAllePersoner(Connection forbindelse) {
//         String sqlSelectAllePersoner = "Select * from person";
//         System.out.println("*******************   Konfig.getAllePersoner()"   );
//         PreparedStatement psSelectAlle = null;
//         ResultSet res;
//         ArrayList<Person> personListe = null;
//         try {
//             psSelectAlle = forbindelse.prepareStatement(sqlSelectAllePersoner);
//             res = psSelectAlle.executeQuery();
//             while (res.next()) {
//                 Person p = new Person(res.getString("personnr"), res.getString("fornavn"), res.getString("etternavn"));
//                 if (personListe == null) {
//                     personListe = new ArrayList<Person>();
//                 }
//                 personListe.add(p);
//                 System.out.println(" Konfig: Person er " +p );
//             }
//         } catch (SQLException e) {
//             Opprydder.rullTilbake(forbindelse);
//             Opprydder.skrivMelding(e, "Konfig.getAllePersoner()");
//         } catch (Exception e) {
//             Opprydder.skrivMelding(e, "Konfig.getAllePersoner - ikke sqlfeil");
//         } finally {
//             Opprydder.settAutoCommit(forbindelse);
//             Opprydder.lukkSetning(psSelectAlle);
//         }
//         try{
//         forbindelse.close();
//         }catch(Exception e){
//             System.out.println("Konfig. Feil i lukk" + e);
//         }
//         return personListe;
//     }
}
