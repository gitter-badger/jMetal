package org.uma.jmetal.solution.util;

/**
 * Created by ajnebro on 13/2/15.
 */
public interface ObjectiveManager {
  public double getObjective(int index) ;
  public void setObjective(int index, double value) ;
  public int getNumberOfObjectives() ;

  public void toBeEvaluated() ;
  public boolean isEvaluated() ;
  public void evaluated() ;
}
