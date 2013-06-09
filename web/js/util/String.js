String.prototype.endsWith = function(pattern) {
    var d = this.length - pattern.length;
    return d >= 0 && this.lastIndexOf(pattern) === d;
};

String.prototype.contains = function(pattern) {
	return this.indexOf(pattern) !== -1;
};