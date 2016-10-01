package models;

import java.util.List;

/**
 * Created by Daniel Filho on 9/23/16.
 */

public class User {

    private String _id;
    private Role role;
    private String pass;
    private String username;
    private String email;
    private List<String> phoneMacs;
    private String name;

    public User(String _id, Role role, String pass, String username, String email) {
        this._id = _id;
        this.role = role;
        this.pass = pass;
        this.username = username;
        this.email = email;
    }

    public User(String name, String _id, Role role, String pass, String username, String email, List<String> phoneMacs) {
        this.name = name;
        this._id = _id;
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

    public List<String> getPhoneMacs() {
        return phoneMacs;
    }

    public void setPhoneMacs(List<String> phoneMacs) {
        this.phoneMacs = phoneMacs;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
