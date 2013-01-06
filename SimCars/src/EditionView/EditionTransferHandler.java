package EditionView;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class EditionTransferHandler extends TransferHandler{

	  /**
	  * Méthode permettant à l'objet de savoir si les données reçues
	  * via un drop sont autorisées à être importées
	  * @param info
	  * @return boolean
	  */
	  public boolean canImport(TransferHandler.TransferSupport info) {
		//Nous contrôlons si les données reçues sont d'un type autorisé, ici String 		
		  if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		    return false;
		  }
		  return true;
	  }

	  /**
	  * C'est ici que l'insertion des données dans notre composant est réalisée
	  * @param support
	  * @return boolean
	  */
	  public boolean importData(TransferHandler.TransferSupport support) {
		//Nous contrôlons si les données reçues sont d'un type autorisé
		  if(!canImport(support))
		    return false;

		  //On récupère notre objet Transferable, celui qui contient les données en transit
		  Transferable data = support.getTransferable();
		  String str = "";

		  try {
			  //Nous récupérons nos données en spécifiant ce que nous attendons		
			  str = (String)data.getTransferData(DataFlavor.stringFlavor);
		  } 
		  catch (UnsupportedFlavorException e){
			  e.printStackTrace();
		  } 
		  catch (IOException e) {
			  e.printStackTrace();
		  }
					
		  //Via le TransferSupport, nous pouvons récupérer notre composant
		  JLabel lab = (JLabel)support.getComponent();
		  //Afin de lui affecter sa nouvelle valeur
		  lab.setText(str);

		  return true;
	  }

	  /**
	  * Cette méthode est invoquée à la fin de l'action DROP
	  * Si des actions sont à faire ensuite, c'est ici qu'il faudra coder le comportement désiré
	  * @param c
	  * @param t
	  * @param action
	  */
	  //protected void exportDone(JComponent c, Transferable t, int action){}

	  /**
	  * Dans cette méthode, nous allons créer l'objet utilisé par le système de drag'n drop
	  * afin de faire circuler les données entre les composants
	  * Vous pouvez voir qu'il s'agit d'un objet de type Transferable
	  * @param c
	  * @return
	  */
	 // protected Transferable createTransferable(JComponent c) {}

	  /**
	  * Cette méthode est utilisée afin de déterminer le comportement 
	  * du composant vis-à-vis du drag'n drop : nous retrouverons
	  * nos variables statiques COPY, MOVE, COPY_OR_MOVE, LINK ou NONE 
	  * @param c
	  * @return int
	  */
	  public int getSourceActions(JComponent c) {
		  return COPY;
	  }
	   
}
