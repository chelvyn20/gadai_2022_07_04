
package id.co.nds.gadai_2022_07_04.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_07_04.entities.TransaksiBarangEntity;
import id.co.nds.gadai_2022_07_04.models.TransaksiBarangModel;

public class TransaksiBarangSpec
		implements Specification<TransaksiBarangEntity> {
	private TransaksiBarangModel transaksiBarangModel;

	public TransaksiBarangSpec(TransaksiBarangModel transaksiBarangModel) {
		super();
		this.transaksiBarangModel = transaksiBarangModel;
	}

	@Override
	public Predicate toPredicate(Root<TransaksiBarangEntity> root,
			CriteriaQuery<?> cq, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		Predicate p = cb.and();
		if (transaksiBarangModel.getNoTransaksi() != null
				&& transaksiBarangModel.getNoTransaksi().length() > 0) {
			p.getExpressions().add(cb.equal(root.get("noTransaksi"),
					transaksiBarangModel.getNoTransaksi()));
		}

		if (transaksiBarangModel.getNoUrut() != null
				&& transaksiBarangModel.getNoUrut() != 0) {
			p.getExpressions().add(cb.equal(root.get("noUrut"),
					transaksiBarangModel.getNoUrut()));
		}
		cq.orderBy(cb.asc(root.get("KeysGadai.noTransaksi")));

		return p;
	}

}
