package springmvc.domene;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {
    
    @NotEmpty
    private Integer personId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String username;
    @Pattern(regexp = "^(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[^a-zA-ZæøåÆØÅ0-9 ].*[^a-zA-ZæøåÆØÅ0-9 ])(?!\\s)\\S{8,}",
            message = "Passordet må inneholde 8 tegn bestående av store og små bokstaver, og minst 2 spesialtegn")
    private String password;
    @Email
    private String email;
    private int phoneNumber;
    

    public Person(Integer personId, String firstName, String surname, String password, String email, int phoneNumber) {
        this.personId = personId;
        this.firstName = firstName.trim().toUpperCase();
        this.surname = surname.trim().toUpperCase();
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public Person(){}
    
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer pId) {
        this.personId = pId;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String un) {
        this.username = un;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) {
            return false;
        } else {
            Person p = (Person) obj;
            if (this.personId.equals(p.getPersonId())) {
                return true;
            } else {
                return false;
            }
        }
    }
}