package com.bit702.suspendpad;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ViewController {
    int Pad_cnt=0;
    private Stage getStage(Node node){
        return (Stage)node.getScene().getWindow();
    }
    @FXML BorderPane BP1;
    @FXML Region Add;


    @FXML void initialize(){
    }
    @FXML void enterRegion(MouseEvent mouseEvent){
        Region region = (Region) mouseEvent.getSource();
        RotateTransition rotateTransition;
        rotateTransition = new RotateTransition(Duration.seconds(0.5),region);
        rotateTransition.setToAngle(180);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }
    @FXML void exitRegion(MouseEvent mouseEvent){
        Region region = (Region) mouseEvent.getSource();
        RotateTransition rotateTransition;
        rotateTransition = new RotateTransition(Duration.seconds(0.5),region);
        rotateTransition.setToAngle(0);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }
    @FXML void releaseAdd() throws IOException{
        Pad pad= new Pad(++Pad_cnt,400,400);
    }
    @FXML void releaseExit(){
        getStage(BP1).close();
    }

}