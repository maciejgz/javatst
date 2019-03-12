package pl.mg.javatst.unba.osp.vo.connectors;

public class A {

    int main;
    public static void main(String[] args)  {

        boolean hasParams = (args == null ? false : true);
        if(hasParams){
            System.out.println("has params");
        }{
            System.out.println("no params");
        }
    }
}
