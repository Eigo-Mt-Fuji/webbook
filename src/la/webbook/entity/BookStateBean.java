/**
 *
 */
package la.webbook.entity;

import java.io.Serializable;

/**
 * @author user
 *
 */
public class BookStateBean implements Serializable {

	private int bookstateId;
	private String bookinfoIsbn;


	public BookStateBean() {

	}

	public int getBookstateId() {
		return bookstateId;
	}
	public void setBookstateId(int bookstateId) {
		this.bookstateId = bookstateId;
	}
	public String getBookinfoIsbn() {
		return bookinfoIsbn;
	}
	public void setBookinfoIsbn(String bookinfoIsbn) {
		this.bookinfoIsbn = bookinfoIsbn;
	}


}
