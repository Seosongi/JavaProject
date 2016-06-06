package MakeUserFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Graphics.GlobalGraphic;
import Graphics.GraphicButton;
import Graphics.GraphicRadioButton;
import MainFrame.MainFrame;

public class MakeUserFrame extends JFrame{
	ChoiceCharacterPanel choiceChracterPa;
	UserInputPanel userInputPa;
	SubmitPanel submitPa;
	
	JTextField userInput;
	ButtonGroup chracterGroup;
	Window f=this;	
	
	
	public MakeUserFrame(){
		setSize(400,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//크기 고정
		setUndecorated(true);
		this.getContentPane().setBackground(GlobalGraphic.basic);
		setVisible(true);
		
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);

	
		setLayout(null);
		
		choiceChracterPa = new ChoiceCharacterPanel();
		userInputPa = new UserInputPanel();
		submitPa = new SubmitPanel();
		
		
		choiceChracterPa.setLocation(0, 50);
		choiceChracterPa.setSize(400,150);
		
		userInputPa.setLocation(0,200);
		userInputPa.setSize(400,50);
		
		submitPa.setLocation(0,250);
		submitPa.setSize(400,50);
		
		add(choiceChracterPa);
		add(userInputPa);
		add(submitPa);
	}
	

		
	class ChoiceCharacterPanel extends JPanel{
		JLabel choicela;
		GraphicRadioButton[] choiceBtn;
	
			
		ChoiceCharacterPanel(){
			this.setSize(400, 250);
			
			setBackground(null);				
						
			makeBtn();
				
		}
			
		void makeBtn(){
			chracterGroup=new ButtonGroup(); 
			
			String path="images/MakeUserFrame/";			
			choiceBtn=new GraphicRadioButton[3];
			choiceBtn[0] =new GraphicRadioButton(path,"MuziBtn",100,100);
			choiceBtn[1] =new GraphicRadioButton(path,"LyanBtn",100,100);
			choiceBtn[2] =new GraphicRadioButton(path,"ApeachBtn",100,100);
			
			for(int i=0; i<3; i++){
				choiceBtn[i].addActionListener( new ChoiceActionListener());
			}
		
			for(int i=0; i<3; i++){
				chracterGroup.add(choiceBtn[i]);
				add(choiceBtn[i]);
			}
		}
		
		class ChoiceActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				GraphicRadioButton btn=(GraphicRadioButton)e.getSource();
				
			}
		}
	}
		
	class UserInputPanel extends JPanel{
		UserInputPanel(){
			setLayout(new FlowLayout());
			setBackground(null);			
			userInput= new JTextField("",15);
			add(userInput);
		}
	}
	
	class SubmitPanel extends JPanel{
		GraphicButton[] submitBtn;
			
		SubmitPanel(){
			setLayout(null);
			setBackground(null);
			makeBtn();
			
			for(int i=0; i<2; i++){
				submitBtn[i].setLocation(90+(i*120),0);
				add(submitBtn[i]);
			}
		}
		
		void makeBtn(){
			String path="images/MakeUserFrame/";
			submitBtn=new GraphicButton[2];
			submitBtn[0] =new GraphicButton(path,"SubmitBtn",100,35);
			submitBtn[1] =new GraphicButton(path,"ConcealBtn",100,35);
			
			
			for(int i=0; i<2;i++){
				submitBtn[i].addActionListener(new SubmitActionListener());
			}
		}
		
		class SubmitActionListener implements ActionListener{
			String chracter;
			public void actionPerformed(ActionEvent e) {
				GraphicButton btn=(GraphicButton)e.getSource();
				if(btn.getFILENAME().equals("SubmitBtn")){
					
					String CHARCTERNAME = null;
					Enumeration<AbstractButton> enums = chracterGroup.getElements();
					while(enums.hasMoreElements()){
						 GraphicRadioButton radiobtn=(GraphicRadioButton)enums.nextElement(); 
						 if(radiobtn.isSelected())    
							 CHARCTERNAME=radiobtn.getFILENAME();
					}
				
				
//					switch(CHARCTERNAME){
//						case "MuziBtn": chracter="MUZI"; break;
//						case "LyanBtn": chracter="LYAN"; break;
//						case "ApeachBtn": chracter="APEACH"; break;
//					}
					
					if(CHARCTERNAME.equals("MuziBtn"))
						chracter="MUZI"; 
					else if(CHARCTERNAME.equals("LyanBtn"))
						chracter="LYAN";
					else if(CHARCTERNAME.equals("ApeachBtn"))
						chracter="APEACH"; 
					
					try {
						writeUser();
						MainFrame.mf.startFra.userListPa.readUser();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}				
				}
				f.dispose();
			}
			
			public void writeUser() throws IOException{				
				BufferedWriter out = new BufferedWriter(new FileWriter("resources/User.txt",true));
				String user=userInput.getText();				
			    out.write(chracter + "\t" + user+"\t");
			    out.newLine();
			   	  
			    out.close();
			}	
			
		}	
					
	}

	public GraphicButton GraphicButton(String string) {
		// TODO Auto-generated method stub
		return null;
	}			
}
