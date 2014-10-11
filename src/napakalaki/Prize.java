/******************************************************************/
// Clase Prize
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/


package napakalaki;

public class Prize {
    
    /******************************************************************/
    
    private int treasure;
    private int level;
    
    
    /******************************************************************/
    
    // Constructor.
    public Prize(int trea, int lvl)
    {
        level = lvl;
        treasure = trea; 
    }
    
    
    /******************************************************************/
    
    // Devuelve el tesoro que tiene el premio.
    public int getTreasure()
    {
        return treasure;
    }
    
    
    /******************************************************************/
    
    // Devuelve el nivel del premio.
    public int getLevel()
    {
        return level;
    }
    
    
    
    /******************************************************************/
    
    // toString de Prize.
    public String toString(){        
        return "   Treasures = " + Integer.toString(treasure) + 
                "\n   Levels = " + Integer.toString(level);
    }
    
    
    /******************************************************************/
    
}
