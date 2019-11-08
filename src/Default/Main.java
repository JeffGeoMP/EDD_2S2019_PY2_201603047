package Default;

import Interface.Login;
import Structures.Hash_Table;
import Structures.Stack;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author JeffGeo
 */
public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
       Stack Operations = new Stack();          //Initializing Stack for Operations in the system
       Hash_Table Users = new Hash_Table();     //Initializing Hash Table for add Users
       
       Login login = new Login(Users, Operations);
       login.setVisible(true);
    }
}
