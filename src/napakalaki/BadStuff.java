/******************************************************************/
// Clase BadStuff
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/




package napakalaki;

import java.util.ArrayList;

public class BadStuff {
   
    /******************************************************************/
    
    private String text;
    private int levels = 1;
    private int nVisibleTreasures = 0; 
    private int nHiddenTreasures = 0;
    private boolean death = false;
    
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    
    
    /******************************************************************/
    
    // Constructor.
    public BadStuff (String text, int levels, int nVisible, int nHidden)
    {
        this.text = text;
        this.levels = levels;
        this.nVisibleTreasures = nVisible;
        this.nHiddenTreasures = nHidden;
    }
    
    
    /******************************************************************/
    
    // Constructor.
    public BadStuff (String text, boolean death)
    {
        this.text = text;
        this.death = death;
    }
    
    
    /******************************************************************/
    
    // Constructor.
    public BadStuff(String text, int levels, ArrayList<TreasureKind> tVisible, 
            ArrayList<TreasureKind> tHidden)
    {
        this.text = text;
        this.levels = levels;
        this.specificHiddenTreasures = tHidden;
        this.specificVisibleTreasures = tVisible;
    }
    
    
    /******************************************************************/
    
    // Devuelve el texto del mal rollo.
    public String getText()
    {
        return this.text;
    }
    
    
    /******************************************************************/
    
    // Devuelve el número de niveles.
    public int getLevels()
    {
        return this.levels;
    }
    
    
    /******************************************************************/
    
    // Devuelve los tesoros visibles.
    public int getnVisibleTreasures()
    {
        return this.nVisibleTreasures;
    }
    
    
    /******************************************************************/
    
    // Devuelve los tesoros ocultos.
    public int getnHiddenTreasures()
    {
        return this.nHiddenTreasures;
    }
        
    
    /******************************************************************/
    
    // Devuelve los tesoros ocultos específicos.
    public ArrayList<TreasureKind> getSpecificHiddenTreasures()
    {
        return specificHiddenTreasures;
    }
    
    
    /******************************************************************/
    
    // Devuelve los tesoros visibles específicos.
    public ArrayList<TreasureKind> getSpecificVisibleTreasures()
    {
        return specificVisibleTreasures;
    }
    
    
    /******************************************************************/
    
    // Devuelve true si el mal rollo solo quita niveles y no tesoros.
    public boolean SoloNiveles()
    {
        boolean solo = false;
        if(nVisibleTreasures==0 && nHiddenTreasures==0 && death==false
                && specificHiddenTreasures.size()==0
                && specificVisibleTreasures.size()==0 && levels != 0)
            solo = true;
        
        return solo;
    }
    
    
    /******************************************************************/
        
    // toString de BadStuff.
    public String toString(){
        
        String tostring = "   Text = " + text + "\n   Levels = " + 
                Integer.toString(levels) + "\n   Visible Treasures = " + 
                Integer.toString(nVisibleTreasures) +
                "\n   Hidden Treasures = " + 
                Integer.toString(nHiddenTreasures) + "\n   Death = " + 
                Boolean.toString(death) + "\n   List Hidden Treasures = ";
        for (TreasureKind tk:specificHiddenTreasures)
        {
            tostring += tk.toString();
        }
        
        tostring += "\n   List Visible Treasures = ";
        
        for (TreasureKind tk:specificVisibleTreasures)
        {
            tostring += tk.toString();
        }
        
        return tostring;
    }
    
    
    /******************************************************************/
    
    // Devuelve true si no hay mal rollo.
    public Boolean isEmpty()
    {
        if(!death && nHiddenTreasures==0 && nVisibleTreasures==0 
                && levels==0 && specificHiddenTreasures.isEmpty()
                && specificVisibleTreasures.isEmpty())
            return true;
        return false;
    }
    
    
    /******************************************************************/
    
    // Quita el tesoro de los visibles.
    public void substractVisibleTreasure(Treasure t)
    {   
	if( nVisibleTreasures == 0 )
	{
	    int index = 0;
	    Boolean find = false;

	    for(TreasureKind tkind:specificVisibleTreasures)
	    {
		if(tkind == t.getType())
		{		
		    index = this.specificVisibleTreasures.indexOf(tkind);		
		    find = true;
		}
	    }

	    if(find)
	    {
		this.specificVisibleTreasures.remove(index);
	    }
	}
	else
	{
	    this.nVisibleTreasures--;
	}
	
    }
    
    
    
    /******************************************************************/
    
    // Quita el tesoro de los ocultos.
    public void substractHiddenTreasure(Treasure t)
    {
	if ( this.nHiddenTreasures == 0)
	{
	    int index = 0;
	    Boolean find = false;
	    for(TreasureKind tkind:specificHiddenTreasures)
	    {
		if(tkind == t.getType())	  
		    index = this.specificHiddenTreasures.indexOf(tkind);		
	    }

	    if(find)
	    {
		this.specificHiddenTreasures.remove(index);
	    }
	}
	else
	{
	    this.nHiddenTreasures--;
	}
    }
    
    
    /******************************************************************/
    
    // Ajusta el mal rollo.
    public BadStuff adjustToFitTreasureLists(ArrayList<Treasure> v, 
            ArrayList<Treasure> h)
    {
	BadStuff bc;
	
	if(this.nHiddenTreasures==0 && this.nVisibleTreasures==0)
	{
	    ArrayList<TreasureKind> qVisibles = new ArrayList();
	    ArrayList<TreasureKind> qOcultos = new ArrayList();
	    
	    ArrayList<TreasureKind> ttvj = new ArrayList();
	    for(Treasure visible:v)
		ttvj.add(visible.getType());
	    
	    ArrayList<TreasureKind> ttoj = new ArrayList();
	    for(Treasure oculto:h)
		ttoj.add(oculto.getType());

	    for(Treasure visible:v)
	    {
		for(TreasureKind vb:this.specificVisibleTreasures)
		{
		    if(visible.getType().equals(vb) && ttvj.contains(vb))
		    {
			qVisibles.add(vb);
			ttvj.remove(vb);
		    }
		}	    
	    }

	    for(Treasure oculto:h)
	    {
		for(TreasureKind ob:this.specificHiddenTreasures)
		{
		    if(oculto.getType().equals(ob) && ttoj.contains(ob))
		    {
			qOcultos.add(ob);		    
			ttoj.remove(ob);
		    }
		}	    
	    }
	    
	    bc = new BadStuff("",0,qVisibles,qOcultos);
	}
	else
	{
	    bc = new BadStuff("",0,Math.min(v.size(), nVisibleTreasures),
		    Math.min(h.size(),nHiddenTreasures));	    	
	}
	
	return bc;
    }
    
    
    /******************************************************************/
    
    // Devuelve true si el mal rollo es la muerte.
    public Boolean myBadStuffIsDeath()
    {
        return death;
    }


}
