/******************************************************************/
// Clase CardDealer
//
// (C) Antonio Calderón Cortiñas
// (C) Francisco Rafael Checa Molina
// (C) Juan Pablo Porcel Porcel
/******************************************************************/



package napakalaki;

import java.util.ArrayList;
import java.util.Collections;

public class CardDealer {
    
    /******************************************************************/
    
    private static CardDealer instance = new CardDealer();
    
    private ArrayList<Monster> unusedMonsters = new ArrayList();
    private ArrayList<Monster> usedMonsters = new ArrayList();
    private ArrayList<Treasure> unusedTreasures = new ArrayList();
    private ArrayList<Treasure> usedTreasures = new ArrayList();
    private ArrayList<Cultist> unusedCultists = new ArrayList();
    
    
    /******************************************************************/
    
    // El constructor privado asegura que no se puede instanciar
    // desde otras clases
    private CardDealer() { }

    // Devuelve la única instancia que existe de la clase Dealer.
    // Clase singleton
    public static CardDealer getInstance()
    {	
	return instance;
    }
    
    
    /******************************************************************/    
    
    // Inicia las cartas de tesoros.
    private void initTreasureCardDeck()
    {
        Treasure t=new Treasure("Si mi amo",0,4,7,TreasureKind.HELMET);
        unusedTreasures.add(t);
        
        t=new Treasure("Botas de investigacion",600,3,4,TreasureKind.SHOE);
        unusedTreasures.add(t);
        
        t=new Treasure("Capucha de Cthulhu",500,3,5,TreasureKind.HELMET);
        unusedTreasures.add(t);
        
        t=new Treasure("A prueba de babas",400,2,5,TreasureKind.ARMOR);
        unusedTreasures.add(t);
        
        t=new Treasure("Botas de lluvia acida",800,1,1,TreasureKind.SHOE);
        unusedTreasures.add(t);
        
        t=new Treasure("Casco minero",400,2,4,TreasureKind.HELMET);
        unusedTreasures.add(t);
        
        t=new Treasure("Ametralladora Thompson",600,4,8,TreasureKind.BOTHHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Camiseta de la UGR",100,1,7,TreasureKind.ARMOR);
        unusedTreasures.add(t);
        
        t=new Treasure("Clavo de rail ferroviario",400,3,6,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
	
	t=new Treasure("Cuchillo de sushi arcano",300,2,3,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Fez alopodo",700,3,5,TreasureKind.HELMET);
        unusedTreasures.add(t);
        
        t=new Treasure("Hacha prehistorica",500,2,5,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("El aparato del Pr. Tesla",900,4,8,TreasureKind.ARMOR);
        unusedTreasures.add(t);
        
        t=new Treasure("Gaita",500,4,5,TreasureKind.BOTHHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Insecticida",300,2,3,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Escopeta de 3 canones",700,4,6,TreasureKind.BOTHHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Garabato mistico",300,2,2,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("La fuerza de Mr.T",1000,0,0,TreasureKind.NECKLACE);
        unusedTreasures.add(t);
        
        t=new Treasure("La rebeca metalica",400,2,3,TreasureKind.ARMOR);
        unusedTreasures.add(t);
        
        t=new Treasure("Mazo de los antiguos",200,3,4,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Necro-playboycon",300,3,5,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Lanzallamas",800,4,8,TreasureKind.BOTHHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Necro-comicon",100,1,1,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Necronomicon",800,5,7,TreasureKind.BOTHHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Linterna a 2 manos",400,3,6,TreasureKind.BOTHHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Necro-gnomicon",200,2,4,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Necrotelecom",300,2,3,TreasureKind.HELMET);
        unusedTreasures.add(t);
        
        t=new Treasure("Porra preternatural",200,2,3,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Tentaculo de pega",200,0,1,TreasureKind.HELMET);
        unusedTreasures.add(t);
        
        t=new Treasure("Zapato deja-amigos",500,0,1,TreasureKind.SHOE);
        unusedTreasures.add(t);
        
        t=new Treasure("Shogulador",600,1,1,TreasureKind.BOTHHAND);
        unusedTreasures.add(t);
        
        t=new Treasure("Varita de atizamiento",400,3,4,TreasureKind.ONEHAND);
        unusedTreasures.add(t);
    }
    
    
    /******************************************************************/
    
    // Inicia las cartas de monstruos.
    private void initMonsterCardDeck()
    {
        Prize p;
        BadStuff bs;
        ArrayList<TreasureKind> tvp, top;
        
        // Monstruo 1 -- Byakhees de bonanza
        
        tvp = new ArrayList();
            tvp.add(TreasureKind.ARMOR);
        top = new ArrayList();
            top.add(TreasureKind.ARMOR);
        p = new Prize(2,1);
        bs = new BadStuff("Pierdes tu armadura visible y otra oculta", 0,
                tvp, top);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, bs, p));
        
        
        
        // Monstruo 2 -- Chibithulhu
        
        tvp = new ArrayList();
            tvp.add(TreasureKind.HELMET);
        top = new ArrayList();
        p = new Prize(1,1);
        bs = new BadStuff("Embobados con el lindo primigenio te descartas "
                + "de tu casco visible", 0, tvp, top);
        unusedMonsters.add(new Monster("Chibithulhu", 2, bs, p));
        
        
        
        // Monstruo 3 -- El sopor de Dunwich
        
        tvp = new ArrayList();
            tvp.add(TreasureKind.SHOE);
        top = new ArrayList();
        p = new Prize(1,1);
        bs = new BadStuff("El primordial bostezo contagioso. "
                + "Pierdes el calzado visible", 0, tvp, top);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, bs, p));
        
        
        
        // Monstruo 4 -- Ángeles de la noche ibicenca
        
        tvp = new ArrayList();
            tvp.add(TreasureKind.ONEHAND);
        top = new ArrayList();
            top.add(TreasureKind.ONEHAND);
        bs = new BadStuff("Te atrapan para llevarte de fiesta y te "
                + "dejan caer en mitad del vuelo. Descarta 1 mano visible"
                + " y 1 mano oculta", 0, tvp, top);
        p = new Prize(4,1);
        unusedMonsters.add(new Monster("Angeles de la noche ibicenca", 14, bs, p));
       
        
        
        // Monstruo 5 -- El gorrón en el umbral
        
        tvp = new ArrayList();
        top = new ArrayList();
        bs = new BadStuff("Pierdes todos tus tesoros visibles", 0, 10, 0);
        p = new Prize(3,1);
        unusedMonsters.add(new Monster("El gorron en el umbral", 10, bs, p));
        
        
        
        // Monstruo 6 -- H.P. Munchcraft
        
        tvp = new ArrayList();
            tvp.add(TreasureKind.ARMOR);
        top = new ArrayList();
        bs = new BadStuff("Pierdes la armadura visible", 0, tvp, top);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("HP Munchcraft", 6, bs, p)); 
	
	
	//Monstruo 7
	
        tvp = new ArrayList();
            tvp.add(TreasureKind.ARMOR);
        top = new ArrayList();
	bs = new BadStuff("Sientes bichos bajo la ropa."
                + " Descarta la armadura visible",0,tvp,top);

        p = new Prize(1,1);
        unusedMonsters.add(new Monster("Bichgooth",2,bs,p)); 
        
        
        //Monstruo 8
        
        bs = new BadStuff("Pierdes 5 niveles y 3 tosoros visibles.",5,3,0);
        p = new Prize(4,2);
        unusedMonsters.add(new Monster("El rey de la rosa",13,bs,p)); 
        
        //Monstruo 9
        
        bs = new BadStuff("Toses los pulmones y pierdes 2 niveles",2,0,0);
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("La que redacta en las tinieblas",2,bs,p));
        
        //Monstruo 10
        
        bs = new BadStuff("Estos monstruos resultan bastante superficiales y"
                + " te aburren mortalmente. Estás muerto",true);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Los hondos",8,bs,p));
        
        //Monstruo 11
        
        bs = new BadStuff("Pierdes 2 niveles y dos tesoros ocultos",2,0,2);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Semillas Cthulhu",4,bs,p));
        
        //Monstruo 12
        
        tvp = new ArrayList();
        tvp.add(TreasureKind.ONEHAND);
        top = new ArrayList();
        bs = new BadStuff("Te intentas escaquear."
                + " Pierdes una mano visible",0,tvp,top);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Dameargo",1,bs,p));
	
	//Monstruo 13
        
        p = new Prize(1,1);
        bs = new BadStuff("Da mucho asquito. Pierdes 3 niveles.",3,0,0);
        unusedMonsters.add(new Monster("Pollipolipo volante",3,bs,p));
        
        //Monstruo 14
        
        p = new Prize(3,1);
        bs = new BadStuff("No le hace gracia que pronuncien mal su nombre. Estas muerto.",true);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth",12,bs,p));
        
        //Monstruo 15
        
        p = new Prize(4,1);
        bs = new BadStuff("La familia te atrapa. Estas muerto", true);
        unusedMonsters.add(new Monster("Familia feliz",1,bs,p));
        
        //Monstruo 16
        
        p = new Prize(2,1);
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.BOTHHAND);
        bs = new BadStuff("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro" +  
                          " y 2 manos visibles.",2,tvp,top);
        unusedMonsters.add(new Monster("Roboggoth",8,bs,p));
        
        //Monstruo 17
        
        p = new Prize(1,1);
        tvp = new ArrayList();
        top = new ArrayList();
        tvp.add(TreasureKind.HELMET);
        bs = new BadStuff("Te asusta en la noche. Pierdes un casco visible.",0,tvp,top);
        unusedMonsters.add(new Monster("El espia",5,bs,p));
        
        //Monstruo 18
        
        p = new Prize(1,1);
        bs = new BadStuff("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.",2,5,0);
        unusedMonsters.add(new Monster("El lenguas",20,bs,p));       
        
        
        // Monstruo 19 -- Bicéfalo
        
        tvp = new ArrayList();
            tvp.add(TreasureKind.ONEHAND);
            tvp.add(TreasureKind.BOTHHAND);
        top = new ArrayList();
        bs = new BadStuff("Te faltan manos para tanta cabeza. Pierdes "
                + "3 niveles y tus tesoros visibles de las manos", 3, tvp, top);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Bicefalo", 20, bs, p));
	
	
	// Monstruo 20
        
        tvp = new ArrayList();
	    tvp.add(TreasureKind.ONEHAND);
        top = new ArrayList();
        bs = new BadStuff("Pierdes 1 mano visible", 0, tvp, top);
        p = new Prize(3,1);
        unusedMonsters.add(new Monster("El mal indecible impronunciable",
		10, bs, p, -2));
	
	
	// Monstruo 21
        
        bs = new BadStuff("Pierdes tus tesoros visibles. Jajaja", 0, 10, 0);
        p = new Prize(2,1);
        unusedMonsters.add(new Monster("Testigos oculares", 6, bs, p, +2));
	
	
	// Monstruo 22 -- El gran cthulhu
        
        bs = new BadStuff("Hoy no es tu dia de suerte", true);
        p = new Prize(2,5);
        unusedMonsters.add(new Monster("El gran cthulhu", 6, bs, p, +4));
	
	
	// Monstruo 23 -- Serpiente Politico
        
        p = new Prize(2,1);
        bs = new BadStuff("Tu gobierno te recorta 2 niveles.",2,0,0);
        unusedMonsters.add(new Monster("Serpiente politico",8,bs,p,-2));
        
	
	// Monstruo 24 -- Felpuggoth
        
        tvp = new ArrayList();
	    tvp.add(TreasureKind.HELMET);
	    tvp.add(TreasureKind.ARMOR);
        top = new ArrayList();
	    top.add(TreasureKind.BOTHHAND);
        p = new Prize(1,1);
        bs = new BadStuff("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas",0,tvp,top);
        unusedMonsters.add(new Monster("Felpuggoth",2,bs,p,+2));

		
	// Monstruo 25 -- Shoggoth

        bs = new BadStuff("Pierdes dos niveles", 2, 0, 0);
        p = new Prize(4,2);
        unusedMonsters.add(new Monster("Shoggoth", 16, bs, p, -4));

	
	// Monstruo 26 -- Lolitagooth

        bs = new BadStuff("Pintalabios negro. Pierdes 2 niveles", 2, 0, 0);
        p = new Prize(1,1);
        unusedMonsters.add(new Monster("Lolitagooth", 2, bs, p, 3));
	
    }
    
    
    /******************************************************************/
    
