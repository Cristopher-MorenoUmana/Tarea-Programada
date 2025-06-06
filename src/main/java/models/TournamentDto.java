package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TournamentDto {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty teamsQuantity;
    private SimpleStringProperty matchDuration;
    private ObjectProperty<Team> winner;
    private ObjectProperty<Sport> sport;

    public TournamentDto() {

        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.teamsQuantity = new SimpleStringProperty();
        this.winner = new SimpleObjectProperty<>();
        this.sport = new SimpleObjectProperty<>();
        this.matchDuration = new SimpleStringProperty();
    }

    public TournamentDto(Tournament tournament) {

        this();
        this.id.setValue(tournament.getTrmId().toString());
        this.name.setValue(tournament.getTrmName());
        this.teamsQuantity.setValue(tournament.getTrmTeamsQuantity().toString());
        this.winner.setValue(tournament.getTrmWinnerId());
        this.sport.setValue(tournament.getTrmSptId());
        this.matchDuration.setValue(tournament.getTrmMatchDuration().toString());
    }

    public String getMatchDuration(){
        return this.matchDuration.get();
    }
    
    public SimpleStringProperty matchDurationProperty(){
        return this.matchDuration;
    }
    
    public void setMatchDuration(String pMatchDuration){
        this.matchDuration.setValue(pMatchDuration);
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
        this.id.set(id != null ? id.toString() : "");
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTeamsQuantity() {
        return teamsQuantity.get();
    }

    public void setTeamsQuantity(String teamsQuantity) {
        this.teamsQuantity.set(teamsQuantity);
    }

    public Team getWinner() {
        return winner.get();
    }

    public void setWinner(Team winner) {
        this.winner.set(winner);
    }

    public Sport getSport() {
        return sport.get();
    }

    public void setSport(Sport sport) {
        this.sport.set(sport);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty teamsQuantityProperty() {
        return teamsQuantity;
    }

    public ObjectProperty<Team> winnerProperty() {
        return winner;
    }

    public ObjectProperty<Sport> sportProperty() {
        return sport;
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

        final TournamentDto other = (TournamentDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("TournamentDto{id=").append(this.id.get());
        sb.append(", nombre del torneo=").append(this.name.get());
        sb.append(", cantidad de equipos=").append(this.teamsQuantity.get());
        sb.append(", ganador=").append(this.winner.get());
        sb.append(", tipo de deporte=").append(this.sport.get());
        sb.append('}');
        return sb.toString();
    }
}