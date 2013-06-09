$.Class('Request',
	{
		url: '',
		response: null,
		
		init: function(url) {
			this.url = url;
		},
		
		getUrl: function() {
			return this.url;
		},
		
		execute: function() {
			$.ajax(this.url, {
				dataType: 'json'
			}).done(function(data) {
				this.executed(data);
			}.bind(this));
		},
		
		executed: function(data) {
			this.response = new Response(data);
		}
	}
);
	
Request('ControllerRequest', {
	baseUrl: 'http://localhost:8084/TSN/',
		
	init: function(controllerName) {
		if (controllerName.substring(0, 4) == 'http') controllerName = controllerName.substring(this.baseUrl.length, controllerName.length-'Controller'.length);
		if (!controllerName.contains("?") && !controllerName.endsWith('Controller')) controllerName += 'Controller';
		this._super(this.baseUrl+controllerName);
	},
		
	getBaseUrl: function() {
		return this.baseUrl;
	},
		
	setBaseUrl: function(baseUrl) {
		this.baseUrl = baseUrl;
	},
		
	executed: function(data) {
		this.response = new ControllerResponse(data);
	}
});
	
$.Class('Response', {
	data: {},
	
	init: function(data) {
		this.data = data;
	},
	
	getResponseText: function() {
		return this.data;
	}
});

Response('ControllerResponse', {
	
	errors: [],
	state: undefined,
	templateVariables: {},
	templateSections: {},
	
	init: function(data) {
		this._super(data);
		
		this.analyse();
		this.display();
	},
	
	/**
	 * Analyses the response.
	 * E.g. looks for errors and template variables.
	 */
	analyse: function() {
		if (this.data.errors != null) this.setErrors(this.data.errors);
		this.setState(this.data.state);
		if (this.data.templateVariables != null) this.setTemplateVariables(this.data.templateVariables);
		//this.setTemplateName(this.data.templateName);
		this.templateSections = this.data.templateSections;
	},
	
	display: function() {
		core.getGui().displayResponseResult(this);
	},

	setState: function(state) {this.state = state; return this;},
	setErrors: function(errors) {this.errors = errors; return this;},
	setTemplateVariables: function(templateVariables) {this.templateVariables = templateVariables; return this;},
	setTemplateName: function(templateName) {this.templateName = templateName; return this;},
	
	hasErrors: function() {return this.errors.length > 0;},
	getErrors: function() {return this.errors;},
	getState: function() {return this.state;},
	getTemplateVariables: function() {return this.templateVariables;},
	getTemplateName: function() {return this.templateName;}
	
});