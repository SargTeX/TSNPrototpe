Theme('JQueryUiTheme', {
	
	init: function() {
		this.addElementParser('DIV', new DivElementParser());
		this.addElementParser('FORM', new FormElementParser());
		this.addElementParser('INPUT', new InputElementParser());
		this.addElementParser('MENU', new MenuElementParser());
	},
	
	affect: function(section) {
		$.fn.formLabels();
		$('.menu').menu();
		$('a').click(function() {
			var link = $(this).attr('href');
			new ControllerRequest(link).execute();
			return false;
		});
		$('form').ajaxForm();
	}
	
});

ElementParser('DivElementParser', {
	getContent: function(element, subcontent) {
		if (element.hasClass('--ui-generic')) return subcontent;
		var content = "<div";
		$.each($(element).getAttributes(), function(name, value) {
			content += ' '+name+'="'+value+'"';
		});
		content += ">"+subcontent+"</div>";
		return content;
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
		var content = '<input type="'+element.attr('type')+'" name="'+element.attr('name')+'" title="'+element.attr('label')+'"';
		if (element.hasAttr('value'))  content += ' value="'+element.attr('value')+'"';
		content += ' />';
		
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