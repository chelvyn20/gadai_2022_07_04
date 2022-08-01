
package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.TransaksiBarangEntity;
import id.co.nds.gadai_2022_07_04.keys.KeysGadai;

@Repository
public interface TransaksiBarangRepo
		extends JpaRepository<TransaksiBarangEntity, KeysGadai>,
		JpaSpecificationExecutor<TransaksiBarangEntity> {

	static boolean existsById(String string) { return false; }

	@Query(value = "SELECT * FROM tx_transaksi_barang WHERE no_transaksi = ?1"
	, nativeQuery = true)
	List<TransaksiBarangEntity> findNoTransaksi(String noTransaksi);
	
}
