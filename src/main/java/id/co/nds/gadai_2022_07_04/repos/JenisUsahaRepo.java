package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.JenisUsahaEntity;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;

@Repository
public interface JenisUsahaRepo extends JpaRepository<JenisUsahaEntity, String>, JpaSpecificationExecutor<JenisUsahaEntity> {
    @Query(value = "SELECT * FROM ms_jenis_usaha WHERE rec_status = '" + GlobalConstant.REC_STATUS_ACTIVE
    + "' ", nativeQuery = true)
    List<JenisUsahaEntity> listActiveJenisUsaha();

}