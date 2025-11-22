package pl.mg.javatst.hackerrank;


import lombok.Data;

import java.io.Serializable;


@Data
public class Player implements Serializable {

    public String name;
    public int score;
}
