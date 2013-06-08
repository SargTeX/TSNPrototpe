/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data.model;

import java.sql.SQLException;
import samples.data.IInstallationCandidate;

/**
 *
 * @author SargTeX
 */
public interface IDataModel extends IInstallationCandidate {
	
	public void create() throws SQLException;
	public void remove() throws SQLException;
	public void read() throws SQLException;
	public void update() throws SQLException;
	
}
