package Interface;

import Others.Errors;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author JeffGeo
 */
public class Result_Import extends javax.swing.JFrame {
    
    public Result_Import(int user_add, ArrayList<Object> Errores) {
        initComponents();
        
        add.setText("Users Added the System: ");
        cont.setText(""+user_add);
        errors.setText("Below are the users that could not be added: ");
        
        DefaultTableModel model = new DefaultTableModel();
        
        int cont = 1;
        for(Object item: Errores){
            Errors e = (Errors)item;
            model = (DefaultTableModel)Table.getModel();
            model.addRow(new Object[]{cont, e.getUser(),e.getReason()});
            cont++;
        }
        Table.setModel(model);
        
        TableColumnModel columnmodel = Table.getColumnModel();
        columnmodel.getColumn(0).setPreferredWidth(50);
        columnmodel.getColumn(1).setPreferredWidth(150);
        columnmodel.getColumn(2).setPreferredWidth(277);
        
        DefaultTableCellRenderer Alin = new DefaultTableCellRenderer();
        Alin.setHorizontalAlignment(SwingConstants.CENTER);
        Table.getColumnModel().getColumn(0).setCellRenderer(Alin);
        Table.getColumnModel().getColumn(1).setCellRenderer(Alin);
        //Table.getColumnModel().getColumn(2).setCellRenderer(Alin);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        add = new javax.swing.JLabel();
        errors = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        cont = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Import Results");

        add.setText("jLabel1");

        errors.setText("jLabel2");

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "User", "Reason"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table);

        cont.setForeground(new java.awt.Color(255, 0, 0));
        cont.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errors, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(cont))
                .addGap(39, 39, 39)
                .addComponent(errors)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JLabel add;
    private javax.swing.JLabel cont;
    private javax.swing.JLabel errors;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
