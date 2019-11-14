package Interface;

import Structures.Stack;
import Structures.Table_Hash;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author JeffGeo
 */
public class Reports extends javax.swing.JFrame {

    private Table_Hash Users;
    private Stack Operations;
    private int conStack = 0;
    private int conHash = 0;

    public Reports(Table_Hash Users, Stack Operations) {
        initComponents();

        this.Users = Users;
        this.Operations = Operations;

        GroupRadioButton();
        image.setOpaque(true);                        //Quit designed for default
        image.setBackground(Color.white);             //pint Jlabel
        image.repaint();
        inf.setText("Select Report to Generate Image");
        
        LoadImages();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Group = new javax.swing.ButtonGroup();
        panel = new javax.swing.JScrollPane();
        image = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Table_Hash = new javax.swing.JRadioButton();
        TreeAVL = new javax.swing.JRadioButton();
        Graph = new javax.swing.JRadioButton();
        Stack = new javax.swing.JRadioButton();
        Matrix = new javax.swing.JRadioButton();
        ver = new javax.swing.JLabel();
        inf = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reports");

        image.setBackground(new java.awt.Color(255, 255, 255));
        image.setForeground(new java.awt.Color(255, 255, 255));
        panel.setViewportView(image);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        Table_Hash.setText("Table Hash");

        TreeAVL.setText("Tree AVL");

        Graph.setText("Graphic");

        Stack.setText("Stack");

        Matrix.setText("Adjacency Matrix");

        ver.setText("jLabel1");
        ver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verMouseClicked(evt);
            }
        });

        inf.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Graph)
                            .addComponent(Stack)
                            .addComponent(Matrix)
                            .addComponent(Table_Hash)
                            .addComponent(TreeAVL)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(ver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(inf, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(Table_Hash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TreeAVL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Graph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Stack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Matrix)
                .addGap(18, 18, 18)
                .addComponent(ver, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inf)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void verMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verMouseClicked
        Valitation();
    }//GEN-LAST:event_verMouseClicked
    
    private void LoadImages(){
        Image img = new ImageIcon(getClass().getResource("../Images/ojo.png")).getImage();
        Image newimg = img.getScaledInstance(ver.getWidth(), ver.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        ver.setIcon(icon);
    }
    
    
    private void Valitation() {
        if (TreeAVL.isSelected()) {
            inf.setText("Successfully Generated Image");
            
            

        } else if (Table_Hash.isSelected()) {
            inf.setText("The Image is Being Processed....");
            this.conHash = (int)(Math.random()*1000000+1);
            Users.GenerateImage(this.conHash);
            
            //Verify if image is in url
            File temp = new File("Hash" + this.conHash + ".png");
            while (!temp.exists()) {
                    inf.setText("Loading image please wait....");
                try {
                    Thread.sleep(1000);               
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "Continue Please...", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            inf.setText("Successfully Generated Image");
            
            if (temp.exists()) {
                System.out.println("Existe");
                image.setIcon(new ImageIcon("Hash" + this.conHash + ".png"));
                image.repaint();
                Users.DeleteFile("Hash" + this.conHash + ".png");
            } else {
                JOptionPane.showMessageDialog(null, "The Image is still rendering", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            inf.setText("Showing Image of the Structure Table Hash");
            
        } else if (Stack.isSelected()) {
            inf.setText("The Image is Being Processed....");
            this.conStack = (int)(Math.random()*1000000+1);
            Operations.GenerateImage(this.conStack);
            
            File temp = new File("Stack" + this.conStack + ".png"); //Verify if image is in url
            while (!temp.exists()) {
                try {
                    Thread.sleep(1000);               
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "Continue Please...", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            inf.setText("Successfully Generated Image");
            
            if (temp.exists()) {
                image.setIcon(new ImageIcon("Stack" + this.conStack + ".png"));
                image.repaint();
                Operations.DeleteFile("Stack" + this.conStack + ".png");
            } else {
                JOptionPane.showMessageDialog(null, "The Image is still rendering", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            inf.setText("Showing Image of the Structure Stack");

        } else if (Matrix.isSelected()) {
            inf.setText("The Image is Being Processed....");
            inf.setText("Successfully Generated Image");
            
        } else if (Graph.isSelected()) {
            inf.setText("The Image is Being Processed....");
            inf.setText("Successfully Generated Image");
        } else {
            inf.setText("Not Process Image");
        }
    }

//    private void Valitation2() {
//        if (TreeAVL.isSelected()) {
//            inf.setText("Showing Image of the Structure Tree AVL");
//            
//
//        } else if (Table_Hash.isSelected()) {
//            File temp = new File("Hash" + this.conHash + ".png");
//            if (temp.exists()) {
//                System.out.println("Existe");
//                image.setIcon(new ImageIcon("Hash" + this.conHash + ".png"));
//                image.repaint();
//                Users.DeleteFile("Hash" + this.conHash + ".png");
//            } else {
//                JOptionPane.showMessageDialog(null, "The Image is still rendering", "Information", JOptionPane.INFORMATION_MESSAGE);
//            }
//        } else if (Stack.isSelected()) {
//            inf.setText("Showing Image of the Structure Stack");
//            File temp = new File("Stack" + this.conStack + ".png");
//            if (temp.exists()) {
//                System.out.println("Existe");
//                image.setIcon(new ImageIcon("Stack" + this.conStack + ".png"));
//                image.repaint();
//                Operations.DeleteFile("Stack" + this.conStack + ".png");
//            } else {
//                JOptionPane.showMessageDialog(null, "The Image is still rendering", "Information", JOptionPane.INFORMATION_MESSAGE);
//            }
////            
//        } else if (Matrix.isSelected()) {
//            inf.setText("Showing Image of the Structure Matrix");
//        } else if (Graph.isSelected()) {
//            inf.setText("Showing Image of the Structure Graph");
//        } else {
//            inf.setText("Not Select Image");
//        }
//    }

    private void GroupRadioButton() {
        Group.add(Table_Hash);
        Group.add(Matrix);
        Group.add(Stack);
        Group.add(TreeAVL);
        Group.add(Graph);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Graph;
    private javax.swing.ButtonGroup Group;
    private javax.swing.JRadioButton Matrix;
    private javax.swing.JRadioButton Stack;
    private javax.swing.JRadioButton Table_Hash;
    private javax.swing.JRadioButton TreeAVL;
    private javax.swing.JLabel image;
    private javax.swing.JLabel inf;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane panel;
    private javax.swing.JLabel ver;
    // End of variables declaration//GEN-END:variables
}
