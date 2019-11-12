package Default;

import Interface.Login;
import Structures.AVLTree;
import Structures.Hash_Table;
import Structures.Stack;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author JeffGeo
 */
public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
//       Stack Operations = new Stack();          //Initializing Stack for Operations in the system
//       Hash_Table Users = new Hash_Table();     //Initializing Hash Table for add Users
//       
//       Login login = new Login(Users, Operations);
//       login.setVisible(true);
        AVLTree Files = new AVLTree();
        Files.add(100);
        Files.add(25);
        Files.add(58);
        Files.add(666);
        Files.InOrden(Files.Root);
        System.out.println("");
        //Files.InOrden(Files.Root);
        
        Files.Remove(57);
        Files.Remove(25);

        
        Files.InOrden(Files.Root);
    }
}
