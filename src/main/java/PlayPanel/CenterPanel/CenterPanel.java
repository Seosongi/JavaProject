package PlayPanel.CenterPanel;

import java.util.Vector;

import Graphics.GlobalGraphic;
import Graphics.GraphicPanel;
import PlayPanel.PlayPanel;

public class CenterPanel extends GraphicPanel{
	public HeartGagePanel heartGagePa;	
	private Vector<FallWordLabel> fallWordLabelArray;	
	public PlayPanel playpanel;
	
	public CenterPanel(String path, String FILENAME, int width, int height,PlayPanel p){
		super(path,FILENAME,width,height);
		setLayout(null);
				
		fallWordLabelArray= new Vector<FallWordLabel>();
	
		heartGagePa = new HeartGagePanel(path,"HeartGageGra",200,30,playpanel);
		heartGagePa.setLocation(150,10);
		add(heartGagePa);
		
		this.playpanel=p;
			
	}
	
	public Vector<FallWordLabel> getLabelArray(){return fallWordLabelArray;}
	
	public void clearLabel(){
		for(int index=0; index<fallWordLabelArray.size();index++){
			fallWordLabelArray.get(index).setVisible(false);
			fallWordLabelArray.get(index).setValid(false);
		}
		
		fallWordLabelArray.removeAllElements();	
	}
		
}
