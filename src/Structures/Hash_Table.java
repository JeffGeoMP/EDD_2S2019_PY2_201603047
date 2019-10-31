
package Structures;
import Others.Users;
import java.util.Arrays;
 /*
 * @author JeffGeo
 */
public class Hash_Table {
    private Object[] Users;
    private int Size;
    private int Mod;
    private int Elements;
    
        public Hash_Table(){
            Size = 7;
            Mod = Size;
            Users = new Object[Size];
            Arrays.fill(Users, null);
        }
        
    public void Add(String user, String pass){
        if(Percentage_Used()<75){
            System.out.println("Porcentaje Usado: "+Percentage_Used());
            int Index = user.length()% Mod;
            if(Users[Index]==null){
                Users[Index] = new Users(user,pass);
                Elements++;
            }else{
                int Squared = Index*Index;
                if(Squared>Users.length){
                    for(int i=0; i<Users.length; i++){
                        if(Users[i]==null){
                            Users[i] = new Users(user,pass);
                            Elements++;
                            break;
                        }
                    }
                }else{
                    if(Users[Squared]!=null){
                        Users[Squared] = new Users(user,pass);
                        Elements++;
                    }
                }
            }
        }else{
            
        }
    }
    
    public void Search(String user){
        
    }
    
       
    private int Percentage_Used(){
        return (Elements*100)/Users.length;
    }
    
    private int Next_Number_Prime(){
        return 0;
    }
    
    private void Array_Increase(){
        
    }
    
    public void Print_Table(){
        int count = 0;
        for (Object User : Users) {
            if(User!=null){
                System.out.println("Hash["+count+"] "+User.toString());
            }
            count++;
        }
    }
}
