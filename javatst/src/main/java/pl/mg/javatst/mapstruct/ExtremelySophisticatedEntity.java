package pl.mg.javatst.mapstruct;

import lombok.Data;

@Data
public class ExtremelySophisticatedEntity {

    public ExtremelySophisticatedEntity(Integer firstValue, String secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public Integer getFirstValue() {
        return firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setFirstValue(Integer firstValue) {
        this.firstValue = firstValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    private Integer firstValue;
    private String secondValue;

}
