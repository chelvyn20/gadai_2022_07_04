
package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.TransaksiInfoEntity;

@Repository
public interface TransaksiInfoRepo
		extends JpaRepository<TransaksiInfoEntity, String>,
		JpaSpecificationExecutor<TransaksiInfoEntity> {

	@Query(value = "select t.*, p.product_name as product_name from tx_transaksi_cicilan_tetap AS t "
			+ " JOIN ms_product AS p ON t.product_id = p.product_id "
			+ " JOIN ms_customer AS c ON t.customer_id = c.customer_id "
			+ " WHERE t.no_transaksi = ?1 AND t.product_id = ?2 AND t.customer_id = ?3 ", nativeQuery = true)
			List<TransaksiInfoEntity> findTransaksibyBarangProductCustomer(
					String noTransaksi, String productId, String customerId);
			
	@Query(value = "select t.*, p.product_name as product_name from tx_transaksi_cicilan_tetap AS t "
	+ " JOIN ms_product AS p ON t.product_id = p.product_id "
	+ " JOIN ms_customer AS c ON t.customer_id = c.customer_id "
	+ " WHERE t.no_transaksi = ?1 AND t.customer_id = ?2 ",nativeQuery = true)
	List<TransaksiInfoEntity> findTransaksibyTransaksiCustomer(
			String noTransaksi, String custId);
}
