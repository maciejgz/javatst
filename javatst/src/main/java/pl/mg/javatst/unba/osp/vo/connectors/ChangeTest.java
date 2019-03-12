package pl.mg.javatst.unba.osp.vo.connectors;

class ChangeTest {
    static int x = 5;
    private int myValue = 0;

    public static void main(String[] args) {
        System.out.println("12345".charAt(6));
    }


    public void showOne(int myValue) {
        myValue = myValue;
    }

    public void showTwo(int myValue) {
        this.myValue = myValue;
    }

}
