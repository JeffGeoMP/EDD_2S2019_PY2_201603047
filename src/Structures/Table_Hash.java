package Structures;

import Others.Users;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/*
 * @author JeffGeo
 */
public class Table_Hash {

    public Object[] Users;
    private int Size_Prime;
    private int Mod;
    private int Elements;

    public Table_Hash() throws NoSuchAlgorithmException {
        Size_Prime = 7;
        Mod = Size_Prime - 1;
        Elements = 0;
        Users = new Object[Size_Prime];
        Arrays.fill(Users, null);
        this.Add_Vector("admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Users);
    }

    public void Add(String user, String pass) throws NoSuchAlgorithmException {
        String newpass = Encrypting(pass);
        Add_Vector(user, newpass, Users);

        if (Percentage_Used() >= 75) {
            Users = Array_Increase();
        }
    }

    private void Add_Vector(String user, String pass, Object[] Array) throws NoSuchAlgorithmException {
        int Index = Function_Hash(user);              //Index of Array for User
        if (Array[Index] == null) {
            Array[Index] = new Users(user, pass);
            Elements++;
        } else {
            int Squared = Index * Index;
            if (Squared > Array.length) {
                for (int i = 0; i < Array.length; i++) {
                    if (Array[i] == null) {
                        Array[i] = new Users(user, pass);
                        Elements++;
                        break;
                    }
                }
            } else {
                if (Array[Squared] == null) {
                    Array[Squared] = new Users(user, pass);
                    Elements++;
                } else {
                    for (int i = 0; i < Array.length; i++) {
                        if (Array[i] == null) {
                            Array[i] = new Users(user, pass);
                            Elements++;
                            break;
                        }
                    }
                }
            }
        }
    }

    private Object[] Array_Increase() throws NoSuchAlgorithmException {
        Size_Prime = Next_Number_Prime();           //Get Next Number Prime
        Object[] Aux = new Object[Size_Prime];      //Initial Array Aux with size of Number Prime
        Arrays.fill(Aux, null);                     //Fill Array Aux 
        Mod = Size_Prime - 1;                         //Change Mod for Hash Function
        Elements = 0;

        Users current_user;

        for (Object User : Users) {
            if (User != null) {
                current_user = (Users) User;
                Add_Vector(current_user.getUser(), current_user.getPass(), Aux);
            }
        }
        return Aux;
    }

    private int Function_Hash(String user) {
        int Hash = 0;
        for (int i = 0; i < user.length(); i++) {
            char Caracter = user.charAt(i);
            Hash = (int) Caracter + Hash;
        }
        Hash = Hash % Mod;              //Index of Array for User
        return Hash;
    }
    
    public int IndexUser(String user){
        for(int i = 0; i<Users.length; i++){
            if(Users[i]!=null){
                Users current_user = (Users) Users[i];
                if(current_user.getUser().equals(user)){
                    return i;
                }
            }
        }
        return -1;
    }

    private int Next_Number_Prime() {
        int New_Prime = Size_Prime + 1; //New_Prime takes the value of size_prime + 1
        int contador = 0;
        while (true) {
            for (int i = 1; i <= New_Prime; i++) {
                if (New_Prime % i == 0) {
                    contador++;
                }
            }
            if (contador == 2) {
                contador = 0;
                break;
            } else {
                New_Prime++;
                contador = 0;
            }
        }
        return New_Prime;
    }

    public boolean Search(String user, String passed) throws NoSuchAlgorithmException {
        String pass = Encrypting(passed);
        int search_index = Function_Hash(user);
        boolean Verification = false;
        Users current_user;
        if (Users[search_index] != null) {
            current_user = (Users) Users[search_index];
            if (current_user.getUser().equals(user) && current_user.getPass().equals(pass)) {
                Verification = true;
            } else {
                search_index = search_index * search_index;
                if (search_index > Users.length) {
                    Verification = false;
                } else {
                    current_user = (Users) Users[search_index];
                    if (current_user.getUser().equals(user) && current_user.getPass().equals(pass)) {
                        Verification = true;
                    } else {
                        for (int i = 0; i < Users.length; i++) {
                            current_user = (Users) Users[i];
                            if (current_user.getUser().equals(user) && current_user.getPass().equals(pass)) {
                                Verification = true;
                                break;
                            } else {
                                Verification = false;
                            }
                        }
                    }
                }
            }
        } else {
            Verification = false;
        }
        return Verification;
    }

    private int Percentage_Used() {
        return (Elements * 100) / Size_Prime;
    }

    public void DeleteFile(String path) {
        File archivo = new File(path);
        archivo.delete();
    }

    public void GenerateImage(int n) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = "Hash" + n + ".png";
        String rutatxt = "Hash.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

       
        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("rankdir = LR");
            pw.println("node[shape=plaintext]");
            pw.println("a[label=<<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"1\">");

            for (int i = 0; i < Users.length; i++) {
                if (Users[i] != null) {
                    Users temp = (Users) Users[i];
                    pw.println("<TR>");
                    pw.println("<TD BGCOLOR=\"#F6DDCC\">" + i + ")</TD>");
                    pw.println("<TD BGCOLOR=\"#F6DDCC\">Username: " + temp.getUser() + " Password: " + temp.getPass() + " Timestamp: " + temp.getDate() + " " + temp.getHour() + "</TD>");
                    pw.println("</TR>");

                } else {
                    pw.println("<TR>");
                    pw.println("<TD BGCOLOR=\"#F6DDCC\">" + i + ")</TD>");
                    pw.println("</TR>");
                }
            }

            pw.println("</TABLE>>]");
            pw.println("b [style=filled, fillcolor=seashell2, label=\"Percentaje Used: "+Percentage_Used()+"%\\n No. Users: "+this.Elements+"\"]");
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
    
    public void Print_Table() {
        int count = 0;
        System.out.println("Percentage Used: " + Percentage_Used() + "%");
        System.out.println("Elements in Table: " + Elements);
        for (Object User : Users) {
            if (User != null) {
                System.out.println("Hash[" + count + "] " + User.toString());
            }
            count++;
        }
    }

    public boolean Validate_user(String user) {
        boolean validation = false;
        Users current_user;
        for (Object User : Users) {
            if (User != null) {
                current_user = (Users) User;
                if (current_user.getUser().equals(user)) {
                    validation = true;
                    break;
                }
            }
        }
        return validation;
    }

    private String Encrypting(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("SHA-256");
        md.update(pass.getBytes());
        byte[] mb = md.digest();
        StringBuilder sb = new StringBuilder();

        for (byte b : mb) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
