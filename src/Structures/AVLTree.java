package Structures;

import Others.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author JeffGeo
 */
public class AVLTree {

    public NodeAVL Root;
    public int count;

    public AVLTree() {
        this.Root = null;
        this.count = 0;
    }

    //Methods for AVL Tree -----------------------------------------------------
    private int Height(NodeAVL n) {
        if (n == null) {
            return -1;
        } else {
            return n.Height;
        }
    }

    private int Balance(NodeAVL n) {
        if (n == null) {
            return 0;
        } else {
            return this.Height(n.Right) - this.Height(n.Left);
        }
    }
    //--------------------------------------------------------------------------

    //Using Interface ----------------------------------------------------------
    public void add(String Filename, String Content, String Username) {
        NodeAVL new_node = new NodeAVL(Filename, Content, Username);
        if (this.Root == null) {
            Root = new_node;
        } else {
            Root = addAVL(new_node, Root);
        }
    }

    public void Remove(String Filename) {
        Root = removeAVL(Root, Filename);
        System.out.println("File Eliminate " + Filename);
    }

    public boolean Search(String Filename) {
        return (searchAVL(this.Root, Filename) != null);
    }

    public void EditName(String Filename, String new_Filename) {
        if (this.Search(Filename)) {
            NodeAVL temp = searchAVL(this.Root, Filename);
            NodeAVL aux = new NodeAVL(new_Filename, temp.File.getContent(), temp.File.getUsername(), temp.File.getDate(), temp.File.getHour());
            Remove(Filename);
            if (this.Root == null) {
                Root = aux;
            } else {
                Root = addAVL(aux, Root);
            }
        } else {
            System.out.println("Not Found File");
        }
    }

    public void EditContent(String Filename, String new_Content) {
        if (this.Search(Filename)) {
            NodeAVL temp = searchAVL(this.Root, Filename);
            Root = removeAVL(Root, Filename);
            temp.File.setContent(new_Content);
            if (this.Root == null) {
                Root = temp;
            } else {
                Root = addAVL(temp, Root);
            }
        } else {
            System.out.println("Not Found File");
        }
    }

    public void GenerateImage(int n) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = "AVLTree" + n + ".png";
        String rutatxt = "src\\Files .DOT\\AVLTree.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("rankdir=TB");
            pw.println("node [shape = record, style=filled, fillcolor=seashell2]");
            pw.print(Code(Root));
            pw.println("}");
            archivo.close();

            // Creates rutaImagen of Structure
            try {
                String[] cmd = new String[5];
                cmd[0] = rutaDot;
                cmd[1] = parametroT;
                cmd[2] = rutatxt;
                cmd[3] = parametroO;
                cmd[4] = rutaImagen;

                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("ERROR en archivo: " + e.toString());
        }
    }

    //--------------------------------------------------------------------------
    public String Code(NodeAVL n){
        String values = "";
        if(n.Left ==null && n.Right==null){
            values += n.File.getFilename()+"[label=\"Filename: "+n.File.getFilename()+"\\n Content: "+n.File.getContent()+"\\n Balance Factor: "+Balance(n)+"\\n Height: "+n.Height+"\\n TimeStamp: "+n.File.getDate()+" "+n.File.getHour()+"\\n Owner: "+n.File.getUsername()+"\"]\n";
        }else{
            values += n.File.getFilename()+"[label=\"<C0>|Filename: "+n.File.getFilename()+"\\n Content: "+n.File.getContent()+"\\n Balance Factor: "+Balance(n)+"\\n Height: "+n.Height+"\\n TimeStamp: "+n.File.getDate()+" "+n.File.getHour()+"\\n Owner: "+n.File.getUsername()+"|<C1>\"]\n";
        }
        if(n.Left!=null){
            values += Code(n.Left) +n.File.getFilename()+":C0->"+n.Left.File.getFilename() + "\n";
        }
        if(n.Right!=null){
            values += Code(n.Right) +n.File.getFilename()+":C1->"+n.Right.File.getFilename()+ "\n";
        }
        return values;
    }
    //Operations in the AVL Tree -----------------------------------------------
    private NodeAVL addAVL(NodeAVL new_node, NodeAVL subtree) {
        NodeAVL sup = subtree;
        int Comparation = new_node.File.getFilename().compareTo(subtree.File.getFilename());
        if (Comparation < 0) {
            if (subtree.Left == null) {
                subtree.Left = new_node;
            } else {
                subtree.Left = addAVL(new_node, subtree.Left);
                if ((this.Height(subtree.Left) - this.Height(subtree.Right)) == 2) {
                    int Comparation2 = Data(new_node).compareTo(subtree.Left.File.getFilename());
                    if (Comparation2 < 0) {
                        sup = RotationLeft(subtree);
                    } else {
                        sup = DRotationLeft(subtree);
                    }
                }
            }
        } else if (Comparation > 0) {
            if (subtree.Right == null) {
                subtree.Right = new_node;
            } else {
                subtree.Right = addAVL(new_node, subtree.Right);
                if ((this.Height(subtree.Right) - this.Height(subtree.Left)) == 2) {
                    int Comparation2 = new_node.File.getFilename().compareTo(subtree.Right.File.getFilename());
                    if (Comparation2 > 0) {
                        sup = RotationRight(subtree);
                    } else {
                        sup = DRotationRight(subtree);
                    }
                }
            }
        } else {
            if (Comparation == 0) {
                System.out.println("File is " + new_node.File.getFilename());
            }
        }
        if (subtree.Left == null && (subtree.Right != null)) {
            subtree.Height = subtree.Right.Height + 1;
        } else if (subtree.Right == null && (subtree.Left != null)) {
            subtree.Height = subtree.Left.Height + 1;
        } else {
            subtree.Height = Math.max(this.Height(subtree.Left), this.Height(subtree.Right)) + 1;
        }
        return sup;
    }

    private NodeAVL searchAVL(NodeAVL root, String Filename) {
        if (root == null) {
            return null;
        } else if (root.File.getFilename().equals(Filename)) {
            return root;
        } else if (root.File.getFilename().compareTo(Filename) < 0) {
            return searchAVL(root.Right, Filename);
        } else {
            return searchAVL(root.Left, Filename);
        }
    }

    private NodeAVL removeAVL(NodeAVL n, String key) {
        if (n == null) {
            return null;
        }
        int Comparation = key.compareTo(n.File.getFilename());
        if (Comparation < 0) {
            n.Left = removeAVL(n.Left, key);
        } else if (Comparation > 0) {
            n.Right = removeAVL(n.Right, key);
        } else {

            if (n.Left == null) {
                return n.Right;

            } else if (n.Right == null) {
                return n.Left;
            } else {
                if (n.Left.Height > n.Right.Height) {
                    String successorValue = findMax(n.Left);
                    n.File.setFilename(successorValue);
                    n.Left = removeAVL(n.Left, successorValue);
                } else {
                    String successorValue = findMin(n.Right);
                    n.File.setFilename(successorValue);
                    n.Right = removeAVL(n.Right, successorValue);
                }
            }
        }

        // Update balance factor and height values.
        int LeftNodeHeight = 0;
        int RightNodeHeight = 0;
        if (n.Left == null) {
            LeftNodeHeight = -1;
        } else {
            LeftNodeHeight = n.Left.Height;
        }

        if (n.Right == null) {
            RightNodeHeight = -1;
        } else {
            RightNodeHeight = n.Right.Height;
        }
        n.Height = Math.max(LeftNodeHeight, RightNodeHeight) + 1;

        // Re-balance tree.
        if (Balance(n) == -2) {
            if (Balance(n.Left) <= 0) {
                return RotationLeft(n);
            } else {
                return DRotationLeft(n);
            }
        } else if (Balance(n) == +2) {
            if (Balance(n.Right) >= 0) {
                return RotationRight(n);
            } else {
                return DRotationRight(n);
            }
        }
        return n;
    }
    //--------------------------------------------------------------------------

    //Others Methods------------------------------------------------------------
    private String findMin(NodeAVL node) {
        while (node.Left != null) {     //Helper Method to find
            node = node.Left;           //the LeftMost Node
        }
        return node.File.getFilename();                //Which has the smallest 
    }

    private String findMax(NodeAVL node) {
        while (node.Right != null) {    //Helper Method to find
            node = node.Right;          //thr RightMost node
        }
        return node.File.getFilename();                //Which has te largest value
    }

    private String Data(NodeAVL n) {
        Files f;
        f = (Files) n.File;
        return f.getFilename();
    }
    //--------------------------------------------------------------------------

    //Orders for print the AVL Tree ---------------------------------------------
    public void InOrden(NodeAVL n) {
        if (n != null) {
            InOrden(n.Left);
            System.out.println(n.File.getFilename() + "," + n.File.getContent());
            InOrden(n.Right);
        }
    }

