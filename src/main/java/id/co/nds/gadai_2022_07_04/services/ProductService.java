package id.co.nds.gadai_2022_07_04.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;
import id.co.nds.gadai_2022_07_04.models.ProductModel;
import id.co.nds.gadai_2022_07_04.repos.ProductRepo;
import id.co.nds.gadai_2022_07_04.repos.specs.ProductSpec;
import id.co.nds.gadai_2022_07_04.validators.ProductValidator;

@Service
public class ProductService implements Serializable{
    
    @Autowired
	private ProductRepo productRepo;

	ProductValidator productValidator = new ProductValidator();

	public ProductEntity add(ProductModel productModel) throws ClientException {

		productValidator.validateProductTipe(productModel.getProductTipe());
        productValidator.validateName(productModel.getProductName());
        productValidator.validateProductDesc(productModel.getProductDesc());
        productValidator.validateProductLtv(productModel.getProductLtv());
		productValidator.validateProductJangkaWaktu(productModel.getProductJangkaWaktu());
        productValidator.validateBiayaAdminBukaTipe(productModel.getProductBiayaAdminBukaTipe());
        productValidator.validateBiayaAdminTutupTipe(productModel.getProductBiayaAdminTutupTipe());
        productValidator.validateBiayaAdminBuka(productModel.getProductBiayaAdminBukaTipe(), productModel.getProductBiayaAdminBuka());
        productValidator.validateBiayaAdminBuka(productModel.getProductBiayaAdminTutupTipe(), productModel.getProductBiayaAdminTutup());
        productValidator.validateBiayaAdminPenyPeriode(productModel.getProductJangkaWaktu(), productModel.getProductBiayaJasaPenyPeriode());

        Long countProductId = productRepo.countByProductId(productModel.getProductId());

        if(countProductId > 0) {
            throw new ClientException("Produk id telah terdaftar");
        }

		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductName(productModel.getProductName());
		productEntity.setProductDesc(productModel.getProductDesc());
		productEntity.setProductTipe(productModel.getProductTipe());
		productEntity.setProductLtv(productModel.getProductLtv());
		productEntity.setProductJangkaWaktu(productModel.getProductJangkaWaktu());
        productEntity.setProductBiayaAdminBuka(productModel.getProductBiayaAdminBuka());
		productEntity.setProductBiayaAdminBukaTipe(productModel.getProductBiayaAdminBukaTipe());
        productEntity.setProductBiayaAdminTutup(productModel.getProductBiayaAdminTutup());
		productEntity.setProductBiayaAdminTutupTipe(productModel.getProductBiayaAdminTutupTipe());
		productEntity.setProductBiayaJasaPenyPeriode(productModel.getProductBiayaJasaPenyPeriode());
		productEntity.setProductBiayaJasaPeny(productModel.getProductBiayaJasaPeny());
		productEntity.setProductBiayaDendaPeriode(productModel.getProductBiayaDendaPeriode());
		productEntity.setProductBiayaDenda(productModel.getProductBiayaDenda());
		productEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		productEntity.setCreatorId(productModel.getActorId() == null ? "1"
				: productModel.getActorId());
		productEntity.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);

		return productRepo.save(productEntity);
		
	}

	public List<ProductEntity> findAllByCriteria(ProductModel productModel) {

		List<ProductEntity> products = new ArrayList<>();
		ProductSpec specs = new ProductSpec(productModel);
		productRepo.findAll(specs).forEach(products::add);

		return products;
	}

	public ProductEntity findById(String id)
			throws ClientException, NotFoundException {

		ProductEntity product = productRepo.findById(id).orElse(null);
		productValidator.nullCheckObject(id);

		return product;
	}

	public ProductEntity edit(ProductModel productModel)
			throws ClientException, NotFoundException {

		if (!productRepo.existsById(productModel.getProductId())) {
			throw new NotFoundException("Cannot find Product with id :"
					+ productModel.getProductId());
		}

		productValidator.validateProductLtv(productModel.getProductLtv());
		productValidator.validateProductJangkaWaktu(productModel.getProductJangkaWaktu());
        productValidator.validateBiayaAdminBuka(productModel.getProductBiayaAdminBukaTipe(), productModel.getProductBiayaAdminBuka());
        productValidator.validateBiayaAdminTutup(productModel.getProductBiayaAdminTutupTipe(), productModel.getProductBiayaAdminTutup());
	

		ProductEntity productEntity = new ProductEntity();

		productEntity = findById(productModel.getProductId());
		productEntity.setProductName(productModel.getProductName());
		productEntity.setProductDesc(productModel.getProductDesc());
		productEntity.setProductLtv(productModel.getProductLtv());
		productEntity.setProductJangkaWaktu(productModel.getProductJangkaWaktu());
		productEntity.setProductBiayaAdminBukaTipe(productModel.getProductBiayaAdminBukaTipe());
		productEntity.setProductBiayaAdminTutupTipe(productModel.getProductBiayaAdminTutupTipe());
        productEntity.setProductBiayaAdminBuka(productModel.getProductBiayaAdminBuka());
        productEntity.setProductBiayaAdminBukaTipe(productModel.getProductBiayaAdminBukaTipe());
        productEntity.setProductBiayaAdminTutup(productModel.getProductBiayaAdminTutup());
        productEntity.setProductBiayaAdminTutupTipe(productModel.getProductBiayaAdminTutupTipe());
        productEntity.setProductBiayaJasaPenyPeriode(productModel.getProductBiayaJasaPenyPeriode());
		productEntity.setProductBiayaJasaPeny(productModel.getProductBiayaJasaPeny());
		productEntity.setProductBiayaDendaPeriode(productModel.getProductBiayaDendaPeriode());
		productEntity.setProductBiayaDenda(productModel.getProductBiayaDenda());
		productEntity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
		productEntity.setUpdaterId(productModel.getActorId() == null ? "1"
				: productModel.getActorId());
		
		productEntity.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);

		return productRepo.save(productEntity);

	}

	public ProductEntity delete(ProductModel productModel)
			throws ClientException, NotFoundException {

		if (!productRepo.existsById(productModel.getProductId())) {
			throw new NotFoundException(
					"Cannot find product id : " + productModel.getProductId());
		}

		ProductEntity product = new ProductEntity();
		product = findById(productModel.getProductId());

		if (product.getRecStatus()
				.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
			throw new ClientException(
					"Product id(" + productModel.getProductId()
							+ ") is already been deleted.");
		}

		product.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
		product.setDeletedDate(new Timestamp(System.currentTimeMillis()));
		product.setDeleterId(productModel.getActorId() == null ? "0"
				: productModel.getActorId());

		return productRepo.save(product);

	}
}
