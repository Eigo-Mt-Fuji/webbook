/**
 *
 */
package la.webbook.entity;

import java.io.Serializable;

/**
 * @author user
 *
 */
public class PublisherBean implements Serializable {

	private String 	publisherCode;
	private String 	publisherName;


	public PublisherBean() {

	}

	public String getPublisherCode() {
		return publisherCode;
	}
	public void setPublisherCode(String publisherCode) {
		this.publisherCode = publisherCode;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

}
