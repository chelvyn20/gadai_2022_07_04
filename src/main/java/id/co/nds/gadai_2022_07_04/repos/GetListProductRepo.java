
package id.co.nds.gadai_2022_07_04.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import id.co.nds.gadai_2022_07_04.entities.ProductGetListEntity;

public interface GetListProductRepo
		extends JpaRepository<ProductGetListEntity, String>,
		JpaSpecificationExecutor<ProductGetListEntity> {

}
