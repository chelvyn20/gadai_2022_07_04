
package id.co.nds.gadai_2022_07_04.repos;

import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.ProductEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, String>,
		JpaSpecificationExecutor<ProductEntity> {

	@Query(value = "SELECT * FROM ms_product WHERE product_type = 'Konsinyasi Cicilan Tetap' "+
	"AND product_id=?1", nativeQuery = true)
	List<ProductEntity> findProductCicilanTetap();

	
}
