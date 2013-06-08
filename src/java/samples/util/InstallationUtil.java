/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.util;

import samples.data.IInstallationCandidate;
import samples.data.model.Session;
import samples.data.model.User;

/**
 *
 * @author SargTeX
 */
public class InstallationUtil {
	
	//<editor-fold defaultstate="collapsed" desc="Attributes">
	private static final String[] classes = new String[] {};
	private static final IInstallationCandidate[] installationCandidates = new IInstallationCandidate[] {
		new User(),
		new Session()
	};
	//</editor-fold>
	
	//<editor-fold defaultstate="collapsed" desc="Methods">
	/**
	 * Installs the system.
	 */
	public static void install() throws Exception {
		for (IInstallationCandidate candidate : installationCandidates) {
			candidate.install();
		}
	}
	
	/**
	 * Installs the system.
	 */
	public static void uninstall() throws Exception {
		for (IInstallationCandidate candidate : installationCandidates) {
			candidate.uninstall();
		}
	}
	
}
