package id.co.nds.gadai_2022_07_04.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.YearMonth;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PembayaranIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor ssci,
			Object o) throws HibernateException {
		// TODO Auto-generated method stub
        Connection connection = ssci.connection();
        
        int year = Integer.parseInt(Integer.toString(YearMonth.now().getYear()).substring(2));
   
        int month = YearMonth.now().getMonthValue();

        String ym = String.format("%2d%02d", year, month);

        try{

            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS seq FROM tx_pembayaran_h ");
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int seq = rs.getInt( "seq")+1;
                String code = String.format("IPC-%2d-%02d-%06d", year, month, seq);
                System.out.println("Generated Pembayaran Id: "+ code);
                return code;
            } else{
                throw new HibernateException("Generator gagal membuat id pelanggan");

            } 
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
	}
	
}
