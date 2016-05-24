package MainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import MainFrame.CenterPanel.CenterPanel;
import MainFrame.EastPanel.EastPanel;
import MainFrame.NorthPanel.NorthPanel;
import MainFrame.SouthPanel.SouthPanel;
import MainFrame.WestPanel.WestPanel;
import MyDictionary.MyDictionary;

public class MainFrame extends JFrame{
	private Window c= this;
	
	public EastPanel ep;
	public NorthPanel np;
	public CenterPanel cp;
	public SouthPanel sp;
	public WestPanel wp;
	
	
	public MainFrame(){	
	
		setTitle("Typing Trainer with English!");
		setSize(800,550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new  ExitListener());
		setVisible(true);
		setResizable(false);//크기 고정
		setBackground(Color.WHITE);
		
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);	
		
		//MyDictionay 초기화 - WORDLIST.txt모든 단어 입력
		try {MyDictionary.readWordFromFile();	} 
		catch (IOException e) {e.printStackTrace();}
	
		
		createMenuBar();
		
		sp= new SouthPanel();
		ep= new EastPanel();
		np= new NorthPanel();
		cp= new CenterPanel();		
		wp= new WestPanel();
		
		Container c = getContentPane();		
		c.add(sp,BorderLayout.SOUTH);
		c.add(ep,BorderLayout.EAST);
		c.add(np,BorderLayout.NORTH);
		c.add(cp,BorderLayout.CENTER);		
		c.add(wp,BorderLayout.WEST);
		
	}
	
void createMenuBar(){
		
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenu helpMenu=new JMenu("Help");
		JMenuItem regame=new JMenuItem("Regame");
		JMenuItem pause=new JMenuItem("Pause");
		JMenuItem open=new JMenuItem("열기");
		JMenuItem save=new JMenuItem("저장");
		JMenuItem exit=new JMenuItem("exit");
		JMenuItem version=new JMenuItem("Version");
		JMenuItem developer=new JMenuItem("Developer");
		
		//파일 메뉴 생성
		fileMenu.add(regame);
		fileMenu.add(pause);
		//fileMenu.add(open);
		//fileMenu.add(save);
		fileMenu.addSeparator();//구분선 추가
		fileMenu.add(exit);
						
		//파일 메뉴 단축키 설정
		regame.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_MASK));
		pause.setAccelerator(KeyStroke.getKeyStroke('P',InputEvent.CTRL_MASK));
		//open.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_MASK));
		//save.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke('X',InputEvent.CTRL_MASK));
		
		//add Listener
		regame.addActionListener(new FileActionListener());
		version.addActionListener(new HelpActionListener());
		developer.addActionListener(new HelpActionListener());
						
		//도움 메뉴 생성
		helpMenu.add(version);
		helpMenu.add(developer);
				
		//메뉴를 메뉴바에 등록
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
				
		//메뉴바 추가
		setJMenuBar(menuBar);
	}
	
	class FileActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JMenuItem item= (JMenuItem) e.getSource();
			String s=item.getText();
			
			if(s.equals("Regame"))
				{

				JFrame confirm =new JFrame();
				String message ="다시하시겠습니까?";
				String regame="Regame";
				Interface.setPlay(false);
		
				int answer=JOptionPane.showConfirmDialog(confirm, message,regame,JOptionPane.YES_NO_OPTION);
				
				if(answer==JOptionPane.YES_OPTION){ 
					StartFrame.StartFrame.mf.dispose();//기존창 닫기
					new StartFrame.StartFrame();
					Interface.setPlay(true);
					
					}
				else if(answer==JOptionPane.NO_OPTION){ 
					Interface.setPlay(true); 
					
					}
				}
		}
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
	
	class ExitListener implements WindowListener{
		 public void windowClosing(WindowEvent e){
			 try {	MyDictionary.writeWordFromFile();} 
			 catch (IOException e1) {e1.printStackTrace();}
			
			 System.exit(0);
		 }

		 public void windowOpened(WindowEvent e){}
		 public void windowActivated(WindowEvent e){}
		 public void windowDeactivated(WindowEvent e){}
		 public void windowClosed(WindowEvent e){}
		 public void windowIconified(WindowEvent e){}
		 public void windowDeiconified(WindowEvent e){}
	}
}
