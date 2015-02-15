package org.uma.jmetal.newfeatures;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.problem.impl.AbstractGenericProblem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultithreadedProblem2<S extends Solution<?>> extends AbstractGenericProblem<S> {
  private Problem<S> problem ;
  ExecutorService executor ;

  public MultithreadedProblem2(Problem<S> problem) {
    this.problem = problem ;
    executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) ;
  }

   public void evaluate(S solution) {
     executor.execute(new MyRunnable(solution, problem));
  }

  public S createSolution() {
    return (S)problem.createSolution();
  }




  private class MyRunnable implements Runnable{

    S s;
    Problem p;

    public MyRunnable(S solution, Problem problem) {
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

  public void finalize() {
    executor.shutdown();
  }
}
