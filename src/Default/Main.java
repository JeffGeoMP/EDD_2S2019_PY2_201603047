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
       
       
       
//       


//       
//        //avl.GenerateImage(1);
//        avl.GenerateImage(1);
//        LinkedList Graph = new LinkedList("Root");
//        String current_path = "";                             //Initial path
//        current_path = Graph.Head.Folders.getFolderPath();    // path Initial
//        System.out.println(current_path);

//        LinkedList Graph = new LinkedList("Root");
//        String current_path = "Root/";
////        Carpeta Root
//        Graph.add("Musica", current_path);
//        Graph.add("Videos", current_path);
//          Graph.add("Peliculas", current_path);
//          Graph.add("Imagenes", current_path);
//          Graph.add("Documentos", current_path);
//          Graph.add("Videos", current_path);
//        Graph.add("Rap", current_path + "Musica/");
//        Graph.add("Cumbias", current_path + "Musica/");
//        Graph.add("Merengue", current_path + "Musica/");
//        Graph.add("Salsa", current_path + "Musica/");
//        Graph.add("Vol1", current_path + "Musica/Merengue/");
//        Graph.add("Vol2", current_path + "Musica/Merengue/");
//        Graph.add("Vol3", current_path + "Musica/Merengue/");
//
//        Graph.edit(current_path+"Musica/", "Merengue", "Reggae");
//        
//               
//        Graph.add("BobMarley", current_path+"Musica/Reggae/");
//                       Graph.print();
//        Graph.GenerateImage(23);
//        Graph.GenerateImageMatrix(755);

        
    }
}
