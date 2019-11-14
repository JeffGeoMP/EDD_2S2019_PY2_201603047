package Others;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JeffGeo
 */
public class Users {
    private String User;
    private String Pass;
    private String Hour;
    private String Date;
    
        public Users(String user, String pass){
            this.User = user;
            this.Pass = pass;
            this.Hour = Hour();
            this.Date = Date();
        }

    public String getHour() {
        return Hour;
    }

    public void setHour(String Hour) {
        this.Hour = Hour;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
        
        public Users(){
            this.User = "";
            this.Pass = "";
        }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    @Override
    public String toString(){
        return "Username: "+User+ " Password: "+Pass;
    }
    
    private String Hour() {
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
        return dateformat.format(date);
    }

    private String Date() {
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        return dateformat.format(date);
    }
}
