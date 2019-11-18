package Interface;

import Others.Errors;
import Others.Users;
import Dialogs.FormA;
import Dialogs.FormOpenFiles;
import Dialogs.FormShare;
import Structures.LinkedList;
import Structures.NodeList;
import Structures.Table_Hash;
import Structures.Stack;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author JeffGeo
 */
public class EDD_Platform extends javax.swing.JFrame {

    //Structures aux
    private Table_Hash Users;           //Table Hash of the System
    private Stack Operations;           //Operations of the System
    private LinkedList Graph;           //Graph of current user

    //Var of Control
    private int Index_User;             //Index Current User
    private String Current_User_Name;   //User Na,e

    //Var for Class
    private String Current_Path;        //Current Path
    private int width;                  //Width of Labels
    private int height;                 //Height of Labels

    public EDD_Platform(String Username, int Index_User, Table_Hash Users, Stack Operations) {
        initComponents();
        this.getContentPane().setBackground(new Color(204, 229, 255));
        this.getContentPane().setBackground(Color.LIGHT_GRAY);

        this.Users = Users;
        this.Operations = Operations;

        this.Current_User_Name = Username;
        this.Index_User = Index_User;

        username.setText(this.Current_User_Name);

        Users current_user = (Users) (Users.Users[Index_User]);
        Graph = current_user.Graph;

        this.Current_Path = "";
        this.width = Panel.getWidth() / 10;     //Weight of Label
        this.height = 60;                       //Height of Label

        this.setTitle("EDD System -" + this.Current_User_Name + "-");
        this.Load_Images();
        this.Initial();
        this.CheckAdmin(this.Current_User_Name);
        //this.PaintPanel();
    }

    //Helps Methods-------------------------------------------------------------
    private void CheckAdmin(String User) {
        if (!User.equals("admin")) {
            Load_Users.setVisible(false);
        }
    }

    private void CleanPanel() {
        this.Panel.removeAll();
        this.Panel.repaint();
    }

    private String GuardarArchivo(File archivo, String documento) {
        String mensaje = null;
        FileOutputStream Salida;
        try {
            Salida = new FileOutputStream(archivo);
            byte[] bytxt = documento.getBytes();
            Salida.write(bytxt);
            mensaje = "File Save";

        } catch (Exception e) {

        }
        return mensaje;
    }

