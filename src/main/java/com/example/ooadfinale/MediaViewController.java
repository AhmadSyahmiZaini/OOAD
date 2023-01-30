package com.example.ooadfinale;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Alya
 * Controller for mediaView.fxml
 * mediaView.fxml displays the media player for the Faculty Introduction Video
 * Creates a media player and allows player to play, pause, reset and exit media.
 */
public class MediaViewController extends SwitchSceneController implements Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button resetButton;

    private File file;

    private Media media;

    private MediaPlayer mediaPlayer;


    /**
     * This method creates a media player for the program.
     * @param url is a pointer to the resource.
     * @param resourceBundle is to enable our application to load data from distinct files containing the media.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        file = new File("C:\\Users\\syahm\\Desktop\\OOADfinale\\src\\main\\resources\\com\\example\\ooadfinale\\OOAD.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }


    /**
     * Plays media.
     */
    public void playMedia(){
        mediaPlayer.play();
    }

    /**
     * Pauses media.
     */
    public void pauseMedia(){
        mediaPlayer.pause();
    }

    /**
     * Resets media.
     */
    public void resetMedia(){
        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }
}