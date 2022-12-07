package com.bit702.mydemo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class myController {
    @FXML private AnchorPane anchorPane;
    @FXML
    private TextArea p1_txt1;
    @FXML private Button p2_bt1;
    @FXML private TextField p2_txt1;
    @FXML private Pane p1;
    @FXML private Pane p2;
    @FXML private ImageView p1_pic1;

    int cnt=0;

    @FXML void initialize(){
        //p1.setVisible(false);
        p2.setVisible(true);
        //p1_pic1.fitHeightProperty().bind(p1.heightProperty());
        //p1_pic1.fitWidthProperty().bind(p1.widthProperty());
    }
    @FXML
    protected void p1_bt1Click() {
        //p1.setVisible(false);
        p2.setVisible(true);
        cnt++;
        p2_txt1.setText("you click "+cnt+" times");
    }
    @FXML
    protected void p2_bt1Click() {
        //p1.setVisible(true);
        p2.setVisible(false);
        cnt++;
        p1_txt1.setText("you click "+cnt+" times");
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("111","*.jpg"));
        Stage s1=(Stage)anchorPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(s1);
        if(file==null){
            System.out.print("no");
        }
        else {
            p1_pic1.setImage(new Image(file.getPath()));
        }
    }
    @FXML void p1_pic1Enter(){
        p1_txt1.setText("don't touch me");
    }
    @FXML void p1_pic1Exit(){
        p1_txt1.setText("you click "+cnt+" times");
    }
}