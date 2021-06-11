package ec.com.ticketshow.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.ticketshow.domain.CertificationProviderService;
import ec.com.ticketshow.dto.CertificateInformationResponse;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class CertificationController {

    @Autowired
    CertificationProviderService certificationProviderService;

    @RequestMapping(path = "/agent/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<CertificateInformationResponse>> getCertificate() {
        return certificationProviderService.getCertification();
    }
}
