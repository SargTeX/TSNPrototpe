package samples.controller;


public abstract class Controller {
	
	public void readParameters() {}
	
	public void readData() {}
	
	public void validate() {}
	
	public void save() {}
	
	public <T> T getParam(String name, Class<T> classType) {
		if (!this.hasParam(name)) return null;
		return ClassUtil.cast(this.getParam(name), classType);
	}
	
	public boolean hasParam(String name) {
		return false;
	}
	
	public Object getParam(String name) {
		return null;
	}
	
}
