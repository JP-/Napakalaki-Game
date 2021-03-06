/******************************************************************/
// Clase CultistView
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/



package GUI;


import napakalaki.Cultist;

public class CultistView extends javax.swing.JPanel {

    
    private Cultist cultistModel;

    
    
    /**
     * Creates new form CultistView
     */
    public CultistView() {
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

        name = new javax.swing.JLabel();
        jLabelCultist = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cultist"));
        setMaximumSize(new java.awt.Dimension(157, 189));
        setMinimumSize(new java.awt.Dimension(157, 189));
        setPreferredSize(new java.awt.Dimension(157, 189));
        setLayout(null);

        name.setBackground(java.awt.Color.white);
        name.setFont(new java.awt.Font("WenQuanYi Micro Hei", 1, 14)); // NOI18N
        name.setForeground(java.awt.Color.darkGray);
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setOpaque(true);
        add(name);
        name.setBounds(31, 152, 100, 20);

        jLabelCultist.setToolTipText("");
        jLabelCultist.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelCultist.setName(""); // NOI18N
        jLabelCultist.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(jLabelCultist);
        jLabelCultist.setBounds(31, 17, 100, 164);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCultist;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables

    public void setCultist (Cultist c) {
	if( c != null )
	{
	    cultistModel = c;
	    String icon = "/res/cultists/" + c.getName() + ".png";
	    jLabelCultist.setIcon(new javax.swing.ImageIcon(getClass().getResource(icon)));
	}
	else
	    jLabelCultist.setIcon(null);
	
	
	this.name.setEnabled(false);
	
	
	repaint();
    }
    
    
    public void setCharacter(String c, String ch)
    {
	this.name.setEnabled(true);
	jLabelCultist.setIcon(new javax.swing.ImageIcon(getClass().getResource(c)));
	this.name.setText(ch);
	repaint();
	validate();
	
    }

}
