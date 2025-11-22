package pl.mg.javatst.codewars;

import lombok.Data;

@Data
public class BWT {

    public String s;
    public int n;

    public BWT(String s, int n) {
        this.s = s;
        this.n = n;
    }

}
