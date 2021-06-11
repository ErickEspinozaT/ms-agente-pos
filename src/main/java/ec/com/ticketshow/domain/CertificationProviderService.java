package ec.com.ticketshow.domain;

import ec.com.ticketshow.dto.CanonicalResponse;
import ec.com.ticketshow.dto.CertificateDTO;
import ec.com.ticketshow.dto.CertificateInformationResponse;
import ec.com.ticketshow.repository.FileRepository;
import ec.com.ticketshow.utils.GetNetworkAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class CertificationProviderService {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	FileRepository fileRepository;

	public ResponseEntity<Mono<CertificateInformationResponse>> getCertification() {
		CanonicalResponse operationResponse = new CanonicalResponse();
		try {
			String macAddress = searchForMac();
			String ipAddress = getIPAddress();
			Properties certificationProperties = fileRepository.readPropertiesFile();
			CertificateDTO certificationInfo = new CertificateDTO(certificationProperties.getProperty("idPOS"),
					certificationProperties.getProperty("code"), macAddress, ipAddress);
			CertificateInformationResponse response = new CertificateInformationResponse();
			operationResponse.setCode("200");
			operationResponse.setMessage("OK");
			response.setOperationResponse(operationResponse);
			response.setData(certificationInfo);
			return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
					.body(Mono.just(response));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	private String getIPAddress() {
		String resp = "";
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			resp = localHost.getHostAddress();
			log.info((resp));
		} catch (Exception e) {
			log.error("ERROR: " + e);
		}
		return resp;
	}

	private String searchForMac() throws SocketException {
		String firstInterface = null;
		Map<String, String> addressByNetwork = new HashMap<>();
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface network = networkInterfaces.nextElement();
			byte[] bmac = network.getHardwareAddress();
			if (bmac != null) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bmac.length; i++) {
					sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "-" : ""));
				}
				if (sb.toString().isEmpty() == false) {
					addressByNetwork.put(network.getName(), sb.toString());
				}
				if (sb.toString().isEmpty() == false && firstInterface == null) {
					firstInterface = network.getName();
				}
			}
		}
		if (firstInterface != null) {
			return addressByNetwork.get(firstInterface);
		}
		return null;
	}

}
