package emsi.iir4.loka.web.controller;

public class TicketDTO {

    //data transfer object for ticket entity

    private Long id;
    private String devUserName;
    private String clientUserName;
    private String title;
    private String description;
    private String envEx;
    private String urgence;
    private String cloture;

    public TicketDTO() {
    }

    public TicketDTO(Long id, String devUserName, String clientUserName, String title, String description, String envEx, String urgence, String cloture) {
        this.id = id;
        this.devUserName = devUserName;
        this.clientUserName = clientUserName;
        this.title = title;
        this.description = description;
        this.envEx = envEx;
        this.urgence = urgence;
        this.cloture = cloture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevUserName() {
        return devUserName;
    }

    public void setDevUserName(String devUserName) {
        this.devUserName = devUserName;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnvEx() {
        return envEx;
    }

    public void setEnvEx(String envEx) {
        this.envEx = envEx;
    }

    public String getUrgence() {
        return urgence;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public String getCloture() {
        return cloture;
    }

    public void setCloture(String cloture) {
        this.cloture = cloture;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", devUserName='" + devUserName + '\'' +
                ", clientUserName='" + clientUserName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", envEx='" + envEx + '\'' +
                ", urgence='" + urgence + '\'' +
                ", cloture='" + cloture + '\'' +
                '}';
    }
}
