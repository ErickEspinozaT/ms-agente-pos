package ec.com.ticketshow.domain;

import ec.com.ticketshow.dto.CanonicalResponse;
import ec.com.ticketshow.dto.CertificateDTO;
import ec.com.ticketshow.dto.CertificateInformationResponse;
import ec.com.ticketshow.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Properties;

@Service
public class CertificationProviderService {
    @Autowired
    FileRepository fileRepository;

    public ResponseEntity<Mono<CertificateInformationResponse>> getCertification(){
        CanonicalResponse operationResponse = new CanonicalResponse();
        try {
            Properties certificationProperties = fileRepository.readPropertiesFile();
            CertificateDTO certificationInfo = new CertificateDTO(certificationProperties.getProperty("idPOS"),
                    certificationProperties.getProperty("code"));
            CertificateInformationResponse response = new CertificateInformationResponse();
            operationResponse.setCode("200");
            operationResponse.setMessage("OK");
            response.setOperationResponse(operationResponse);
            response.setData(certificationInfo);
            return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .body(Mono.just(response));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
