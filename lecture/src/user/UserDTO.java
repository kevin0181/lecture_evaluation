package user;

public class UserDTO {

	private String userID;
	private String userPassword;
	private String userEmail;
	private String userEmailHash;
	private boolean userEmailChecked;

	public String getUserID() {
//		System.out.println("userID-get");
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
//		System.out.println("userPassword-get");
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
//		System.out.println("userEmail-get");
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmailHash() {
//		System.out.println("userEmailHash-get");
		return userEmailHash;
	}

	public void setUserEmailHash(String userEmailHash) {
		this.userEmailHash = userEmailHash;
	}

	public boolean isUserEmailChecked() {
//		System.out.println("userEmailchecked-get");
		return userEmailChecked;
	}

	public void setUserEmailChecked(boolean userEmailChecked) {
		this.userEmailChecked = userEmailChecked;
	}

	public UserDTO() {

	}

	public UserDTO(String userID, String userPassword, String userEmail, String userEmailHash,
			boolean userEmailChecked) {
//		System.out.println(userID);
//		System.out.println(userPassword);
//		System.out.println(userEmail);
//		System.out.println(userEmailHash);
//		System.out.println(userEmailChecked);
		this.userID = userID;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userEmailHash = userEmailHash;
		this.userEmailChecked = userEmailChecked;
	}

}
