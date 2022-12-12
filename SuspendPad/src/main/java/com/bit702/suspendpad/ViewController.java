package com.bit702.suspendpad;

import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;

public class ViewController {
    int Pad_cnt=0;
    Pad[] pad;
    ObservableList<String> observableList;
    private Stage getStage(Node node){
        return (Stage)node.getScene().getWindow();
    }
    @FXML
    BorderPane BP1;
    @FXML
    Region Add;
    @FXML
    ScrollPane SP1;
    @FXML
    ListView <String> LV1;

    @FXML void initialize(){
        pad=new Pad[100];
        observableList=LV1.getItems();
        LV1.setCellFactory((ListView <String> listView)->new LVCell());
        LV1.setFixedCellSize(50);
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
        pad[++Pad_cnt]= new Pad(Pad_cnt,400,400);
        observableList.add(""+Pad_cnt);
    }
    @FXML void releaseExit(){
        getStage(BP1).close();
    }
    @FXML void clickListView(MouseEvent mouseEvent){
        if(LV1.getSelectionModel().getSelectedItem()==null)return;
        String s=LV1.getSelectionModel().getSelectedItem();
        int id=Integer.parseInt(s);
        if(mouseEvent.getButton()==MouseButton.MIDDLE){
            observableList.remove(s);
            pad[id].hide();
        }
        if(mouseEvent.getButton()==MouseButton.PRIMARY){
            pad[id].show();
        }
    }
}
