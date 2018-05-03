package pl.mg.javatst.certificate.oca;

public class BlockLoading {


    static {
        System.out.println("static block");
    }

    {
        System.out.println("simple block");
    }


    {
        System.out.println("simple block 2");
    }

    public BlockLoading() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        BlockLoading blockLoading = new BlockLoading();
    }
}
