package models;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TeamDto {
    
    private SimpleStringProperty id;
    private SimpleStringProperty logoUrl;
    private SimpleStringProperty name;
    private ObjectProperty<Sport> sport;
    
    public TeamDto(){
        
        this.id = new SimpleStringProperty();
        this.logoUrl = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.sport = new SimpleObjectProperty<>();
    }
    
    public TeamDto(Team team){
        
        this();
        this.id.setValue(team.getTeamId().toString());
        this.logoUrl.setValue(team.getTeamLogoUrl());
        this.name.setValue(team.getTeamName());
        this.sport.setValue(team.getTeamSptId());
    }
    
    public Sport getSport(){
        return this.sport.get();
    }
    
    public ObjectProperty<Sport> sportProperty(){
        return this.sport;
    }
    
    public void setSport(Sport pSport){
        this.sport.set(pSport);
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
            this.id.set(String.valueOf(id));
        }
    }

    public String getLogoUrl() {
        return logoUrl.get();
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl.set(logoUrl);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty logoUrlProperty() {
        return logoUrl;
    }

    public SimpleStringProperty nameProperty() {
        return name;
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

        final TeamDto other = (TeamDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("TeamDto{id=").append(this.id.get());
        sb.append(", nombre=").append(this.name.get());
        sb.append(", url del Logo=").append(this.logoUrl.get());
        sb.append('}');
        return sb.toString();
    }
}