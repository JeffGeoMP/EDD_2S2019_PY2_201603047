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

    public Reports(Table_Hash Users, Stack Operations) {
        initComponents();

        this.Users = Users;
        this.Operations = Operations;

        GroupRadioButton();
        image.setOpaque(true);                        //Quitamos el diseño por defecto
        image.setBackground(Color.white);             //Pintamos el Label
        image.repaint();
        
        
        Image img = new ImageIcon(getClass().getResource("../Images/ojo.png")).getImage();
        Image newimg = img.getScaledInstance(ver.getWidth(), ver.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        ver.setIcon(icon);
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
        jButton1 = new javax.swing.JButton();
        ver = new javax.swing.JLabel();

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

        jButton1.setText("Generate .png");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ver.setText("jLabel1");
        ver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verMouseClicked(evt);
            }
        });

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
                            .addComponent(jButton1)
                            .addComponent(Table_Hash)
                            .addComponent(TreeAVL)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(ver, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(ver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(panel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Valitation();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void verMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verMouseClicked
        Valitation2();
    }//GEN-LAST:event_verMouseClicked

    private void Valitation() {
        if (TreeAVL.isSelected()) {
            System.out.println("Tree AVL Graph");
            image.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../Images/loader.gif")).getImage()));
            image.repaint();

        } else if (Table_Hash.isSelected()) {
            System.out.println("Table Hash Graph");
            image.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../Images/loader.gif")).getImage()));
            image.repaint();
        } else if (Stack.isSelected()) {
            this.conStack++;
            Operations.GenerateImage(this.conStack);
            image.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../Images/loader.gif")).getImage()));
            image.repaint();

            File temp = new File("Stack" + this.conStack + ".png");
            while (!temp.exists()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "Continue Please...", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else if (Matrix.isSelected()) {
            System.out.println("Matrix Graph");
            image.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../Images/loader.gif")).getImage()));
            image.repaint();;
        } else if (Graph.isSelected()) {
            System.out.println("Grafo Graph");
            Operations.add("Test in Ejec", "AAA");
        } else {
            System.out.println("Not Select");
        }
    }

    private void Valitation2() {
        if (TreeAVL.isSelected()) {
            image.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("AVLTree.png")).getImage()));
            image.repaint();

        } else if (Table_Hash.isSelected()) {
            image.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("THash.png")).getImage()));
            image.repaint();
        } else if (Stack.isSelected()) {
            File temp = new File("Stack" + this.conStack + ".png");
            if (temp.exists()) {
                System.out.println("Existe");
                image.setIcon(new ImageIcon("Stack" + this.conStack + ".png"));
                image.repaint();
                Operations.borraArchivo("Stack" + this.conStack + ".png");
            } else {
                JOptionPane.showMessageDialog(null, "The Image is still rendering", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
//            
        } else if (Matrix.isSelected()) {
            image.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("THash.png")).getImage()));
            image.repaint();
        } else if (Graph.isSelected()) {
            image.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("THash.png")).getImage()));
            image.repaint();
        } else {
            System.out.println("Not Select");
        }
    }

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
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane panel;
    private javax.swing.JLabel ver;
    // End of variables declaration//GEN-END:variables
}
