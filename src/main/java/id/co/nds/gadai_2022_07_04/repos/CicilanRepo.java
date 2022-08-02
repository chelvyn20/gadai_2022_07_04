package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.key.CicilanKey;

@Repository
@Transactional
public interface CicilanRepo extends JpaRepository<CicilanEntity, CicilanKey>, JpaSpecificationExecutor<CicilanEntity> {

    @Query(value = "SELECT * FROM TX_CICILAN WHERE no_transaksi = :no_transaksi", nativeQuery = true)
    List<CicilanEntity> getPembayaranByNoTrans(@Param("no_transaksi") String noTransaksi);

}