    // Baraja el montón de tesoros.
    private void shuffleTreasures()
    {
        Collections.shuffle(unusedTreasures);
    }
    
    
    /******************************************************************/
    
    // Baraja el montón de monstruos.
    private void shuffleMonsters()
    {
        Collections.shuffle(unusedMonsters);
    }
    
    
    /******************************************************************/
    
    // Devuelve el siguiente tesoro del montón.
    public Treasure nextTreasure()
    {
        if(unusedTreasures.isEmpty())
        {
	    unusedTreasures.addAll(usedTreasures);
            usedTreasures.clear();
            shuffleTreasures();
        }
        Treasure temp = unusedTreasures.get(0);
        unusedTreasures.remove(0);
        return temp;
    }
    
    
    /******************************************************************/
    
    // Devuelve el siguiente monstruo del montón.
    public Monster nextMonster()
    {
        if(unusedMonsters.isEmpty())
        {
            unusedMonsters.addAll(usedMonsters);
            usedTreasures.clear();
            shuffleMonsters();
        }
        Monster temp = unusedMonsters.get(0);
        unusedMonsters.remove(0);
        return temp;
    }
    
    
    /******************************************************************/
    
    // Añade al montón de tesoros usados el tesoro que se le pasa.
    public void giveTreasureBack(Treasure t)
    {
        usedTreasures.add(t);
    }
    
    
    /******************************************************************/
    
