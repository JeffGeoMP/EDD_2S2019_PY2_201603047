package Default;

import Interface.Login;
import Structures.AVLTree;
import Structures.LinkedList;
import Structures.NodeList;
import Structures.Table_Hash;
import Structures.Stack;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author JeffGeo
 */
public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
       Stack Operations = new Stack();          //Initializing Stack for Operations in the system
       Table_Hash Users = new Table_Hash();     //Initializing Hash Table for add Users

       Login login = new Login(Users, Operations);
       login.setVisible(true);

        
    }
}
