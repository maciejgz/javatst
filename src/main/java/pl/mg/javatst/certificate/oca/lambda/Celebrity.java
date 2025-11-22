package pl.mg.javatst.certificate.oca.lambda;

import java.io.Serializable;

public class Celebrity implements Serializable {
  private String name;
  private boolean canSing;
  private boolean canDance;

  public Celebrity(String name, boolean canSing, boolean canDance) {
    this.name = name;
    this.canSing = canSing;
    this.canDance = canDance;
  }

  @Override
  public String toString() {
    return "Celebrity{"
        + "name='"
        + name
        + '\''
        + ", canSing="
        + canSing
        + ", canDance="
        + canDance
        + '}';
  }

  public boolean isCanDance() {
    return canDance;
  }

  public void setCanDance(boolean canDance) {
    this.canDance = canDance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isCanSing() {
    return canSing;
  }

  public void setCanSing(boolean canSing) {
    this.canSing = canSing;
  }
}
