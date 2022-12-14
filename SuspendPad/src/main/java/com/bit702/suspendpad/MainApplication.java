package com.bit702.suspendpad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
/*
主程序
 */
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //基础----------------------------------------------------
        //FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("View.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene scene = new Scene(root, 400, 400, Color.TRANSPARENT);
        stage.setTitle("I'm Suspending~");
        stage.setScene(scene);
        //--------------------------------------------------------
        //总是在最顶层---------------------------------------
        stage.setAlwaysOnTop(true);
        //-------------------------------------------------
        //无边框---------------------------------------------
        BasicFunction basicFunction= new BasicFunction();
        basicFunction.setNoBorder(stage,root);
        //--------------------------------------------------
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}