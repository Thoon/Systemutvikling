package springmvc.domene;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {
    
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String surname;
    @Pattern(regexp = "^(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[^a-zA-ZæøåÆØÅ0-9 ].*[^a-zA-ZæøåÆØÅ0-9 ])(?!\\s)\\S{8,}",
            message = "Passordet må inneholde 8 tegn bestående av store og små bokstaver, og minst 2 spesialtegn")
    @NotEmpty
    private String password;
    @Email
    private String email;
    @Min(8)
    private int phoneNumber;
    private String permission;
    private boolean isActive;
    
    public Person(String firstName, String surname, String password, String email, int phoneNumber, String permission, boolean isActive) {
        this.firstName = firstName.trim().toUpperCase();
        this.surname = surname.trim().toUpperCase();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String sn) {
        this.surname = sn;
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
    
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}