package ScoreFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import PlayPanel.PlayPanel;
public class ScoreFrame extends JFrame{
		public static ScoreFrame sf;
		public static FileIO fIO;
		public PlayPanel playpanel;
		
		public ScoreFrame(){
			setSize(800,550);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setUndecorated(true);
			setVisible(true);
			
			Dimension frameSize = getSize();
			Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();		
			setLocation((windowSize.width - frameSize.width) / 2,
					(windowSize.height - frameSize.height) / 2);
			
			String npPath="images/ScoreFrame/NorthPanel/";
			
			NorthPanel north=new NorthPanel(npPath,"backgorund",800,60);
			CenterPanel center=new CenterPanel();
			
			add(center,BorderLayout.CENTER);
			add(north,BorderLayout.NORTH);

			fIO=new FileIO(playpanel);
			
		}
		
		public ScoreFrame(PlayPanel p){
			this();
			playpanel=p;
		}
	
		public static void main(String[] args){
			sf=new ScoreFrame();
		
		}
}

