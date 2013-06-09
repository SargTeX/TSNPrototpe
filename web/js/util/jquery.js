(function($) {
	$.fn.outerHTML = function(s) {
		return s
		? this.before(s).remove()
		: jQuery("<p>").append(this.eq(0).clone()).html();
	};

	$.fn.hasAttr = function(name) {
		return (this.attr(name) !== undefined);
	};

	$.fn.getAttributes = function() {
		var attributes = {};
		if (this.length) {
			$.each(this[0].attributes, function(index, attr) {
				attributes[attr.name] = attr.value;
			});
		}
		return attributes;
	};

	$.expr[':'].parents = function(a,i,m){
		return jQuery(a).parents(m[3]).length < 1;
	};
})(jQuery);