package com.bit702.suspendpad;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
/*
用来保存Pad的缩略图，编号等信息
主要用在ListView中
 */
public class PadView {
    private String string;//在pad的list中的编号
    private Image image;//对应pad的缩略图
    private boolean show;//对应pad是否显示
    //初始化
    public PadView(String name,Image snap){
        string=name;
        image=snap;
        show=true;
    }
    //各种get和set
    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
    public int getId(){
        return Integer.parseInt(string);
    }

    public void setShow(boolean is) {
        this.show = is;
    }

    public boolean isShow() {
        return show;
    }
}
