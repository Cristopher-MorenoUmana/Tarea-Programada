package com.una.tarea_programada;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.SportDto;
import models.Sport;
import util.Response;
import service.SportService;

public class SportRegisterController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField sportNameTxtF;
    @FXML
    private TextField ballUrlTxtF;
    @FXML
    private Button editBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TableView<SportDto> sportsTableView;
    @FXML
    private TableColumn<SportDto, String> nameColumn;
    @FXML
    private TableColumn<SportDto, Integer> idColumn;

    private String sportName = "", sportId = "", ballUrl = "";
    @FXML
    private TextField idTxtF;
    @FXML
    private Text successTxt;

    @FXML
    private Text failTxt;
    @FXML
    private VBox fieldsVbox;
    @FXML
    private Button finishBtn;
    
    private SportDto sportDto;

    private ObservableList<SportDto> dtoSports = FXCollections.observableArrayList();
    
    private String option;

    @FXML
    private ImageView ball_ImageView;
    
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
        showSports();
    }

    private void updateWindowSize() {

        Platform.runLater(() -> App.getStage().sizeToScene());
    }
      
    private void captureText(){
        
        this.sportNameTxtF.focusedProperty().addListener((obs,wasFocused,isNowFocused)->{
            this.sportName = this.sportNameTxtF.getText();
        });
        
        this.ballUrlTxtF.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            this.ballUrl = this.ballUrlTxtF.getText();
        });
        
        this.idTxtF.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            this.sportId = this.idTxtF.getText();
        });
    }
    
    private boolean isValidData(String selectedOption) {

        boolean isNameValid = !this.sportName.isBlank();
        boolean isBallUrlValid = !this.ballUrl.isBlank();
        boolean isIdValid = !this.sportId.isBlank();
        
        return switch (selectedOption) {
            case "ADD" -> isNameValid && isBallUrlValid;
            case "UPDATE" -> isNameValid && isBallUrlValid && isIdValid;
            case "DELETE", "SHOW" -> isIdValid;
            default -> false;
        };
    }
    
    private void handleOptionButtons(){
        
        this.editBtn.setOnAction(eh->{

            this.fieldsVbox.setDisable(false);
            
            this.option = "UPDATE";
            
            this.sportNameTxtF.setDisable(false);
            this.ballUrlTxtF.setDisable(false);
            this.idTxtF.setDisable(false);
            
            handleCRUD();   
        });

        this.addBtn.setOnAction(eh->{
            
            this.fieldsVbox.setDisable(false);
            
            this.option = "ADD";
            
            this.idTxtF.setDisable(true);
            
            this.sportNameTxtF.setDisable(false);
            this.ballUrlTxtF.setDisable(false);
            
            handleCRUD();
        });

        this.deleteBtn.setOnAction(eh->{
            
            this.fieldsVbox.setDisable(false);
            
            this.option = "DELETE";
            
            this.idTxtF.setDisable(false);
            this.sportNameTxtF.setDisable(true);
            this.ballUrlTxtF.setDisable(true);
            
            handleCRUD();
        });
    }
    
    private void handleCRUD() {

        if (this.option.equals("ADD")) {

            this.finishBtn.setOnAction(eh ->{
                
               this.mainAnchorPane.requestFocus(); 
               addSport();
            });    
        }

        if (this.option.equals("UPDATE")) {

            this.finishBtn.setOnAction(eh -> {

                this.mainAnchorPane.requestFocus();
                updateSport();
            });
        }

        if (this.option.equals("DELETE")) {

            this.finishBtn.setOnAction(eh -> {

                this.mainAnchorPane.requestFocus();
                deleteSport();
            });
        }
    }

    private void addSport() {

        if (!isValidData("ADD")) {
            this.failTxt.setVisible(true);
            return;
        }

        this.successTxt.setVisible(false);
        
        this.sportDto = new SportDto();

        this.failTxt.setVisible(false);

        this.sportDto.setName(this.sportName);
        this.sportDto.setBallUrl(this.ballUrl);

        SportService service = new SportService();

        Response response = service.createSport(this.sportDto);

        if (response.getSuccess() == 'N') {
            System.out.println(response.getMessage() + response.getInternalMessage());
            return;
        }

        SportDto sport = (SportDto) response.getData("Deporte");

        Image ball_Image = new Image(getClass().getResource(this.ballUrl).toString());
        
        ball_ImageView.setImage(ball_Image);
        
        this.successTxt.setVisible(true);
        this.dtoSports.add(sport);
    }

    private void deleteSport() {

        if (isValidData("DELETE") == false) {
            this.failTxt.setVisible(true);
            return;
        }

        this.failTxt.setVisible(false);

        SportService service = new SportService();

        int id = Integer.parseInt(this.sportId);

        Response response = service.deleteSport(id);

        if (response.getSuccess() == 'N') {
            System.out.println(response.getMessage() + response.getInternalMessage());
            return;
        }

        this.dtoSports.removeIf(u -> u.getID().equals(id));
        this.successTxt.setVisible(true);
    }

    private void showSports(){
        
        SportService service = new SportService();
        
        Response response = service.listSports();

        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        if (response.getSuccess() == 'N') {
            System.out.println(response.getMessage() + response.getInternalMessage());
            return;
        }
        
        ObservableList<Sport> sports = (ObservableList<Sport>) response.getData("Deportes");
        
        if (sports == null || sports.isEmpty()) {
            System.out.println("No hay deportes para mostrar.");
            return;
        }
        
        for(Sport sport : sports){
            dtoSports.add(new SportDto(sport));
        }
        
        this.sportsTableView.setItems(this.dtoSports);
    }
    
    private void updateSport() {

        if (isValidData("UPDATE") == false) {

            this.failTxt.setVisible(true);
            return;
        }

        this.successTxt.setVisible(false);
        this.failTxt.setVisible(false);

        this.sportDto = new SportDto();

        this.sportDto.setName(this.sportName);
        this.sportDto.setBallUrl(this.ballUrl);
 
        SportService service = new SportService();

        int id = Integer.parseInt(this.sportId);

        this.sportDto.setID(id);

        Response response = service.updateSport(this.sportDto);

        if (response.getSuccess() == 'N') {
            System.out.println(response.getMessage() + response.getInternalMessage());
            return;
        }

        Image ball_Image = new Image(getClass().getResource(this.ballUrl).toString());

        ball_ImageView.setImage(ball_Image);

        for (int i = 0; i < this.dtoSports.size(); i++) {

            if (this.sportDto.equals(this.dtoSports.get(i))) {

                this.dtoSports.set(i, this.sportDto);

                this.successTxt.setVisible(true);

                return;
            }
        }
    }
}
