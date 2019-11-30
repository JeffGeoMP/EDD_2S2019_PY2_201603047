package Interface;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Structures.Table_Hash;
import Structures.Stack;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JeffGeo
 */
public class Login extends javax.swing.JFrame {
    
    private Table_Hash Users;
    private Stack Operations;
    private int UserIndex;
    
    public Login(Table_Hash Users, Stack Operations) {
        initComponents();
        Load_Images();             //Load Imagenes for JFrame
        this.Users = Users;             //Load Users
        this.Operations = Operations;   //Load Operations
        this.UserIndex = -1;            //Index Users
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        Username = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        New_User = new javax.swing.JLabel();
        Login = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        jLabel1.setText("Username ");

        jLabel2.setText("Password");

        jButton1.setText("SIGN IN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        New_User.setForeground(new java.awt.Color(51, 51, 255));
        New_User.setText("Create a new account");
        New_User.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                New_UserMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(New_User, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(Username)
                            .addComponent(Password)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(New_User)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(Username.getText().isEmpty() && Password.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "The Fields is Empty", "Information", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                if(Users.Search(Username.getText(), Password.getText())){
                    JOptionPane.showMessageDialog(null, "Welcome "+Username.getText()+" to EDD System","Information",JOptionPane.INFORMATION_MESSAGE);
                    UserIndex = Users.IndexUser(Username.getText());
                    Operations.add("Login User", Username.getText());
                    
                    EDD_Platform System = new EDD_Platform(Username.getText(), UserIndex, Users, Operations);
                    System.setVisible(true);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Credentials","Informaction",JOptionPane.ERROR_MESSAGE);
                    Username.setText("");
                    Password.setText("");
                }
            } catch (NoSuchAlgorithmException ex) {
                JOptionPane.showMessageDialog(null, "Fatal Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void New_UserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_New_UserMouseClicked
        int Resp = JOptionPane.showConfirmDialog(null, "Do you Want to Create a new User","Information",JOptionPane.YES_NO_OPTION);
        if(Resp == JOptionPane.YES_OPTION){
            Register r = new Register(Users, Operations);
            r.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_New_UserMouseClicked

    private void Load_Images(){
        Image imglogin = new ImageIcon(getClass().getResource("/Images/login.png")).getImage();
        Image newimglogin = imglogin.getScaledInstance(Login.getWidth(), Login.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimglogin);
        Login.setIcon(icon);
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Login;
    private javax.swing.JLabel New_User;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField Username;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
