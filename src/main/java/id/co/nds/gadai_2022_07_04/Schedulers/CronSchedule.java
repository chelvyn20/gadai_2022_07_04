
package id.co.nds.gadai_2022_07_04.Schedulers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.entities.DendaEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiCicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.services.CicilanService;
import id.co.nds.gadai_2022_07_04.services.DendaService;
import id.co.nds.gadai_2022_07_04.services.ProductServices;
import id.co.nds.gadai_2022_07_04.services.TransaksiService;

@Component
public class CronSchedule {

	@Autowired
	private CicilanService cicilanService;

	@Autowired
	private TransaksiService transaksiService;

	@Autowired
	private DendaService dendaService;

	@Autowired
	private ProductServices productServices;

	static final Logger logger = LogManager.getLogger(CronSchedule.class);

	@Scheduled(cron = "0/10 * * * * ?")
	public void cronSchedule() throws Exception {

		List<CicilanEntity> cicilan = cicilanService.findAll();
		List<TransaksiCicilanTetapEntity> transaksi = transaksiService
				.findAllTransaksi();

		double dendaTotal = 0;
		List<String> result = new ArrayList<>();

		for (int i = 0; i < transaksi.size(); i++) {
			ProductEntity product = productServices
					.findById(transaksi.get(i).getProductId());
			for (int j = 0; j < cicilan.size(); j++) {
				if (transaksi.get(i).getNoTransaksi()
						.equals(cicilan.get(j).getNoTransaksi())) {
					if (cicilan.get(j).getTxStatus().equals("AKTIF")) {
						if (cicilan.get(j).getTanggalAktif().compareTo(
								cicilan.get(j).getTanggalJatuhTempo()) > 0) {
							dendaService.add(cicilan.get(j).getNoTransaksi(),
									cicilan.get(j).getCicilanKe(),
									cicilan.get(j).getTxPokok(),
									cicilan.get(j).getTxBunga());

							cicilanService.editTerlambat(
									cicilan.get(j).getNoTransaksi(),
									cicilan.get(j).getCicilanKe());
						} else {
							result.add("Cicilan Ke : "
									+ cicilan.get(j).getCicilanKe());
							result.add("Status Cicilan :"
									+ cicilan.get(j).getTxStatus());

						}

					} else if (cicilan.get(j).getTxStatus()
							.equals("BELUM AKTIF")) {

						LocalDateTime now = LocalDateTime.now();
						Date Tgl = Date.from(
								now.atZone(ZoneId.systemDefault()).toInstant());

						if (Tgl.compareTo(
								cicilan.get(j).getTanggalAktif()) > 0) {
							cicilanService.editAktif(
									cicilan.get(j).getNoTransaksi(),
									cicilan.get(j).getCicilanKe());
							// logger.info(" Cicilan Ke :" + "AKTIF");
							// logger.info("Status Cicilan : "
							// + cicilan.get(j).getTxStatus());
						}

						else {
							result.add("Cicilan Ke : "
									+ cicilan.get(j).getCicilanKe());
							result.add("Status Cicilan :"
									+ cicilan.get(j).getTxStatus());
						}

					}
					if (cicilan.get(j).getTxStatus().equals("TERLAMBAT")) {

						List<DendaEntity> denda = dendaService
								.findAllByTransaksiCicilan(
										transaksi.get(i).getNoTransaksi(),
										cicilan.get(j).getCicilanKe());
						int dendacount = denda.size();
						LocalDateTime now = LocalDateTime.now(); // Current Date
						Date Tglhari = Date.from(
								now.atZone(ZoneId.systemDefault()).toInstant());
						for (int b = 0; b < denda.size(); b++) {
							dendaTotal += denda.get(b).getBiayaDenda();
						}

						for (int d = dendacount; d < dendacount; d++) {
							if (Tglhari.compareTo(
									denda.get(d).getTglDenda()) >= product
											.getBDKeterlambatanPer()) {
								dendaService.addDendaBerikut(
										cicilan.get(j).getNoTransaksi(),
										cicilan.get(j).getCicilanKe(),
										cicilan.get(j).getTxPokok(),
										cicilan.get(j).getTxBunga());
							}
						}

						result.add("Cicilan Ke : "
								+ cicilan.get(j).getCicilanKe());
						result.add("Status Cicilan :"
								+ cicilan.get(j).getTxStatus());
					}

					// logger.info(
					// " Cicilan Ke :" + cicilan.get(j).getCicilanKe());
					// logger.info(
					// "Status Cicilan : " + cicilan.get(j).getTxStatus());

				}

			}
			if (result.size() > 0) {
				logger.debug("Start Cron at", Calendar.getInstance().getTime());
				logger.info(
						"No Transaksi : " + transaksi.get(i).getNoTransaksi());
				logger.info("Denda Total : " + dendaTotal);
				for (int r = 0; r < result.size(); r++) {
					logger.info(result.get(r));
				}

			}
		}

	}
}
