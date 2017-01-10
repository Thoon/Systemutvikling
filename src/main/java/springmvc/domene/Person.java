package springmvc.domene;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Creates object of type Person
 * @author ntnu
 */
public class Person {
    
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String password;
    @Email
    private String email;
    @Min(8)
    private int phoneNumber;
    private int permission;
    private boolean isActive;
    
    public Person(String firstName, String lastName, String password, String email, int phoneNumber, int permission, boolean isActive) {
        this.firstName = firstName.trim().toUpperCase();
        this.lastName = lastName.trim().toUpperCase();
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.permission = permission;
        this.isActive = isActive;
    }
    
    public Person(){}
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String sn) {
        this.lastName = sn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int pn) {
        this.phoneNumber = pn;
    }
    
    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
    
    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public String getFullname(){
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", permission=" + permission + '}';
    }
}