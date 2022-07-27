
package id.co.nds.gadai_2022_07_04.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.keys.KeysTransaksiCicilan;

@Repository
public interface CicilanRepo
		extends JpaRepository<CicilanEntity, KeysTransaksiCicilan>,
		JpaSpecificationExecutor<CicilanEntity> {

	@Query(value = "UPDATE tx_cicilan SET tx_status = 'AKTIF' "
			+ "WHERE no_transaksi= ?1 AND cicilan_ke = ?2 ", nativeQuery = true)
	CicilanEntity UpdateStatusAktif(String noTransaksi, Integer cicilanKe);

	@Query(value = "UPDATE tx_cicilan SET tx_status = 'TERLAMBAT' "
			+ "WHERE no_transaksi= ?1 AND cicilan_ke = ?2 ", nativeQuery = true)
	CicilanEntity UpdateStatusTerlambat(String noTransaksi, Integer cicilanKe);

}
