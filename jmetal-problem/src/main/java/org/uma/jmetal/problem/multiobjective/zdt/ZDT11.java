//  ZDT1.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.uma.jmetal.problem.multiobjective.zdt;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.impl.GenericDoubleSolution;
import org.uma.jmetal.solution.util.MultithreadedObjectiveManager;
import org.uma.jmetal.util.JMetalException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Class representing problem ZDT1 */
public class ZDT11 extends AbstractDoubleProblem {
  final ExecutorService executor;
  Problem p2 = new ZDT1(1000) ;

  /** Constructor. Creates default instance of problem ZDT1 (30 decision variables) */
  public ZDT11() {
    this(1000);
  }

  /**
   * Creates a new instance of problem ZDT1.
   *
   * @param numberOfVariables Number of variables.
   */
  public ZDT11(Integer numberOfVariables) {
    setNumberOfVariables(numberOfVariables);
    setNumberOfObjectives(2);
    setName("ZDT1");

    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

    for (int i = 0; i < getNumberOfVariables(); i++) {
      lowerLimit.add(0.0);
      upperLimit.add(1.0);
    }

    setLowerLimit(lowerLimit);
    setUpperLimit(upperLimit);

    executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }

  @Override
  public DoubleSolution createSolution() {
    DoubleSolution solution = new GenericDoubleSolution(this);
    solution.setObjectiveManager(new MultithreadedObjectiveManager(1000));

    return solution ;
  }

  /** Evaluate() method */
  public void evaluate(DoubleSolution solution) {
    executor.execute(new MyRunnable(solution, p2));
  }

  /**
   * Returns the value of the ZDT1 function G.
   *
   * @param solution Solution
   */
  private double evalG(DoubleSolution solution) {
    double g = 0.0;
    for (int i = 1; i < solution.getNumberOfVariables(); i++) {
      g += solution.getVariableValue(i);
    }
    double constant = 9.0 / (solution.getNumberOfVariables() - 1);
    g = constant * g;
    g = g + 1.0;
    return g;
  }

  /**
   * Returns the value of the ZDT1 function H.
   *
   * @param f First argument of the function H.
   * @param g Second argument of the function H.
   */
  public double evalH(double f, double g) {
    double h ;
    h = 1.0 - Math.sqrt(f / g);
    return h;
  }

  public class MyRunnable implements Runnable{

    Solution s;
    Problem p;

    public MyRunnable(Solution solution, Problem problem) {
      s = solution;
      p = problem;
    }
    @Override
    public void run() {
      s.getObjectiveManager().toBeEvaluated();
      try {
        p.evaluate(s);
      } catch (JMetalException ex) {
        Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
      }
      s.getObjectiveManager().evaluated();
    }


  }



}
