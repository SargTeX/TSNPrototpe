jQuery.fn.outerHTML = function(s) {
    return s
        ? this.before(s).remove()
        : jQuery("<p>").append(this.eq(0).clone()).html();
};

jQuery.fn.hasAttr = function(name) {
	return (this.attr(name) !== undefined);
}

$.Class('ElementParser', {
	getContent: function(element, subcontent) {
		
	}
});

$.Class('Theme', {
	
	code: null,
	elementParser: {},
	
	/**
	 * Adds a new element parser to the parser list.
	 * 
	 * @param elementName the tag name of the element this parser works on
	 * @param parser the parser for the element
	 */
	addElementParser: function(elementName, parser) {
		this.elementParser[elementName] = parser;
		return this;
	},
	
	affect: function() {
		
	},
	
	/**
	 * Parses the given content into plain html, css and javascript.
	 */
	parse: function(data) {
		this.code = '<div class="--ui-generic">'+data+'</div>';
		var content = "";
		$.each($(this.code), function(index, elem) {
			content += this.parseElement(elem);
		}.bind(this));
		return content;
	},
	
	/**
	 * Parses one specific element.
	 */
	parseElement: function(element) {
		var tagName = element.tagName;
		element = $(element);
		var content = "";
		$.each(element.children(), function(index, elem) {
			content += this.parseElement(elem);
		}.bind(this));
		
		// get element parser
		if (this.elementParser[tagName] != null) {
			return this.elementParser[tagName].getContent(element, content);
		}
		return element.outerHTML();
	}
	
});

$.Class('Gui', {
	bodySelector: 'body',
	templateUrl: 'templates/',
	mainTemplate: 'main',
	templates: {},
	theme: null,
	currentSections: {},
	variables: {},
		
	init: function(bodySelector) {
		if (bodySelector != undefined) this.bodySelector = bodySelector;
		
		// display main template
		
	},
	
	setSection: function(sectionName, templateName) {
		this.currentSections[sectionName] = templateName;
	},
		
	displayResponseResult: function(response) {
		this.variables = this.getTemplateVariables();
		$.each(response.getTemplateVariables(), function(name, value) {this.variables[name] = value;}.bind(this));
		$.each(response.templateSections, function(section, template) {
			var resultText = this.getCompiledTemplate(template);
			$('#'+section).html(resultText);
			this.theme.affect('#'+section);
		}.bind(this));
	},
	
	getTemplateVariables: function() {
		return {
			gui: this
		};
	},

	getTemplate: function(templateName) {
		if (this.templates[templateName] == undefined) this.loadTemplate(templateName);
		return this.templates[templateName];
	},
	
	getCompiledTemplate: function(templateName) {
		return this.compile(this.getTemplate(templateName), this.variables);
	},

	loadTemplate: function(templateName) {
		$.ajax({
			url: this.templateUrl+templateName+'.tpl',
			async: false
		}).done(function(response) {
			this.templates[templateName] = response;
		}.bind(this));
	},
	
	compile: function(template, variables) {
		var tempFn = doT.template(template);
		var resultText = tempFn(variables);
		resultText = this.theme.parse(resultText);
		return resultText;
	}
		
});
	
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
		this._super(this.baseUrl+controllerName+'Controller');
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
		gui.displayResponseResult(this);
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


var gui = new Gui();