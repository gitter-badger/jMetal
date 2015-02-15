package org.uma.jmetal.newfeatures.obj;

import org.uma.jmetal.solution.Solution;

/**
 * Created by ajnebro on 14/2/15.
 */
public class MultithreadedObjectives implements Objectives {
  private double[] objective ;
  private Solution solution ;

  public MultithreadedObjectives(int numberOfObjectives, Solution solution) {
    objective = new double[numberOfObjectives] ;
    this.solution = solution ;
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
