package config;

import java.awt.Color;
import java.awt.Font;

import vue.FenetrePrincipale;

public final class Config {
	
	static public final int largeurEcran = 1200;//1200
	static public final int hauteurEcran = 700;//700
	//static public final Color couleurDeFond = Color.LIGHT_GRAY;
	static public final Color couleurDeFond = Color.black;
	static public final Font font = new Font("Courier New", Font.BOLD, 16);
	static public FenetrePrincipale fp = null;
	static public final String ACTION_RETOURPRINCIPALE= "RETOURE_PRINCIPALE";
	static public final String ACTION_LANCERSIMUCOURSE= "LANCER_SIMU_COURSE";
	static public final String ACTION_QUITTER_SIMU= "QUITTER_SIMU";
	static public final String ACTION_ENREGISTRER_RES= "ENREGISTRER_RES";
	
	
	static public final int ACTION_LANCERSAISON = 1;
	static public final int ACTION_LANCERUNECOURSE= 2;
	static public final int ACTION_EDITER= 3;
	static public final int ACTION_INFOVOITURE= 4;
	static public final int ACTION_INFOLOGICIEL= 5;
	
	
	
	
	
	public static void setF(FenetrePrincipale f)
	{
		fp = f;
	}
	
	private Config()
	{
		
	}
}
