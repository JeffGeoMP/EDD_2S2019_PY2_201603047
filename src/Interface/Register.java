package Interface;

import Structures.Table_Hash;
import Structures.Stack;
import java.awt.Color;
import java.awt.Image;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author JeffGeo
 */
public class Register extends javax.swing.JFrame {

    private Table_Hash Users;
    private Stack Operations;

    public Register(Table_Hash Users, Stack Operations) {
        initComponents();
        Load_Images(register);          //Load Imagenes for the from
        Listeners();                    //Add Listeners for the buttons
        AD(false);

        this.Users = Users;
        this.Operations = Operations;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        cpass = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        UserV = new javax.swing.JLabel();
        PassV = new javax.swing.JLabel();
        Pass2V = new javax.swing.JLabel();
        check = new javax.swing.JButton();
        register = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Check In");

        jLabel1.setText("UserName");

        jLabel2.setText("Password");

        jLabel3.setText("Confirm Password");

        check.setText("CHECK IN");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });

        register.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Pass2V, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cpass, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(PassV, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(UserV, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PassV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pass2V, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(check)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        if (!pass.getText().isEmpty() && !cpass.getText().isEmpty() && !username.getText().isEmpty()) {
            if (pass.getText().equals(cpass.getText())) {
                try {
                    JOptionPane.showMessageDialog(null, "User Add to EDD Drive", "Information", JOptionPane.INFORMATION_MESSAGE);
                    Users.Add(username.getText(), pass.getText());
                    Operations.add("Register User", username.getText());
                    Login login = new Login(Users, Operations);
                    login.setVisible(true);
                    this.dispose();
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                Pass2V.setText("Passwords Do Not Match");
                Pass2V.setForeground(Color.RED);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Check to Information", "Information", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_checkActionPerformed

    private void Load_Images(JLabel checkin) {
        Image imglogin = new ImageIcon(getClass().getResource("../Images/checkin.png")).getImage();
        Image newimglogin = imglogin.getScaledInstance(checkin.getWidth(), checkin.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimglogin);
        checkin.setIcon(icon);
    }

    private void Listeners() {
        pass.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Pass();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Pass();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Pass();
            }
        });

        cpass.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                CPass();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CPass();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CPass();
            }
        });

        username.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Username();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Username();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Username();
            }
        });
    }

    private void Pass() {
        if (pass.getText().isEmpty()) {
            PassV.setText("Obligatory Field");
            PassV.setForeground(Color.RED);
            check.setEnabled(false);

        } else if (pass.getText().length() < 8) {
            PassV.setText("Invalid Password");
            PassV.setForeground(Color.RED);
            check.setEnabled(false);

        } else {
            PassV.setText("Valid Password");
            PassV.setForeground(Color.BLUE);
            check.setEnabled(true);
        }
    }

    private void CPass() {
        if (cpass.getText().isEmpty()) {
            Pass2V.setText("Obligatory Field");
            Pass2V.setForeground(Color.RED);

        } else if (cpass.getText().equals(pass.getText())) {
            Pass2V.setText("Password Match");
            Pass2V.setForeground(Color.BLUE);

        } else {
            Pass2V.setText("Passwords Do Not Match");
            Pass2V.setForeground(Color.RED);

        }
    }

    private void Username() {
        if (username.getText().isEmpty()) {
            UserV.setText("Obligatory Field");
            UserV.setForeground(Color.RED);
            AD(false);
        } else if (Users.Validate_user(username.getText())) {
            UserV.setText("Username not Available");
            UserV.setForeground(Color.RED);
            AD(false);
        } else {
            UserV.setText("Username Available");
            UserV.setForeground(Color.BLUE);
            AD(true);
        }
    }

    private void AD(boolean v) {
        check.setEnabled(v);
        pass.setEnabled(v);
        cpass.setEnabled(v);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Pass2V;
    private javax.swing.JLabel PassV;
    private javax.swing.JLabel UserV;
    private javax.swing.JButton check;
    private javax.swing.JPasswordField cpass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JPasswordField pass;
    private javax.swing.JLabel register;
    public javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
