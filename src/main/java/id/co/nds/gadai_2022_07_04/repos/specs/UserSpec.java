
package id.co.nds.gadai_2022_07_04.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_07_04.entities.UserEntity;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;
import id.co.nds.gadai_2022_07_04.models.UserModel;

public class UserSpec implements Specification<UserEntity> {

	private UserModel userModel;

	public UserSpec(UserModel userModel) {
		super();
		this.userModel = userModel;
	}

	@Override
	public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		
		Predicate p = cb.and();

		if (userModel.getUserId() != null) {
			p.getExpressions()
					.add(cb.equal(root.get("userId"), userModel.getUserId()));
		}

		if (userModel.getUserName() != null
				&& userModel.getUserName().length() > 0) {
			p.getExpressions().add(cb.like(cb.lower(root.get("userName")),
					"%" + userModel.getUserName().toLowerCase() + "%"));
		}

		if (userModel.getRecStatus() != null && (userModel.getRecStatus().trim()
				.equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
				|| userModel.getRecStatus().trim().equalsIgnoreCase(
						GlobalConstant.REC_STATUS_NON_ACTIVE))) {
			p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")),
					userModel.getRecStatus().toUpperCase()));
		}

		cq.orderBy(cb.asc(root.get("userId")));

		return p;
	}

}
