/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SargTeX
 */
public class Response {
	
	private List<String> errors = new ArrayList<String>();
	private String state = "undefined";
	private Map<String, Object> templateVariables = new HashMap<String, Object>();
	private Map<String, String> templateSections = new HashMap<String, String>();
	
	public Response setState(String state) {this.state = state; return this;}
	public Response setErrors(List<String> errors) {this.errors = errors; return this;}
	public Response setTemplateVariables(Map<String, Object> templateVariables) {this.templateVariables = templateVariables; return this;}
	public Response setTemplateSections(Map<String, String> templateSections) {this.templateSections = templateSections; return this;}
	public String getState() {return this.state;}
	public List<String> getErrors() {return this.errors;}
	public Map<String, Object> getTemplateVariables() {return this.templateVariables;}
	public Map<String, String> getTemplateSections() {return this.templateSections;}
	
}
