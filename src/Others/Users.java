package Others;

/**
 *
 * @author JeffGeo
 */
public class Users {
    private String User;
    private String Pass;
    
        public Users(String user, String pass){
            this.User = user;
            this.Pass = pass;
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
}
