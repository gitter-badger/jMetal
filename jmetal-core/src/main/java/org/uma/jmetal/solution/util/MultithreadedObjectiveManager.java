package org.uma.jmetal.solution.util;

/**
 * Created by ajnebro on 13/2/15.
 */
public class MultithreadedObjectiveManager implements ObjectiveManager {
  private double[] objective ;
  protected boolean evaluated;

  public MultithreadedObjectiveManager(int numberOfObjectives) {
    objective = new double[numberOfObjectives] ;
  }


  @Override
  public synchronized double getObjective(int index) {
    if (!this.isEvaluated())
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    return objective[index] ;
  }

  @Override
  public synchronized void setObjective(int index, double value) {
    objective[index] = value ;
  }

  @Override
  public int getNumberOfObjectives() {
    return objective.length ;
  }

  @Override
  synchronized public boolean isEvaluated() {
    return evaluated;
  }

  @Override
  synchronized public void evaluated() {
    evaluated = true;
    notify(); // releases threads waiting on the getObjective
  }

  @Override
  synchronized public void toBeEvaluated() {
    evaluated = false;
  }
}
