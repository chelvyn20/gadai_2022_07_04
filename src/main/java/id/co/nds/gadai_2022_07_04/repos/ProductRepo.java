package id.co.nds.gadai_2022_07_04.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {
    @Query(value = "SELECT COUNT(*) FROM ms_product WHERE rec_status = '" + 
    GlobalConstant.REC_STATUS_ACTIVE + "' AND LOWER(product_id) = LOWER(:product_id)", nativeQuery = true)
    long countByProductId(@Param("product_id") String productId);
}

