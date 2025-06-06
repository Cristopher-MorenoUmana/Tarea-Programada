package com.una.tarea_programada;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.fxml.Initializable;


public class ClashController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private ListView<?> tournamentListView;
    @FXML
    private Text team_1_Txt1;
    @FXML
    private Button buildClashAgain;
    @FXML
    private Button buildClash;
    @FXML
    private ImageView team_1_ImageView;
    @FXML
    private ImageView team_2_ImageView;
    @FXML
    private ImageView winnerClash_1_ImageView;
    @FXML
    private ImageView team_3_ImageView;
    @FXML
    private ImageView team_4_ImageView;
    @FXML
    private ImageView winnerClash_2_ImageView;
    @FXML
    private ImageView team_5_ImageView;
    @FXML
    private ImageView team_6_ImageView;
    @FXML
    private ImageView winnerClash_3_ImageView;
    @FXML
    private ImageView team_7_ImageView;
    @FXML
    private ImageView team_8_ImageView;
    @FXML
    private ImageView winnerClash_4_ImageView;
    @FXML
    private ImageView winnerClash_5_ImageView;
    @FXML
    private ImageView winnerClash_6_ImageView;
    @FXML
    private ImageView tournamentWinnerImageView;
    @FXML
    private Button backBtn;

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
