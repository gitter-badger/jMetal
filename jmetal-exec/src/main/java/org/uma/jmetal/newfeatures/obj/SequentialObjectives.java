package org.uma.jmetal.newfeatures.obj;

/**
 * Created by ajnebro on 14/2/15.
 */
public class SequentialObjectives implements Objectives {
  double[] objective ;

  public SequentialObjectives(int numberOfObjectives) {
    objective = new double[numberOfObjectives] ;
  }

  @Override public double getObjective(int index) {
    return objective[index];
  }

  @Override public void setObjective(int index, double value) {
    objective[index] = value ;
  }

  @Override public int getNumberOfObjectives() {
    return objective.length;
  }
}
