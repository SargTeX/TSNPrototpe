package samples.controller;

import samples.data.model.User;
import samples.data.model.Userlist;


public class AddUserToUserlistController extends Controller {
	
	private User user;
	private Userlist userlist;
	
	@Override
	public void readParameters() {
		if (this.hasParam("user")) this.user = this.getParam("user", User.class);
	//	else this.user = Core.getSession().getUser();
		
		// either object or null
		this.userlist = this.getParam("userlist", Userlist.class);
	}
	
	public void readData() {}
	
	@Override
	public void validate() {
		if (this.userlist == null) this.addError("input.userlist.empty");
		
		// check permissions
	//	Core.getUser().checkPermission("userlist.canAddUserToList", user, userlist);
	}
	
	@Override
	public void save() {
		System.out.println("Hello World!");
	}
	
}
