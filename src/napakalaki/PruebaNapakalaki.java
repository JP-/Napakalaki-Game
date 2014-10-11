/******************************************************************/
/*
 * Project: Napakalaki (Java)
 * 
 * Grupo: 2
 * 
 * (C) Antonio Calderón Cortiñas
 * (C) Francisco Rafael Checa Molina
 * (C) Juan Pablo Porcel Porcel
 */
/******************************************************************/


package napakalaki;

import GUI.NapakalakiView;
import GUI.PlayerNamesCapture;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PruebaNapakalaki {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException 
    {	
	
	
	
	Napakalaki napakalakiModel = Napakalaki.getInstance();
	
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<String> characters = new ArrayList<String>();
	
	NapakalakiView napakalakiView = new NapakalakiView();
	
	
	Clip sonido = AudioSystem.getClip(); 
	File a = new File("src/res/kk.wav");
	sonido.open(AudioSystem.getAudioInputStream(a));
	sonido.loop(Clip.LOOP_CONTINUOUSLY);
	
	Dice.createInstance(napakalakiView);
	
	napakalakiView.setNapakalaki(napakalakiModel);
	
	PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
	
	names = namesCapture.getNames();
	characters = namesCapture.getCharacters();
	
	napakalakiModel.initGame(names, characters);
	
	napakalakiView.begin();
	napakalakiView.showView();
	
	
	    
        
        
    }
}
