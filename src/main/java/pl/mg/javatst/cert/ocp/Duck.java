package pl.mg.javatst.cert.ocp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Duck {

    private String name;

    private Integer weight;

}
