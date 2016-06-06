package MainFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Graphics.GraphicButton;
import MakeUserFrame.MakeUserFrame;
import PlayPanel.PlayPanel;
import StartFrame.StartFrame;


public class MainFrame extends JFrame{
	public static MainFrame mf;
	public MainPagePanel mainPa;
	public PlayPanel playPa;
	public StartFrame startFra;
	public MakeUserFrame makeUserFra;
	
	
	MainFrame(){
		setSize(800,550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//�겕湲� 怨좎젙
		setUndecorated(true);
		setVisible(true);
		
		//com.sun.awt.AWTutilities.setWindowShape(this,new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),20,20));
		
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		
		mainPa=new MainPagePanel();
		this.setContentPane(mainPa);
		
		
		createMenuBar();
		
	}
	
	
	void createMenuBar(){
		
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenu helpMenu=new JMenu("Help");
		JMenuItem regame=new JMenuItem("Regame");
		JMenuItem exit=new JMenuItem("exit");
		JMenuItem version=new JMenuItem("Version");
		JMenuItem developer=new JMenuItem("Developer");
		
		menuBar.setPreferredSize(new Dimension(800,30));
		menuBar.setBackground(Color.LIGHT_GRAY);
		//�뙆�씪 硫붾돱 �깮�꽦
		fileMenu.add(regame);
	
		fileMenu.addSeparator();//援щ텇�꽑 異붽�
		fileMenu.add(exit);
						
		//�뙆�씪 硫붾돱 �떒異뺥궎 �꽕�젙
		regame.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke('X',InputEvent.CTRL_MASK));
		
		//add Listener
		version.addActionListener(new HelpActionListener());
		developer.addActionListener(new HelpActionListener());
						
		//�룄�� 硫붾돱 �깮�꽦
		helpMenu.add(version);
		helpMenu.add(developer);
				
		//硫붾돱瑜� 硫붾돱諛붿뿉 �벑濡�
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
				
		//硫붾돱諛� 異붽�
		setJMenuBar(menuBar);
	}
	
	class HelpActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JMenuItem item= (JMenuItem) e.getSource();
			String s=item.getText();
			if(s.equals("Version"))
				JOptionPane.showMessageDialog(null, "version. 1.00\n2016.05.23","Version",JOptionPane.INFORMATION_MESSAGE);
			else if(s.equals("Developer"))
				JOptionPane.showMessageDialog(null, "Hansung.Univ\nComputer Engneering\n\nLee Changoo / Seo Songi","Developer",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class MainPagePanel extends JPanel{
			MainPagePanel(){
			setLayout(null);
			MakeButton();
		}
		
		void MakeButton(){
			String path="images/MainFrame/MainPage/";
			
			GraphicButton btn[] = new GraphicButton[4];
			btn[0]=new GraphicButton(path,"Startbtn",100,35);
			btn[1]=new GraphicButton(path,"WordSetbtn",100,35);
			btn[2]=new GraphicButton(path,"Helpbtn",100,35);
			btn[3]=new GraphicButton(path,"Exitbtn",100,35);
			
			for(int i=0; i<4; i++){
				btn[i].addActionListener(new MenuAction());
			}
			
			for(int i=0; i<4; i++){
				btn[i].setLocation(330, 280+(i*40));
				add(btn[i]);
			}		
		}
				
		class MenuAction implements ActionListener {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				GraphicButton btn=(GraphicButton)e.getSource();

				if(btn.getFILENAME().equals("Startbtn"))startFra=new StartFrame();
				else if(btn.getFILENAME().equals("WordSetBtn"));
				else if(btn.getFILENAME().equals("Help"));
				else if(btn.getFILENAME().equals("Exitbtn"))System.exit(0);
				
				
//				switch(btn.getFILENAME()){
//				case "Startbtn": startFra=new StartFrame(); break;
//				case "WordSetBtn": break;
//				case "Help": break;
//				case "Exitbtn": System.exit(0);break; 
//				}
				
			}
		}
				
		public void paintComponent(Graphics g) {
			ImageIcon background = new ImageIcon("images/MainFrame/MainPage/Background.png");	
			g.drawImage(background.getImage(), 0, 0, null);	
			setOpaque(false);
			super.paintComponent(g);
		}	
	}		
	
	public static void main(String[] args){
	
		mf =new MainFrame();
	}
}
