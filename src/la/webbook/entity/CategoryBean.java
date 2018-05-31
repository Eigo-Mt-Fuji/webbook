/**
 *
 */
package la.webbook.entity;

import java.io.Serializable;

/**
 * @author user
 *
 */
public class CategoryBean implements Serializable {

	private String categoryCode;
	private String categoryName;


	public CategoryBean() {

	}

	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



}