    // Devuelve al montón de monstruos usados el monstruo que se le pasa.
    public void giveMonsterBack(Monster m)
    {
        usedMonsters.add(m);
    }
    
    
    /******************************************************************/
    
    // Inicia las cartas.
    public void initCards()
    {
        this.initMonsterCardDeck();
	this.shuffleMonsters();
        this.initTreasureCardDeck();
	this.shuffleTreasures();
	this.initCultistCardDeck();
	this.shuffleCultist();
    }
    
    
    /******************************************************************/
    
    // Baraja el montón de cultistas.
    public void shuffleCultist()
    {
	Collections.shuffle(unusedCultists);
    }
    
    
    /******************************************************************/
    
    // Inicia las cartas de cultistas.
    public void  initCultistCardDeck()
    {
	Cultist temp = new Cultist("Sectario1", 1);
	this.unusedCultists.add(temp);
	
	temp = new Cultist("Sectario2", 2);
	this.unusedCultists.add(temp);
	
	temp = new Cultist("Sectario3", 1);
	this.unusedCultists.add(temp);
	
	temp = new Cultist("Sectario4", 2);
	this.unusedCultists.add(temp);
	
	temp = new Cultist("Sectario5", 1);
	this.unusedCultists.add(temp);
	
	temp = new Cultist("Sectario6", 1);
	this.unusedCultists.add(temp);	
    }
    
    
    /******************************************************************/
    
    // Devuelve la siguiente carta de cultista del montón.
    public Cultist nextCultist()
    {
	Cultist temp = unusedCultists.get(0);
        unusedCultists.remove(0);
        return temp;
    }
    
    
    /******************************************************************/    
    
    
    
}


