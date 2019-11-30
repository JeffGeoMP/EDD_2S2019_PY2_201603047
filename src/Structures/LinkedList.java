package Structures;

import Others.Folder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author JeffGeo
 */
public class LinkedList {

    public NodeList Head;
    public NodeList End;
    public int Size;

    public LinkedList() {
        this.Head = null;
        this.End = null;
        this.Size = 0;
    }

    public LinkedList(String Nameroot) {
        this.addRoot(Nameroot);
    }

    //Methods Graph-----------------------------------------------------------
    public boolean Isempty() {
        return (this.Head == null) ? true : false;
    }

    public boolean add(String Foldername, String FolderPath) {
        boolean successfull = false;
        NodeList Parent = searchGraph(FolderPath);
        if (Parent != null) {
            String new_path = FolderPath + Foldername + "/";
            NodeList aux = searchGraph(new_path);
            if (aux == null) {
                NodeList new_node = new NodeList(Foldername, new_path, Parent);
                NodeList new_node_list = new NodeList(Foldername, new_path, Parent);
                addGraph(new_node);
                Parent.SubFolders.addGraph(new_node_list);
                successfull = true;
            } else {
                JOptionPane.showMessageDialog(null, "Foldername already exists", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            System.out.println("Parent Not Found");
        }
        return successfull;
    }

    public boolean Remove(String FolderPath) {
        boolean successfull = false;
        NodeList delete_node = searchGraph(FolderPath);
        if (delete_node != null) {
            NodeList parent_node = delete_node.Parent;
            RemoveNodes(delete_node);
            NodeList delete_node_parent = parent_node.SubFolders.searchGraph(FolderPath);
            if (delete_node_parent != null) {
                parent_node.SubFolders.RemoveNodes(delete_node_parent);
                successfull = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Folder not exists", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        return successfull;
    }

    public boolean search(String path) {
        return searchGraph(path) == null;
    }

    public void rename(String pathParent, String oldname, String newname) {
        boolean successfull = false;

        String old_path = pathParent + oldname + "/";
        String new_path = pathParent + newname + "/";

        NodeList node_parent = searchGraph(pathParent);
        NodeList old_node_sub = node_parent.SubFolders.searchGraph(old_path);
        NodeList new_node_sub = node_parent.SubFolders.searchGraph(new_path);
        
        if (old_node_sub != null) {
            if (new_node_sub == null) {
                NodeList temp = this.Head;
                while (temp != null) {
                    VerPath(temp, old_path, new_path, oldname, newname);
                    
                    NodeList sub = temp.SubFolders.Head;
                    while(sub!=null){
                        VerPath(sub, old_path, new_path, oldname, newname);
                        sub = sub.Next;
                    }
                    
                    temp = temp.Next;
                }
            } else {
                JOptionPane.showMessageDialog(null, "The Foldername already exists", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Folder not exists", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
//-------------------------------------------------------------------------
//Helps Methods -----------------------------------------------------------

    private void addRoot(String Nameroot) {
        NodeList new_node = new NodeList(Nameroot, "Root/", null);
        addGraph(new_node);
    }

    private void addGraph(NodeList new_node) {
        if (this.Isempty()) {
            this.Head = new_node;
            this.End = new_node;
            this.Size++;
        } else {
            this.End.Next = new_node;
            this.End = new_node;
            this.Size++;
        }
    }

    private NodeList RemoveGraph(NodeList remove) {
        NodeList next = null;
        if (remove == this.Head) {
            this.Head = remove.Next;
            remove.Next = null;
            this.Size--;
            next = this.Head;
        } else {
            NodeList temp = this.Head;
            while (temp != null) {
                if (temp.Next != null) {
                    if (CompareRout(remove.Folders.getFolderPath(), temp.Next.Folders.getFolderPath())) {
                        break;
                    }
                }
                temp = temp.Next;
            }
            if (remove != this.End) {
                temp.Next = remove.Next;
                remove.Next = null;
                this.Size--;
                next = temp.Next;
            } else {
                temp.Next = null;
                this.End = temp;
                this.Size--;
                next = null;
            }

        }
        return next;
    }

    public NodeList searchGraph(String path) {
        NodeList temp = this.Head;
        while (temp != null) {
            if (temp.Folders.getFolderPath().equals(path)) {
                return temp;
            }
            temp = temp.Next;
        }
        return temp;
    }

    private void RemoveNodes(NodeList current) {
        NodeList temp = this.Head;
        while (temp != null) {
            if (CompareRout(current.Folders.getFolderPath(), temp.Folders.getFolderPath())) {
                temp = RemoveGraph(temp);
            } else {
                temp = temp.Next;
            }
        }
    }

  
    private void VerPath(NodeList n, String oldpath, String newpath, String oldname, String newname) {
        if (n.Folders.getFolderPath().length() >= oldpath.length()) {
            if (n.Folders.getFolderPath().contains(oldpath)) {
                String restpath = "";
                for (int i = oldpath.length(); i < n.Folders.getFolderPath().length(); i++) {
                    restpath += n.Folders.getFolderPath().charAt(i);
                }
                String currentpath = newpath + restpath;
                if (n.Folders.getFolderName().equals(oldname) && n.Folders.getFolderPath().equals(oldpath)) {
                    n.Folders.setFolderName(newname);
                    n.Folders.setFolderPath(currentpath);
                }else{
                    n.Folders.setFolderPath(currentpath);
                }
            }
        }
    }

    //-------------------------------------------------------------------------
    //Others Methods-----------------------------------------------------------
    private boolean CompareRout(String pathRemover, String pathEntrante) {
        boolean verification = false;
        String new_path = "";
        if (pathEntrante.length() > pathRemover.length()) {
            for (int i = 0; i < pathRemover.length(); i++) {
                new_path += pathEntrante.charAt(i);
            }
            if (pathRemover.equals(new_path)) {
                verification = true;
            } else {
                verification = false;
            }

        } else if (pathEntrante.length() < pathRemover.length()) {
            verification = false;
        } else {
            if (pathRemover.equals(pathEntrante)) {
                verification = true;
            } else {
                verification = false;
            }
        }
        return verification;
    }

    public String FolderPathUp(String Foldername) {
        String Path = "";
        if (!this.Isempty()) {
            NodeList temp = this.Head;
            while (temp != null) {
                if (temp.Folders.getFolderName().equals(Foldername)) {
                    Path = temp.Folders.getFolderPath();
                }
                temp = temp.Next;
            }
            return Path;
        } else {
            return Path;
        }
    }

    public void print() {
        NodeList temp = this.Head;
        System.out.println("EDD Cloud");
        System.out.println("Folders in the system: " + this.Size);
        while (temp != null) {
            System.out.println("*******************************************************************************");
            System.out.println("Foldername: " + temp.Folders.getFolderName());
            System.out.println("FolderPath: " + temp.Folders.getFolderPath());
            System.out.println("Folder Up: " + printFolderUp(temp));
            temp.SubFolders.printSub();
            System.out.println("");
            System.out.println("");
            temp = temp.Next;
        }
    }

    private void printSub() {
        NodeList temp = this.Head;
        System.out.println("*******************************************************************************");
        System.out.println("Folders in the Sub: " + this.Size);
        while (temp != null) {
            System.out.println("Foldername: " + temp.Folders.getFolderName() + "  FolderPath: " + temp.Folders.getFolderPath());
            temp = temp.Next;
        }
        System.out.println("*******************************************************************************");
    }

    private String printFolderUp(NodeList n) {
        if (n.Parent == null) {
            return "Nodo Raiz";
        } else {
            return n.Parent.Folders.getFolderName();
        }
    }

    private void printFiles(NodeList n) {
        if (n.Files.Root != null) {
            n.Files.InOrden(n.Files.Root);
        }
    }

    public String QuitSlash(String n) {
        String newn = "";
        newn = n.replace("/", "");
        newn = newn.replaceAll("\\s", "");
        return newn;
    }

    public void DeleteFile(String path) {
        File archivo = new File(path);
        archivo.delete();
    }

    public void GenerateImage(int n) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = "Graph" + n + ".png";
        String rutatxt = "Graph.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("graph [layout = circo]");
            pw.println("node [shape = circle, style=filled, fillcolor=seashell2]");
            NodeList temp = this.Head;
            while (temp != null) {
                pw.println(QuitSlash(temp.Folders.getFolderPath()) + " [label= \"" + temp.Folders.getFolderName() + "\\n Directories: " + temp.SubFolders.Size + "\"]");
                temp = temp.Next;
            }

            temp = this.Head;
            while (temp != null) {
                if (temp.Parent != null) {
                    pw.println(QuitSlash(temp.Parent.Folders.getFolderPath()) + "->" + QuitSlash(temp.Folders.getFolderPath()));
                }
                temp = temp.Next;
            }

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

    public void GenerateImageMatrix(int n) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = "Matrix" + n + ".png";
        String rutatxt = "Matrix.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("rankdir = TB;");
            pw.println("node [shape = rectangle, style=filled, fillcolor=seashell2];");
            pw.println("graph [nodesep = 0.5, splines=ortho];");

            //Print Header F-----------------------------------------------------
            NodeList current = this.Head;
            while (current != null) {
                pw.println("F" + QuitSlash(current.Folders.getFolderPath()) + " [label=\"" + current.Folders.getFolderName() + "\"]");
                current = current.Next;
            }
            //------------------------------------------------------------------

            //Linked Nodes F ---------------------------------------------------
            current = this.Head;
            if (current != null) {
                pw.println("Matrix->" + "F" + QuitSlash(current.Folders.getFolderPath()));
            }
            while (current != null) {
                if (current.Next != null) {
                    pw.println("F" + QuitSlash(current.Folders.getFolderPath()) + " -> F" + QuitSlash(current.Next.Folders.getFolderPath()));
                }
                current = current.Next;
            }
            //------------------------------------------------------------------

            //Print Header C -----------------------------------------------------
            current = this.Head;
            while (current != null) {
                pw.println("C" + QuitSlash(current.Folders.getFolderPath()) + " [label=\"" + current.Folders.getFolderName() + "\"]");
                current = current.Next;
            }
            //------------------------------------------------------------------

            //Linked Nodes C ---------------------------------------------------
            current = this.Head;
            if (current != null) {
                pw.println("Matrix->" + "C" + QuitSlash(current.Folders.getFolderPath()));
            }
            while (current != null) {
                if (current.Next != null) {
                    pw.println("C" + QuitSlash(current.Folders.getFolderPath()) + " -> C" + QuitSlash(current.Next.Folders.getFolderPath()));
                }
                current = current.Next;
            }
            //------------------------------------------------------------------

            // Nodes------------------------------------------------------------
            current = this.Head;
            while (current != null) {
                if (!current.Folders.getFolderPath().equalsIgnoreCase("Root/")) {
                    pw.println("N" + QuitSlash(current.Folders.getFolderPath()) + " [label =\"" + current.Folders.getFolderPath() + "\"]");
                }
                current = current.Next;
            }
            //------------------------------------------------------------------

            //Linked Nodes with path---------------------------------------------
            current = this.Head;
            while (current != null) {
                NodeList sub = current.SubFolders.Head;
                while (sub != null) {
                    if (sub.Next != null) {
                        pw.println("N" + QuitSlash(sub.Folders.getFolderPath()) + " -> " + "N" + QuitSlash(sub.Next.Folders.getFolderPath()));
                    }
                    sub = sub.Next;
                }
                current = current.Next;
            }
            //------------------------------------------------------------------

            //Linked Nodes with F-----------------------------------------------
            current = this.Head;
            while (current != null) {
                NodeList sub = current.SubFolders.Head;
                if (sub != null) {
                    pw.println("F" + QuitSlash(current.Folders.getFolderPath()) + " -> " + "N" + QuitSlash(sub.Folders.getFolderPath()) + "[constraint = false]");
                }
                current = current.Next;
            }
            //------------------------------------------------------------------

            //Rank Nodes with F------------------------------------------------------
            current = this.Head;
            while (current != null) {
                NodeList sub = current.SubFolders.Head;
                if (sub != null) {
                    pw.print("{rank=same; F" + QuitSlash(current.Folders.getFolderPath()));
                }
                while (sub != null) {
                    pw.print(" " + "N" + QuitSlash(sub.Folders.getFolderPath()));
                    sub = sub.Next;
                }
                sub = current.SubFolders.Head;
                if (sub != null) {
                    pw.println(" }");
                }
                current = current.Next;
            }
            //------------------------------------------------------------------

            //Rank Nodes with C------------------------------------------------------
            current = this.Head;
            while (current != null) {
                if (current != Head) {
                    pw.println("C" + QuitSlash(current.Folders.getFolderPath()) + "->" + "N" + QuitSlash(current.Folders.getFolderPath()));
                }
                current = current.Next;
            }
            //------------------------------------------------------------------

            //Rank Columns -----------------------------------------------------
            current = this.Head;
            if (current != null) {
                pw.print("{rank=same; Matrix ");
            }
            while (current != null) {
                pw.print(" C" + QuitSlash(current.Folders.getFolderPath()));
                current = current.Next;
            }
            pw.println("}");
            //------------------------------------------------------------------

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
//-------------------------------------------------------------------------

}
