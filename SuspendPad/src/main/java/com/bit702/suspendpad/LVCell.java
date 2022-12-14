package com.bit702.suspendpad;

import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/*
ListView的Cell类的废案
没有使用
 */
public class LVCell extends ListCell<PadView> {
    @Override
    public void updateItem(PadView item,boolean empty){
        super.updateItem(item,empty);
        if(item!=null){
            ImageView imageView = new ImageView();
            imageView.setImage(item.getImage());
            imageView.fitHeightProperty().bind(this.heightProperty());
            imageView.setFitWidth(50);
            imageView.setVisible(true);
            setGraphic(imageView);

            if(item.isShow()){
                setText("展示中~");
            }
            else {
                setText("已隐藏QAQ");
            }
        }
        else {
            setText(null);
            setGraphic(null);
        }
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(getListView().getItems().size()>1)getListView().getSelectionModel().select(getIndex());
                setTextFill(Color.WHITE);
                setBackground(new Background(new BackgroundFill(Color.web("rgba(0,0,0,0.5)"),null,null)));
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(getListView().getItems().size()>1)getListView().getSelectionModel().clearSelection();
                setTextFill(Color.BLACK);
                setBackground(new Background(new BackgroundFill(Color.web("rgba(0,0,0,0.2)"),null,null)));
            }
        });
    }
}
