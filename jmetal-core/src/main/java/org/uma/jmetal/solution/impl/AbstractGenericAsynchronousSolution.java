package org.uma.jmetal.solution.impl;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.AsynchronousSolution;

public abstract class AbstractGenericAsynchronousSolution<Type, P extends Problem> extends AbstractGenericSolution<Type, P>
													   		    				     implements AsynchronousSolution<Type>{
	
	//flag to check whether the solution is already evaluated
	protected boolean evaluated;
	
	protected AbstractGenericAsynchronousSolution() {
		super();
		evaluated = false;
	}
	
	synchronized public void setObjective(int index, double value) {
	    objectives.set(index, value) ;
	}

    synchronized public double getObjective(int index) {
    	if (!isEvaluated())
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// need to report here that the problem is no longer executable
				e.printStackTrace();
			}
	    return objectives.get(index);
	}
    
    synchronized public boolean isEvaluated() {
    	return evaluated;
    }
	
    synchronized public void setAsEvaluated() {
    	evaluated = true;
    	notify(); // releases threads waiting on the getObjective
    }
	
    synchronized public void setAsNonEvaluated() {
    	evaluated = false;
    }
    
    
}
