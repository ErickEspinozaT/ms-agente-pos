package ec.com.ticketshow.dto;

import lombok.Data;

@Data
public class CertificateInformationResponse {
	private CanonicalResponse operationResponse;
	private CertificateDTO data;
}