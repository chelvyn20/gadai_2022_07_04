
package id.co.nds.gadai_2022_07_04.entities;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ms_product")
public class ProductGetListEntity {
	
	@Id
	@Column(name = "product_id")
	private String productId;
	
	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_desc")
	private String productDesc;

	public String getProductId() { return productId; }

	public void setProductId(String productId) { this.productId = productId; }

	public String getProductName() { return productName; }

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() { return productDesc; }

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Optional<ProductEntity> findById(String id) { return null; }
}
