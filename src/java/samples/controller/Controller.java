package samples.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import samples.data.Response;
import samples.util.JsonUtil;


public abstract class Controller extends ServletController {
	
	/**
	 * Contains all the content and variables that will be used within the template.
	 */
	private Map<String, Object> templateVariables = new HashMap<String, Object>();
	
	/**
	 * Contains a list of errors.
	 */
	private List<String> errorList = new ArrayList<String>();
	private Map<String, String> templateSections = new HashMap<String, String>();
	
	protected void reset() {
		this.templateVariables = new HashMap<String, Object>();
		this.errorList = new ArrayList<String>();
		this.templateSections = new HashMap<String, String>();
	}
	
	public void readParameters() {}
	
	public void readData() {}
	
	public void validate() {}
	
	public void save() {
		this.set("state", "success");
	}
	
	public void assignVariables() {
		
	}
	
	public void assignSections() {
		
	}
	
	public <T> T getParam(String name, Class<T> classType) {
		if (!this.hasParam(name)) return null;
		return JsonUtil.getGson().fromJson(this.getParam(name), classType);
	}
	
	public boolean hasParam(String name) {
		return false;
	}
	
	public Controller addError(String error) {
		this.errorList.add(error);
		return this;
	}
	
	/**
	 * @return true if there was a validation error or false if it wasn't
	 */
	public boolean hasError() {return !this.errorList.isEmpty();}
	
	/**
	 * Sets the value for a template variable.
	 */
	public Controller set(String name, Object value) {
		this.templateVariables.put(name, value);
		return this;
	}
	
	/**
	 * Sets the name of the template that will be displayed in the given section.
	 * 
	 * @param sectionName the name of the section
	 * @param templateName the name of the template that will be inserted within the given section
	 */
	public Controller setTemplate(String sectionName, String templateName) {
		this.templateSections.put(sectionName, templateName);
		return this;
	}
	
	/**
	 * Builds a response.
	 */
	public String getResponse() {
		Response response = new Response();
		response.setErrors(this.errorList);
		response.setTemplateVariables(this.templateVariables);
		response.setTemplateSections(this.templateSections);
		return JsonUtil.toJson(response);
	}
	
}
