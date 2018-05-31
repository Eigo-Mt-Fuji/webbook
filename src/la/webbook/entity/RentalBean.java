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
public class RentalBean implements Serializable {

	private int rentalId ; // SERIAL NOT NULL,
	private int bookstateId; // INTEGER NOT NULL REFERENCES BookState,
	private int userId;// INTEGER NOT NULL REFERENCES Member,
	private Date rentalRent;// DATE NOT NULL,
	private Date rentalReturn;// DATE


	public RentalBean() {

	}
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public int getBookstateId() {
		return bookstateId;
	}
	public void setBookstateId(int bookstateId) {
		this.bookstateId = bookstateId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getRentalRent() {
		return rentalRent;
	}
	public void setRentalRent(Date rentalRent) {
		this.rentalRent = rentalRent;
	}
	public Date getRentalReturn() {
		return rentalReturn;
	}
	public void setRentalReturn(Date rentalReturn) {
		this.rentalReturn = rentalReturn;
	}


}
