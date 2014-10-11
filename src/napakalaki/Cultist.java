/******************************************************************/
// Clase Cultist
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/


package napakalaki;


public class Cultist implements Card
{
    
    
    /******************************************************************/
    
    private String name;
    
    private int gainedLevels;
    
    /******************************************************************/
    
    // Constructor
    public Cultist(String name, int gainedLevels)
    {
	
	this.name = name;
	this.gainedLevels = gainedLevels;
    }
    
    
    /******************************************************************/
    
    // Devuelve el valor básico.
    public int getBasicValue()
    {
	return this.gainedLevels;
    }
    
    
    /******************************************************************/
    
    // Devuelve el valor especial.
    public int getSpecialValue()
    {
	return this.gainedLevels * CultistPlayer.getTotalCultistPlayers();
    }
    
    
    /******************************************************************/
    
    // Devuelve el nombre de Cultist.
    public String getName()
    {
	return name;
    }
    
    
    /******************************************************************/
    
}
