package pl.mg.javatst.pattern.singleton;

public class Singleton {

    private static Singleton _instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (_instance == null) {
            _instance = new Singleton();
        }
        return _instance;
    }

    public String doSomething() {
        return "workin...";
    }
}
