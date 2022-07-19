
package id.co.nds.gadai_2022_07_04.repos;

import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, String>,
		JpaSpecificationExecutor<ProductEntity> {

	// 		@Query(value = "SELECT COUNT(*) FROM ms_product WHERE rec_status = 'A' "
	// 		+ " AND LOWER(user_phone) = LOWER(:user_phone)", nativeQuery = true)
	// long countByPhone(@Param("user_phone") String userPhone);
}
