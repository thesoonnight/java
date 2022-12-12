package com.bit702.suspendpad;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

import java.io.File;
import java.io.IOException;

public class EXViewController {
    private Stage getStage(Node node){
        return (Stage)node.getScene().getWindow();
    }
    @FXML
    AnchorPane AP1;
    @FXML
    BorderPane BP1;
    @FXML
    StackPane SP1;
    @FXML
    ImageView IV1;
    @FXML
    TextArea TA1;
    @FXML void initialize(){
        IV1.fitWidthProperty().bind(AP1.widthProperty());
        IV1.fitHeightProperty().bind(AP1.heightProperty());
        IV1.setVisible(false);
        TA1.setVisible(false);
        TA1.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                double size=TA1.getFont().getSize();
                if(scrollEvent.getDeltaY()>0){
                    TA1.setFont(new Font(size+1));
                }
                if(scrollEvent.getDeltaY()<0){
                    if(size>1)TA1.setFont(new Font(size-1));
                }
            }
        });
    }
    @FXML void enterRegion(MouseEvent mouseEvent){
        Region region = (Region) mouseEvent.getSource();
        region.setStyle("-fx-background-color: #CCFFFF; -fx-shape: \"M512 0c282.7776 0 512 229.2224 512 512s-229.2224 512-512 512S0 794.7776 0 512 229.2224 0 512 0z m0 76.8C271.6416 76.8 76.8 271.6416 76.8 512s194.8416 435.2 435.2 435.2 435.2-194.8416 435.2-435.2S752.3584 76.8 512 76.8zM512 230.4a38.4 38.4 0 0 1 38.4 38.4v204.7744l204.8 0.0256a38.4 38.4 0 0 1 0 76.8l-204.8-0.0256V755.2a38.4 38.4 0 0 1-76.8 0v-204.8256l-204.8 0.0256a38.4 38.4 0 0 1 0-76.8l204.8-0.0256V268.8A38.4 38.4 0 0 1 512 230.4z\";");
    }
    @FXML void exitRegion(MouseEvent mouseEvent){
        Region region = (Region) mouseEvent.getSource();
        region.setStyle("-fx-background-color: grey; -fx-shape: \"M512 0c282.7776 0 512 229.2224 512 512s-229.2224 512-512 512S0 794.7776 0 512 229.2224 0 512 0z m0 76.8C271.6416 76.8 76.8 271.6416 76.8 512s194.8416 435.2 435.2 435.2 435.2-194.8416 435.2-435.2S752.3584 76.8 512 76.8zM512 230.4a38.4 38.4 0 0 1 38.4 38.4v204.7744l204.8 0.0256a38.4 38.4 0 0 1 0 76.8l-204.8-0.0256V755.2a38.4 38.4 0 0 1-76.8 0v-204.8256l-204.8 0.0256a38.4 38.4 0 0 1 0-76.8l204.8-0.0256V268.8A38.4 38.4 0 0 1 512 230.4z\";");
    }
    @FXML void releaseRegion(MouseEvent mouseEvent){
        if(mouseEvent.getButton()== MouseButton.SECONDARY){
            FileChooser fileChooser=new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("111","*.jpg","*.png","*.jpeg","*.gif","*.bmp"));
            Stage stage=getStage(BP1);
            File file = fileChooser.showOpenDialog(stage);
            if(file==null){
                return;
            }
            else {
                IV1.setImage(new Image(file.getPath()));
                IV1.setVisible(true);
                SP1.setVisible(false);
            }
            // 获取文件的扩展名（即文件类型）
            //String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            //if (fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("jpg")) {
            //System.out.print("this is");
            //}
            return;
        }
        if(mouseEvent.getButton()==MouseButton.PRIMARY){
            TA1.setVisible(true);
        }
    }
    @FXML void releaseBP1(MouseEvent mouseEvent){
        if(mouseEvent.getButton()==MouseButton.MIDDLE){
            getStage(AP1).close();//中键退出
        }
    }
    boolean CTRLTA1=false;
    @FXML void keypressTA1(KeyEvent keyEvent){
        if(keyEvent.isControlDown()){
            CTRLTA1=true;
        }
    }
    @FXML void keyreleaseTA1(KeyEvent keyEvent){
        if(!keyEvent.isControlDown()){
            CTRLTA1=false;
        }
    }
    @FXML void scrollTA1(ScrollEvent scrollEvent){
        /*if(CTRLTA1==false)return;
        double size=TA1.getFont().getSize();
        if(scrollEvent.getDeltaY()>0){
            TA1.setFont(new Font(size+1));
        }
        if(scrollEvent.getDeltaY()<0){
            if(size>1)TA1.setFont(new Font(size-1));
        }*/
    }
}
