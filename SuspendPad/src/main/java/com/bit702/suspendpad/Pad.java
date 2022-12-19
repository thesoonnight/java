package com.bit702.suspendpad;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;
/*
生成的悬浮窗类
 */
public class Pad {
    private int Id;//在list中的编号
    private int X;//X轴位置
    private int Y;//Y轴位置
    private boolean alive;//有没有被close
    private Stage stage;//对应的舞台
    private Parent root;//对应的FXMLLoader
    private Scene scene;//对应的场景
    //初始化
    public Pad(int id,int x,int y) throws IOException {
        Id=id;X=x;Y=y;alive=true;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("EXView.fxml"));
        scene = new Scene(root, X, Y, Color.TRANSPARENT);
        stage.setTitle("I'm No. "+Id+" ~~~");//标题
        stage.setScene(scene);
        //无边框-------------
        BasicFunction basicFunction = new BasicFunction();
        basicFunction.setNoBorder(stage,root);
        //------------------
        //总是在屏幕最上方---------------
        stage.setAlwaysOnTop(true);
        //----------------------------
        //随机位置----------------------------------------
        Random random=new Random();
        stage.setX(random.nextInt(1600));
        stage.setY(random.nextInt(900));
        //----------------------------------------------
        stage.show();
    }
    //展示
    public void show(){
        stage.show();
    }
    //隐藏
    public void hide(){
        stage.hide();
    }
    //关闭
    public void close(){
        stage.close();
    }
    //获取缩略图
    public Image getSnap(){
        return root.getChildrenUnmodifiable().get(0).snapshot(new SnapshotParameters(),null);
    }
    //是否显示出来了
    public boolean isShow(){
        return stage.isShowing();
    }
    //设置有没有被close
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    //获取有没有被close
    public boolean isAlive() {
        return alive;
    }
    public void showAsImage(Image image){
        ImageView imageView=(ImageView) root.lookup("#IV1");
        StackPane stackPane=(StackPane) root.lookup("#SP1");
        stackPane.setVisible(false);
        imageView.setImage(image);
        imageView.setVisible(true);
    }
}