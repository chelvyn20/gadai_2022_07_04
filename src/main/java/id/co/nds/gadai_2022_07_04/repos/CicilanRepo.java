
package id.co.nds.gadai_2022_07_04.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.keys.KeysTransaksiCicilan;

@Repository
@Transactional
public interface CicilanRepo
		extends JpaRepository<CicilanEntity, KeysTransaksiCicilan>,
		JpaSpecificationExecutor<CicilanEntity> {

	@Query(value = "UPDATE tx_cicilan SET tx_status = 'AKTIF' "
			+ "WHERE no_transaksi= ?1 AND cicilan_ke = ?2 ", nativeQuery = true)
	CicilanEntity UpdateStatusAktif(String noTransaksi, Integer cicilanKe);

	@Query(value = "UPDATE tx_cicilan SET tx_status = 'TERLAMBAT' "
			+ "WHERE no_transaksi= ?1 AND cicilan_ke = ?2 ", nativeQuery = true)
	CicilanEntity UpdateStatusTerlambat(String noTransaksi, Integer cicilanKe);

	@Query(value = "SELECT * FROM tx_cicilan WHERE no_transaksi = ?1 ", nativeQuery = true)
	List<CicilanEntity> findCicilanByTransaksi(String noTransaksi);

	@Query(value = "SELECT * FROM tx_cicilan "
			+ " WHERE no_transaksi = ?1 AND cicilan_ke =?2 ", nativeQuery = true)
	CicilanEntity listCicilanByTransaksicicilanke(String noTransaksi,
			Integer cicilanKe);

	@Modifying
	@Query(value = "UPDATE tx_cicilan set tx_status ='DIBAYAR', "
			+ " no_pembayaran = ?1, tanggal_bayar = ?2 "
			+ "WHERE no_transaksi = ?3 AND cicilan_ke = ?4 ", nativeQuery = true)
	void UpdateCicilan(String noPembayaran, Date Tgl_bayar, String noTransaksi,
			Integer CicilanKe);

}
