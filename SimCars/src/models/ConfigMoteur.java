package models;

public abstract class ConfigMoteur {
	public static int MOTEUR_ESSENCE_VITESSE_MAX = 50;
	public static int MOTEUR_ELECTRIQUE_VITESSE_MAX = 35;
	
	public static double MOTEUR_ESSENCE_ACCELERATION = 2;
	public static double MOTEUR_ELECTRIQUE_ACCELERATION = 3; 
	
	public static double MOTEUR_ESSENCE_CONSOMMATION = 0.035;
	public static double MOTEUR_ELECTRIQUE_CONSOMMATION = 0.025;
	public static double HYBRIDE_RECHARGE_BATTERIE = 0.015;
}
