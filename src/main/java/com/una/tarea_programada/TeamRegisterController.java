package com.una.tarea_programada;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Team;
import models.Sport;
import models.TeamDto;
import service.TeamService;
import service.SportService;
import util.Response;

public class TeamRegisterController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private TextField logoUrlTxtF;
    @FXML
    private TextField idTxtF;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private TableView<TeamDto> teamsTableView;
    @FXML
    private TableColumn<TeamDto, String> nameColumn;
    @FXML
    private TableColumn<TeamDto, Integer> idColumn;
    @FXML
    private TableColumn<TeamDto, String> sportColumn;
    @FXML
    private TableColumn<TeamDto, String> logoColumn;
    @FXML
    private VBox fieldsVbox;
    @FXML
    private Text successTxt;
    @FXML
    private Text failTxt;
    @FXML
    private ImageView logoImageView;

    private String option;

    @FXML
    private TextField nameTxtF;
   
    @FXML
    private TextField sportIdTxtF;

    private String name = "", logoUrl = "",  sportId = "", id = "";
    @FXML
    private Button finishBtn;
    
    private TeamDto teamDto;
    
    private ObservableList<TeamDto> dtoTeams = FXCollections.observableArrayList();
 
    private Sport sport;
    
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
    
   public void initialize() {

        this.option = "";
        this.fieldsVbox.setDisable(true);
        
        updateWindowSize();
        captureText();
        handleOptionButtons();
        showTeams();
    }

    private void updateWindowSize() {

        Platform.runLater(() -> App.getStage().sizeToScene());
    }
      
    private void captureText(){
        
        this.nameTxtF.focusedProperty().addListener((obs,wasFocused,isNowFocused)->{
            this.name = this.nameTxtF.getText();
        });
        
        this.logoUrlTxtF.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            this.logoUrl = this.logoUrlTxtF.getText();
        });
        
        this.sportIdTxtF.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            this.sportId = this.sportIdTxtF.getText();
        });
        
        this.idTxtF.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            this.id = this.idTxtF.getText();
        });
    }
    
    private boolean isValidData(String selectedOption) {

        boolean isNameValid = !this.name.isBlank();
        boolean isLogoUrlValid = !this.logoUrl.isBlank();
        boolean isIdValid = !this.id.isBlank();
        boolean isSportIdValid = !this.sportId.isBlank();

        if (!isSportIdValid) {

            return false;
        }

        SportService sportService = new SportService();

        int idToSearch;

        try {
            idToSearch = Integer.parseInt(sportId);
        } catch (NumberFormatException e) {
            return false;
        }

        Response response = sportService.getSportById(idToSearch);

        if (response.getSuccess() == 'N') {
            return false;
        }
        
        this.sport = (Sport)response.getData("Deporte");
        
        return switch (selectedOption) {
            case "ADD" ->
                isNameValid && isLogoUrlValid;
            case "UPDATE" ->
                isNameValid && isLogoUrlValid && isIdValid;
            case "DELETE", "SHOW" ->
                isIdValid;
            default ->
                false;
        };
    }
    
    private void handleOptionButtons(){
        
        this.editBtn.setOnAction(eh->{

            this.fieldsVbox.setDisable(false);
            
            this.option = "UPDATE";
            
            this.nameTxtF.setDisable(false);
            this.logoUrlTxtF.setDisable(false);
            this.sportIdTxtF.setDisable(false);
            this.idTxtF.setDisable(false);
            
            handleCRUD();   
        });

        this.addBtn.setOnAction(eh->{
            
            this.fieldsVbox.setDisable(false);
            
            this.option = "ADD";
            
            this.nameTxtF.setDisable(false);
            this.logoUrlTxtF.setDisable(false);
            this.sportIdTxtF.setDisable(false);
            this.idTxtF.setDisable(true);
            
            handleCRUD();
        });

        this.deleteBtn.setOnAction(eh->{
            
            this.fieldsVbox.setDisable(false);
            
            this.option = "DELETE";
            
            this.nameTxtF.setDisable(true);
            this.logoUrlTxtF.setDisable(true);
            this.sportIdTxtF.setDisable(true);
            this.idTxtF.setDisable(false);
            
            handleCRUD();
        });
    }
    
    private void handleCRUD() {

        if (this.option.equals("ADD")) {

            this.finishBtn.setOnAction(eh ->{
                
               this.mainAnchorPane.requestFocus(); 
               addTeam();
            });    
        }

        if (this.option.equals("UPDATE")) {

            this.finishBtn.setOnAction(eh -> {

                this.mainAnchorPane.requestFocus();
                updateTeam();
            });
        }

        if (this.option.equals("DELETE")) {

            this.finishBtn.setOnAction(eh -> {

                this.mainAnchorPane.requestFocus();
                deleteTeam();
            });
        }
    }

    private void addTeam() {

        if (!isValidData("ADD")) {
            this.failTxt.setVisible(true);
            return;
        }

        this.successTxt.setVisible(false);
        
        this.teamDto = new TeamDto();

        this.failTxt.setVisible(false);
        
        this.teamDto.setName(this.name);
        this.teamDto.setLogoUrl(this.logoUrl);
        this.teamDto.setSport(sport);
        
        TeamService service = new TeamService();

        Response response = service.createTeam(this.teamDto);

        if (response.getSuccess() == 'N') {
            System.out.println(response.getMessage() + response.getInternalMessage());
            return;
        }

        TeamDto team = (TeamDto) response.getData("Equipo");

        Image logo_Image = new Image(getClass().getResource(this.logoUrl).toString());
        
        logoImageView.setImage(logo_Image);
        
        this.successTxt.setVisible(true);
        this.dtoTeams.add(team);
    }

    private void deleteTeam() {

        if (isValidData("DELETE") == false) {
            this.failTxt.setVisible(true);
            return;
        }

        this.failTxt.setVisible(false);

        TeamService service = new TeamService();

        int teamId = Integer.parseInt(this.id);

        Response response = service.deleteTeam(teamId);

        if (response.getSuccess() == 'N') {
            System.out.println(response.getMessage() + response.getInternalMessage());
            return;
        }

        this.dtoTeams.removeIf(u -> u.getID().equals(teamId));
        this.successTxt.setVisible(true);
    }

    private void showTeams(){
        
        TeamService service = new TeamService();
        
        Response response = service.listTeams();

        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.logoColumn.setCellValueFactory(new PropertyValueFactory<>("logoUrl"));
        this.sportColumn.setCellValueFactory(new PropertyValueFactory<>("SportNameAndId"));
        
        if (response.getSuccess() == 'N') {
            System.out.println(response.getMessage() + response.getInternalMessage());
            return;
        }
        
        ObservableList<Team> teams = (ObservableList<Team>) response.getData("Equipos");
        
        if (teams == null || teams.isEmpty()) {
            System.out.println("No hay equipos para mostrar.");
            return;
        }
        
        for(Team team : teams){
            dtoTeams.add(new TeamDto(team));
        }
        
        this.teamsTableView.setItems(this.dtoTeams);
    }
    
    private void updateTeam() {

        if (isValidData("UPDATE") == false) {

            this.failTxt.setVisible(true);
            return;
        }

        this.successTxt.setVisible(false);
        this.failTxt.setVisible(false);

        this.teamDto = new TeamDto();

        this.teamDto.setName(this.name);
        this.teamDto.setLogoUrl(this.logoUrl);
        this.teamDto.setSport(sport);
        
        TeamService service = new TeamService();

        int teamId = Integer.parseInt(this.id);

        this.teamDto.setID(teamId);

        Response response = service.updateTeam(this.teamDto);

        if (response.getSuccess() == 'N') {
            System.out.println(response.getMessage() + response.getInternalMessage());
            return;
        }

        Image logo_Image = new Image(getClass().getResource(this.logoUrl).toString());

        logoImageView.setImage(logo_Image);

        for (int i = 0; i < this.dtoTeams.size(); i++) {

            if (this.teamDto.equals(this.dtoTeams.get(i))) {

                this.dtoTeams.set(i, this.teamDto);

                this.successTxt.setVisible(true);

                return;
            }
        }
    }
}
