package ec.com.ticketshow.dto;

public class CertificateDTO {

    private String idPOS;
    private String code;

    public CertificateDTO(String idPOS, String code) {
        this.idPOS = idPOS;
        this.code = code;
    }

    public String getIdPOS() {
        return idPOS;
    }

    public void setIdPOS(String idPOS) {
        this.idPOS = idPOS;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
