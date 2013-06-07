Theme('JQueryUITheme', {
	
	init: function() {
		this.addElementParser('FORM', new FormElementParser());
		this.addElementParser('INPUT', new InputElementParser());
		this.addElementParser('MENU', new MenuElementParser());
	},
	
	affect: function(section) {
		console.log("affect");
		$.fn.formLabels();
		$('.menu').menu();
	}
	
});

ElementParser('FormElementParser', {
	getContent: function(element, subcontent) {
		var content = '<form method="post" action="'+new ControllerRequest(element.attr('controller')).getUrl()+'">';
		content += subcontent;
		content += '</form>'
		return content;
	}
});

ElementParser('InputElementParser', {
	getContent: function(element) {
		var content = '<input type="'+element.attr('type')+'" name="'+element.attr('name')+'" title="'+element.attr('label')+'" />';
		
		return content;
	}
});

ElementParser('MenuElementParser', {
	getContent: function(element, subcontent) {
		var content = "<ul class='menu'>";
		$.each(element.children(), function(index, child) {
			child = $(child);
			content += "<li><a href='"+new ControllerRequest(child.attr('controller')).getUrl()+"'>"+child.text()+"</a></li>";
		}.bind(this));
		content += "</ul>";
		return content;
	}
});



gui.theme = new JQueryUITheme();