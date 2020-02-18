package pl.mg.javatst.cert.ocp;

@FunctionalInterface
public interface SampleFunctionalInterface<T, V> {

    public V doSomething(T value);
}
