package org.uma.jmetal.newfeatures;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.problem.impl.AbstractGenericProblem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultithreadedProblem<S extends Solution<?>> extends AbstractGenericProblem<S> {
  private AbstractGenericProblem problem ;
  ExecutorService executor ;

  public MultithreadedProblem(AbstractGenericProblem problem) {
    this.problem = problem ;
    executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) ;
  }

  @Override public void evaluate(S solution) {
     executor.execute(new MyRunnable(solution, problem));
  }

  @Override public S createSolution() {
    return (S)problem.createSolution();
  }

  private class MyRunnable implements Runnable{

    Solution s;
    Problem p;

    public MyRunnable(Solution solution, Problem problem) {
      s = solution;
      p = problem;
    }
    @Override
    public void run() {
      try {
        p.evaluate(s);
      } catch (JMetalException ex) {
        Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
      }
      //s.setEvaluated();
    }

  }

}
