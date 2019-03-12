package pl.mg.javatst.util;

public class NewTest {
    public static void main(String[] args) throws Exception {
        NewTest tc = new NewTest();
        tc.switchString("c");
        float f = -1;
    }

    public void switchString(String input) {
        switch (input) {
            case "a":
                System.out.println("apple");
            case "b":
                System.out.println("bat");
                break;
            case "c":
                System.out.println("cat");
            default:
                System.out.println("none");
        }
    }
}
