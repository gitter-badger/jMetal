package org.uma.jmetal.solution.util;

/**
 * Created by ajnebro on 13/2/15.
 */
public class SequentialObjectiveManager implements ObjectiveManager {
  private double[] objective ;
  boolean isEvaluated ;

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

  @Override public void toBeEvaluated() {
    isEvaluated = false ;
  }

  @Override public boolean isEvaluated() {
    return isEvaluated;
  }

  @Override public void evaluated() {
    isEvaluated = true ;
  }
}
