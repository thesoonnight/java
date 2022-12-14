package com.bit702.suspendpad;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
主界面的控制器
 */
public class ViewController {
    private int Pad_cnt=0;//当前pad的个数
    private List <Pad> pad;//用于存放pad
    private List <PadView> padView;//用于存放padView
    private ObservableList<PadView> observableList;//ListView里面用来存放padView
    //获取对应的舞台
    private Stage getStage(Node node){
        return (Stage)node.getScene().getWindow();
    }
    //实时更新padView
    private void upPadView(int id){
        PadView tmp=padView.get(id);
        Pad tmp1=pad.get(id);
        tmp.setImage(tmp1.getSnap());
        tmp.setShow(tmp1.isShow());
        tmp.setString(""+id);
        padView.set(id,tmp);
    }
    @FXML
    BorderPane BP1;
    @FXML
    Region Add;
    @FXML
    ListView <PadView> LV1;
    @FXML
    ImageView IV1;
    //初始化
    @FXML void initialize(){
        pad=new ArrayList<>();
        padView=new ArrayList<>();
        observableList=LV1.getItems();
        //LV1.setCellFactory((ListView <PadView> listView)->new LVCell());
        LV1.setFixedCellSize(50);//设置ListView中Cell的高度、
        //设置Cell的样式和响应
        LV1.setCellFactory((ListView <PadView> listView)->new ListCell<>() {
            boolean isOn=false;//鼠标是否在当前Cell上方悬停
            //更新
            @Override
            public void updateItem(PadView item,boolean empty){
                super.updateItem(item,empty);
                //通常的背景和鼠标悬停的背景
                Background normalBack =new Background(new BackgroundFill(Color.web("rgba(0,0,0,0.2)"),null,null));
                Background onBack=new Background(new BackgroundFill(Color.web("rgba(0,0,0,0.5)"),null,null));
                if(!isOn){
                    setBackground(normalBack);
                }
                //对于当前Cell中有没有东西进行讨论
                if(item!=null){
                    //设置padView的缩略图为背景---------------------------------------
                    ImageView imageView = new ImageView();
                    imageView.setImage(item.getImage());
                    imageView.fitHeightProperty().bind(this.heightProperty());
                    imageView.setFitWidth(50);
                    imageView.setVisible(true);
                    setGraphic(imageView);
                    //-------------------------------------------------------------
                    //设置文字------------------------
                    if(item.isShow()){
                        setText("展示中~点击中键删除");
                    }
                    else {
                        setText("已隐藏QAQ点击左键恢复，点击中键删除");
                    }
                    //-------------------------------
                }
                else {
                    //如果没有则清空
                    setText(null);
                    setGraphic(null);
                }
                //鼠标进入，设置文字和背景颜色
                setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        isOn=true;
                        setTextFill(Color.WHITE);//设置文字颜色
                        setBackground(onBack);//设置背景
                    }
                });
                //鼠标离开，设置文字和背景颜色
                setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        isOn=false;
                        setTextFill(Color.BLACK);//设置文字颜色
                        setBackground(normalBack);//设置背景
                    }
                });
                //鼠标释放
                setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        //确保点击到的不是空的
                        if(getListView().getItems().size()<=getIndex()){
                            return;
                        }
                        String s;//鼠标指着的Cell对应的padView的编号
                        s=getListView().getItems().get(getIndex()).getString();//获取编号
                        int id=Integer.parseInt(s);//转化为整数
                        //如果是中键则彻底关闭对应的pad-------------------------
                        if(mouseEvent.getButton()==MouseButton.MIDDLE){
                            observableList.remove(padView.get(id));
                            pad.get(id).close();
                            pad.remove(id);
                            padView.remove(id);
                        }
                        //-------------------------------------------------
                        //如果是左键的展示对应的pad-----------------------------------
                        if(mouseEvent.getButton()==MouseButton.PRIMARY){
                            pad.get(id).show();
                        }
                        //--------------------------------------------------------
                    }
                });
            }
        });
        //持续更新缩略图
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now){
                if(now%1000!=0)return;//控制间隔
                for(int i=0;i<pad.size();i++){
                    upPadView(i);//更新padView
                    observableList.set(i,padView.get(i));//更新完后更新ListView
                }
            }
        };
        timer.start();
    }
    //鼠标进入图标
    @FXML void enterRegion(MouseEvent mouseEvent){
        //当前图标旋转180度
        Region region = (Region) mouseEvent.getSource();
        RotateTransition rotateTransition;
        rotateTransition = new RotateTransition(Duration.seconds(0.5),region);
        rotateTransition.setToAngle(180);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }
    //鼠标离开图标
    @FXML void exitRegion(MouseEvent mouseEvent){
        //撤销旋转（转回去）
        Region region = (Region) mouseEvent.getSource();
        RotateTransition rotateTransition;
        rotateTransition = new RotateTransition(Duration.seconds(0.5),region);
        rotateTransition.setToAngle(0);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }
    //释放添加按钮后添加新pad
    @FXML void releaseAdd() throws IOException{
        pad.add(new Pad(Pad_cnt,400,400));
        padView.add(new PadView(""+(pad.size()-1),pad.get(pad.size()-1).getSnap()));
        observableList.add(padView.get(padView.size()-1));
    }
    //释放离开按钮后全部关闭
    @FXML void releaseExit(){
        for(int i=0;i<pad.size();i++){
            pad.get(i).close();
        }
        getStage(BP1).close();
    }
}
