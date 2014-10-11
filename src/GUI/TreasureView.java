/******************************************************************/
// Clase TreasureView
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/


package GUI;

import napakalaki.Treasure;

public class TreasureView extends javax.swing.JPanel {

    
    private Treasure treasureModel;
    private boolean selected = false;
    
    
    public boolean isSelected()
    {
	return selected;
    }
    
    
    public Treasure getTreasure()
    {
	return this.treasureModel;
    }
    
    /**
     * Creates new form TreasureView
     */
    public TreasureView() {
	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTreasure = new javax.swing.JLabel();

        jLabelTreasure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelTreasureMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelTreasure, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelTreasure, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelTreasureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelTreasureMouseClicked
        // TODO add your handling code here:
	if(selected)
	{
	    selected = false;
	    jLabelTreasure.setBorder(javax.swing.BorderFactory.createEmptyBorder());	    
	}
	else
	{
	    selected = true;
	    jLabelTreasure.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green, 3));
	}
	
	repaint();
    }//GEN-LAST:event_jLabelTreasureMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelTreasure;
    // End of variables declaration//GEN-END:variables

    
    
    public void setTreasure (Treasure t) 
    {
	treasureModel = t;
	String icon = "/res/treasures/" + t.getName() + ".jpg";
	jLabelTreasure.setIcon(new javax.swing.ImageIcon(getClass().getResource(icon)));
	repaint();
    }



}