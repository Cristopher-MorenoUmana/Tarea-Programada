package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class MatchDto {

    private SimpleStringProperty id;
    private SimpleStringProperty scoreTeam1;
    private SimpleStringProperty scoreTeam2;
    private ObjectProperty<Team> team1;
    private ObjectProperty<Team> team2;
    private ObjectProperty<Team> winner;
    private SimpleStringProperty state;

    public MatchDto() {

        this.id = new SimpleStringProperty();
        this.scoreTeam1 = new SimpleStringProperty();
        this.scoreTeam2 = new SimpleStringProperty();
        this.state = new SimpleStringProperty();
        this.team1 = new SimpleObjectProperty<>();
        this.team2 = new SimpleObjectProperty<>();
        this.winner = new SimpleObjectProperty<>();
    }

    public MatchDto(Match match) {

        this();
        this.id.setValue(match.getMthId().toString());
        this.scoreTeam1.setValue(match.getMthScoreTeam1().toString());
        this.scoreTeam2.setValue(match.getMthScoreTeam2().toString());
        this.state.setValue(match.getMthState());
        this.team1.setValue(match.getMthTeam1());
        this.team2.setValue(match.getMthTeam2());
        this.winner.setValue(match.getMthWinner());
    }

    public String getScoreTeam1() {
        return scoreTeam1.get();
    }

    public void setScoreTeam1(String scoreTeam1) {
        this.scoreTeam1.set(scoreTeam1);
    }

    public String getScoreTeam2() {
        return scoreTeam2.get();
    }

    public void setScoreTeam2(String scoreTeam2) {
        this.scoreTeam2.set(scoreTeam2);
    }   

    public Team getTeam1() {
        return team1.get();
    }

    public void setTeam1(Team team1) {
        this.team1.set(team1);
    }

    public Team getTeam2() {
        return team2.get();
    }

    public void setTeam2(Team team2) {
        this.team2.set(team2);
    }

    public Team getWinner() {
        return winner.get();
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public void setWinner(Team winner) {
        this.winner.set(winner);
    }
    
    public SimpleStringProperty scoreTeam1Property() {
        return scoreTeam1;
    }

    public SimpleStringProperty scoreTeam2Property() {
        return scoreTeam2;
    }

    public ObjectProperty<Team> team1Property() {
        return team1;
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public ObjectProperty<Team> team2Property() {
        return team2;
    }

    public ObjectProperty<Team> winnerProperty() {
        return winner;
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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

        final MatchDto other = (MatchDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("MatchDto{id=").append(this.id.get());
        sb.append(", equipo 1=").append(this.team1.get());
        sb.append(", equipo 2=").append(this.team2.get());
        sb.append(", estado=").append(this.state.get());
        sb.append(", marcador equipo 1=").append(this.scoreTeam1.get());
        sb.append(", marcador equipo 2=").append(this.scoreTeam2.get());
        sb.append(", ganador=").append(this.winner.get());
        sb.append('}');
        return sb.toString();
    }
}
