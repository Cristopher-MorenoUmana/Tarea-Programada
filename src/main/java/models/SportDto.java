package models;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

public class SportDto {
    
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty ballUrl;

    public SportDto(){
        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.ballUrl = new SimpleStringProperty();
    }
    
    public SportDto(Sport sport){
        
        this();
        this.id.setValue(sport.getSptId().toString());
        this.name.setValue(sport.getSptName());
        this.ballUrl.setValue(sport.getSptBallUrl());
    }

    public Integer getID() {
        if (id.get() == null || id.get().isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(id.get());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    public void setID(Integer id) {
        if (id == null) {
            this.id.set("");
        } else {
            this.id.set(id.toString());
        }
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBallUrl() {
        return ballUrl.get();
    }

    public void setBallUrl(String ballUrl) {
        this.ballUrl.set(ballUrl);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty ballUrlProperty() {
        return ballUrl;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.getID());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final SportDto other = (SportDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("SportDto{id=").append(this.id.get());
        sb.append(", nombre=").append(this.name.get());
        sb.append(", url del balon=").append(this.ballUrl.get());
        sb.append('}');
        return sb.toString();
    }
}
