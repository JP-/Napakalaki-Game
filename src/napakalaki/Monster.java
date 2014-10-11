/******************************************************************/
// Clase Monster
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/


package napakalaki;

public class Monster implements Card{
    
    /******************************************************************/
    
    private String name;
    private int combatLevel;
    private Prize price;
    private BadStuff bc;
    private int levelChangeAgainstCultistPlayer;
    
    
    /******************************************************************/    
    
    // Constructor.
    public Monster(String name, int level, BadStuff bc, Prize price)
    {
        this.name = name;
        this.combatLevel = level;
        this.bc = bc;
        this.price = price;
	this.levelChangeAgainstCultistPlayer = 0;
    }    
    
    /******************************************************************/  
    
    // Constructor.
    public Monster(String name, int level, BadStuff bc, Prize price, int lc)
    {
        this.name = name;
        this.combatLevel = level;
        this.bc = bc;
        this.price = price;
	this.levelChangeAgainstCultistPlayer = lc;
    }
    
    /******************************************************************/
    
    // Devuelve el nombre del monstruo.
    public String getName()
    {
        return this.name;
    }
    
    
    /******************************************************************/
    
    // Devuelve el nivel del monstruo para combatir.
    public int getCombatLevel()
    {
        return this.combatLevel;
    }
    
    
    /******************************************************************/
    
    // Devuelve el mal rollo del monstruo.
    public BadStuff getBadStuff()
    {
        return this.bc;
    }
    
    
    /******************************************************************/
    
    // Devuelve el premio del monstruo.
    public Prize getPrize()
    {
        return this.price;
    }
    
    
    /******************************************************************/
    
    // Devuelve los niveles que se ganan tras ganar al monstruo.
    public int getLevelsGained()
    {
	return price.getLevel();
    }    
    
    
    /******************************************************************/
    
    // Devuelve los tesoros que se ganan tras ganar al monstruo.
    public int getTreasuresGained()
    {
	return price.getTreasure();
    }    
    
    
    /******************************************************************/
    
    // toString de Monster
    public String toString(){
        
        return "Name: " + name + " Combat Level: " + 
                Integer.toString(combatLevel) + "\nPrize: \n" + price.toString()
                + "\nBud Stuff: \n" + bc.toString();
    }
    
    
    /******************************************************************/
    
    // Devuelve true si su mal rollo es la muerte del jugador.
    public Boolean kills()
    {
        return bc.myBadStuffIsDeath();
    }    
    
    
    /******************************************************************/
    
    // Devuelve el nivel básico para combatir.
    public int getBasicValue()
    {
	return this.getCombatLevel();	
    }    
    
    
    /******************************************************************/
    
    // Devuelve el nivel especial para combatir.
    public int getSpecialValue()
    {
	return this.getCombatLevel() + this.levelChangeAgainstCultistPlayer;
    }    
    
    
    /******************************************************************/

}
