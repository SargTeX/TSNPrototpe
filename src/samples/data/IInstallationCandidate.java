/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data;

/**
 *
 * @author SargTeX
 */
public interface IInstallationCandidate {
	
	public IInstallationCandidate install() throws Exception;
	public IInstallationCandidate uninstall() throws Exception;
	
}
