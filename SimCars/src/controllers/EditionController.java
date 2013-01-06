package controllers;

import models.EditionModel;
import EditionView.EditionView;

public class EditionController {
	
	protected EditionModel editionModel;
	protected EditionView editionView;
	
	public EditionController() {
		editionModel = new EditionModel();
		editionView = new EditionView(this);
	}
	
	public EditionModel getEditionModel() {
		return this.editionModel;
	}
	
	public EditionView getEditionView() {
		return this.editionView;
	}
	
	public void dropItem(int type, int ligne, int colonne) {
		if(this.editionModel.validateItem(type, ligne, colonne)) {
			this.editionView.addItem(type, ligne, colonne);
		}
		//else afficher erreur?
	}
}
