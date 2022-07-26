
package id.co.nds.gadai_2022_07_04.keys;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;

@Embeddable
public class KeysGadai implements Serializable {

	@Id
	@GenericGenerator(name = "transaksi_id_seq", strategy = "id.co.nds.gadai_2022_07_04.generators.TransaksiIdGenerator")
	@GeneratedValue(generator = "transaksi_id_seq")
	@JoinColumn(name = "no_transaksi", referencedColumnName = "noTransaksi")
	@Column(name = "no_transaksi")
	private String noTransaksi;

	@Column(name ="no_urut")
	private Integer noUrut;

	public KeysGadai(String noTransaksi, Integer noUrut) {
		this.noTransaksi = noTransaksi;
		this.noUrut = noUrut;
	}

	public KeysGadai() {
		
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		KeysGadai that = (KeysGadai) o;
		return noTransaksi.equals(that.noTransaksi)
				&& noUrut.equals(that.noUrut);
	}

	@Override
	public int hashCode() { // TODO Auto-generated method stub
		return Objects.hash(noTransaksi, noUrut);
	}

	public String getNoTransaksi() { return noTransaksi; }

	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}

	public Integer getNoUrut() { return noUrut; }

	public void setNoUrut(Integer noUrut) { this.noUrut = noUrut; }

}
