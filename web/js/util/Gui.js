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