package StartFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.JPanel;

import Graphics.GlobalGraphic;
import Graphics.GraphicButton;
import Graphics.GraphicRadioButton;
import MainFrame.MainFrame;
import PlayPanel.PlayPanel;

public class SubmitPanel extends JPanel{
	
	String user;
	String character;	
	GraphicButton[] submitBtn;
	
	SubmitPanel(){
		setLayout(null);
		setBackground(null);
		makeBtn();
	}
	
	void makeBtn(){
		String path="images/StartFrame/";
		submitBtn=new GraphicButton[2];
		submitBtn[0] =new GraphicButton(path,"SubmitBtn",100,35);
		submitBtn[1] =new GraphicButton(path,"ConcealBtn",100,35);
		
		for(int i=0; i<2; i++){
			submitBtn[i].addActionListener(new SubmitAction());
		}
		
		for(int i=0; i<2; i++){
			submitBtn[i].setLocation(40+(i*120),0);
			add(submitBtn[i]);
		}
	}
	
	
	class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GraphicButton btn=(GraphicButton)e.getSource();
			if(btn.getFILENAME()=="SubmitBtn"){
				
				String[] spliter;
				
				String item=(String)MainFrame.mf.startFra.userListPa.getSelectedItem();
				
				spliter=item.split("\t");
				
				character=spliter[0];
				user=spliter[1];
				int level = 0;
				double speed = 0;
			
				
				String levelbtn = null;
				Enumeration<AbstractButton> enums = MainFrame.mf.startFra.levelListPa.levelGroup.getElements();
				while(enums.hasMoreElements()){
					 GraphicRadioButton radiobtn=(GraphicRadioButton)enums.nextElement(); 
					 if(radiobtn.isSelected())    
						 levelbtn=radiobtn.getFILENAME();
				}
				
//				switch(character){
//				case "MUZI.": GlobalGraphic.character=new Color(251,233,13); 
//						GlobalGraphic.path="images/MainFrame/Muzi/";
//						break;
//				case "LYAN.": GlobalGraphic.character=new Color(215,209,137);
//						GlobalGraphic.path="images/MainFrame/Lyan/";
//						break;
//				case "APEACH.": GlobalGraphic.character=new Color(247,171,171);
//						GlobalGraphic.path="images/MainFrame/Apeach/";
//						break;
//				}	
				if(character.equals("MUZI."))
				{
					GlobalGraphic.character=new Color(251,233,13); 
					GlobalGraphic.path="images/MainFrame/Muzi/";
				}
				else if(character.equals("LYAN."))
				{
					 GlobalGraphic.character=new Color(215,209,137);
					GlobalGraphic.path="images/MainFrame/Lyan/";
				}
				else if(character.equals("APEACH."))
				{
					 GlobalGraphic.character=new Color(247,171,171);
					 GlobalGraphic.path="images/MainFrame/Apeach/";
				}
					
				
//				switch(levelbtn){
//					case "levelBtn1": level=1;speed=10; break;
//					case "levelBtn2": level=5;speed=20; break;
//					case "levelBtn3": level=10;speed=30; break;
//				}
				
				if(levelbtn.equals("levelBtn1"))
				{
					 level=1;speed=10;
				}
				else if(levelbtn.equals("levelBtn1"))
				{
					level=5;speed=20; 
				}
				else if(levelbtn.equals("levelBtn3"))
				{
					level=10;speed=30;
				}
				
				MainFrame.mf.setContentPane(MainFrame.mf.playPa=new PlayPanel(user,character,level,speed));
			}
			
			else;
			MainFrame.mf.startFra.dispose();
			
		}
	}
}	