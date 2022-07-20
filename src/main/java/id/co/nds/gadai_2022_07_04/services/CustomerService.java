package id.co.nds.gadai_2022_07_04.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_07_04.entities.CustomerEntity;
import id.co.nds.gadai_2022_07_04.entities.JenisUsahaEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;
import id.co.nds.gadai_2022_07_04.models.CustomerModel;
import id.co.nds.gadai_2022_07_04.repos.CustomerRepo;
import id.co.nds.gadai_2022_07_04.repos.JenisUsahaRepo;
import id.co.nds.gadai_2022_07_04.repos.specs.CustomerSpec;
import id.co.nds.gadai_2022_07_04.validators.CustomerValidator;

@Service
public class CustomerService implements Serializable {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private JenisUsahaRepo jenisUsahaRepo;

    CustomerValidator customerValidator = new CustomerValidator();

    public CustomerEntity add(CustomerModel customerModel) throws ClientException {
        customerValidator.notnullChekcCustId(customerModel.getCustId());
        customerValidator.nullChekcCustName(customerModel.getCustName());
        customerValidator.validateName(customerModel.getCustName());
        customerValidator.nullChekcCustKtp(customerModel.getCustKtp());
        customerValidator.validateCustKtp(customerModel.getCustKtp());
        customerValidator.nullChekcCallNumber(customerModel.getCustHp());
        customerValidator.validateCallNumber(customerModel.getCustHp());
        customerValidator.nullChekcCustJk(customerModel.getCustJk());
        customerValidator.validateCustJk(customerModel.getCustJk());
        customerValidator.nullChekcCustJenisUsaha(customerModel.getCustJenisUsahaId());
        customerValidator.validateJenisUsaha(customerModel.getCustJenisUsahaId());
        customerValidator.nullChekcCustLimitTxn(customerModel.getCustLimitTxn());
        customerValidator.validatetLimitTxn(customerModel.getCustLimitTxn());

        Long count = customerRepo.countByKtp((customerModel.getCustKtp()));
        if (count > 0) {
            throw new ClientException("Nomor KTP sudah digunakan");

        }

        Long countHp = customerRepo.countByCallNumber((customerModel.getCustHp()));
        if (countHp > 0) {
            throw new ClientException("Nomor Hp sudah digunakan");

        }
        CustomerEntity customer = new CustomerEntity();
        customer.setCustName(customerModel.getCustName());
        customer.setCustKtp(customerModel.getCustKtp());
        customer.setCustHp(customerModel.getCustHp());
        customer.setCustJk(customerModel.getCustJk());
        customer.setCustJenisUsahaId(customerModel.getCustJenisUsahaId());
        customer.setCustLimitTxn(customerModel.getCustLimitTxn());
        customer.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        customer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        customer.setCreatorId(customerModel.getActorId() == null ? "1"
				: customerModel.getActorId());
        return customerRepo.save(customer);
    }

    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customers = new ArrayList<>();
        customerRepo.findAll().forEach(customers::add);
        return customers;
    }

    public List<CustomerEntity> findAllByCriteria(CustomerModel customerModel) {
        List<CustomerEntity> customers = new ArrayList<>();
        CustomerSpec customerSpec = new CustomerSpec(customerModel);
        customerRepo.findAll(customerSpec).forEach(customers::add);
        return customers;
    }

    public CustomerEntity findById(String id) throws ClientException, NotFoundException {
        customerValidator.nullChekcCustId((id));
        customerValidator.validateCustId(id);

        CustomerEntity customer = customerRepo.findById(id).orElse(null);
        customerValidator.nullChekcObject(customer);
        customerValidator.validateCustStatus(id, customer.getRecStatus());
        return customer;
    }

    public CustomerEntity update(CustomerModel customerModel)
            throws ClientException, NotFoundException {
        // validation
        customerValidator.nullChekcCustId(customerModel.getCustId());
        customerValidator.validateCustId(customerModel.getCustId());

        if (!customerRepo.existsById((customerModel.getCustId()))) {
            throw new NotFoundException("Id Pelanggan Tidak Ditemukan");
        }

        // proses
        CustomerEntity customer = new CustomerEntity();
        customer = findById(customerModel.getCustId());

        if (customerModel.getCustName() != null) {
            customerValidator.validateName((customerModel.getCustName()));
            customer.setCustName((customerModel.getCustName()));
        }

        if (customerModel.getCustKtp() != null) {
            customerValidator.validateCustKtp((customerModel.getCustKtp()));

            Long count = customerRepo.countByKtp((customerModel.getCustKtp()));

            if (count > 0) {
                throw new ClientException("Nomor KTP sudah digunakan");

            }
            customer.setCustKtp((customerModel.getCustKtp()));
        }

        if (customerModel.getCustHp() != null) {
            customerValidator.validateCallNumber((customerModel.getCustHp()));

            Long count = customerRepo.countByCallNumber((customerModel.getCustHp()));
            if (count > 0) {
                throw new ClientException("Nomor HP sudah digunakan");

            }
            customer.setCustHp((customerModel.getCustHp()));
        }

        if (customerModel.getCustJk() != null) {
            customerValidator.validateCustJk((customerModel.getCustJk()));
            customer.setCustJk((customerModel.getCustJk()));
        }

        if (customerModel.getCustJenisUsahaId() != null) {
            customerValidator.validateJenisUsaha((customerModel.getCustJenisUsahaId()));
            customer.setCustJenisUsahaId((customerModel.getCustJenisUsahaId()));
        }

        if (customerModel.getCustLimitTxn() != null) {
            customerValidator.validatetLimitTxn((customerModel.getCustLimitTxn()));
            customer.setCustLimitTxn((customerModel.getCustLimitTxn()));
        }

        customer.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        customer.setUpdaterId(customerModel.getActorId() == null ? "0" : customerModel.getActorId());

        return customerRepo.save(customer);
    }

    public CustomerEntity delete(CustomerModel customerModel) throws ClientException, NotFoundException {
        // validation
        customerValidator.nullChekcCustId(customerModel.getCustId());
        customerValidator.validateCustId(customerModel.getCustId());

        if (!customerRepo.existsById((customerModel.getCustId()))) {
            throw new NotFoundException(" Id pelanggan tidak ditemukan ");
        }
        // proses
        CustomerEntity customer = new CustomerEntity();
        customer = findById(customerModel.getCustId());

        if (customer.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Sukses hapus data pelanggan (" + customerModel.getCustId());
        }
        customer.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        customer.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        customer.setDeleterId(customerModel.getActorId() == null ? "0" : customerModel.getActorId());

        return customerRepo.save(customer);
    }

    public List<JenisUsahaEntity> findAllJenisUsaha() {
        List<JenisUsahaEntity> jenisUsaha = new ArrayList<>();
        jenisUsahaRepo.listActiveJenisUsaha().forEach(jenisUsaha::add);
        return jenisUsaha;
    }

}
