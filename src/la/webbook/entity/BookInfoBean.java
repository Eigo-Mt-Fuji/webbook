/**
 *
 */
package la.webbook.entity;

import java.io.Serializable;

/**
 * @author user
 *
 */
public class BookInfoBean implements Serializable {

	private String bookinfoIsbn ; //CHAR(13) PRIMARY KEY,
	private String categoryCode;// CHAR(2) NOT NULL REFERENCES Category,
	private String publisherCode;// CHAR(2) NOT NULL REFERENCES Publisher,
	private String bookinfoName;// VARCHAR(100) NOT NULL,
	private String bookinfoAuthor;// VARCHAR(20) NOT NULL


	public BookInfoBean() {
	}
	public String getBookinfoIsbn() {
		return bookinfoIsbn;
	}
	public void setBookinfoIsbn(String bookinfoIsbn) {
		this.bookinfoIsbn = bookinfoIsbn;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getPublisherCode() {
		return publisherCode;
	}
	public void setPublisherCode(String publisherCode) {
		this.publisherCode = publisherCode;
	}
	public String getBookinfoName() {
		return bookinfoName;
	}
	public void setBookinfoName(String bookinfoName) {
		this.bookinfoName = bookinfoName;
	}
	public String getBookinfoAuthor() {
		return bookinfoAuthor;
	}
	public void setBookinfoAuthor(String bookinfoAuthor) {
		this.bookinfoAuthor = bookinfoAuthor;
	}
}
