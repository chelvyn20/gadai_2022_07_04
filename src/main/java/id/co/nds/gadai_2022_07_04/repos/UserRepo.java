
package id.co.nds.gadai_2022_07_04.repos;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.gadai_2022_07_04.entities.UserEntity;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String>,
		JpaSpecificationExecutor<UserEntity> {

	@Query(value = "SELECT COUNT(*) FROM ms_user WHERE rec_status = 'A' "
			+ " AND LOWER(user_phone) = LOWER(:user_phone)", nativeQuery = true)
	long countByPhone(@Param("user_phone") String userPhone);

	// @Query(value = "SELECT * FROM ms_user WHERE rec_status = 'A' ", nativeQuery = true)
	
	// List<UserEntity> findAllUser();

	boolean existsById(String userId);

	Optional<UserEntity> findById(String id);

}
