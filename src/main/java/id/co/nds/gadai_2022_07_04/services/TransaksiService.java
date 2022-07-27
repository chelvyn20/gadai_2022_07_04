
package id.co.nds.gadai_2022_07_04.services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.entities.CustomerEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiBarangEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiCicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiGetDetailEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiInfoEntity;
import id.co.nds.gadai_2022_07_04.entities.UserEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductGetListEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.globals.GlobalTypeProduct;
import id.co.nds.gadai_2022_07_04.keys.KeysTransaksiCicilan;
import id.co.nds.gadai_2022_07_04.models.CustomerModel;
import id.co.nds.gadai_2022_07_04.models.TransaksiModel;
import id.co.nds.gadai_2022_07_04.repos.CicilanRepo;
import id.co.nds.gadai_2022_07_04.repos.CustomerRepo;
import id.co.nds.gadai_2022_07_04.repos.GetDetailTransaksiRepo;
import id.co.nds.gadai_2022_07_04.repos.GetListProductRepo;
import id.co.nds.gadai_2022_07_04.repos.ProductRepo;
import id.co.nds.gadai_2022_07_04.repos.TransaksiBarangRepo;
import id.co.nds.gadai_2022_07_04.repos.TransaksiInfoRepo;
import id.co.nds.gadai_2022_07_04.repos.TransaksiRepo;
import id.co.nds.gadai_2022_07_04.repos.UserRepo;
import id.co.nds.gadai_2022_07_04.repos.specs.CustomerSpec;
import id.co.nds.gadai_2022_07_04.repos.specs.TransaksiSpec;
import id.co.nds.gadai_2022_07_04.validators.ProductValidator;
import id.co.nds.gadai_2022_07_04.validators.TransaksiBarangValidator;
import id.co.nds.gadai_2022_07_04.validators.TransaksiValidator;

@Service

public class TransaksiService {
	@Autowired
	private TransaksiRepo transaksiRepo;

	@Autowired
	private TransaksiBarangRepo transaksiBarangRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CicilanRepo cicilanRepo;

	@Autowired
	private TransaksiInfoRepo transaksiInfoRepo;

	@Autowired
	private GetDetailTransaksiRepo getDetailTransaksiRepo;

	@Autowired
	private GetListProductRepo getListProductRepo;

	ProductValidator productValidator = new ProductValidator();
	TransaksiBarangValidator transaksiBarangValidator = new TransaksiBarangValidator();

	private Double AdmBukaAkhir, TotalBiayaJasaPenyPer, TotalBiayaJasaPenyAll,
			TotalPengembalian;
	private Integer JangkaWaktu;
	private Integer PeriodeJasaPenyimpanan;

	TransaksiValidator transaksiValidator = new TransaksiValidator();

	public List<TransaksiCicilanTetapEntity> findAllTransaksi() {

		List<TransaksiCicilanTetapEntity> transaksi = new ArrayList<>();
		transaksiRepo.findAllTransaksiCustomerProduct().forEach(transaksi::add);

		return transaksi;
	}

	public List<CustomerEntity> HasilCariDataPelanggan(
			CustomerModel customerModel) {

		List<CustomerEntity> customer = new ArrayList<>();
		CustomerSpec specs = new CustomerSpec(customerModel);
		customerRepo.findAll(specs).forEach(customer::add);

		return customer;
	}

