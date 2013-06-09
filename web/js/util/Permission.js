$.Class('PermissionUtilClass', {
	getSetTemplate: function(permission, value) {
		var type = permission.type;
		var templateName = "input.text";
		if (type == 'string') templateName = "input.text";
		else if (type == 'password') templateName = "input.password";
		else if (type == 'email') templateName = "input.email";
		else if (type == 'boolean') templateName = "input.boolean";
		
		// compile template and return compiled
		return core.getGui().getCompiledTemplate(templateName);
	}
});

var PermissionUtil = new PermissionUtilClass();