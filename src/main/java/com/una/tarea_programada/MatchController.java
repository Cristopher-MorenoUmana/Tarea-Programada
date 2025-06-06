package com.una.tarea_programada;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MatchController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button backBtn;
    @FXML
    private ImageView goal_1_ImageView;
    @FXML
    private ImageView ball_ImageView;
    @FXML
    private ImageView goal_2_ImageView;
    @FXML
    private Button skipMatchBtn;
    @FXML
    private Text team_1_PointsTxt;
    @FXML
    private Text team_1_NameTxt;
    @FXML
    private Text timeRemainingBtn;
    @FXML
    private Text team_2_PointsTxt;
    @FXML
    private Text team_2_NameTxt;
    @FXML
    private Text remainingMatchesBtn;
    @FXML
    private ImageView team_1_LogoImageView;
    @FXML
    private ImageView team_2_LogoImageView1;

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }

    public void initialize() {

        updateWindowSize();
    }

    private void updateWindowSize() {

        Platform.runLater(() ->  App.getStage().sizeToScene());
    }
}
