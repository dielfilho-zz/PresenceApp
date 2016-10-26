package models;

/**
 * Created by Daniel Filho on 9/23/16.
 */

public class User {

    private String id;
    private Role role;
    private String pass;
    private String username;
    private String email;
    private String phoneMacs;
    private String name;

    public User(String id, Role role, String pass, String username, String email) {
        this.id = id;
        this.role = role;
        this.pass = pass;
        this.username = username;
        this.email = email;
    }

    public User(String name, String id, Role role, String pass, String username, String email, String phoneMacs) {
        this.name = name;
        this.id = id;
        this.role = role;
        this.pass = pass;
        this.username = username;
        this.email = email;
        this.phoneMacs = phoneMacs;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneMac() {
        return phoneMacs;
    }

    public void setPhoneMac(String phoneMac) {
        this.phoneMacs = phoneMacs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
}
