package com.bit702.suspendpad;

import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LVCell extends ListCell<String> {
    @Override
    public void updateItem(String item,boolean empty){
        super.updateItem(item,empty);
        //Rectangle rectangle = new Rectangle(getPrefWidth(),getPrefHeight());
        //rectangle.setFill(Color.web("#ccffff"));
        if(isSelected()){
            setTextFill(Color.WHITE);
            setBackground(new Background(new BackgroundFill(Color.web("rgba(0,0,0,0.5)"),null,null)));
        }
        else {
            setTextFill(Color.BLACK);
            setBackground(new Background(new BackgroundFill(Color.web("rgba(0,0,0,0.2)"),null,null)));
        }
        if(item!=null){
            setText("编号:"+this.getItem());
            //this.setBackground(new Background(new BackgroundFill(color,null,null)));
        }
        else {
            setText(null);
        }
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getListView().getSelectionModel().select(getIndex());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getListView().getSelectionModel().clearSelection();
            }
        });
    }
}
