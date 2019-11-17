package Interface;

import Others.Errors;
import Others.Users;
import Structures.AVLTree;
import Dialogs.FormA;
import Dialogs.FormOpenFiles;
import Structures.LinkedList;
import Structures.NodeList;
import Structures.Table_Hash;
import Structures.Stack;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author JeffGeo
 */
public class EDD_Platform extends javax.swing.JFrame {

    //Structures aux
    private Table_Hash Users;
    private Stack Operations;
    private LinkedList Graph;

    //Var of Control
    private int Index_User;
    private String Current_User_Name;

    //Var for Class
    private String Current_Path;
    private int width;
    private int height;

    public EDD_Platform(String Username, int Index_User, Table_Hash Users, Stack Operations) {
        initComponents();
        this.Users = Users;
        this.Operations = Operations;

        this.Current_User_Name = Username;
        this.Index_User = Index_User;

        Users current_user = (Users) (Users.Users[Index_User]);
        Graph = current_user.Graph;

        this.Current_Path = "";
        this.width = Panel.getWidth() / 10;     //Weight of Label
        this.height = 60;                       //Height of Label

        this.setTitle("EDD System -" + this.Current_User_Name + "-");
        System.out.println("User: " + this.Current_User_Name + "Index: " + this.Index_User);
        this.Load_Images();
        this.CheckAdmin(this.Current_User_Name);
        this.Initial();
        //this.PaintPanel();
    }

    private void CheckAdmin(String User) {
        if (!User.equals("admin")) {
            Load_Users.setVisible(false);
        }
    }

    private void CleanPanel() {
        this.Panel.removeAll();
        this.Panel.repaint();
    }

    private void PaintPanel(String path) {
        JFrame f = this;
        MouseListener eventfolders = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel aux = (JLabel) e.getSource();                //Creamos un JLabel Auxiliar

                ActionListener actionopen = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Name: " + aux.getName() + "  Text: " + aux.getText());
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
                JMenuItem open = new JMenuItem("Open");
                open.addActionListener(actionopen);
                menu.add(open);

                JMenuItem edit = new JMenuItem("Edit");
                edit.addActionListener(actionedit);
                menu.add(edit);

                JMenuItem Remove = new JMenuItem("Delete");
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

                ActionListener actionopen = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NodeList nodeavl = Graph.searchGraph(Current_Path);
                        if (nodeavl != null) {
                            if (nodeavl.Files.Root != null) {
                                FormOpenFiles open = new FormOpenFiles(f, true);
                                if (nodeavl.Files.getContent(aux.getName()) != null) {
                                    System.out.println(nodeavl.Files.getContent(aux.getName()));
                                    open.filename.setText("File: " + aux.getText());
                                    open.cadena.setText(nodeavl.Files.getContent(aux.getName()));
                                    open.setVisible(true);
                                    String new_content = "" + open.New_Content;
                                    if (!new_content.isEmpty()) {
                                        nodeavl.Files.EditContent(aux.getName(), new_content);
                                        CleanPanel();
                                        PaintPanel(Current_Path);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Not Change", "Information", JOptionPane.INFORMATION_MESSAGE);
                                    }
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
                                if (!new_name.isEmpty()) {
                                    if (!nodeavl.Files.Search(nodeavl.Files.removeqt(new_name))) {
                                        nodeavl.Files.EditName(aux.getName(), new_name);
                                        CleanPanel();
                                        PaintPanel(Current_Path);
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Name is empty", "Information", JOptionPane.INFORMATION_MESSAGE);
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
                                FormOpenFiles edit = new FormOpenFiles(f, true);
                                if (nodeavl.Files.getContent(aux.getName()) != null) {
                                    System.out.println(nodeavl.Files.getContent(aux.getName()));
                                    edit.filename.setText("File: " + aux.getText());
                                    edit.cadena.setText(nodeavl.Files.getContent(aux.getName()));
                                    edit.setVisible(true);
                                    String new_content = "" + edit.New_Content;
                                    if (!new_content.isEmpty()) {
                                        nodeavl.Files.EditContent(aux.getName(), new_content);
                                        CleanPanel();
                                        PaintPanel(Current_Path);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Not Change", "Information", JOptionPane.INFORMATION_MESSAGE);
                                    }
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
                                nodeavl.Files.Remove(aux.getName());
                                CleanPanel();
                                PaintPanel(Current_Path);
                            }
                        }
                    }
                };
                JPopupMenu menu = new JPopupMenu();
                JMenuItem open = new JMenuItem("Open");
                open.addActionListener(actionopen);
                menu.add(open);

                JMenuItem editname = new JMenuItem("Edit Name");
                editname.addActionListener(actioneditname);
                menu.add(editname);

                JMenuItem editcontent = new JMenuItem("Edit Content");
                editcontent.addActionListener(actioneditcontent);
                menu.add(editcontent);

                JMenuItem Remove = new JMenuItem("Delete");
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

        int Line_Folders = 0;
        int Panel_Height = 20;
        int aux = 0;

        NodeList temp = Graph.Head;

        int i = 0;
        while (temp != null) {
            if (temp.Parent != null) {
                if (temp.Parent.Folders.getFolderPath().equals(path)) {
                    JLabel Folder = new JLabel();
                    Folder.setName(temp.Folders.getFolderPath());
                    Folder.setText(temp.Folders.getFolderName());

                    if (i != 0 && i % 9 == 0) {
                        Line_Folders++;
                        Panel_Height = Line_Folders * this.height + 20;
                    }
                    aux = i - 9 * (Line_Folders);
                    Folder.setBounds(aux * this.width + 15, Panel_Height, this.width, this.height);
                    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                    Folder.setBorder(border);
                    Folder.addMouseListener(eventfolders);
                    Panel.add(Folder);
                    i++;
                }
            }
            temp = temp.Next;
        }

        NodeList tempavl = Graph.searchGraph(path);
        if (tempavl != null) {
            if (tempavl.Files.Root != null) {
                tempavl.Files.AddGraphics(tempavl.Files.Root, i, Panel, Panel_Height, this.width, this.height, Line_Folders, eventfiles, aux);
            }
        }
        Panel_Height += this.height + 20;
        Panel.setPreferredSize(new Dimension(Panel.getWidth(), Panel_Height));
    }

    private void Initial() {
        if (Graph.Head != null) {
            UpdatePath(Graph.Head.Folders.getFolderPath());
            PaintPanel(Current_Path);
        } else {
            System.out.println("Eror Head equals null");
        }
    }

    private void UpdatePath(String new_direction) {
        Direction.setText(new_direction);
        Current_Path = new_direction;
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

        Image imgShare = new ImageIcon(getClass().getResource("../Images/share.png")).getImage();
        Image newimgShare = imgShare.getScaledInstance(Share.getWidth(), Share.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconShare = new ImageIcon(newimgShare);
        Share.setIcon(iconShare);

        Image imgDownLoad = new ImageIcon(getClass().getResource("../Images/download.png")).getImage();
        Image newimgDownload = imgDownLoad.getScaledInstance(Download.getWidth(), Download.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconDownload = new ImageIcon(newimgDownload);
        Download.setIcon(iconDownload);

        Image imgReports = new ImageIcon(getClass().getResource("../Images/Reports.png")).getImage();
        Image newimgReports = imgReports.getScaledInstance(Reports.getWidth(), Reports.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconReports = new ImageIcon(newimgReports);
        Reports.setIcon(iconReports);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Direction = new javax.swing.JLabel();
        Anew = new javax.swing.JLabel();
        Fnew = new javax.swing.JLabel();
        Fup = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        Load_Users = new javax.swing.JLabel();
        Files_Load = new javax.swing.JLabel();
        Share = new javax.swing.JLabel();
        Download = new javax.swing.JLabel();
        Reports = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Scroll = new javax.swing.JScrollPane();
        Panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frame"); // NOI18N

        Direction.setText("jLabel1");

        Anew.setText("jLabel1");
        Anew.setToolTipText("Creates a new File");
        Anew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AnewMouseClicked(evt);
            }
        });

        Fnew.setText("jLabel4");
        Fnew.setToolTipText("Creates a new Folder");
        Fnew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FnewMouseClicked(evt);
            }
        });

