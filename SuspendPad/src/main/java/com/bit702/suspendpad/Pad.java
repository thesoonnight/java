package com.bit702.suspendpad;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class Pad {

    private int Id;
    private int X;
    private int Y;
    private Stage stage;
    private Parent root;
    private Scene scene;
    public Pad(int id,int x,int y) throws IOException {
        Id=id;X=x;Y=y;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("EXView.fxml"));
        scene = new Scene(root, X, Y, Color.TRANSPARENT);
        stage.setTitle("I'm No. "+Id+" ~~~");
        stage.setScene(scene);
        BasicFunction basicFunction = new BasicFunction();
        basicFunction.setNoBorder(stage,root);
        stage.setAlwaysOnTop(true);
        //随机位置---------------------------------------
        Random random=new Random();
        stage.setX(random.nextInt(1200));
        stage.setY(random.nextInt(1000));
        //----------------------------------------------
        stage.show();
    }
}
