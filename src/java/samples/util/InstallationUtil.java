/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.util;

import samples.data.IInstallationCandidate;
import samples.data.model.Language;
import samples.data.model.LanguageItem;
import samples.data.model.LanguageItemTranslation;
import samples.data.model.Permission;
import samples.data.model.PermissionValue;
import samples.data.model.Session;
import samples.data.model.User;
import samples.data.model.UserToUserlist;
import samples.data.model.Usergroup;
import samples.data.model.Userlist;

/**
 *
 * @author SargTeX
 */
public class InstallationUtil {
	
	//<editor-fold defaultstate="collapsed" desc="Attributes">
	private static final String[] classes = new String[] {};
	private static final IInstallationCandidate[] installationCandidates = new IInstallationCandidate[] {
		new Language(),
		new LanguageItem(),
		new LanguageItemTranslation(),
		new Permission(),
		new PermissionValue(),
		new Session(),
		new User(),
		new UserToUserlist(),
		new Usergroup(),
		new Userlist()
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
