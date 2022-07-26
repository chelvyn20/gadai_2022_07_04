package id.co.nds.gadai_2022_07_04.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.CustomerEntity;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, String>, JpaSpecificationExecutor<CustomerEntity> {
    @Query(value = "SELECT COUNT (*) FROM ms_customer WHERE rec_status = '" + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND LOWER(customer_name) = LOWER (:name)", nativeQuery = true)
    long countByName(@Param("name") String name);

    @Query(value = "SELECT COUNT (*) FROM ms_customer WHERE rec_status = '" + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND customer_identity_no = (:cust_ktp)", nativeQuery = true)
    long countByKtp(@Param("cust_ktp") String custKtp);

    @Query(value = "SELECT COUNT (*) FROM ms_customer WHERE rec_status = '" + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND customer_phone = (:cust_hp)", nativeQuery = true)
    long countByCallNumber(@Param("cust_hp") String custHp);

    @Query(value = "SELECT COUNT (*) FROM ms_customer WHERE rec_status = '"  + GlobalConstant.REC_STATUS_ACTIVE
    + "' AND customer_id = :cust_id",nativeQuery = true)
    long countById(@Param("cust_id")String id);


//     @Query(value = "SELECT COUNT (*) FROM ms_customer WHERE rec_status = '"  + GlobalConstant.REC_STATUS_ACTIVE
//     + "' AND customer_id = :cust_id",nativeQuery = true)
//     List<CustomerEntity> countById(@Param("cust_id")String id);



}
