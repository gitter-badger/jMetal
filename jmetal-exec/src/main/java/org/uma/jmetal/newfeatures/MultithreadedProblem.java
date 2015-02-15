package org.uma.jmetal.newfeatures;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultithreadedProblem<S extends Solution<?>, P extends Problem> {
  private P problem ;
  ExecutorService executor ;

  public MultithreadedProblem(P problem) {
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

    Solution s;
    P p;

    public MyRunnable(Solution solution, P problem) {
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
