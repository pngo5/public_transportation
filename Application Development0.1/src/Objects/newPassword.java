package Objects;

/**
 * 
 * @author coleman
 * 
 *         this class is for resetting user passwords
 *
 */

public class newPassword {
	
	private String newPassword;
	private String confirmPassword;
	
	public newPassword(String newPassword, String confirmPassword) {
		super();
		this.newPassword=newPassword;
		this.confirmPassword=confirmPassword;
		
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public String getconfirmPassword() {
		return confirmPassword;
	}
	
	public String setNewPassword() {
		return newPassword;
	}
	
	public String setconfirmPassword() {
		return confirmPassword;
	}
}



