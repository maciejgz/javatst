package pl.mg.javatst.certificate.oca.lambda;

import pl.mg.javatst.java8.behaviourparametrization.BehaviourParametrization;

import java.util.ArrayList;
import java.util.List;

public class CelebrityTester {

  public static void main(String[] args) {
    List<Celebrity> celebrities = new ArrayList<>();
    Celebrity celebrity1 = new Celebrity("singer", true, false);
    Celebrity dancer1 = new Celebrity("dancer", false, true);
    celebrities.add(celebrity1);
    celebrities.add(dancer1);
    print(celebrities, a -> a.isCanSing());

    printWithCustomInterface(celebrities, a -> a.isCanDance());

    simplePrint(celebrities);

    someStreamTest(celebrities);
  }

  private static void someStreamTest(List<Celebrity> celebrities) {
    System.out.println(celebrities.stream().filter(c -> c.isCanDance()).count());
  }

  private static void simplePrint(List<Celebrity> celebrities) {
    celebrities.forEach(
        (celebrity -> {
          System.out.println(celebrity.toString() + " ");
        }));
  }

  public static void print(
      List<Celebrity> celebrities, BehaviourParametrization.Predicate<Celebrity> talentChecker) {
    for (Celebrity celebrity : celebrities) {
      if (talentChecker.test(celebrity)) {
        System.out.println(celebrity.toString());
      }
    }
  }

  public static void printWithCustomInterface(
      List<Celebrity> celebrities, CheckTalent talentChecker) {

    for (Celebrity celebrity : celebrities) {
      if (talentChecker.test(celebrity)) {
        System.out.println(celebrity.toString());
      }
    }
  }
}