//    public void PreOrden(NodeAVL n) {
//        if (n != null) {
//            System.out.print(n.Key + ",");
//            PreOrden(n.Left);
//            PreOrden(n.Right);
//        }
//    }
//
//    public void PostOrden(NodeAVL n) {
//        if (n != null) {
//            PostOrden(n.Left);
//            PostOrden(n.Right);
//            System.out.print(n.Key + ",");
//        }
//    }
    //--------------------------------------------------------------------------
    //Rotations for AVL Tree----------------------------------------------------
    //Simple left Rotation
    private NodeAVL RotationLeft(NodeAVL n) { //Right Rotation
        NodeAVL aux = n.Left;  //equals aux to subtree of n
        n.Left = aux.Right;     //The subtree of n in the branch left
        //will equals to the branch left of aux
        aux.Right = n;
        n.Height = Math.max(this.Height(n.Left), this.Height(n.Right)) + 1;
        aux.Height = Math.max(this.Height(aux.Left), this.Height(aux.Right)) + 1;

        return aux;
    }

    //Simple Right Rotation
    private NodeAVL RotationRight(NodeAVL n) { //Left Rotation
        NodeAVL aux = n.Right;  //equals aux to subtree of n
        n.Right = aux.Left;     //The subtree of n in the branch right
        //will equals to the branch left of aux
        aux.Left = n;
        n.Height = Math.max(this.Height(n.Left), this.Height(n.Right)) + 1;
        aux.Height = Math.max(this.Height(aux.Left), this.Height(aux.Right)) + 1;

        return aux;
    }

    //Double Left Rotation
    private NodeAVL DRotationLeft(NodeAVL n) {
        NodeAVL aux;
        n.Left = this.RotationRight(n.Left);
        aux = RotationLeft(n);
        return aux;
    }

    //Double Right Rotation
    private NodeAVL DRotationRight(NodeAVL n) {
        NodeAVL aux;
        n.Right = this.RotationLeft(n.Right);
        aux = RotationRight(n);
        return aux;
    }
    //--------------------------------------------------------------------------
}

class NodeAVL {

    public Files File;  //Here save the data of the files
    public int Height;
    public NodeAVL Left;
    public NodeAVL Right;

    public NodeAVL(String Filename, String Content, String Username) {
        this.File = new Files(Filename, Content, Username);
        this.Height = 0;
        this.Left = null;
        this.Right = null;
    }

    public NodeAVL(String Filename, String Content, String Username, String Date, String Hour) {
        this.File = new Files(Filename, Content, Username, Date, Hour);
        this.Height = 0;
        this.Left = null;
        this.Right = null;
    }
}
