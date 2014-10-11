/******************************************************************/
// Clase Player
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/


package napakalaki;

import java.util.ArrayList;


public class Player 
{
    
    /******************************************************************/
    
    private Boolean dead = true;
    private String name;
    private int level;
    
    private ArrayList<Treasure> hiddenTreasures = new ArrayList();
    private ArrayList<Treasure> visibleTreasures = new ArrayList();
    private BadStuff pendingBadStuff = null;
    
    private String character = null;
    
    
    
    /******************************************************************/    
    
    // Constructor, inicia el nombre y el rol.
    public Player (String name, String c)
    {
		this.name = name;
		this.level = 1;
		this.character = c;

		this.initTreasures();
    } 
    
    
    /******************************************************************/
    
    // Decuelve el rol que tiene el jugador.
    public String getCharacter()
    {
	return this.character;
    }
    
    /******************************************************************/  
    
    // Devuelve el nombre que tiene el jugador.
    public String getName()
    {
		return name;
    }
    
    /******************************************************************/  
    
    // Devuelve a la vida al jugador.
    private void bringToLife()
    {
        this.dead = false;
    }
    
    
    /******************************************************************/
    
    // Devuelve un entero que es el nivel de combate que tiene el jugador
    // para luchar contra el monstruo.
    public int getCombatLevel()
    {
	Boolean collar = false;
	int nivel = level;
	for(Treasure neck:this.visibleTreasures)
	{
	    if (neck.getType().equals(TreasureKind.NECKLACE))
		collar = true;	    
	}
	
	
	for(Treasure tesoro:this.visibleTreasures)
	{
	    if(collar)
		nivel += tesoro.getMaxBonus();
	    else 
		nivel += tesoro.getMinBonus();			
	}
	
	return nivel;
    }
    
        
    /******************************************************************/
    
    // Incrementa el nivel del jugador.
    private void incrementLevels(int i)
    {      
        this.level += i;
    }
    
        
    /******************************************************************/
    
    // Decrementa el nivel del jugador.
    private void decrementLevels(int i)            
    {
	
        if((this.level -= i) < 1)
	    level = 1;
    }
    
    
    /******************************************************************/
    
    // Aplica el mal rollo que tiene el jugador tras luchar frente el monstruo.
    private void setPendingBadStuff(BadStuff b)
    {
        this.pendingBadStuff = b;
    }
    
    
    /******************************************************************/
    
    // Mata al jugador. Pone su nivel a 1 e inicia sus tesoros.
    private void die()
    {
	this.level = 1;
	
	CardDealer dealer = CardDealer.getInstance();
	
	for(Treasure tesoro:visibleTreasures)
	{
	    dealer.giveTreasureBack(tesoro);
	}
	visibleTreasures.clear();
	    
	for(Treasure tesoro:hiddenTreasures)
	{
	    dealer.giveTreasureBack(tesoro);
	}
	hiddenTreasures.clear();
	
	this.dieIfNoTreasures();    
    }
    
    
    /******************************************************************/
    
    // Descarta el tesoro collar si este es visible. Solo se puede 
    // usar un turno.
    private void discardNecklaceIfVisible()
    {
	Treasure collar = null;
	Boolean found = false;
	for(Treasure neck:this.visibleTreasures)
	{
	    if (neck.getType().equals(TreasureKind.NECKLACE))
            {
		found = true;
                collar = neck;
            }
	}    
	
	if(found)
	{
	    CardDealer dealer = CardDealer.getInstance();
	    dealer.giveTreasureBack(collar);
	    visibleTreasures.remove(collar);
	}
    }
    
    
    /******************************************************************/
    
    // El jugador muere cuando no tiene tesoros.
    private void dieIfNoTreasures()
    {
        if(visibleTreasures.isEmpty() && hiddenTreasures.isEmpty())
            dead = true;
    }
    
    
    /******************************************************************/
    
    // Caclcula el número de monedas de oro que tiene el jugador.
    protected int computeGoldCoinsValue(ArrayList<Treasure> t)
    {
        int n_coins = 0;
        for(Treasure tesoro:t)
        {
            n_coins += tesoro.getGoldCoins();
        }
        
        return n_coins;
    }
    
    
    /******************************************************************/
    
    // Devuelve true si el jugador puede comprar niveles.
    // No puede si con ello gana la partida.
    private Boolean canIBuyLevels(int l)
    {
        if(level+l < 10)
            return true;
        return false;
    }
    
    
    /******************************************************************/    
    
