/******************************************************************/
// Clase Napakalaki
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/


package napakalaki;

import java.util.ArrayList;
import java.util.Random;

public class Napakalaki {
    
    /******************************************************************/
    
    private static Napakalaki instance = new Napakalaki();
    
    
    private ArrayList<Player> players = new ArrayList();
    private Player currentPlayer = null;
    private Monster currentMonster = null;
    private CardDealer dealer = CardDealer.getInstance();
    
    
    
    /******************************************************************/
    
    // El constructor privado asegura que no se puede instanciar
    // desde otras clases
    private Napakalaki() { }
    
    
    /******************************************************************/
    
    // Devuelve la única instancia que existe de la clase Napakalaki
    // Clase singleton
    public static Napakalaki getInstance()
    {	
	return instance;
    }
    
    
    /******************************************************************/    
    
    // Inicia los jugadores, con su nombre y rol asignado.
    private void initPlayers(ArrayList<String> names, ArrayList<String> characters)
    {
	for(int i=0; i<names.size(); i++)
	{
	    Player p = new Player(names.get(i), characters.get(i));
	    this.players.add(p);
	}
		
    }
    
    
    /******************************************************************/
    
    // Siguiente jugador que le toca jugar. Si es el primero se hace 
    // aleatoriamente. Si no se sigue el orden de iniciacialización.
    private Player nextPlayer()
    {	
	
	int indice;
	Random rnd = new Random();

	if (this.currentPlayer == null)
		indice = rnd.nextInt(players.size());

	else
	{
		indice = players.indexOf(currentPlayer);
		indice = (indice+1)%players.size();
	}

	return players.get(indice);
    }
    
    
    /******************************************************************/
    
    // Devuelve true si se puede pasar al siguiente turno. Si el jugador 
    // que estaba jugando ha terminado su turno.
    private Boolean nextTurnAllowed() 
    {
	if(currentPlayer == null)
		return true;

	return currentPlayer.validState();
    }
    
    
    /******************************************************************/    
    
    // Simula el combate entre el jugador y el monstruo. Devuelce el resultado
    // de este.
    public CombatResult developCombat()
    {	
	CombatResult combatResult = currentPlayer.combat(currentMonster);
	dealer.giveMonsterBack(currentMonster);

	if (combatResult == CombatResult.LoseAndConvert)
	{
	    Cultist c = dealer.nextCultist();
	    CultistPlayer cp = new CultistPlayer(currentPlayer, c);

	    int index = this.players.indexOf(currentPlayer);

	    players.remove(index);
	    players.add(index, cp);

	    currentPlayer = cp;
	}
	return combatResult;	
    }
    
    
    /******************************************************************/
    
    // Descarta los tesoros visibles que se le pasan como argumento.
    public void discardVisibleTreasures(ArrayList<Treasure> treasures)
    {
	for(Treasure tesoro:treasures)
	{
		currentPlayer.discardVisibleTreasure(tesoro);
		dealer.giveTreasureBack(tesoro);	    
	}
    
    }
    
    
    /******************************************************************/
    
    // Descarta los tesoros ocultos que se le pasan como argumento.
    public void discardHiddenTreasures(ArrayList<Treasure> treasures)
    {
	for(Treasure tesoro:treasures)
	{
		currentPlayer.discardHiddenTreasure(tesoro);
		dealer.giveTreasureBack(tesoro);	    
	}
    }
    
    
    /******************************************************************/
    
    // Hace los tesoros que se le pasan como argumento visibles.
    public void makeTreasuresVisible(ArrayList<Treasure> treasures)
    {
        for(Treasure t:treasures)
        {            
	    if( this.currentPlayer.canMakeTreasureVisible(t) )
		currentPlayer.makeTreasureVisible(t);               
        }
            
    }
    
    
    /******************************************************************/
    
    // Suma niveles al jugador que compra con los tesoros que se le pasa.
    public Boolean buyLevels(ArrayList<Treasure> visible,
	    ArrayList<Treasure> hidden) 
    {
    	return currentPlayer.buyLevels(visible, hidden);    
    }
    
    
    /******************************************************************/
    
    // Inicia el juego. Inicia las cartas y los jugadores y calcula el 
    // jugador que inicia la partida.
    public void initGame(ArrayList<String> names, ArrayList<String> characters) 
    {
        dealer.initCards();
        this.initPlayers(names, characters);
        this.nextTurn();    
    }
    
    
    /******************************************************************/
    
    // Devuelve el jugador del que es el turno.
    public Player getCurrentPlayer()
    {
	return currentPlayer;
    }
    
    
    /******************************************************************/
    
    // Devuelve el monstruo que se está usando ahora mismo.
    public Monster getCurrentMonster()
    {
	return currentMonster;
    }
        
    
    /******************************************************************/
    
    // Pasa al siguiente turno. Otro monstruo y otro jugador. Devuelve
    // true si se ha realizado con éxito.
    public Boolean nextTurn() 
    {
	Boolean stateOK = this.nextTurnAllowed();

	if(stateOK)
	{
	    currentMonster = this.dealer.nextMonster();
	    currentPlayer = this.nextPlayer();

	    if(currentPlayer.isDead())
		currentPlayer.initTreasures();
	}

	return stateOK;    
    }
     
    
    /******************************************************************/
    
    // Devuelve true si es el final del juego.
    public Boolean endOfGame(CombatResult result) 
    {
	return result.equals(CombatResult.WinAndWinGame);
    }   
    
    
    /******************************************************************/
    
    
    
    
    // EXAMEN
    
    
    public String regalarTesoro(ArrayList<Treasure> tesoros)
    {
	
	Player p_menor = players.get(0);
	
	for (Player p:players)
	{
	    if(p.getLevels() < p_menor.getLevels())
		p_menor = p;	    
	}
	
	Treasure regalo = tesoros.get(0);
	for(Treasure t:tesoros)
	    currentPlayer.discardHiddenTreasure(t);
	
	if (p_menor.recibirRegalo(regalo)){	    
	    return "Gracias por el regalo";
	}
	
	return "Mi secta no me permite aceptar regalos";	
    }
    
    
    
    // FIN EXAMEN
    
}