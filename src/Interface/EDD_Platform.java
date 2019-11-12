package Interface;

import Others.Errors;
import Structures.Hash_Table;
import Structures.Stack;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author JeffGeo
 */
public class EDD_Platform extends javax.swing.JFrame {

    private Hash_Table Users;
    private Stack Operations;
    private String Current_User;

    public EDD_Platform(String Username, Hash_Table Users, Stack Operations) {
        initComponents();
        this.Users = Users;
        this.Operations = Operations;
        this.Current_User = Username;
        this.setTitle("EDD System -" + this.Current_User + "-");
        this.Load_Images();
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

        Image imgadelete = new ImageIcon(getClass().getResource("../Images/Adelete.png")).getImage();
        Image newimgadelete = imgadelete.getScaledInstance(Adelete.getWidth(), Adelete.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconadelete = new ImageIcon(newimgadelete);
        Adelete.setIcon(iconadelete);

        Image imgamodify = new ImageIcon(getClass().getResource("../Images/Amodify.png")).getImage();
        Image newimgamodify = imgamodify.getScaledInstance(Amodify.getWidth(), Amodify.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconamodify = new ImageIcon(newimgamodify);
        Amodify.setIcon(iconamodify);

        Image imgFnew = new ImageIcon(getClass().getResource("../Images/Fcreate.png")).getImage();
        Image newimgFnew = imgFnew.getScaledInstance(Fnew.getWidth(), Fnew.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconFnew = new ImageIcon(newimgFnew);
        Fnew.setIcon(iconFnew);

        Image imgFdelete = new ImageIcon(getClass().getResource("../Images/Fdelete.png")).getImage();
        Image newimgFdelete = imgFdelete.getScaledInstance(Fdelete.getWidth(), Fdelete.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconFdelete = new ImageIcon(newimgFdelete);
        Fdelete.setIcon(iconFdelete);

        Image imgFmodify = new ImageIcon(getClass().getResource("../Images/Fmodify.png")).getImage();
        Image newimgFmodify = imgFmodify.getScaledInstance(Fmodify.getWidth(), Fmodify.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconFmodify = new ImageIcon(newimgFmodify);
        Fmodify.setIcon(iconFmodify);

        Image imgFup = new ImageIcon(getClass().getResource("../Images/Fup.png")).getImage();
        Image newimgFup = imgFup.getScaledInstance(Fup.getWidth(), Fup.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconFup = new ImageIcon(newimgFup);
        Fup.setIcon(iconFup);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        Direction = new javax.swing.JLabel();
        Anew = new javax.swing.JLabel();
        Adelete = new javax.swing.JLabel();
        Amodify = new javax.swing.JLabel();
        Fnew = new javax.swing.JLabel();
        Fdelete = new javax.swing.JLabel();
        Fmodify = new javax.swing.JLabel();
        Fup = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        Load_Users = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frame"); // NOI18N

        Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Files and Folders", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        Direction.setText("jLabel1");

        Anew.setText("jLabel1");
        Anew.setToolTipText("Creates a new File");

        Adelete.setText("jLabel2");
        Adelete.setToolTipText("Deletes a File");

        Amodify.setText("jLabel3");
        Amodify.setToolTipText("Edit a File");

        Fnew.setText("jLabel4");
        Fnew.setToolTipText("Creates a new Folder");

        Fdelete.setText("jLabel5");
        Fdelete.setToolTipText("Deletes a Folder");

        Fmodify.setText("jLabel6");
        Fmodify.setToolTipText("Edit a Folder");

        Fup.setText("jLabel7");
        Fup.setToolTipText("Up a Folder");

        exit.setText("jLabel1");
        exit.setToolTipText("Exit of your Session");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        Load_Users.setText("jLabel1");
        Load_Users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Load_UsersMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Adelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Anew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Amodify, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fnew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fdelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fmodify, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Load_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Direction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Load_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Anew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Adelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Amodify, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fnew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fmodify, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fup, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                Operations.add("Add User", Current_User);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Adelete;
    private javax.swing.JLabel Amodify;
    private javax.swing.JLabel Anew;
    private javax.swing.JLabel Direction;
    private javax.swing.JLabel Fdelete;
    private javax.swing.JLabel Fmodify;
    private javax.swing.JLabel Fnew;
    private javax.swing.JLabel Fup;
    private javax.swing.JLabel Load_Users;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel exit;
    // End of variables declaration//GEN-END:variables
}
