/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import samples.data.model.LanguageItemTranslation;
import samples.util.DebugUtil;
import samples.util.StringUtil;

/**
 *
 * @author SargTeX
 */
public class LanguageItemTranslateController extends Controller {
	
	private String languageCode;
	private String itemName;
	private String translation;
	
	@Override
	public void readParameters() {
		languageCode = getParam("languageCode");
		itemName = getParam("itemName");
		translation = getParam("translation");
	}
	
	@Override
	public void validate() {
		if (StringUtil.isEmpty(languageCode)) this.addError("input.languageCode.empty");
		if (StringUtil.isEmpty(itemName)) this.addError("input.itemName.empty");
	}
	
	@Override
	public void save() {
		try {
			LanguageItemTranslation translation = LanguageItemTranslation.find("languageCode = ? AND itemName = ?", languageCode, itemName);
			if (translation == null) {
				translation = new LanguageItemTranslation();
				translation.setLanguageCode(languageCode).setItemName(itemName).setTranslation(this.translation).create();
			} else translation.setTranslation(this.translation).update();
		} catch (SQLException ex) {
			this.addError(ex);
			DebugUtil.log(ex);
		}
	}
	
	@Override
	public void assignSections() {
//		setTemplate("content", "languageItemTranslate");
	}
	
	@Override
	public void assignVariables() {
		set("languageCode", languageCode);
		set("itemName", itemName);
		set("translation", translation);
	}
	
}
