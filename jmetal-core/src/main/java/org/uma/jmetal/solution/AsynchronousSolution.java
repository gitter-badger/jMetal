package org.uma.jmetal.solution;

/* 
 * author: Juan J. Durillo 
 * 
 */
public interface AsynchronousSolution<Type> extends Solution<Type> {
	public boolean isEvaluated();
	public void setAsEvaluated();
	public void setAsNonEvaluated();
}
