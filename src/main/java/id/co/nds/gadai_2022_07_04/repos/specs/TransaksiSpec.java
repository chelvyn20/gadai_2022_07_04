package id.co.nds.gadai_2022_07_04.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_07_04.entities.TransaksiCicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.models.TransaksiModel;

public class TransaksiSpec
		implements Specification<TransaksiCicilanTetapEntity> {

	private TransaksiModel transaksiModel;

	

	public TransaksiSpec(TransaksiModel transaksiModel) {
		super();
		this.transaksiModel = transaksiModel;
	}

    @Override
	public Predicate toPredicate(Root<TransaksiCicilanTetapEntity> root,
			CriteriaQuery<?> cq, CriteriaBuilder cb) {
		
		Predicate p = cb.and();

		if (transaksiModel.getNoTransaksi() != null
				&& transaksiModel.getNoTransaksi().length() != 0) {
			p.getExpressions().add(cb.equal(root.get("noTransaksi"),
					transaksiModel.getNoTransaksi()));
		}
		if (transaksiModel.getProductId() != null
				&& transaksiModel.getProductId().length() != 0) {
			p.getExpressions().add(cb.equal(root.get("productId"),
					transaksiModel.getProductId()));
		}
		// if (transaksiModel.getTrxDateBegin() != null
		// 		&& transaksiModel.getTrxDateBegin().length() != 0) {
		// 	p.getExpressions()
		// 			.add(cb.equal(root.get(String.format("tanggal_tx")),
		// 					transaksiModel.getTrxDateBegin()));
		// }
		
		if (transaksiModel.getCustId() != null
				&& transaksiModel.getCustId().length() != 0) {
			p.getExpressions()
					.add(cb.equal(root.get(String.format("customer_id")),
							transaksiModel.getTrxDateBegin()));
		}
		

		cq.orderBy(cb.asc(root.get("noTransaksi")));
		return p;
	}
	
}
