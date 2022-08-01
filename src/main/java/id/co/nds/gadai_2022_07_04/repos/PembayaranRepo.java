
package id.co.nds.gadai_2022_07_04.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.PembayaranEntity;

@Repository
public interface PembayaranRepo extends JpaRepository<PembayaranEntity, String>,
		JpaSpecificationExecutor<PembayaranEntity> {

	
}
