
package id.co.nds.gadai_2022_07_04.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;
import id.co.nds.gadai_2022_07_04.models.ProductModel;

public class ProductSpec implements Specification<ProductEntity> {
	private ProductModel productModel;

	public ProductSpec(ProductModel productModel) {
		super();
		this.productModel = productModel;
	}

	@Override
	public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		// TODO Auto-generated method stub

		Predicate p = cb.and();

		if (productModel.getProductid() != null
				&& productModel.getProductid().length() > 0) {

			p.getExpressions().add(cb.equal(root.get("Productid"),
					productModel.getProductid()));
		}

		if (productModel.getProductName() != null
				&& productModel.getProductName().length() > 0) {

			p.getExpressions().add(cb.like(cb.lower(root.get("productName")),
					"%" + productModel.getProductName().toLowerCase() + "%"));
		}

		if (productModel.getProductType() != null
				&& productModel.getProductType().length() > 0) {

			p.getExpressions().add(cb.like(cb.lower(root.get("producttType")),
					"%" + productModel.getProductType().toLowerCase() + "%"));
		}

		if (productModel.getProductLTV() != null
				&& productModel.getProductLTV() != 0) {

			p.getExpressions().add(cb.equal(cb.lower(root.get("productLTV")),
					productModel.getProductLTV()));
		}
		if (productModel.getProductBiayaJasaPeny() != null
				&& productModel.getProductBiayaJasaPeny() != 0) {

			p.getExpressions().add(cb.equal(cb.lower(root.get("productBiayaJasaPeny")),
					productModel.getProductBiayaJasaPeny()));
		}
		
		

		if (productModel.getRecStatus() != null && (productModel.getRecStatus()
				.trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
				|| productModel.getRecStatus().trim().equalsIgnoreCase(
						GlobalConstant.REC_STATUS_NON_ACTIVE))) {
			p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")),
					productModel.getRecStatus().toUpperCase()));
		}
		cq.orderBy(cb.asc(root.get("productId")));

		return p;
	}

}
