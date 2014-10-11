/******************************************************************/
// Clase Treasure
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/


package napakalaki;

public class Treasure implements Card 
{
    
    /******************************************************************/
    
    private String name;
    private int goldCoins;
    private int minBonus;
    private int maxBonus;
    private TreasureKind type;
    
    
    
    /******************************************************************/
    
    // Constructor.
    public Treasure(String n, int g, int min, int max, TreasureKind t)
    {
	name = n;
	goldCoins = g;
	minBonus = min;
	maxBonus = max;
	type = t;
    }
    
    
    /******************************************************************/
    
    // Devuelve el nombre del tesoro.
    public String getName() 
    {
	return name;
    }
    
    
    /******************************************************************/
    
    // Devuelve el número de monedas de oro que tiene asignadas.
    public int getGoldCoins()
    {
	return goldCoins;
    }
    
    
    /******************************************************************/
    
    // Devuelve el mínimo bonus que se puede aplicar.
    public int getMinBonus()
    {
	return minBonus;
    }
    
    
    /******************************************************************/
    
    // Devuelve el máximo bonus que se puede aplicar.
    public int getMaxBonus()
    {
	return maxBonus;
    }
    
    
    /******************************************************************/
    
    // Devuelve el tipo de tesoro.
    public TreasureKind getType()
    {
	return type;
    }
    
    
    /******************************************************************/
    
    // Devuelve el valor básico.
    public int getBasicValue()
    {
	return this.getMinBonus();
    }
    
    
    /******************************************************************/
    
    // Devuelve el valor especial.
    public int getSpecialValue()
    {
	return this.getMaxBonus();
    }
    
    
    /******************************************************************/
	    
}
