package ec.com.ticketshow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDTO {
	private String idPOS;
	private String code;
	private String macAddress;
	private String ipAddress;
}
