package Structures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JeffGeo
 */
public class Stack {

    private Node head;
    private int count;
//    public int print;

    public Stack() {
        this.head = null;
        this.count = 0;
//        this.print = 0;
    }

    public void add(String Operation, String User) {
        Node new_operation = new Node(Operation, User);
        if (Isempty()) {
            this.head = new_operation;
            this.count++;
        } else {
            new_operation.next = this.head;
            this.head = new_operation;
            this.count++;
        }
    }

    public void printStack() {
        if (Isempty()) {
            System.out.println("Not Found Operations in the System");
        } else {
            Node temp = this.head;

            while (temp != null) {
                System.out.println("Date: " + temp.Date + " Hour: " + temp.Hour + " Operation: " + temp.Operation + " User: " + temp.Username);
                temp = temp.next;
            }
        }
    }

    public void DeleteFile(String path) {
        File archivo = new File(path);
        archivo.delete();
    }

    public void GenerateImage(int n) {
        final String rutaDot = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String rutaImagen = "Stack" + n + ".png";
        String rutatxt = "Stack.txt";
        String parametroT = "-Tpng";
        String parametroO = "-o";

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter(rutatxt);
            pw = new PrintWriter(archivo);
            pw.println("digraph G {");
            pw.println("rankdir=LR");
            pw.println("node [shape = record, style=filled, fillcolor=seashell2]");

            if (this.head != null) {
                pw.println("stack[label=\"");
                Node temp = this.head;
                while (temp != null) {
                    if (temp.next != null) {
                        pw.println("Date: " + temp.Date + " Hour: " + temp.Hour + "\\n UserName: " + temp.Username + "\\n Operation: " + temp.Operation + " | ");
                    } else {
                        pw.println("Date: " + temp.Date + " Hour: " + temp.Hour + "\\n UserName: " + temp.Username + "\\n Operation: " + temp.Operation);
                    }
                    temp = temp.next;
                }
                pw.println("\"];");
            } else {
                pw.println("node0[label=\"Not Found Operations\"];");
            }

            pw.println("aux [shape = plaintext, label=\"Operaciones: " + this.count + "\"]");
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

    private boolean Isempty() {
        return (null == this.head);
    }
}

class Node {

    public String Operation;
    public String Username;
    public String Hour;
    public String Date;
    public Node next;

    public Node(String Operation, String User) {
        this.Date = Date();
        this.Hour = Hour();
        this.Operation = Operation;
        this.Username = User;
        this.next = null;
    }

    private String Hour() {
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
        return dateformat.format(date);
    }

    private String Date() {
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        return dateformat.format(date);
    }
}
