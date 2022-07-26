package id.co.nds.gadai_2022_07_04.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Embeddable
public class KeysTransaksiCicilan implements Serializable {
	
	
	@Id
	@GenericGenerator(name = "transaksi_id_seq", strategy = "id.co.nds.gadai_2022_07_04.generators.TransaksiIdGenerator")
	@GeneratedValue(generator = "transaksi_id_seq")
	@Column(name = "no_transaksi")
	private String noTransaksi;
	
	@Column(name = "cicilan_ke")
	private Integer cicilanKe;

	public KeysTransaksiCicilan(){

	}

	public KeysTransaksiCicilan(String noTransaksi, Integer cicilanKe) {
		this.noTransaksi = noTransaksi;
		this.cicilanKe = cicilanKe;
	}

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public Integer getCicilanKe() { return cicilanKe; }

	public void setCicilanKe(Integer cicilanKe) { this.cicilanKe = cicilanKe; }
}
