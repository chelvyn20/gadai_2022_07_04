package id.co.nds.gadai_2022_07_04.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_07_04.entities.PembayaranEntity;
import id.co.nds.gadai_2022_07_04.models.PembayaranModel;




public class PembayaranSpec implements Specification<PembayaranEntity> {
    private PembayaranModel pembayaranModel;

    public PembayaranSpec(PembayaranModel pembayaranModel){
        super();
        this.pembayaranModel=pembayaranModel;
    }
  
    @Override
    public Predicate toPredicate(Root<PembayaranEntity> root, CriteriaQuery<?>cq, CriteriaBuilder cb){
        Predicate p = cb.and();
        
        if (pembayaranModel.getNoTransaksi() != null && pembayaranModel.getNoTransaksi().length() > 0) {
            p.getExpressions().add(cb.equal(root.get("noTransaksi"), pembayaranModel.getNoTransaksi()));
        }

        if (pembayaranModel.getCustId() != null && pembayaranModel.getCustId().length() > 0) {
            p.getExpressions().add(cb.equal(root.get("custId"), pembayaranModel.getCustId()));
        }


        cq.orderBy(cb.asc(root.get("noTransaksi")));
        return p;

    }
    
    
    
}