    // Aplica el premio al jugador tras enfrentarse al monstruo y ganar.
    public void applyPrize(Monster currentMonster)
    {
        this.incrementLevels(currentMonster.getLevelsGained());
        int nTreasures = currentMonster.getTreasuresGained();
        
        if(nTreasures > 0 && this.hiddenTreasures.size()<5)
        {
            CardDealer dealer = CardDealer.getInstance();
            for(int i=1; i<=nTreasures; i++)
            {
                Treasure treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);          
            }
        }
           
    }
    
    
    /******************************************************************/ 
    
    // Devuelve true si el jugador está muerto.
    public Boolean isDead()
    {
	return dead;
    }
    
    
    /******************************************************************/
    
    // Aplica el mal rollo al jugador. Actualiza sus tesoros.
    public void applyBadStuff(BadStuff bad)
    {
        this.decrementLevels(bad.getLevels());
        this.setPendingBadStuff(bad.adjustToFitTreasureLists(visibleTreasures, 
                hiddenTreasures));
    }
    
    
    /******************************************************************/
    
    // Devuelve los tesoros ocultos del jugador.
    public ArrayList<Treasure> getHiddenTreasures()
    {
	return hiddenTreasures;
    }
    
    
    /******************************************************************/
    
    // Devuelve los tesoros visibles del jugador.
    public ArrayList<Treasure> getVisibleTreasures()
    {
	return visibleTreasures;
    }
    
    
    /******************************************************************/    
    
    // Simula el combate entre el jugador y el monstruo y devuelve el resultado.
    public CombatResult combat(Monster m)
    {
	int myLevel = getCombatLevel();
	int monsterLevel = this.getOponentLevel(m);
	CombatResult combatResult;
	
	if(myLevel>monsterLevel)
	{
	    this.applyPrize(m);
	    if(level>=10)
		combatResult = CombatResult.WinAndWinGame;
	    else
		combatResult = CombatResult.Win;
	}
	else
	{
	    Dice dice = Dice.getInstance();
	    int scape = dice.nextNumber("Lose", "Can you scape?");
	    
	    if(scape<5)
	    {
		Boolean amIDead = m.kills();
		if(amIDead)
		{
		    this.die();
		    combatResult = CombatResult.LoseAndDie;
		}
		else if(this.shouldConvert())
		{
		    combatResult = CombatResult.LoseAndConvert;
		}
		else
		{
		    combatResult = CombatResult.Lose;
		    BadStuff bad = m.getBadStuff();
		    this.applyBadStuff(bad);
		}		
	    }
	    else
		combatResult = CombatResult.LoseAndScape;	    
	}
	this.discardNecklaceIfVisible();
	
	return combatResult;	
    }    
    
    
    /******************************************************************/
    
    // Hace el tesoro que se le pasa como argumento visible.
    public void makeTreasureVisible(Treasure t)
    {
        if(this.canMakeTreasureVisible(t))
        {
            this.visibleTreasures.add(t);
            this.hiddenTreasures.remove(t);
        }
    }
    
    
    /******************************************************************/
    
    // Devuelve true si el tesoro se puede hacer visible.
    public Boolean canMakeTreasureVisible(Treasure t)
    {
	Boolean ican = false;

	if(t.getType()==TreasureKind.BOTHHAND ){
	    if(howManyVisibleTreasures(TreasureKind.ONEHAND)==0 && 
		    howManyVisibleTreasures(TreasureKind.BOTHHAND)==0)
		ican=true;

	}else if(t.getType()==TreasureKind.ONEHAND){
	    if(howManyVisibleTreasures(TreasureKind.BOTHHAND)==0 &&
		    howManyVisibleTreasures(TreasureKind.ONEHAND)<2)
		ican=true;

	}else if(howManyVisibleTreasures(t.getType())==0){
	    ican = true;
	}
	return ican;	
    }
    
    
    /******************************************************************/
    
    // Devuelve el número de tesoros visibles de un tipo dado por "tKind".
    private int howManyVisibleTreasures(TreasureKind tKind)
    {
        int n_tesoros = 0;
        for(Treasure tesoro:this.visibleTreasures)
	{
	    if(tesoro.getType().equals(tKind))
		n_tesoros++;		
	}
        return n_tesoros;
    }

    /******************************************************************/
    
    // Descarta el tesoro visible que se le pasa como argumento.
    public void discardVisibleTreasure(Treasure t)
    {
	this.visibleTreasures.remove(t);
	if(pendingBadStuff!=null && !pendingBadStuff.isEmpty())
	{
	    this.pendingBadStuff.substractVisibleTreasure(t);
	}
	
	this.dieIfNoTreasures();
    }
    
    
    /******************************************************************/
    
    // DEscarta el tesoro oculto que se le pasa como argumento.
    public void discardHiddenTreasure(Treasure t)
    {
	this.hiddenTreasures.remove(t);
	if(pendingBadStuff!=null && !pendingBadStuff.isEmpty())
	{
	    this.pendingBadStuff.substractHiddenTreasure(t);
	}
	
	this.dieIfNoTreasures();
    }
    
    
    /******************************************************************/
    
    // Compra niveles para el jugador dados los tesoros. Se suman a los niveles
    // que ya tenía.
    public Boolean buyLevels(ArrayList<Treasure> visible,
            ArrayList<Treasure> hidden)
    {
	
   	int levelsMayBought = this.computeGoldCoinsValue(visible)/1000;
	levelsMayBought += this.computeGoldCoinsValue(hidden)/1000;
	
	Boolean canI = this.canIBuyLevels(levelsMayBought);
	
	if(canI)
	{
	    this.incrementLevels(levelsMayBought);
	}
	
	CardDealer dealer = CardDealer.getInstance();
	
	this.visibleTreasures.removeAll(visible);
	this.hiddenTreasures.removeAll(hidden);
	
	
	for(Treasure tesoro:visible)
	{
	    dealer.giveTreasureBack(tesoro);
	}
	
	for(Treasure tesoro:hidden)
	{
	    dealer.giveTreasureBack(tesoro);
	}  
	
	return canI;    
    }
    
    
    /******************************************************************/
    
    // Devuelve true si el estado del jugador es correcto antes de pasar
    // el turno.
    public Boolean validState()
    {
	if(this.pendingBadStuff == null && this.hiddenTreasures.size()<5)
	    return true;
	
        if(this.pendingBadStuff.isEmpty() && this.hiddenTreasures.size()<5)
            return true;
	else
	    return false;            
    }
    
    
    /******************************************************************/
    
    // Inicia los tesoros del jugador.
    public void initTreasures()
    {
	CardDealer dealer = CardDealer.getInstance();
	
	Dice dice = Dice.getInstance();
	
	this.bringToLife();
	this.hiddenTreasures.add(dealer.nextTreasure());
	
	int number = dice.nextNumber(this.name, "Init treasures");
	
	if(number>1)
	{
	    this.hiddenTreasures.add(dealer.nextTreasure());    
	}
	if(number==6)
	{
	    this.hiddenTreasures.add(dealer.nextTreasure());    
	}    
    }
    
    
    /******************************************************************/
    
    // Devuelve true si el jugador tiene tesoros visibles.
    public Boolean hasVisibleTreasures()
    {
        if(!this.visibleTreasures.isEmpty())
            return true;
        return false;
    }
    
    
    /******************************************************************/
    
    // Devuelve los niveles que tiene el jugador.
    public int getLevels()
    {
	return level;	
    }
    
    
    /******************************************************************/
    
    // Constructor de copia.
    public Player(Player p)
    {
	this.dead = p.dead;
	this.hiddenTreasures = p.hiddenTreasures;
	this.level = p.level;
	this.name = p.name;
	this.pendingBadStuff = p.pendingBadStuff;
	this.visibleTreasures = p.visibleTreasures;
    }
    
    /******************************************************************/
    
    // Devuelve el nivel del monstruo oponente.
    public int getOponentLevel(Monster m)
    {
	return m.getBasicValue();
    }
    
    
    /******************************************************************/
    
    // Devuelve true si el jugador puede convertirse en Cultist tras perder.
    public Boolean shouldConvert()
    {
	Dice dice = Dice.getInstance();
	
	if (dice.nextNumber("Lose", "Can you convert?") == 6)
	    return true;
	
	return false;
    }
    
    
    /******************************************************************/
    
    // Devuelve el mal rollo pendiente.
    public BadStuff getPendingBadStuff()
    {
	return this.pendingBadStuff;
    }
    
    /******************************************************************/
    
    
    
    
    // EXAMEN
    
    
    
    Boolean recibirRegalo(Treasure t)
    {	
	this.hiddenTreasures.add(t);	
	
	return true;
    }
    
    
    
    
    // FIN EXAMEN
    
    
    
    
    
    
    // EXAMEN SEPTIEMBRE
    
    
    public Boolean beginTurn()
    {
	Boolean ok = true;
	Dice dice = Dice.getInstance();
	
	int number = dice.nextNumber(this.name, this.name + " si sacas un 1 pierdes turno.");
	
	if(number == 1)
	{
	    ok = false;
	} 
	
	return ok;
    }
    
    // FIN EXAMEN 
    
   

}
