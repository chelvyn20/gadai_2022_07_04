package id.co.nds.gadai_2022_07_04.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;

public class CicilanSpec implements Specification<CicilanEntity> {

	@Override
	public Predicate toPredicate(Root<CicilanEntity> root,
			CriteriaQuery<?> cq, CriteriaBuilder cb) {
		// TODO Auto-generated method stub

		Predicate p = cb.and();
		
		return null;
	}
	
}
