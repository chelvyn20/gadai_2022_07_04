package id.co.nds.gadai_2022_07_04.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.CicilanTetapInfoEntity;

@Repository
 public interface CicilanTetapInfoRepo extends JpaRepository<CicilanTetapInfoEntity, String> {

     @Query(value = "SELECT ct.*, c.customer_name AS customer_name, p.product_name AS product_name FROM tx_transaksi_cicilan_tetap AS ct " +  "JOIN ms_customer AS c ON ct.customer_id = c.customer_id " + "JOIN ms_product AS p ON ct.product_id = p.product_id " + "WHERE ct.no_transaksi = ?1", nativeQuery = true)
     CicilanTetapInfoEntity find(String noTransaksi);
 }