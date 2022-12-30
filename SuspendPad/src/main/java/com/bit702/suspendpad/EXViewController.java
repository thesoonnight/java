package com.bit702.suspendpad;

import javafx.animation.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
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

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
pad的控制器
 */
public class EXViewController {
    boolean CTRLTA1=false;//在TA1时ctrl是否按下
    //获取当前舞台
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
    //初始化
    @FXML void initialize(){
        //将IV1的大小绑定为AP1的大小-----------------------------
        IV1.fitWidthProperty().bind(AP1.widthProperty());
        IV1.fitHeightProperty().bind(AP1.heightProperty());
        //---------------------------------------------------
        //初始时图片和文字区均不可见----
        IV1.setVisible(false);
        TA1.setVisible(false);
        //------------------------
    }
    //鼠标进入图标变色
    @FXML void enterRegion(MouseEvent mouseEvent){
        Region region = (Region) mouseEvent.getSource();
        region.setStyle("-fx-background-color: #CCFFFF; -fx-shape: \"M512 0c282.7776 0 512 229.2224 512 512s-229.2224 512-512 512S0 794.7776 0 512 229.2224 0 512 0z m0 76.8C271.6416 76.8 76.8 271.6416 76.8 512s194.8416 435.2 435.2 435.2 435.2-194.8416 435.2-435.2S752.3584 76.8 512 76.8zM512 230.4a38.4 38.4 0 0 1 38.4 38.4v204.7744l204.8 0.0256a38.4 38.4 0 0 1 0 76.8l-204.8-0.0256V755.2a38.4 38.4 0 0 1-76.8 0v-204.8256l-204.8 0.0256a38.4 38.4 0 0 1 0-76.8l204.8-0.0256V268.8A38.4 38.4 0 0 1 512 230.4z\";");
    }
    //鼠标离开图标恢复
    @FXML void exitRegion(MouseEvent mouseEvent){
        Region region = (Region) mouseEvent.getSource();
        region.setStyle("-fx-background-color: grey; -fx-shape: \"M512 0c282.7776 0 512 229.2224 512 512s-229.2224 512-512 512S0 794.7776 0 512 229.2224 0 512 0z m0 76.8C271.6416 76.8 76.8 271.6416 76.8 512s194.8416 435.2 435.2 435.2 435.2-194.8416 435.2-435.2S752.3584 76.8 512 76.8zM512 230.4a38.4 38.4 0 0 1 38.4 38.4v204.7744l204.8 0.0256a38.4 38.4 0 0 1 0 76.8l-204.8-0.0256V755.2a38.4 38.4 0 0 1-76.8 0v-204.8256l-204.8 0.0256a38.4 38.4 0 0 1 0-76.8l204.8-0.0256V268.8A38.4 38.4 0 0 1 512 230.4z\";");
    }
    //释放按钮后的事件
    @FXML void releaseRegion(MouseEvent mouseEvent){
        //如果是右键则打开图片
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
            return;
        }
        //如果是左键则打开文字
        if(mouseEvent.getButton()==MouseButton.PRIMARY){
            TA1.setVisible(true);
            SP1.setVisible(false);
        }
    }
    //释放中键隐藏窗口
    @FXML void releaseBP1(MouseEvent mouseEvent){
        if(mouseEvent.getButton()==MouseButton.MIDDLE){
            getStage(AP1).hide();//中键隐藏
        }
    }
    //在TA1是ctrl是否按下-------------------------------------
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
    //------------------------------------------------------
    //在TA1时按ctrl加滚轮滚动调节字体大小
    @FXML void scrollTA1(ScrollEvent scrollEvent){
        if(!CTRLTA1)return;
        double size=TA1.getFont().getSize();
        //向上滚
        if(scrollEvent.getDeltaY()>0){
            TA1.setFont(new Font(size+1));
            //重置光标----------------------------
            TA1.setText(TA1.getText());
            TA1.positionCaret(TA1.getLength());
            //----------------------------------
        }
        //向下滚
        if(scrollEvent.getDeltaY()<0){
            if(size>1)TA1.setFont(new Font(size-1));
            //重置光标----------------------------
            TA1.setText(TA1.getText());
            TA1.positionCaret(TA1.getLength());
            //----------------------------------
        }
    }
    //右键点击图片进行OCR识别
    @FXML void clickIV1(MouseEvent mouseEvent)throws IOException{
        if(mouseEvent.getButton()==MouseButton.SECONDARY){
            Image img=IV1.getImage();
            //-----------------------------------------------------
            ArrayList<String> arrayList=Ocr.ocr(img);
            String string="";
            for(String s:arrayList){
                string=string+"\n"+s;
            }
            //-----------------------------------------------------
            //注：如果文字识别功能失效，可能是因为时间太久导致百度的权限到期，将上部分横线中的注释掉再删掉下面一行的注释即可
            //String string=OcrDemo.get(BasicFunction.toFile(img));

            //设置剪切板---------------
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(string);
            clipboard.setContent(content);
            //-----------------------
        }
    }
}
