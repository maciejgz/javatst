package pl.mg.javatst.unba.datasource;

import pl.mg.javatst.unba.ScenarioCounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej Gzik on 2015-04-26.
 */
public class RatingScenario {


    private int scenarioId;
    private String scenarioDes;
    private List<ScenarioCounter> scenarioCounters = new ArrayList<ScenarioCounter>();

    @Override
    public String toString() {
        return "RatingScenario{" +
                "scenarioId=" + scenarioId +
                ", scenarioDes='" + scenarioDes + '\'' +
                ", scenarioCounters=" + scenarioCounters +
                '}';
    }

    public int getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(int scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getScenarioDes() {
        return scenarioDes;
    }

    public void setScenarioDes(String scenarioDes) {
        this.scenarioDes = scenarioDes;
    }

    public List<ScenarioCounter> getScenarioCounters() {
        return scenarioCounters;
    }

    public void setScenarioCounters(List<ScenarioCounter> scenarioCounter) {
        this.scenarioCounters = scenarioCounter;
    }


}
