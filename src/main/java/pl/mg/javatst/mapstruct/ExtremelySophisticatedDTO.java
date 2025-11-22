package pl.mg.javatst.mapstruct;


import lombok.Data;

@Data
public class ExtremelySophisticatedDTO {

    public ExtremelySophisticatedDTO(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    private String first;
    private String second;
}
