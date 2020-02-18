package pl.mg.javatst.cert.ocp;

public interface DefaultInterface {

    public static final String STATIC_VAL = "val";

    public static String staticMethod() {
        return "static" + STATIC_VAL;
    }

    public default String someDefaultMethod(String sample) {
        return "default " + sample;
    }

}
