/******************************************************************/
// Clase CultistPlayer
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/


package napakalaki;

import java.util.ArrayList;


public class CultistPlayer extends Player
{
    
    /******************************************************************/
    
    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
	   
    
    
    /******************************************************************/
    
    // Constructor.
    public CultistPlayer (Player p, Cultist c)
    {
	super(p);
	this.myCultistCard = c;
	totalCultistPlayers++;	
    }
    
    /******************************************************************/
    
    // Devuelve el número de cultistas en la partida.
    public static int getTotalCultistPlayers()
    {
	return totalCultistPlayers;
    }
    
    
    /******************************************************************/
    
    // Devuelve el nivel del monstruo oponente.
    public int getOponentLevel(Monster m)
    {
	return m.getSpecialValue();
    }
    
    
    /******************************************************************/
    
    // Devuelve el nivel para combatir.
    public int getCombatLevel()
    {
	return super.getCombatLevel() + this.myCultistCard.getSpecialValue();
    }
    
    
    /******************************************************************/
    
    // Devuelve false. No se puede convertir porque ya es Cultist.
    public Boolean shouldConvert()
    {
	return false;
    }
    
    
    /******************************************************************/
    
    // Calcula las monedas de oro que tiene.
    public int computeGoldCoinsValue(ArrayList<Treasure> t)
    {
	return super.computeGoldCoinsValue(t)*2;
    }
    
 
    /******************************************************************/
    
    // Devuelve la carta de cultista.
    public Cultist getCultistCard()
    {
	return this.myCultistCard;
    }
    
    
    
    
    
    // EXAMEN
    
    
    Boolean recibirRegalo(Treasure t)
    {	
	return false;
    }
    
    
    
    // FIN EXAMEN
    
    
    
    
    // EXAMEN SEPTIEMBRE
    
    
    public Boolean beginTurn()
    {
	return true;
    }
    
    // FIN EXAMEN 
}
