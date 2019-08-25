package ec.com.ticketshow.dto;

public class CertificateInformationResponse {
    private CanonicalResponse operationResponse;
    private CertificateDTO data;


    public CertificateDTO getData() {
        return data;
    }

    public void setData(CertificateDTO data) {
        this.data = data;
    }

    public CanonicalResponse getOperationResponse() {
        return operationResponse;
    }

    public void setOperationResponse(CanonicalResponse operationResponse) {
        this.operationResponse = operationResponse;
    }
}