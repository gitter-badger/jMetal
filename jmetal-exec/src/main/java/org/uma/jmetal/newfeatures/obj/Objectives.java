package org.uma.jmetal.newfeatures.obj;

/**
 * Created by ajnebro on 14/2/15.
 */
public interface Objectives {
  public double getObjective(int index) ;
  public void setObjective(int index, double value) ;
  public int getNumberOfObjectives() ;
}
