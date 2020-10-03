package org.launchcode.spaday.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class User {
    @NotBlank(message = "Please, enter user name")
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
    private String username;

    @Email(message = "Invalid email. Try again.")
    private String email;

    @NotBlank(message = "Please, enter your password")
    @Size(min=6, message = "Password must be at least 6 characters long.")
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    public User() {

    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword(this.password, this.verifyPassword);

    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword(this.password, this.verifyPassword);

    }

    private void checkPassword(String password, String verifyPassword){
        if(password != null && (verifyPassword != null) && (!password.equals(verifyPassword))){
            setVerifyPassword(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassword());
    }
}
