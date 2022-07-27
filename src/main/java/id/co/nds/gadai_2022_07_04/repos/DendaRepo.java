
package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.DendaEntity;

@Repository
public interface DendaRepo extends JpaRepository<DendaEntity, String>,
		JpaSpecificationExecutor<DendaEntity> {

	@Query(value = "SELECT * FROM tx_denda_keterlambatan WHERE "
			+ "no_transaksi = ?1 AND cicilan_ke = ?2 ", nativeQuery = true)
	List<DendaEntity> listDendaByTransaksiCicilanke(String noTransaksi,
			Integer cicilanKe);

}