	public TransaksiCicilanTetapEntity Hitung(TransaksiModel transaksiModel)
			throws ClientException, NotFoundException {

		double TotalTaksir = 0;
		double BiayaAdmTutup = 0;
		double NilaiPinjaman = 0;
		double maxNilaiPinjaman = 0;

		if (!productRepo.existsById(transaksiModel.getProductId())) {
			throw new NotFoundException("Cannot find product with id : "
					+ transaksiModel.getProductId());
		}

		if (!customerRepo.existsById(transaksiModel.getCustId())) {
			throw new NotFoundException("Cannot find customer id with id : "
					+ transaksiModel.getCustId());
		}

		if (!userRepo.existsById(transaksiModel.getActorId())) {
			throw new NotFoundException("Cannot find User id with id : "
					+ transaksiModel.getActorId());
		}

		Long countId = transaksiRepo.countById((transaksiModel.getCustId()));
		if (countId > 0) {
			throw new ClientException("Customer Id sudah digunakan");

		}

		TransaksiCicilanTetapEntity transaksi = new TransaksiCicilanTetapEntity();

		List<TransaksiBarangEntity> transaksiBarang = new ArrayList<>();
		transaksiBarangRepo.findNoTransaksi(transaksiModel.getNoTransaksi())
				.forEach(transaksiBarang::add);

		ProductEntity product = productRepo
				.findById(transaksiModel.getProductId()).orElse(null);

		JangkaWaktu = product.getProductTenor();
		PeriodeJasaPenyimpanan = product.getBiayaJsPenyPer();

		if (!product.getProductType().equals(GlobalTypeProduct.TETAP)) {
			throw new ClientException("product  Is Not Found");
		}
		for (int i = 0; i < transaksiModel.getDaftarBarangGadai().size(); i++) {

			TotalTaksir += transaksiModel.getDaftarBarangGadai().get(i)
					.getJumlah()
					* transaksiModel.getDaftarBarangGadai().get(i)
							.getHargaPersatuan();

		}
		transaksi.setNoTransaksi(transaksiModel.getNoTransaksi());
		transaksi.setProductId(transaksiModel.getProductId());
		transaksi.setCustomerId(transaksiModel.getCustId());
		transaksi.setCreatedId(transaksiModel.getActorId());
		// ---------Total Nilai Taksiran------//
		transaksi.setTotalNilaiTak(TotalTaksir);

		maxNilaiPinjaman = TotalTaksir * product.getProductLtv();
		transaksi.setMaxNilaiPinj(maxNilaiPinjaman);
		// -----------Nilai Pencairan Pelanggan-----------//
		transaksi.setNilaiPencairanPel(
				transaksiModel.getNilaipencairanPelanggan());
		// ------------Diskin Admin Buka -----------------//
		transaksi.setDiskonAdmBuka(transaksiModel.getDiskonAdmBuka());
		// ------------LTV-----------------//
		transaksi.setTxLtv(product.getProductLtv());
		// ---------------admin Buka Type--------------//
		if (product.getBiayaAdmBukaType().equals("PERSEN")) {
			// -------------------admin Buka Value -------------------//
			Double totalAdmBuka = transaksiModel.getNilaipencairanPelanggan()
					* product.getBiayaAdmBukaVal();
			transaksi.setBiayaAdmBuka(totalAdmBuka);

			// ------------------------Biaya Admin buka----------------//
			AdmBukaAkhir = totalAdmBuka
					- (totalAdmBuka * transaksiModel.getDiskonAdmBuka() / 100);
			transaksi.setBiayaAdmBukaAk(AdmBukaAkhir);
		} else if (product.getBiayaAdmBukaType().equals("NOMINAL")) {
			// -------------------admin Buka Value -------------------//
			transaksi.setBiayaAdmBuka(product.getBiayaAdmBukaVal());
			// ------------------------Biaya Admin buka----------------//
			AdmBukaAkhir = product.getBiayaAdmBukaVal()
					- (product.getBiayaAdmBukaVal()
							* transaksiModel.getDiskonAdmBuka() / 100);
			transaksi.setBiayaAdmBukaAk(AdmBukaAkhir);
		}

		transaksi.setDiskonAdmBuka(transaksiModel.getDiskonAdmBuka());
		NilaiPinjaman = transaksiModel.getNilaipencairanPelanggan()
				+ AdmBukaAkhir;
		transaksi.setTotalNilaiPinj(NilaiPinjaman);

		LocalDateTime now = LocalDateTime.now(); // Current Date and Time
		LocalDateTime sameDayNextMonth = now
				.plusMonths(product.getProductTenor());

		Date DateNow = Date
				.from(now.atZone(ZoneId.systemDefault()).toInstant());
		transaksi.setTanggal_tx(DateNow);
		Date convertedDatetime = Date.from(
				sameDayNextMonth.atZone(ZoneId.systemDefault()).toInstant());
		transaksi.setTanggalJatuhTempo(convertedDatetime);
		transaksi.setTxBiayaJasaPeny(product.getBiayaJsPenyRate());

		TotalBiayaJasaPenyPer = ((product.getBiayaJsPenyRate() / 100)
				* NilaiPinjaman)
				/ (product.getProductTenor() / product.getBiayaJsPenyPer());
		transaksi.setTxBiayaJasaPenyPer(TotalBiayaJasaPenyPer);
		TotalBiayaJasaPenyAll = (product.getProductTenor()
				/ product.getBiayaJsPenyPer()) * TotalBiayaJasaPenyPer;
		transaksi.setTotalBiayaJasaPeny(TotalBiayaJasaPenyAll);

		if (product.getBiayaAdmTutupType().equals("PERSEN")) {
			Double Total = transaksiModel.getNilaipencairanPelanggan()
					* product.getBiayaAdmTutupVal();
			BiayaAdmTutup += Total;
			transaksi.setTxBiayaAdmTutup(Total);
		} else if (product.getBiayaAdmTutupType().equals("NOMINAL")) {
			BiayaAdmTutup += product.getBiayaAdmTutupVal();
			transaksi.setTxBiayaAdmTutup(product.getBiayaAdmTutupVal());
		}
		TotalPengembalian = NilaiPinjaman + TotalBiayaJasaPenyAll
				+ BiayaAdmTutup;
		List<TransaksiBarangEntity> transaksiBarangs = new ArrayList<>();
		for (int i = 0; i < transaksiModel.getDaftarBarangGadai().size(); i++) {
			TransaksiBarangEntity transaksiBarangEntity = new TransaksiBarangEntity();
			transaksiBarangEntity.setNoUrut(i + 1);
			transaksiBarangEntity.setNamaBarang(transaksiModel
					.getDaftarBarangGadai().get(i).getNamaBarang());
			transaksiBarangEntity.setKondisi(
					transaksiModel.getDaftarBarangGadai().get(i).getKondisi());
			transaksiBarangEntity.setJumlah(
					transaksiModel.getDaftarBarangGadai().get(i).getJumlah());
			transaksiBarangEntity.setHargaPerSatuan(transaksiModel
					.getDaftarBarangGadai().get(i).getHargaPersatuan());
			transaksiBarangs.add(transaksiBarangEntity);

		}
		transaksi.setDaftarBarangGadai(transaksiBarangs);
		transaksi.setTotalPengem(TotalPengembalian);
		transaksi.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		return transaksi;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {
			Exception.class })
	public TransaksiCicilanTetapEntity doSave(TransaksiModel transaksiModel)
			throws ClientException, NotFoundException {

		TransaksiCicilanTetapEntity transaksiCicilanTetapEntity = Hitung(
				transaksiModel);
		transaksiValidator.validatePersenAdmBuka(
				transaksiCicilanTetapEntity.getDiskonAdmBuka());
		UserEntity user = userRepo
				.findById(transaksiCicilanTetapEntity.getCreatedId())
				.orElse(null);

		CustomerEntity customer = customerRepo
				.findById(transaksiCicilanTetapEntity.getCustomerId())
				.orElse(null);

		// if
		// (!transaksiBarangRepo.existsById(transaksiCicilanTetapEntity.getNoTransaksi()))
		// {
		List<TransaksiBarangEntity> transaksiBarang = new ArrayList<>();
		transaksiBarangRepo
				.findNoTransaksi(transaksiCicilanTetapEntity.getNoTransaksi())
				.forEach(transaksiBarang::add);

		// if (transaksiBarang.size() == 0) {
		// throw new ClientException("Barang Gadai must have min 1 Unit");
		// }
		// }
		if (transaksiCicilanTetapEntity.getNilaiPencairanPel() <= user
				.getMaxLimit()) {
			throw new ClientException(
					"You Should Input nilai pencairan pelanggan  more than Max limit from User : "
							+ user.getMaxLimit());
		}

		if (transaksiCicilanTetapEntity.getNilaiPencairanPel() <= customer
				.getCustLimitTxn()) {
			throw new ClientException(
					"You Should Input nilai pencairan pelanggan  more than Max limit from Customer : "
							+ user.getMaxLimit());
		}

		if (transaksiCicilanTetapEntity.getNilaiPencairanPel() <= 1000000) {
			throw new ClientException(
					"Nilai Pencairan Pelanggan min 1.000.000");
		}

		List<TransaksiBarangEntity> transaksiBarangs = new ArrayList<>();
		for (int i = 0; i < transaksiModel.getDaftarBarangGadai().size(); i++) {

			transaksiBarangValidator.validateNamaBarang(transaksiModel
					.getDaftarBarangGadai().get(i).getNamaBarang());
			transaksiBarangValidator.validateDesc(
					transaksiModel.getDaftarBarangGadai().get(i).getKondisi());
			transaksiBarangValidator.validatejumlah(
					transaksiModel.getDaftarBarangGadai().get(i).getJumlah());

			transaksiBarangValidator.nullDesc(
					transaksiModel.getDaftarBarangGadai().get(i).getKondisi());
			transaksiBarangValidator.nullNamabarang(transaksiModel
					.getDaftarBarangGadai().get(i).getNamaBarang());
			transaksiBarangValidator.nullTotal(transaksiModel
					.getDaftarBarangGadai().get(i).getHargaPersatuan());
			transaksiBarangValidator.nulljumlah(
					transaksiModel.getDaftarBarangGadai().get(i).getJumlah());

			TransaksiBarangEntity transaksiBarangEntity = new TransaksiBarangEntity();
			transaksiBarangEntity.setNamaBarang(transaksiModel
					.getDaftarBarangGadai().get(i).getNamaBarang());
			transaksiBarangEntity.setNoUrut(i + 1);
			transaksiBarangEntity.setKondisi(
					transaksiModel.getDaftarBarangGadai().get(i).getKondisi());
			transaksiBarangEntity.setJumlah(
					transaksiModel.getDaftarBarangGadai().get(i).getJumlah());
			transaksiBarangEntity.setHargaPerSatuan(transaksiModel
					.getDaftarBarangGadai().get(i).getHargaPersatuan());
			transaksiBarangRepo.save(transaksiBarangEntity);
			transaksiBarangs.add(transaksiBarangEntity);

		}

		transaksiCicilanTetapEntity.setDaftarBarangGadai(transaksiBarangs);

		// TransaksiBarangEntity transaksibarang = new TransaksiBarangEntity();

		doSaveCicilan(transaksiCicilanTetapEntity);

		return transaksiRepo.save(transaksiCicilanTetapEntity);

	}

