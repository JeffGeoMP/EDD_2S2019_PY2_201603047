package Structures;

import Others.Files;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    public boolean add(String Filename, String Content, String Username) {
        boolean successfull = false;
        NodeAVL new_node = new NodeAVL(Filename, Content, Username);
        if (this.Root == null) {
            Root = new_node;
            successfull = true;
        } else {
            Root = addAVL(new_node, Root);
            successfull = true;
        }
        return successfull;
    }

    public boolean Remove(String FilenameAbsolute) {
        boolean successfull = false;
        NodeAVL aux = searchAVL(this.Root, FilenameAbsolute);
        if (aux != null) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to Delete the File: " + FilenameAbsolute, "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Root = removeAVL(Root, FilenameAbsolute);
                JOptionPane.showMessageDialog(null, "File Delete", "Information", JOptionPane.INFORMATION_MESSAGE);
                successfull = true;
            } else {
                JOptionPane.showMessageDialog(null, "Without Changes", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File Not Found", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        return successfull;
    }

    public boolean Rename(String FilenameAbsolute, String newFilenameAbsolute) {
        boolean sucessfull = false;
        if (!newFilenameAbsolute.isEmpty()) {
            if (!FilenameAbsolute.equals(newFilenameAbsolute)) {
                NodeAVL temp = searchAVL(this.Root, FilenameAbsolute);
                NodeAVL temp2 = searchAVL(this.Root, newFilenameAbsolute);
                if (temp != null) {
                    if (temp2 == null) {
                        NodeAVL aux = new NodeAVL(newFilenameAbsolute, temp.File.getContent(), temp.File.getUsername(), temp.File.getDate(), temp.File.getHour());
                        this.Root = removeAVL(this.Root, FilenameAbsolute);
                        if (this.Root == null) {
                            Root = aux;
                        } else {
                            Root = addAVL(aux, Root);
                        }
                        sucessfull = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "New File Name Already Exists", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "File Not Found", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Old Name and New Name is equals", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "New File Name is empty", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        return sucessfull;
    }

    public boolean EditContent(String FilenameAbsolute, String new_Content) {
        boolean successfull = false;
        NodeAVL temp = searchAVL(this.Root, FilenameAbsolute);

        if (temp != null) {
            if (!temp.File.getContent().equals(new_Content)) {
                temp.File.setContent(new_Content);
                JOptionPane.showMessageDialog(null, "Content Changue", "Information", JOptionPane.INFORMATION_MESSAGE);
                successfull = true;
            } else {
                JOptionPane.showMessageDialog(null, "Not Change Content", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "File Not Found", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        return successfull;
    }

    public String getContent(String Filename) {
        NodeAVL temp = searchAVL(this.Root, Filename);
        String Content = "";
        if (temp != null) {
            Content = temp.File.getContent();
        }
        return Content;
    }

    public int AddGraphics(int sep, NodeAVL n, int x, JPanel panel, int Panel_Height, int width, int height, int Line_Folders, MouseListener e, int aux, ImageIcon icon) {
        int panel_height = Panel_Height;
        int Folders_Line = 7;
        int sep_ver = 30;
        String Noes = Nodes(this.Root);
        String New_Line = "";
        for (int i = 0; i < Noes.length(); i++) {
            if (Noes.charAt(i) != '\n') {
                New_Line += Noes.charAt(i);
            } else {
                //Fields[0] = FilenameAbsolute
                //Fields[1] = Filename;
                String[] Fields = New_Line.split(",");
                JLabel File = new JLabel();
                JLabel Etiqueta = new JLabel();
                File.setName(Fields[1]);
                File.setText(Fields[0]);
                File.setIcon(icon);
                File.addMouseListener(e);

                Etiqueta.setText(Fields[0]);
                Etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
                if (x != 0 && x % Folders_Line == 0) {
                    Line_Folders++;
                    panel_height = Line_Folders * height + Line_Folders * sep_ver + 15;
                    sep = 20;
                }
                aux = x - Folders_Line * (Line_Folders);
                File.setBounds(aux * width + sep, panel_height, width, height);
                Etiqueta.setBounds(aux * width + sep, panel_height + height - 15, width, 30);
                sep += 20;
//                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
//                File.setBorder(border);

                panel.add(File);
                panel.add(Etiqueta);
                x++;

                New_Line = "";
            }
        }
        return panel_height;
    }

    public void DeleteFile(String path) {
        File archivo = new File(path);
        archivo.delete();
    }

    public int BulkLoad(JFrame frame, String Username) {
        boolean VerFile = false;
        int addfiles = 0;
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter Filter = new FileNameExtensionFilter("Files .CSV", "csv");
        file.setFileFilter(Filter);

        int result = file.showOpenDialog(frame);
        if (result != JFileChooser.CANCEL_OPTION) {
            BufferedReader br = null;
            try {
                File filename = file.getSelectedFile();
                br = new BufferedReader(new FileReader(filename.getAbsoluteFile()));
                String line = br.readLine();
                while (null != line) {
                    String[] fields = line.split(",");
                    if (fields[0].equalsIgnoreCase("Archivo") && fields[1].equalsIgnoreCase("Contenido")) {
                        VerFile = true;
                    }
                    if (!fields[0].equalsIgnoreCase("Archivo") && !fields[1].equalsIgnoreCase("Contenido") && VerFile == true) {
                        this.add(this.removeqt(fields[0]), this.removeqt(fields[1]), Username);
                        addfiles++;
                    }
                    line = br.readLine();
                }
                br.close();

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Could not open file", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not open file", "Information", JOptionPane.INFORMATION_MESSAGE);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not open file", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        return addfiles;
    }

    public void GenerateImage(int n) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = "AVLTree" + n + ".png";
        String rutatxt = "AVLTree.txt";
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
            if (Root != null) {
                pw.print(Code(Root));
            } else {
                pw.print("node0[label=\"Not Found Files\"];");
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

    //--------------------------------------------------------------------------
    private String Code(NodeAVL n) {
        String values = "";
        if (n.Left == null && n.Right == null) {
            values += removept(n.File.getFilename()) + "[label=\"Filename: " + n.File.getFilename() + "\\n Content: " + n.File.getContent() + "\\n Balance Factor: " + Balance(n) + "\\n Height: " + n.Height + "\\n TimeStamp: " + n.File.getDate() + " " + n.File.getHour() + "\\n Owner: " + n.File.getUsername() + "\"]\n";
        } else {
            values += removept(n.File.getFilename()) + "[label=\"<C0>|Filename: " + n.File.getFilename() + "\\n Content: " + n.File.getContent() + "\\n Balance Factor: " + Balance(n) + "\\n Height: " + n.Height + "\\n TimeStamp: " + n.File.getDate() + " " + n.File.getHour() + "\\n Owner: " + n.File.getUsername() + "|<C1>\"]\n";
        }
        if (n.Left != null) {
            values += Code(n.Left) + removept(n.File.getFilename()) + ":C0->" + removept(n.Left.File.getFilename()) + "\n";
        }
        if (n.Right != null) {
            values += Code(n.Right) + removept(n.File.getFilename()) + ":C1->" + removept(n.Right.File.getFilename()) + "\n";
        }
        return values;
    }

    private String removept(String s) {
        String new_s = "";
        for (int i = 0; i < s.length(); i++) {
            char car = s.charAt(i);
            if (car != 46 && car != ' ') {        //46 is in code ascci = .
                new_s += car;
            }
        }
        return new_s;
    }

    public String Nodes(NodeAVL n) {
        String values = "";

        if (n.Left == null && n.Right == null) {
            values += n.File.getFilename() + "," + n.File.getFilename() + "\n";
        } else {
            values += n.File.getFilename() + "," + n.File.getFilename() + "\n";
        }
        if (n.Left != null) {
            //values += Nodes(n.Left) + "";
            values += Nodes(n.Left);
        }
        if (n.Right != null) {
            // values += Nodes(n.Right) + "";
            values += Nodes(n.Right);
        }
        return values;
    }

    //Operations in the AVL Tree -----------------------------------------------
    private NodeAVL addAVL(NodeAVL subtree, NodeAVL new_node) {
        NodeAVL sup = new_node;
        int Comparation = subtree.File.getFilename().compareToIgnoreCase(new_node.File.getFilename());
        if (Comparation < 0) {
            if (new_node.Left == null) {
                new_node.Left = subtree;
            } else {
                new_node.Left = addAVL(subtree, new_node.Left);
                if ((this.Height(new_node.Left) - this.Height(new_node.Right)) == 2) {
                    int Comparation2 = Data(subtree).compareToIgnoreCase(new_node.Left.File.getFilename());
                    if (Comparation2 < 0) {
                        sup = RotationLeft(new_node);
                    } else {
                        sup = DRotationLeft(new_node);
                    }
                }
            }
        } else if (Comparation > 0) {
            if (new_node.Right == null) {
                new_node.Right = subtree;
            } else {
                new_node.Right = addAVL(subtree, new_node.Right);
                if ((this.Height(new_node.Right) - this.Height(new_node.Left)) == 2) {
                    int Comparation2 = subtree.File.getFilename().compareToIgnoreCase(new_node.Right.File.getFilename());
                    if (Comparation2 > 0) {
                        sup = RotationRight(new_node);
                    } else {
                        sup = DRotationRight(new_node);
                    }
                }
            }
        } else {
            if (Comparation == 0) {
                if (JOptionPane.showConfirmDialog(null, "Do you want to overwrite the file " + subtree.File.getFilename() + "?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    NodeAVL node = searchAVL(this.Root, subtree.File.getFilename());
                    node.File.setContent(subtree.File.getContent());
                    JOptionPane.showMessageDialog(null, "Overwritten File", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "File not Overwritten", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (new_node.Left == null && (new_node.Right != null)) {
            new_node.Height = new_node.Right.Height + 1;
        } else if (new_node.Right == null && (new_node.Left != null)) {
            new_node.Height = new_node.Left.Height + 1;
        } else {
            new_node.Height = Math.max(this.Height(new_node.Left), this.Height(new_node.Right)) + 1;
        }
        return sup;
    }

    private NodeAVL searchAVL(NodeAVL root, String FilenameAbsolute) {
        if (root == null) {
            return null;
        } else if (root.File.getFilename().equals(FilenameAbsolute)) {
            return root;
        } else if (root.File.getFilename().compareToIgnoreCase(FilenameAbsolute) < 0) {
            return searchAVL(root.Right, FilenameAbsolute);
        } else {
            return searchAVL(root.Left, FilenameAbsolute);
        }
    }

    private NodeAVL removeAVL(NodeAVL n, String FilenameAbsolute) {
        if (n == null) {
            return null;
        }
        int Comparation = FilenameAbsolute.compareToIgnoreCase(n.File.getFilename());
        if (Comparation < 0) {
            n.Left = removeAVL(n.Left, FilenameAbsolute);
        } else if (Comparation > 0) {
            n.Right = removeAVL(n.Right, FilenameAbsolute);
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

    public String removeqt(String s) {
        String new_s = "";
        for (int i = 0; i < s.length(); i++) {
            char car = s.charAt(i);
            if (car != 34) {
                new_s += car;
            }
        }
        return new_s;
    }
    //--------------------------------------------------------------------------

    //Orders for print the AVL Tree ---------------------------------------------
    public void InOrden(NodeAVL n) {
        if (n != null) {
            InOrden(n.Left);
            System.out.println(n.File.getFilename() + " , " + n.File.getContent());
            InOrden(n.Right);
        }
    }

//    public void PreOrden(NodeAVL n) {
//        if (n != null) {
//            System.removeqt.print(n.Key + ",");
//            PreOrden(n.Left);
//            PreOrden(n.Right);
//        }
//    }
//
//    public void PostOrden(NodeAVL n) {
//        if (n != null) {
//            PostOrden(n.Left);
//            PostOrden(n.Right);
//            System.removeqt.print(n.Key + ",");
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
