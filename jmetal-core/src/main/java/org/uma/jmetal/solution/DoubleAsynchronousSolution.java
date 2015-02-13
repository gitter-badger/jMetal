package org.uma.jmetal.solution;

/**
 * @author Juanjo
 * @version 0.1
 */
public interface DoubleAsynchronousSolution extends AsynchronousSolution<Double> {
  public Double getLowerBound(int index) ;
  public Double getUpperBound(int index) ;
 }
