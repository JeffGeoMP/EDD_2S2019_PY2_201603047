package Others;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JeffGeo
 */
public class Files {
    private String Filename;
    private String Content;
    private String Date;
    private String Hour;
    private String Username;

    public Files(String Filename, String Content, String Username) {
        this.Filename = Filename;
        this.Content = Content;
        this.Username = Username;
        this.Date = Date();
        this.Hour = Hour();
    }
    
    public Files(String Filename, String Content, String Username, String Date, String Hour){
        this.Filename = Filename;
        this.Content = Content;
        this.Username = Username;
        this.Date = Date;
        this.Hour = Hour;
    }

    public String getFilename() {
        return Filename;
    }

    public String getContent() {
        return Content;
    }

    public String getDate() {
        return Date;
    }

    public String getHour() {
        return Hour;
    }

    public String getUsername() {
        return Username;
    }

    public void setFilename(String Filename) {
        this.Filename = Filename;
    }

    public void setContent(String Content) {
        this.Content = Content;
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
