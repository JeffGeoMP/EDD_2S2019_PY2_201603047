
package Others;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JeffGeo
 */
public class Folder {
    private String FolderName;
    private String FolderPath;
    private String Hour;
    private String Date;

    public Folder(String FolderName, String FolderPath) {
        this.FolderName = FolderName;
        this.FolderPath = FolderPath;
        this.Hour = Hour();
        this.Date = Date();
    }
    
    public String getFolderPath() {
        return FolderPath;
    }

    public void setFolderPath(String FolderPath) {
        this.FolderPath = FolderPath;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String FolderName) {
        this.FolderName = FolderName;
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