    private String ChangueExt(String n) {
        String new_name = "";
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) != '.') {
                new_name += n.charAt(i);
            } else {
                break;
            }
        }
        new_name = new_name + ".txt";
        return new_name;
    }

    private void UpdatePath(String new_direction) {
        Direction.setText(new_direction);
        Current_Path = new_direction;
    }

    private ImageIcon IconFolder(int Width, int Height) {
        Image img = new ImageIcon(getClass().getResource("../Images/folder.png")).getImage();
        Image newimg = img.getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        return icon;
    }

    private ImageIcon IconFile(int Width, int Height) {
        Image img = new ImageIcon(getClass().getResource("../Images/file.png")).getImage();
        Image newimg = img.getScaledInstance(Width, Height - 15, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        return icon;
    }

    private void Load_Images() {
        Image imgexit = new ImageIcon(getClass().getResource("../Images/exit.png")).getImage();
        Image newimgexit = imgexit.getScaledInstance(exit.getWidth(), exit.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconexit = new ImageIcon(newimgexit);
        exit.setIcon(iconexit);

        Image imganew = new ImageIcon(getClass().getResource("../Images/Acreate.png")).getImage();
        Image newimganew = imganew.getScaledInstance(Anew.getWidth(), Anew.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconanew = new ImageIcon(newimganew);
        Anew.setIcon(iconanew);

        Image imgFnew = new ImageIcon(getClass().getResource("../Images/Fcreate.png")).getImage();
        Image newimgFnew = imgFnew.getScaledInstance(Fnew.getWidth(), Fnew.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconFnew = new ImageIcon(newimgFnew);
        Fnew.setIcon(iconFnew);

        Image imgFup = new ImageIcon(getClass().getResource("../Images/Fup.png")).getImage();
        Image newimgFup = imgFup.getScaledInstance(Fup.getWidth(), Fup.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconFup = new ImageIcon(newimgFup);
        Fup.setIcon(iconFup);

        Image imgUserLoad = new ImageIcon(getClass().getResource("../Images/Users.png")).getImage();
        Image newimgUserLoad = imgUserLoad.getScaledInstance(Load_Users.getWidth(), Load_Users.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconUserLoad = new ImageIcon(newimgUserLoad);
        Load_Users.setIcon(iconUserLoad);

        Image imgFilesLoad = new ImageIcon(getClass().getResource("../Images/Aload.png")).getImage();
        Image newimgFilesLoad = imgFilesLoad.getScaledInstance(Files_Load.getWidth(), Files_Load.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconFilesLoad = new ImageIcon(newimgFilesLoad);
        Files_Load.setIcon(iconFilesLoad);

        Image imgReports = new ImageIcon(getClass().getResource("../Images/Reports.png")).getImage();
        Image newimgReports = imgReports.getScaledInstance(Reports.getWidth(), Reports.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconReports = new ImageIcon(newimgReports);
        Reports.setIcon(iconReports);
    }
    //-------------------------------------------------------------------------

    //Functionalities of System -----------------------------------------------
    private void Initial() {
        if (Graph.Head != null) {
            UpdatePath(Graph.Head.Folders.getFolderPath());
            PaintPanel(Current_Path);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Folder Root not Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void PaintPanel(String path) {
        JFrame f = this;
        //Events----------------------------------------------------------------
        MouseListener eventfolders = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel aux = (JLabel) e.getSource();                //Creamos un JLabel Auxiliar

                ActionListener actionopen = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UpdatePath(aux.getName());
                        CleanPanel();
                        PaintPanel(Current_Path);
                    }
                };

                ActionListener actionedit = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Name: " + aux.getName() + "  Text: " + aux.getText());
                        NodeList parent = Graph.searchGraph(aux.getName());
                        if (parent != null) {
                            parent = parent.Parent;
                            if (parent != null) {
                                String new_name = "" + JOptionPane.showInputDialog(null, "Input New Name for Folder", "Information", JOptionPane.INFORMATION_MESSAGE);
                                if (!new_name.isEmpty()) {
                                    Graph.edit(parent.Folders.getFolderPath(), aux.getText(), new_name);
                                    CleanPanel();
                                    PaintPanel(Current_Path);

                                } else {
                                    JOptionPane.showMessageDialog(null, "The new name is Empty", "Information", JOptionPane.INFORMATION_MESSAGE);
                                }

                            }
                        }
                    }
                };

                ActionListener actiondelete = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Name: " + aux.getName() + "  Text: " + aux.getText());
                        Graph.Remove(aux.getName());
                        CleanPanel();
                        PaintPanel(Current_Path);
                    }
                };
                JPopupMenu menu = new JPopupMenu();
                JMenuItem open = new JMenuItem("Open Folder");
                open.addActionListener(actionopen);
                menu.add(open);

                JMenuItem edit = new JMenuItem("Edit Folder Name");
                edit.addActionListener(actionedit);
                menu.add(edit);

                JMenuItem Remove = new JMenuItem("Delete Folder");
                Remove.addActionListener(actiondelete);
                menu.add(Remove);

                menu.show(aux, aux.getWidth() - 10, aux.getHeight() - 15);
            }

            @Override

            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        MouseListener eventfiles = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel aux = (JLabel) e.getSource();                //Creamos un JLabel Auxiliar

                //Functionality 100%
                ActionListener actionopen = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NodeList nodeavl = Graph.searchGraph(Current_Path);
                        if (nodeavl != null) {
                            if (nodeavl.Files.Root != null) {
                                FormOpenFiles open = new FormOpenFiles(f, true);
                                open.filename.setText("File: " + aux.getText());
                                open.cadena.setText(nodeavl.Files.getContent(aux.getName()));
                                open.setVisible(true);
                                String new_content = open.New_Content;
                                if (nodeavl.Files.EditContent(aux.getName(), new_content)) {
                                    Operations.add("Edit Content File: " + aux.getName(), Current_User_Name);
                                }
                            }
                        }
                    }
                };

                ActionListener actioneditname = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NodeList nodeavl = Graph.searchGraph(Current_Path);
                        if (nodeavl != null) {
                            if (nodeavl.Files.Root != null) {
                                String new_name = "" + JOptionPane.showInputDialog(null, "Input new name for " + aux.getText(), "Information", JOptionPane.INFORMATION_MESSAGE);
                                if (nodeavl.Files.Rename(aux.getName(), new_name)) {
                                    Operations.add("Changue Name File", Current_User_Name);
                                    CleanPanel();
                                    PaintPanel(Current_Path);
                                }
                            }
                        }
                    }
                };

                ActionListener actioneditcontent = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NodeList nodeavl = Graph.searchGraph(Current_Path);
                        if (nodeavl != null) {
                            if (nodeavl.Files.Root != null) {
                                FormOpenFiles open = new FormOpenFiles(f, true);
                                open.filename.setText("File: " + aux.getText());
                                open.cadena.setText(nodeavl.Files.getContent(aux.getName()));
                                open.setVisible(true);
                                String new_content = open.New_Content;
                                if (nodeavl.Files.EditContent(aux.getName(), new_content)) {
                                    Operations.add("Edit Content File: " + aux.getName(), Current_User_Name);
                                }
                            }
                        }
                    }
                };

                ActionListener actiondelete = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NodeList nodeavl = Graph.searchGraph(Current_Path);
                        if (nodeavl != null) {
                            if (nodeavl.Files.Root != null) {
                                if (nodeavl.Files.Remove(aux.getName())) {
                                    Operations.add("Delete File", Current_User_Name);
                                    CleanPanel();
                                    PaintPanel(Current_Path);
                                }
                            }
                        }
                    }
                };

                ActionListener actionshare = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NodeList temp = Graph.searchGraph(Current_Path);
                        String Content = temp.Files.getContent(aux.getName());

                        FormShare form = new FormShare(f, true);
                        form.username.setText(Current_User_Name);
                        form.path.setText(Current_Path + "/" + aux.getText());
                        form.setVisible(true);

                        String des = "" + form.recipient;
                        if (!des.isEmpty()) {
                            int index_user_d = -1;
                            for (int i = 0; i < Users.Users.length; i++) {
                                Users current_user = (Users) Users.Users[i];
                                if (current_user != null) {
                                    if (current_user.getUser().equals(des)) {
                                        index_user_d = i;
                                    }
                                }
                            }
                            if (index_user_d > 0) {
                                Users current_user = (Users) Users.Users[index_user_d];
                                current_user.Graph.Head.Files.add(aux.getText(), Content, Current_User_Name);
                                JOptionPane.showMessageDialog(null, "Successfull Operation");
                                Operations.add("Share File: " + aux.getText() + " to " + current_user.getUser(), Current_User_Name);
                                JOptionPane.showMessageDialog(null, "Share File: " + aux.getText() + " to " + current_user.getUser(), "Information", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Not User Available", "Information", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "File Not Share", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                };

                ActionListener actiondownload = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NodeList temp = Graph.searchGraph(Current_Path);
                        String Content = temp.Files.getContent(aux.getName());

                        JFileChooser Choose = new JFileChooser();
                        File download_file;
                        Choose.setSelectedFile(new File(ChangueExt(aux.getText())));
                        if (Choose.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
                            download_file = Choose.getSelectedFile();

                            if (download_file.getName().endsWith(".txt")) {
                                String Doc = Content;

                                String Msj = GuardarArchivo(download_file, Doc);
                                if (Msj != null) {
                                    JOptionPane.showMessageDialog(null, "File Download successful");
                                    Operations.add("Download File: " + aux.getName(), Current_User_Name);
                                    if (temp != null) {
                                        if (temp.Files.Root != null) {
                                            FormOpenFiles open = new FormOpenFiles(f, true);
                                            open.filename.setText("File: " + aux.getText());
                                            open.cadena.setText(temp.Files.getContent(aux.getName()));
                                            open.setVisible(true);
                                            String new_content = open.New_Content;
                                            if (temp.Files.EditContent(aux.getName(), new_content)) {
                                                Operations.add("Edit Content File: " + aux.getName(), Current_User_Name);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                };

                JPopupMenu menu = new JPopupMenu();
                JMenuItem open = new JMenuItem("Open File");
                open.addActionListener(actionopen);
                menu.add(open);

                JMenuItem editname = new JMenuItem("Rename File");
                editname.addActionListener(actioneditname);
                menu.add(editname);

                JMenuItem editcontent = new JMenuItem("Edit File Content");
                editcontent.addActionListener(actioneditcontent);
                menu.add(editcontent);

                JMenuItem Remove = new JMenuItem("Delete File");
                Remove.addActionListener(actiondelete);
                menu.add(Remove);

                JMenuItem Share = new JMenuItem("Share File");
                Share.addActionListener(actionshare);
                menu.add(Share);

                JMenuItem Download = new JMenuItem("Download File");
                Download.addActionListener(actiondownload);
                menu.add(Download);

                menu.show(aux, aux.getWidth() - 10, aux.getHeight() - 15);
            }

            @Override

            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        //----------------------------------------------------------------------

        int Folders_Line = 7;
        int Line_Folders = 0;
        int Panel_Height = 20;
        int sep_hor = 25;
        int sep_ver = 30; //size etiqueta
        int aux = 0;

        NodeList temp = Graph.Head;
        NodeList temp2 = Graph.searchGraph(path);

        if (temp2 != null) {
            count.setText("" + temp2.SubFolders.Size);
        }

        int i = 0;
        while (temp != null) {
            if (temp.Parent != null) {
                if (temp.Parent.Folders.getFolderPath().equals(path)) {
                    JLabel Folder = new JLabel();
                    JLabel Etiqueta = new JLabel();
                    Folder.setName(temp.Folders.getFolderPath());
                    Folder.setText(temp.Folders.getFolderName());
                    Folder.setIcon(IconFolder(this.width, this.height));
                    Folder.addMouseListener(eventfolders);

                    Etiqueta.setText(temp.Folders.getFolderName());
                    Etiqueta.setHorizontalAlignment(SwingConstants.CENTER);

                    if (i != 0 && i % Folders_Line == 0) {
                        Line_Folders++;
                        Panel_Height = Line_Folders * this.height + Line_Folders * sep_ver + 15;
                        sep_hor = 25;
                    }
                    aux = i - Folders_Line * (Line_Folders);
                    Folder.setBounds(aux * this.width + sep_hor, Panel_Height, this.width, this.height);
                    Etiqueta.setBounds(aux * this.width + sep_hor, Panel_Height + this.height - 15, this.width, sep_ver);
                    sep_hor += 25;
                    Panel.add(Folder);
                    Panel.add(Etiqueta);
                    i++;
                }
            }
            temp = temp.Next;
        }

        NodeList tempavl = Graph.searchGraph(path);
        if (tempavl != null) {
            if (tempavl.Files.Root != null) {
                String Noes = tempavl.Files.Nodes(tempavl.Files.Root);
                String New_Line = "";
                for (int j = 0; j < Noes.length(); j++) {
                    if (Noes.charAt(j) != '\n') {
                        New_Line += Noes.charAt(j);
                    } else {
                        //Fields[0] = FilenameAbsolute
                        //Fields[1] = Filename;
                        String[] Fields = New_Line.split(",");
                        JLabel File = new JLabel();
                        JLabel Etiqueta = new JLabel();
                        File.setName(Fields[1]);
                        File.setText(Fields[0]);
                        File.setIcon(IconFile(this.width, this.height));
                        File.addMouseListener(eventfiles);

                        Etiqueta.setText(Fields[0]);
                        Etiqueta.setHorizontalAlignment(SwingConstants.CENTER);

                        if (i != 0 && i % Folders_Line == 0) {
                            Line_Folders++;
                            Panel_Height = Line_Folders * this.height + Line_Folders * sep_ver + 15;
                            sep_hor = 25;
                        }
                        aux = i - Folders_Line * (Line_Folders);
                        File.setBounds(aux * this.width + sep_hor, Panel_Height, this.width, this.height);
                        Etiqueta.setBounds(aux * this.width + sep_hor, Panel_Height + this.height - 15, this.width, sep_ver);
                        sep_hor += 25;
                        Panel.add(File);
                        Panel.add(Etiqueta);
                        i++;
                        New_Line = "";
                    }
                }
            }
        }
        Panel_Height += this.height + 20;
        Panel.setPreferredSize(new Dimension(Panel.getWidth(), Panel_Height));
        Scroll.repaint();
    }
    //-------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Direction = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        Load_Users = new javax.swing.JLabel();
        Files_Load = new javax.swing.JLabel();
        Reports = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Scroll = new javax.swing.JScrollPane();
        Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        Fup = new javax.swing.JLabel();
        Fnew = new javax.swing.JLabel();
        Anew = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setName("frame"); // NOI18N

        Direction.setText("jLabel1");

        exit.setText("jLabel1");
        exit.setToolTipText("Exit of your Session");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        Load_Users.setText("jLabel1");
        Load_Users.setToolTipText("Bulk Loading Users");
        Load_Users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Load_UsersMouseClicked(evt);
            }
        });

        Files_Load.setText("jLabel1");
        Files_Load.setToolTipText("Bulk Loading Files");
        Files_Load.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Files_LoadMouseClicked(evt);
            }
        });

        Reports.setText("jLabel4");
        Reports.setToolTipText("Reports of Structures");
        Reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportsMouseClicked(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 102, 204));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setForeground(new java.awt.Color(0, 102, 204));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Folders and Files", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        Scroll.setForeground(new java.awt.Color(0, 102, 204));

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        Scroll.setViewportView(Panel);

        jLabel1.setText("Subfolders: ");

        count.setForeground(new java.awt.Color(0, 0, 255));
        count.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel2.setText("Username: ");

        username.setForeground(new java.awt.Color(0, 0, 255));
        username.setText("jLabel3");

        Fup.setText("jLabel7");
        Fup.setToolTipText("Up a Folder");
        Fup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FupMouseClicked(evt);
            }
        });

        Fnew.setText("jLabel4");
        Fnew.setToolTipText("Creates a new Folder");
        Fnew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FnewMouseClicked(evt);
            }
        });

        Anew.setText("jLabel1");
        Anew.setToolTipText("Creates a new File");
        Anew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AnewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Direction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Scroll, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Anew, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(Fnew, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(Fup, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Load_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(Files_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Reports, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Reports, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Files_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Load_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Anew, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Fnew, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Fup, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))
                        .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(Direction, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(username)))))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        Login Login = new Login(Users, Operations);
        Login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_exitMouseClicked

    private void Load_UsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Load_UsersMouseClicked
        int cont_user = 0;
        boolean verfile = false;
        ArrayList<Object> Errores = new ArrayList();
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter Filter = new FileNameExtensionFilter("Files .CSV", "csv");
        file.setFileFilter(Filter);

        int result = file.showOpenDialog(this);
        if (result != JFileChooser.CANCEL_OPTION) {
            File filename = file.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(filename.getAbsoluteFile()));
                String line = br.readLine();
                while (null != line) {
                    String[] fields = line.split(",");
                    if (fields[0].equalsIgnoreCase("Usuario") && fields[1].equalsIgnoreCase("Password")) {
                        verfile = true;
                    }
                    if (!fields[0].equalsIgnoreCase("Usuario") && !fields[1].equalsIgnoreCase("Password") && verfile == true) {
                        if (!Users.Validate_user(fields[0])) {
                            if (fields[1].length() >= 8) {
                                Users.Add(fields[0], fields[1]);
                                Operations.add("Add User", Current_User_Name);
                                cont_user++;
                            } else {
                                Errores.add(new Errors(fields[0], "The Password is Less than 8 Characters "));
                            }
                        } else {
                            Errores.add(new Errors(fields[0], "User Already Registered"));
                        }
                    }
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Could not open file", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not read file", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (NoSuchAlgorithmException ex) {
                JOptionPane.showMessageDialog(null, "Could not read file", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            Operations.add("They were added " + cont_user + " Users", Current_User_Name);
            Result_Import ri = new Result_Import(cont_user, Errores);
            ri.setVisible(true);
        }
    }//GEN-LAST:event_Load_UsersMouseClicked

    private void ReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsMouseClicked
        NodeList aux = Graph.searchGraph(Current_Path);
        if (aux != null) {
            Reports reports = new Reports(Users, Operations, aux.Files, Graph);
            reports.setVisible(true);
        }

    }//GEN-LAST:event_ReportsMouseClicked

    private void Files_LoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Files_LoadMouseClicked
        NodeList aux = Graph.searchGraph(Current_Path);
        if (aux != null) {
            int addfiles = aux.Files.BulkLoad(this, Current_User_Name);
            Operations.add("They were added " + addfiles + " files", Current_User_Name);
            CleanPanel();
            PaintPanel(Current_Path);
        }
    }//GEN-LAST:event_Files_LoadMouseClicked

    private void AnewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnewMouseClicked
        NodeList current_avl = Graph.searchGraph(Current_Path);
        if (current_avl != null) {
            FormA a = new FormA(this, true);
            a.setVisible(true);
            String[] Data = a.Data;
            if (Data != null) {
                if (current_avl.Files.add(Data[0], Data[1], Current_User_Name)) {
                    Operations.add("Was added File: " + Data[1], Current_User_Name);
                    this.CleanPanel();
                    this.PaintPanel(Current_Path);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Not Add File", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_AnewMouseClicked

    private void FnewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FnewMouseClicked
        String name = "" + JOptionPane.showInputDialog(null, "Input name for folder", "Information", JOptionPane.INFORMATION_MESSAGE);
        if (!name.isEmpty()) {
            NodeList temp = Graph.searchGraph(Current_Path + name + "/");
            if (temp == null) {
                Graph.add(name, Current_Path);
                Operations.add("Was added Folder: " + name, Current_User_Name);
                this.CleanPanel();
                this.PaintPanel(Current_Path);
            } else {
                JOptionPane.showMessageDialog(null, "Warning: The Folder Name already Exist", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Field is Empty", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_FnewMouseClicked

    private void FupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FupMouseClicked
        NodeList temp = Graph.searchGraph(Current_Path);
        if (temp != null) {
            temp = temp.Parent;
            if (temp != null) {
                UpdatePath(temp.Folders.getFolderPath());
                CleanPanel();
                PaintPanel(Current_Path);
            } else {
                JOptionPane.showMessageDialog(null, "Access Denied");
            }
        }
    }//GEN-LAST:event_FupMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Anew;
    private javax.swing.JLabel Direction;
    private javax.swing.JLabel Files_Load;
    private javax.swing.JLabel Fnew;
    private javax.swing.JLabel Fup;
    private javax.swing.JLabel Load_Users;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel Reports;
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JLabel count;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
