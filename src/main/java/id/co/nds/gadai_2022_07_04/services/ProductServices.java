
package id.co.nds.gadai_2022_07_04.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;
import id.co.nds.gadai_2022_07_04.models.ProductModel;
import id.co.nds.gadai_2022_07_04.repos.ProductRepo;
import id.co.nds.gadai_2022_07_04.repos.specs.ProductSpec;
import id.co.nds.gadai_2022_07_04.validators.ProductValidator;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {
		Exception.class })
public class ProductServices {

	@Autowired
	private ProductRepo productRepo;

	ProductValidator productValidator = new ProductValidator();

	public ProductEntity add(ProductModel productModel) throws ClientException {

		productValidator.validateProductLTV(productModel.getProductLTV());
		productValidator.validateJangkaWaktu(
		productModel.getProductJangkawaktu(),
		productModel.getProductBiayaPenyPeriode());

		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductName(productModel.getProductName());
		productEntity.setProductDesc(productModel.getProductDesc());
		productEntity.setProductType(productModel.getProductType());

		productEntity.setProductLtv(productModel.getProductLTV());
		productEntity.setProductTenor(productModel.getProductJangkawaktu());
		productEntity.setBiayaAdmBukaType(
				productModel.getProductBiayaAdminBukaType());
		productEntity.setBiayaAdmTutupType(
				productModel.getProductBiayaAdminTutupType());
		

		if (productModel.getProductBiayaAdminBukaType().equals("PERSEN")) {
			productValidator
					.validateBABPersen(productModel.getProductBiayaAdminBuka());
			productEntity.setBiayaAdmBukaVal(
					productModel.getProductBiayaAdminBuka());
		} else if (productModel.getProductBiayaAdminBukaType()
				.equals("NOMINAL")) {
			productEntity.setBiayaAdmBukaVal(
					productModel.getProductBiayaAdminBuka());
		}
		productEntity.setBiayaAdmTutupType(
				productModel.getProductBiayaAdminTutupType());

		if (productModel.getProductBiayaAdminTutupType().equals("PERSEN")) {
			productValidator.validateBATPersen(
					productModel.getProductBiayaAdminTutup());
			productEntity.setBiayaAdmTutupVal(
					productModel.getProductBiayaAdminTutup());
		} else if (productModel.getProductBiayaAdminBukaType()
				.equals("NOMINAL")) {
			productEntity.setBiayaAdmTutupVal(
					productModel.getProductBiayaAdminTutup());
		}
		productEntity
				.setBiayaJsPenyPer(productModel.getProductBiayaPenyPeriode());
		productEntity
				.setBiayaJsPenyRate(productModel.getProductBiayaJasaPeny());
		productEntity.setBDKeterlambatanPer(
				productModel.getProductBiayaDendaPeriode());
		productEntity
				.setbDKeterlambatanRate(productModel.getProductBiayaDenda());
		productEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		productEntity.setCreatedBy(productModel.getActorId() == null ? "1"
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

		if (!productRepo.existsById(productModel.getProductid())) {
			throw new NotFoundException("Cannot find Product with id :"
					+ productModel.getProductid());
		}

		productValidator.validateProductLTV(productModel.getProductLTV());
		productValidator.validateJangkaWaktu(
		productModel.getProductJangkawaktu(),
		productModel.getProductBiayaPenyPeriode());


		ProductEntity productEntity = new ProductEntity();
		productEntity = findById(productModel.getProductid());
		productEntity.setProductName(productModel.getProductName());
		productEntity.setProductDesc(productModel.getProductDesc());

		productEntity.setProductLtv(productModel.getProductLTV());
		productEntity.setProductTenor(productModel.getProductJangkawaktu());
		productEntity.setBiayaAdmBukaType(
				productModel.getProductBiayaAdminBukaType());
		
		productEntity.setBiayaAdmTutupType(
				productModel.getProductBiayaAdminTutupType());

		if (productModel.getProductBiayaAdminBukaType().equals("PERSEN")) {
			productValidator
					.validateBABPersen(productModel.getProductBiayaAdminBuka());
			productEntity.setBiayaAdmBukaVal(
					productModel.getProductBiayaAdminBuka());
		} else if (productModel.getProductBiayaAdminBukaType()
				.equals("NOMINAL")) {
			productEntity.setBiayaAdmBukaVal(
					productModel.getProductBiayaAdminBuka());
		}
		productEntity.setBiayaAdmTutupType(
				productModel.getProductBiayaAdminTutupType());

		if (productModel.getProductBiayaAdminTutupType().equals("PERSEN")) {
			productValidator.validateBATPersen(
					productModel.getProductBiayaAdminTutup());
			productEntity.setBiayaAdmTutupVal(
					productModel.getProductBiayaAdminTutup());
		} else if (productModel.getProductBiayaAdminBukaType()
				.equals("NOMINAL")) {
			productEntity.setBiayaAdmTutupVal(
					productModel.getProductBiayaAdminTutup());
		}
		productEntity
				.setBiayaJsPenyPer(productModel.getProductBiayaPenyPeriode());
		productEntity
				.setBiayaJsPenyRate(productModel.getProductBiayaJasaPeny());
		productEntity.setBDKeterlambatanPer(
				productModel.getProductBiayaDendaPeriode());
		productEntity
				.setbDKeterlambatanRate(productModel.getProductBiayaDenda());
		productEntity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
		productEntity.setUpdatedBy(productModel.getActorId() == null ? "1"
				: productModel.getActorId());
		
		productEntity.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);

		return productRepo.save(productEntity);

	}

	public ProductEntity delete(ProductModel productModel)
			throws ClientException, NotFoundException {

		if (!productRepo.existsById(productModel.getProductid())) {
			throw new NotFoundException(
					"Cannot find product id : " + productModel.getProductid());
		}

		ProductEntity product = new ProductEntity();
		product = findById(productModel.getProductid());

		if (product.getRecStatus()
				.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
			throw new ClientException(
					"Product id(" + productModel.getProductid()
							+ ") is already been deleted.");
		}

		product.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
		product.setDeletedDate(new Timestamp(System.currentTimeMillis()));
		product.setDeletedBy(productModel.getActorId() == null ? "0"
				: productModel.getActorId());

		return productRepo.save(product);

	}
}
