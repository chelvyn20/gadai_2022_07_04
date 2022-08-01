
package id.co.nds.gadai_2022_07_04.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_07_04.entities.DendaEntity;

@Repository
@Transactional
public interface DendaRepo extends JpaRepository<DendaEntity, String>,
		JpaSpecificationExecutor<DendaEntity> {

	@Query(value = "SELECT * FROM tx_denda_keterlambatan WHERE "
			+ " no_transaksi = ?1 AND cicilan_ke = ?2 ", nativeQuery = true)
	List<DendaEntity> listDendaByTransaksiCicilanke(String noTransaksi,
			Integer cicilanKe);

	@Query(value = "SELECT * FROM tx_denda_keterlambatan WHERE "
			+ " no_transaksi = ?1", nativeQuery = true)
	List<DendaEntity> findallByTransaksi(String noTransaksi);

	@Query(value = "SELECT * FROM tx_denda_keterlambatan WHERE "
			+ "no_transaksi = ?1 AND cicilan_ke = ?2 ", nativeQuery = true)
	DendaEntity listDendaByTransaksiKe(String noTransaksi,
			Integer cicilanKe);
	
	
	@Query(value = "SELECT * FROM tx_denda_keterlambatan "+
	" WHERE no_transaksi = '1' AND cicilan_ke = '1'",nativeQuery = true)
	DendaEntity detaildataDenda(String noTransaksi, Integer cicilanKe);

	@Modifying
	@Query(value = "UPDATE tx_denda_keterlambatan set tgl_pembayaran_denda =?1, no_pembayaran =?2 "
			+ " WHERE no_transaksi = ?3 AND cicilan_ke = ?4 ", nativeQuery = true)
	void updateDendaByTransaksiKe(Date tglDenda, String NoPembayaran,
			String noTransaksi, Integer cicilanKe);
}
