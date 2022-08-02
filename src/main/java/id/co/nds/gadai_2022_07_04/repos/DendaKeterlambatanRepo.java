package id.co.nds.gadai_2022_07_04.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.DendaKeterlambatanEntity;

@Repository
public interface DendaKeterlambatanRepo extends JpaRepository<DendaKeterlambatanEntity, String>{
    @Query(value = "SELECT * FROM TX_DENDA_KETERLAMBATAN WHERE UPPER(no_transaksi) = UPPER(:no_transaksi) AND cicilan_ke = :cicilan_ke", nativeQuery = true)
    DendaKeterlambatanEntity findByNoTransaksiCicilanKe(@Param("no_transaksi") String noTransaksi, @Param("cicilan_ke") Integer cicilanKe);

}