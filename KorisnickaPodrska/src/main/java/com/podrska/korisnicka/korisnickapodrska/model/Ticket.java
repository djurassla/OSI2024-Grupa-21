package com.podrska.korisnicka.korisnickapodrska.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ticket {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty description;
    private final SimpleStringProperty status;
    private final SimpleStringProperty createdBy;

    private final SimpleStringProperty updatedBy;

    private final SimpleStringProperty assigned;


    public Ticket(int id, String description, String status, String createdBy,String updatedBy,String assigned) {
        this.id = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
        this.status = new SimpleStringProperty(status);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.updatedBy = new SimpleStringProperty(updatedBy);
        this.assigned = new SimpleStringProperty(assigned);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public String getCreatedBy() {
        return createdBy.get();
    }

    public SimpleStringProperty createdByProperty() {
        return createdBy;
    }


    public String getUpdatedBy() {
        return updatedBy.get();
    }

    public SimpleStringProperty updatedByProperty() {
        return updatedBy;
    }

    public String getAssigned() {
        return assigned.get();
    }

    public SimpleStringProperty assignedProperty() {
        return assigned;
    }
}
