package org.uma.jmetal.newfeatures;

/**
 * Created by ajnebro on 13/2/15.
 */
public class SequentialObjectiveManager implements ObjectiveManager {
  private double[] objective ;

  public SequentialObjectiveManager(int numberOfObjectives) {
    objective = new double[numberOfObjectives] ;
  }

  @Override
  public double getObjective(int index) {
    return objective[index] ;
  }

  @Override
  public void setObjective(int index, double value) {
    objective[index] = value ;
  }

  @Override
  public int getNumberOfObjectives() {
    return objective.length ;
  }
}
