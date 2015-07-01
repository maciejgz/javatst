package pl.mg.javatst.unba;

/**
 * Created by Maciej Gzik on 2015-04-26.
 */
public class ScenarioCounter {

    private int counterId;
    private String counterDes;
    private int step;
    private int volumeTotal;
    private int volumeLeft;
    private int volumeLimit;

    @Override
    public String toString() {
        return "ScenarioCounter{" +
                "counterId=" + counterId +
                ", counterDes='" + counterDes + '\'' +
                ", step=" + step +
                ", volumeTotal=" + volumeTotal +
                ", volumeLeft=" + volumeLeft +
                ", volumeLimit=" + volumeLimit +
                '}';
    }

    public int getCounterId() {
        return counterId;
    }

    public void setCounterId(int counterId) {
        this.counterId = counterId;
    }

    public String getCounterDes() {
        return counterDes;
    }

    public void setCounterDes(String counterDes) {
        this.counterDes = counterDes;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(int volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public int getVolumeLeft() {
        return volumeLeft;
    }

    public void setVolumeLeft(int volumeLeft) {
        this.volumeLeft = volumeLeft;
    }

    public int getVolumeLimit() {
        return volumeLimit;
    }

    public void setVolumeLimit(int volumeLimit) {
        this.volumeLimit = volumeLimit;
    }
}
