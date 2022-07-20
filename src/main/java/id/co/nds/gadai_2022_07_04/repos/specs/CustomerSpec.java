package id.co.nds.gadai_2022_07_04.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.gadai_2022_07_04.entities.CustomerEntity;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;
import id.co.nds.gadai_2022_07_04.models.CustomerModel;

public class CustomerSpec implements Specification<CustomerEntity>{
    private CustomerModel customerModel;

    public CustomerSpec(CustomerModel customerModel){
        super();
        this.customerModel=customerModel;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?>cq, CriteriaBuilder cb){
        Predicate p = cb.and();
        
        if(customerModel.getCustId()!=null && customerModel.getCustId().length() > 0){
            p.getExpressions().add(cb.equal(root.get("custId"), customerModel.getCustId()));
        }
        
        if (customerModel.getCustName()!=null && customerModel.getCustName().length() > 0 ){
            p.getExpressions().add(cb.like(cb.lower(root.get("custName")),
            "%" +    customerModel.getCustName().toLowerCase()+ "%"));
        }

        if (customerModel.getCustKtp()!=null && customerModel.getCustKtp().length() > 0 ){
            p.getExpressions().add(cb.like(root.get("custKtp"),
            "%" +    customerModel.getCustKtp()+ "%"));
        }
        if(customerModel.getCustHp()!=null && customerModel.getCustHp().length() > 0){
            p.getExpressions().add(cb.equal(root.get("custId"), customerModel.getCustHp()));
        }

        if(customerModel.getCustJenisUsahaId()!=null && customerModel.getCustJenisUsahaId().length() > 0){
            p.getExpressions().add(cb.equal(root.get("custId"), customerModel.getCustJenisUsahaId()));
        }
        if(customerModel.getActorId()!=null && customerModel.getActorId() != "0"){
            p.getExpressions().add(cb.equal(root.get("custId"), customerModel.getActorId()));
        }

        if (customerModel.getRecStatus() != null
        && (customerModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
                || customerModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))) {
        p.getExpressions()
            .add(cb.equal(cb.upper(root.get("recStatus")), customerModel.getRecStatus().toUpperCase()));
}
        cq.orderBy(cb.asc(root.get("custId")));
        return p;

    }
  
   
    
    
}
