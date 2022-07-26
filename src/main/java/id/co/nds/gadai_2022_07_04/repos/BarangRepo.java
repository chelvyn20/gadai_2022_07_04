package id.co.nds.gadai_2022_07_04.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_07_04.entities.BarangEntity;
import id.co.nds.gadai_2022_07_04.key.BarangKey;

@Repository
@Transactional
public interface BarangRepo extends JpaRepository<BarangEntity,BarangKey>{
    @Query(value = "SELECT * FROM tx_transaksi_barang  WHERE no_transaksi = :no_transaksi",nativeQuery = true)
    List<BarangEntity> findBarangByNoTrx(@Param("no_transaksi")String noTransaksi);

}


