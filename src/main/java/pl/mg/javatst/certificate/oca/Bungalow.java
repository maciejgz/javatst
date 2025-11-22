package pl.mg.javatst.certificate.oca;

public interface Bungalow extends Haouse {
    public default String getAddress() {
        return "101 Smart Str";
    }
}
