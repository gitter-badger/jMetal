package org.uma.jmetal.newfeatures;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.impl.AbstractGenericSolution;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ajnebro on 13/2/15.
 */
public class ConcurrentSolution extends AbstractGenericSolution {
  private boolean isEvaluated ;
  private Solution solution ;

  public ConcurrentSolution(Solution solution) {
    this.solution = solution ;
  }

  @Override
  synchronized public double getObjective(int index) {
    if (this.isEvaluated()) {
      try {
        wait() ;
      } catch (InterruptedException e) {
        Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, e);
      }
    }
    return solution.getObjective(index);
  }

  @Override public String getVariableValueString(int index) {
    return null;
  }

  @Override public Solution copy() {
    return null ;
  }

  synchronized public boolean isEvaluated() {
    return isEvaluated;
  }

  synchronized public void setEvaluated () {
    isEvaluated = true;
    notifyAll();
  }

  synchronized public void setUnEvaluated() {
    isEvaluated = false;
  }

}
