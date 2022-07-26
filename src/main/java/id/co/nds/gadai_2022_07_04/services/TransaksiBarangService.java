
package id.co.nds.gadai_2022_07_04.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiBarangEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.keys.KeysGadai;
import id.co.nds.gadai_2022_07_04.models.ProductModel;
import id.co.nds.gadai_2022_07_04.models.TransaksiBarangModel;
import id.co.nds.gadai_2022_07_04.repos.TransaksiBarangRepo;
import id.co.nds.gadai_2022_07_04.repos.specs.TransaksiBarangSpec;
import id.co.nds.gadai_2022_07_04.validators.TransaksiBarangValidator;

@Service
public class TransaksiBarangService {

	@Autowired
	private TransaksiBarangRepo transaksiBarangRepo;

	TransaksiBarangValidator transaksiBarangValidator = new TransaksiBarangValidator();

	public List<TransaksiBarangEntity> findAll() {

		List<TransaksiBarangEntity> transaksi = new ArrayList<>();
		transaksiBarangRepo.findAll().forEach(transaksi::add);

		return transaksi;
	}

	// public TransaksiBarangEntity add(TransaksiBarangModel transaksiBarangModel)
	// 		throws ClientException {

	// 	TransaksiBarangEntity transaksi = new TransaksiBarangEntity();
	// 	// transaksi.setNoTransaksi(transaksiBarangModel.getNoTransaksi());
	// 	transaksi.setNamaBarang(transaksiBarangModel.getNamaBarang());
	// 	transaksi.setKondisi(transaksiBarangModel.getKondisi());
	// 	transaksi.setJumlah(transaksiBarangModel.getJumlah());
	// 	transaksi.setHargaPerSatuan(transaksiBarangModel.getHargaPersatuan());
	// 	transaksi.

	// 	return transaksiBarangRepo.save(transaksi);
	// }

	public List<TransaksiBarangEntity> findAllByCriteria(
			TransaksiBarangModel transaksiBarangModel) {

		List<TransaksiBarangEntity> transaksi = new ArrayList<>();
		TransaksiBarangSpec specs = new TransaksiBarangSpec(
				transaksiBarangModel);
		transaksiBarangRepo.findAll(specs).forEach(transaksi::add);

		return transaksi;
	}

	public TransaksiBarangEntity findById(String noTransaksi, Integer noUrut)
			throws ClientException, NotFoundException {

		KeysGadai keysGadai = new KeysGadai(noTransaksi, noUrut);
		TransaksiBarangEntity transaksi = transaksiBarangRepo
				.findById(keysGadai).orElse(null);
		transaksiBarangValidator.nullCheckObject(transaksi);

		return transaksi;

	}

	// public TransaksiBarangEntity delete(
	// TransaksiBarangModel transaksiBarangModel)
	// throws ClientException, NotFoundException {

	// if (!TransaksiBarangRepo
	// .existsById(transaksiBarangModel.getNoTransaksi())) {

	// throw new NotFoundException(
	// "Cannot find Transaksi Barang with noTransaksi : "
	// + transaksiBarangModel.getNoTransaksi());
	// }

	// TransaksiBarangEntity transaksi = new TransaksiBarangEntity();
	// transaksi = findById(transaksiBarangModel.getNoTransaksi(),
	// transaksiBarangModel.getNoUrut());

	// }
}
