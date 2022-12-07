
class human{
    public String nm;
    public human(String nn){
        nm=nn;
    }
    public void name(){
        System.out.print("name:"+nm);
    }
}

class man extends human{
    public String hand;
    public man(String mm,String myhand){
        super(mm);hand=myhand;//父亲有带参数的构造器就必须super
    }
    public void say(){
        System.out.print("i am "+nm);
    }
}

public class one {
    public static void main (String[] args){
        man a=new man("lyc","1");
        a.say();
    }
}