	public CicilanEntity doSaveCicilan(
			TransaksiCicilanTetapEntity transaksiCicilanTetapEntity) {

		int JangkaWaktuLooping = JangkaWaktu / PeriodeJasaPenyimpanan;
		double Pokok = transaksiCicilanTetapEntity.getTxBiayaJasaPenyPer();

		// List<CicilanEntity> listcicilan = new ArrayList<>();

		for (int i = 0; i < JangkaWaktuLooping; i++) {
			CicilanEntity cicilan = new CicilanEntity();
			cicilan.setCicilanKe(i + 1);
			Date tanggalTransaksi = transaksiCicilanTetapEntity.getTanggal_tx();
			LocalDateTime cicilanTgl = tanggalTransaksi.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDateTime();
			double bunga = Pokok * 1 / 100;

			cicilan.setTxPokok(Pokok);
			cicilan.setTxBunga(bunga);
			if (i == 0) {
				cicilan.setTxStatus("AKTIF");
			} else {
				cicilan.setTxStatus("BELUM AKTIF");
			}
			
				LocalDateTime tgl = cicilanTgl.plusMonths(i);
				LocalDateTime tgljatuhTemp = cicilanTgl.plusMonths(i + 1);
				LocalDateTime tglJatuhTem = tgljatuhTemp.minusDays(1);
				Date TanggalAktif = Date
						.from(tgl.atZone(ZoneId.systemDefault()).toInstant());
				Date TanggalJatuhTemp = Date.from(
						tglJatuhTem.atZone(ZoneId.systemDefault()).toInstant());
				cicilan.setTanggalAktif(TanggalAktif);
				cicilan.setTanggalJatuhTempo(TanggalJatuhTemp);
			

			// listcicilan.add(cicilan);

			cicilanRepo.save(cicilan);
		}
		return null;

	}

