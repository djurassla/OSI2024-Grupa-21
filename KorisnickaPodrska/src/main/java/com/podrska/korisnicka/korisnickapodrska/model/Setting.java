package com.podrska.korisnicka.korisnickapodrska.model;

import javafx.beans.property.SimpleStringProperty;

public class Setting {

    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty role;

    public Setting(String username, String firstName, String lastName, String role, String password) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.role = new SimpleStringProperty(role);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }


    public String getUsername() {
        return username.get();
    }


    public String getPassword() {
        return password.get();
    }


    public String getFirstName() {
        return firstName.get();
    }


    public String getLastName() {
        return lastName.get();
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty getUsernamePropery() {
        return username;
    }


    public SimpleStringProperty getFirstNamePropery() {
        return firstName;
    }


    public SimpleStringProperty getLastNamePropery() {
        return lastName;
    }

    public SimpleStringProperty getRolePropery() {
        return role;
    }
}
