package com.javatts;

import com.darkprograms.speech.synthesiser.SynthesiserV2;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    private final String API_KEY = "$$Place your api key here$$";
    private SynthesiserV2 synthesiserV2 = new SynthesiserV2(API_KEY);

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField textField;

    @FXML
    private JFXButton speakButton;

    @FXML
    void speakButtonClicked(ActionEvent event) {

        textField.setEditable(false);
        speak(textField.getText());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textField.setEditable(true);
    }

    private void speak(String text) {

        new Thread(
                () -> {

                    try {

                        System.out.println("-> " + text);
                        synthesiserV2.setLanguage("en");
                        synthesiserV2.setPitch(1.0f);

                        AdvancedPlayer advancedPlayer = new AdvancedPlayer(synthesiserV2.getMP3Data(text));
                        advancedPlayer.play();
                    } catch (IOException | JavaLayerException exception) {

                        System.out.println("{X} Exception in : " + getClass().getSimpleName());
                        exception.printStackTrace();
                    }

                    textField.setEditable(true);
                }).start();
    }


}
