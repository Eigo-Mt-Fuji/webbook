/**
 *
 */
package la.webbook.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author user
 *
 */
public class MemberBean implements Serializable {

	private int userId; //SERIAL PRIMARY KEY,
	private String userFamilyName; //user_family_name VARCHAR(10) NOT NULL,
	private String userName; // user_name VARCHAR(10) NOT NULL,
	private String userPostal; // user_postal CHAR(7) NOT NULL,
	private String userAddress; // user_address VARCHAR(100) NOT NULL,
	private String userEmail; // user_email VARCHAR(100) NOT NULL,
	private String userTel; // user_tel VARCHAR(20) NOT NULL,
	private Date userBirthday; // user_birthday DATE NOT NULL,
	private String userPassword; // user_password VARCHAR(12) DEFAULT 'himitu' NOT NULL,
	private String userRole; // user_role CHAR(1) NOT NULL


	public MemberBean() {

	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserFamilyName() {
		return userFamilyName;
	}
	public void setUserFamilyName(String userFamilyName) {
		this.userFamilyName = userFamilyName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPostal() {
		return userPostal;
	}
	public void setUserPostal(String userPostal) {
		this.userPostal = userPostal;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


}
