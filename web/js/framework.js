$.Class('Core', {
	
	gui: null,
	getGui: function() {return this.gui;},
	
	/**
	 * Imports a javascript file.
	 */
	load: function(name) {
		if (!$.isArray(name)) name = [name];
		for (var i = 0; i < name.length; ++i) {
			var value = name[i];
			$.ajax({
				url: 'js/'+value+'.js',
				datatype: 'script',
				async: false
			});
		}
		
		return this;
	},
	
	/**
	 * Loads a theme.
	 */
	loadTheme: function(file, themeCreateFn) {
		if (this.gui == null) this.gui = new Gui();
		console.log(this.gui);
		this.load(file);
		this.gui.theme = themeCreateFn();
		return this;
	}
	
});

var core = new Core();