	public List<TransaksiCicilanTetapEntity> findAll(
			TransaksiModel transaksiModel) {
		List<TransaksiCicilanTetapEntity> transaksi = new ArrayList<>();
		TransaksiSpec specs = new TransaksiSpec(transaksiModel);
		transaksiRepo.findAll(specs).forEach(transaksi::add);

		return transaksi;
	}

	public List<TransaksiInfoEntity> findAllByCriteria(String noTransaksi,
			String productId, String custId) {

		List<TransaksiInfoEntity> transaksi = new ArrayList<>();
		transaksiInfoRepo.findTransaksibyBarangProductCustomer(noTransaksi,
				productId, custId).forEach(transaksi::add);

		return transaksi;
	}

	public List<TransaksiGetDetailEntity> GetDetailTransaksiCicilanTetap(
			String noTransaksi) {
		List<TransaksiGetDetailEntity> transaksi = new ArrayList<>();
		getDetailTransaksiRepo.findTransaksibyBarangProductCustomer(noTransaksi)
				.forEach(transaksi::add);
		return transaksi;
	}

	public ProductGetListEntity findById(String id)
			throws ClientException, NotFoundException {

		ProductGetListEntity product = getListProductRepo.findById(id)
				.orElse(null);
		productValidator.nullCheckObject(id);

		return product;
	}
}
