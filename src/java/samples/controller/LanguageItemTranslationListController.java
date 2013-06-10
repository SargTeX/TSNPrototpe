/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import samples.data.model.LanguageItem;
import samples.util.DebugUtil;

/**
 *
 * @author SargTeX
 */
public class LanguageItemTranslationListController extends Controller {
	
	private String languageCode;
	private LanguageItem[] items;
	
	@Override
	public void readParameters() {
		languageCode = getParam("languageCode");
	}
	
	@Override
	public void readData() {
		try {
			items = LanguageItem.getAll();
			for (LanguageItem languageItem : items) {
				languageItem.loadTranslation(languageCode);
			}
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
		setTemplate("content", "languageItemTranslationList");
	}
	
	@Override
	public void assignVariables() {
		set("languageCode", languageCode);
		set("items", items);
	}
	
}
