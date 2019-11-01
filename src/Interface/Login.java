package Interface;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Structures.Hash_Table;
/**
 *
 * @author JeffGeo
 */
public class Login extends javax.swing.JFrame {
    
    public static Hash_Table Table;

    public Login() {
        initComponents();
        Load_Images(Login);
        Table = new Hash_Table();
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
        Forgot_Pass = new javax.swing.JLabel();
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

        Forgot_Pass.setForeground(new java.awt.Color(51, 51, 255));
        Forgot_Pass.setText("Forgot your password?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(New_User, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(Forgot_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(Username)
                            .addComponent(Password)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(New_User)
                    .addComponent(Forgot_Pass))
                .addGap(27, 27, 27))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(Username.getText().equalsIgnoreCase("Admin") && Password.getText().equalsIgnoreCase("Admin")){
            JOptionPane.showMessageDialog(null, "Welcome Administrator", "Information", JOptionPane.INFORMATION_MESSAGE);
        }else{
          Table.Print_Table();
              String Data[] = Table.Search(Username.getText(), Password.getText());
              if(!Data[0].isEmpty() && !Data[1].isEmpty()){
                  JOptionPane.showMessageDialog(null, "Welcome "+Data[0]+" to EDD Drive","Information",JOptionPane.INFORMATION_MESSAGE);
                  Username.setText("");
                  Password.setText("");
              }else{
                  JOptionPane.showMessageDialog(null, "Invalid Credentials","Informaction",JOptionPane.ERROR_MESSAGE);
                  Username.setText("");
                  Password.setText("");
              }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void New_UserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_New_UserMouseClicked
      int Resp = JOptionPane.showConfirmDialog(null, "Do you Want to Create a new User","Information",JOptionPane.YES_NO_OPTION);
      
      if(Resp == JOptionPane.YES_OPTION){
          Register r = new Register();
          r.setVisible(true);
      }
      if(Resp == JOptionPane.NO_OPTION){
          System.out.println("Sin Abrir Form");
      }
    }//GEN-LAST:event_New_UserMouseClicked

    private void Load_Images(JLabel login){
        Image imglogin = new ImageIcon(getClass().getResource("../Images/login.png")).getImage();
        Image newimglogin = imglogin.getScaledInstance(login.getWidth(), login.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimglogin);
        login.setIcon(icon);
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Forgot_Pass;
    private javax.swing.JLabel Login;
    private javax.swing.JLabel New_User;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField Username;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
