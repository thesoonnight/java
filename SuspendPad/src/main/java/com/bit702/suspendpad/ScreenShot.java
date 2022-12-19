package com.bit702.suspendpad;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class ScreenShot {
    static ImageView iv;
    static Button btn;
    static Stage primary, stage,stage1;
    static double sceneX_start;
    static double sceneY_start;
    static double sceneX_end;
    static double sceneY_end;
    static HBox hbox;
    private WritableImage writableImage;

    public ScreenShot(){
        //show();
        /*
        stage=new Stage();
        primary = stage;
        AnchorPane root = new AnchorPane();
        btn = new Button("截图");
        iv = new ImageView();
        iv.setFitWidth(400);
        iv.setPreserveRatio(true);

        root.getChildren().addAll(btn, iv);
        AnchorPane.setTopAnchor(btn, 50.0);
        AnchorPane.setLeftAnchor(btn, 50.0);
        AnchorPane.setTopAnchor(iv, 100.0);
        AnchorPane.setLeftAnchor(iv, 50.0);
        Scene scene = new Scene(root);
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setScene(scene);
        stage.show();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                show();
            }

        });
        //设置快捷键
        KeyCombination key = KeyCombination.valueOf("ctrl+alt+a");
        Mnemonic mc = new Mnemonic(btn, key);
        scene.addMnemonic(mc);

         */

    }

    public void show() {
        //primary.setIconified(true);// 隐藏窗口
        stage1 = new Stage();
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color:#B5B5B522");
        Scene scene = new Scene(an);
        scene.setFill(Paint.valueOf("#ffffff00"));

        stage1.setFullScreenExitHint("");// 设置空字符串
        stage1.setScene(scene);
        stage1.setFullScreen(true);// 全屏
        stage1.initStyle(StageStyle.TRANSPARENT);// 透明
        stage1.show();

        drag(an);//调用矩形拖拉

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    stage1.close();
                    //primary.setIconified(false);
                }
            }

        });
    }

    /* 矩形拖拉 */
    private void drag(AnchorPane an) {
        an.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {

                an.getChildren().clear();

                hbox = new HBox();
                hbox.setBackground(null);
                hbox.setBorder(new Border(new BorderStroke(Paint.valueOf("#CD3700"), BorderStrokeStyle.SOLID, null,
                        new BorderWidths(2))));
                /* 获取坐标 */
                sceneX_start = arg0.getSceneX();
                sceneY_start = arg0.getSceneY();

                an.getChildren().add(hbox);

                AnchorPane.setLeftAnchor(hbox, sceneX_start);
                AnchorPane.setTopAnchor(hbox, sceneY_start);
            }

        });
        /* 拖拽检测 */
        an.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                an.startFullDrag();
            }

        });
        /* 拖拽获取坐标 */
        an.setOnMouseDragOver(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Label label = new Label();
                label.setAlignment(Pos.CENTER);
                label.setPrefWidth(170);
                label.setPrefHeight(30);

                an.getChildren().add(label);

                AnchorPane.setLeftAnchor(label, sceneX_start);
                AnchorPane.setTopAnchor(label, sceneY_start - label.getPrefHeight());

                label.setTextFill(Paint.valueOf("#ffffff"));
                label.setStyle("-fx-background-color:#000000");

                double sceneX = event.getSceneX();
                double sceneY = event.getSceneY();

                double width = sceneX - sceneX_start;
                if(width<0)width=-width;
                double height = sceneY - sceneY_start;
                if(height<0)height=-height;

                hbox.setPrefWidth(width);
                hbox.setPrefHeight(height);
                DecimalFormat df = new DecimalFormat("#.##");
                label.setText("宽度：" + df.format(width) + "高度：" + df.format(height));

            }

        });
        /*当鼠标拖拽出矩形后，可以通过点击完成，得到截图*/
        an.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                sceneX_end = event.getSceneX();
                sceneY_end = event.getSceneY();

                Button btn_fin = new Button("完成");
                hbox.getChildren().add(btn_fin);
                hbox.setAlignment(Pos.BOTTOM_RIGHT);

                btn_fin.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent arg0) {
                        try {
                            getScreenImg();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void getScreenImg() throws Exception {
        stage1.close();// 关闭当前窗口
        double w = sceneX_end - sceneX_start;
        double h = sceneY_end - sceneY_start;
        if(w<0)w=-w;
        if(h<0)h=-h;
        /*截图*/
        Robot robot = new Robot();
        Rectangle rec = new Rectangle((int) sceneX_start, (int) sceneY_start, (int) w, (int) h);
        BufferedImage buffimg = robot.createScreenCapture(rec);

        /*将图片显示在面板上*/
        writableImage = SwingFXUtils.toFXImage(buffimg, null);
        //iv.setImage(wi);

        /* 获取系统剪切板 */
        Clipboard cb = Clipboard.getSystemClipboard();

        /* 将图片放在剪切板上 */
        ClipboardContent content = new ClipboardContent();
        content.putImage(writableImage);
        cb.setContent(content);
        ViewController.stage.show();
        /* 写入图片到D盘 */
        //ImageIO.write(buffimg, "png", new File("D:/img.png"));
        //primary.setIconified(false);
    }

    public WritableImage getWritableImage() {
        return writableImage;
    }
}