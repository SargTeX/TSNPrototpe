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
	
	public boolean exists();
	
	public IDataModel create() throws SQLException;
	public IDataModel remove() throws SQLException;
	public IDataModel read() throws SQLException;
	public IDataModel update() throws SQLException;
	
}
