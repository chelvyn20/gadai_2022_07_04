
package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.InformasiTransaksiEntity;

@Repository
public interface InformasiTransaksiRepo
		extends JpaRepository<InformasiTransaksiEntity, String>,
		JpaSpecificationExecutor<InformasiTransaksiEntity> {

	@Query(value = " SELECT * fROM tx_transaksi_cicilan_tetap AS t JOIN ms_product AS p ON  p.product_id = t.product_id JOIN ms_customer AS c ON c.customer_id = t.customer_id	WHERE t.no_transaksi= ?1 ", nativeQuery = true)
	InformasiTransaksiEntity findTransaksiByProductCustomer(String noTransaksi);
}
