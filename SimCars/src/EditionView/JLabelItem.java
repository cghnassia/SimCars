package EditionView;

import javax.swing.JLabel;

public class JLabelItem extends JLabel {
	
	protected int type;
	
	public JLabelItem(int pType) {
		super();
		this.type = pType;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int pType) {
		this.type = pType;
	}
}
