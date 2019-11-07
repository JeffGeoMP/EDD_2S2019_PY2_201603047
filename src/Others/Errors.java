package Others;

/**
 *
 * @author JeffGeo
 */
public class Errors {
    private String user;
    private String reason;
    
    public Errors(String user, String reason){
        this.user = user;
        this.reason = reason;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
