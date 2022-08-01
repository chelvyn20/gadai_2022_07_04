
package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.TransaksiGetDetailEntity;
@Repository
public interface GetDetailTransaksiRepo
		extends JpaRepository<TransaksiGetDetailEntity, String> {

	@Query(value = "select * from tx_transaksi_cicilan_tetap AS t "
			+ " JOIN ms_product AS p ON t.product_id = p.product_id "
			+ " JOIN ms_customer AS c ON t.customer_id = c.customer_id "
			+ " WHERE t.no_transaksi = ?1", nativeQuery = true)
	List<TransaksiGetDetailEntity> findTransaksibyBarangProductCustomer(
			String noTransaksi);
}
