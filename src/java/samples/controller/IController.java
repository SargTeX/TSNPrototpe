/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

/**
 *
 * @author SargTeX
 */
public interface IController {
	
	public void readParameters();
	public void readData();
	public void validate();
	public void save();
	public void assignVariables();
	public void assignSections();
	
	public boolean hasError();
	public IController addError(String error);
	public String getResponse();
	
}
