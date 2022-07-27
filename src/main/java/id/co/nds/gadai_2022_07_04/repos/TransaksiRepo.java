
package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.TransaksiCicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;

@Repository
public interface TransaksiRepo
		extends JpaRepository<TransaksiCicilanTetapEntity, String>,
		JpaSpecificationExecutor<TransaksiCicilanTetapEntity> {

	@Query(value = "SELECT * fROM tx_transaksi_cicilan_tetap AS t  JOIN ms_product AS p ON  p.product_id = t.product_id JOIN ms_customer AS c ON c.customer_id = t.customer_id ", nativeQuery = true)
	List<TransaksiCicilanTetapEntity> findAllTransaksiCustomerProduct();

	@Query(value = "SELECT COUNT (*) FROM tx_transaksi_cicilan_tetap "
			 + " WHERE LOWER(customer_id) = LOWER (:id)", nativeQuery = true)
	long countById(@Param("id") String id);
	
	@Query(value = "SELECT * FROM tx_transaksi_cicilan_tetap WHERE no_transaksi =?1", nativeQuery = true)
	List<TransaksiCicilanTetapEntity> findallByNoTransaksi(String noTransaksi);
}
