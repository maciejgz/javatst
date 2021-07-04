package pl.mg.javatst.hackerrank;

/**
 * Write a method replacing spaces with %20 in String.
 * Zadanie polega na tym, że jako parametry otrzymujemy odpowiednio długą tablicę char (pozwalającą na dodanie wsyzstkich %20)
 * oraz realnej długości strinhu
 */
public class URLify {

    public static void main(String[] args) {
        URLify urLify = new URLify();

        System.out.println(urLify.solutionString("Jon Jon"));
    }

    public String solutionString(String input) {
        return input.replaceAll(" ", "%20");
    }

}