        Fup.setText("jLabel7");
        Fup.setToolTipText("Up a Folder");
        Fup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FupMouseClicked(evt);
            }
        });

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

        Share.setText("jLabel2");
        Share.setToolTipText("Share Files with Others Users");

        Download.setText("jLabel3");
        Download.setToolTipText("Download File");

        Reports.setText("jLabel4");
        Reports.setToolTipText("Reports of Structures");
        Reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportsMouseClicked(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Folders and Files", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );

        Scroll.setViewportView(Panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Anew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fnew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Direction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(Load_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Files_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Share, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Download, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(Reports, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Scroll))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Share, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Files_Load, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addComponent(Load_Users, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addComponent(Download, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jSeparator2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Reports, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)))
                        .addGap(33, 33, 33)
                        .addComponent(Anew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fnew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fup, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192))
                    .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Direction)
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
                    if (!fields[0].equalsIgnoreCase("Usuario") && !fields[1].equalsIgnoreCase("Password")) {
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
            aux.Files.BulkLoad(this, Current_User_Name);
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
                current_avl.Files.add(Data[0], Data[1], Current_User_Name);
                this.CleanPanel();
                this.PaintPanel(Current_Path);
            } else {
                JOptionPane.showMessageDialog(null, "Not Add File", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_AnewMouseClicked

    private void FnewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FnewMouseClicked
        String name = "" + JOptionPane.showInputDialog(null, "Input name for folder", "Information", JOptionPane.INFORMATION_MESSAGE);
        if (!name.isEmpty()) {
            Graph.add(name, Current_Path);
            this.CleanPanel();
            this.PaintPanel(Current_Path);
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
                JOptionPane.showMessageDialog(null, "Acces Denied");
            }
        }
    }//GEN-LAST:event_FupMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Anew;
    private javax.swing.JLabel Direction;
    private javax.swing.JLabel Download;
    private javax.swing.JLabel Files_Load;
    private javax.swing.JLabel Fnew;
    private javax.swing.JLabel Fup;
    private javax.swing.JLabel Load_Users;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel Reports;
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JLabel Share;
    private javax.swing.JLabel exit;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
