package Default;

import Interface.Login;
import Structures.AVLTree;
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
//        AVLTree avl = new AVLTree();
//        avl.add("a", "CCC", "Jefferson");
//        avl.add("b", "CCC", "Jefferson");
//        avl.add("j", "CCC", "Jefferson");
//        avl.add("l", "CCC", "Jefferson");
//        avl.add("m", "CCC", "Jefferson");
//        avl.add("u", "CCC", "Jefferson");
//        avl.add("n", "CCC", "Jefferson");
//        avl.InOrden(avl.Root);
//       
//        //avl.GenerateImage(1);
//        avl.GenerateImage(1);
    }
}
