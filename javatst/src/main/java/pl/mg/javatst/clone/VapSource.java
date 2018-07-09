package pl.mg.javatst.clone;

public class VapSource implements Cloneable{

    private String sourceName;
    private int vapSourceId;

    public VapSource(String sourceName, int vapSourceId) {
        this.sourceName = sourceName;
        this.vapSourceId = vapSourceId;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "VapSource{" +
                "sourceName='" + sourceName + '\'' +
                ", vapSourceId=" + vapSourceId +
                '}';
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public int getVapSourceId() {
        return vapSourceId;
    }

    public void setVapSourceId(int vapSourceId) {
        this.vapSourceId = vapSourceId;
    }
}
