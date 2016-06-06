package PlayPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.JPanel;

import Graphics.GlobalGraphic;
import MainFrame.MainFrame;
import MyDictionary.MyDictionary;
import PlayPanel.CenterPanel.CenterPanel;
import PlayPanel.CenterPanel.FallWordLabel;
import PlayPanel.EastPanel.EastPanel;
import PlayPanel.NorthPanel.NorthPanel;
import PlayPanel.SouthPanel.SouthPanel;
import PlayPanel.WestPanel.WestPanel;

public class PlayPanel extends JPanel{
	public EastPanel ep;
	public NorthPanel np;
	public CenterPanel cp;
	public SouthPanel sp;
	public WestPanel wp;
	
	private boolean play;
	private boolean turn; //입력 차례 (한글,영문)
	private Integer level; 
	private Double speed;
	private Integer score;
	private String character;
	private String user;
	private int count;
	
	private Vector<FallingAni> fallingAniArray;
	private FallingRun run;
	private SpeedAni speedAni;	
	
	public PlayPanel(){
		
		//MyDictionay 초기화 - WORDLIST.txt모든 단어 입력
		try {MyDictionary.readWordFromFile();	} 
		catch (IOException e) {e.printStackTrace();}
		
		
		this.setBackground(Color.WHITE);
		String npPath=GlobalGraphic.path+"NorthPanel/";
		String cpPath=GlobalGraphic.path+"CenterPanel/";	
		wp= new WestPanel();
		sp= new SouthPanel();
		ep= new EastPanel();
		np= new NorthPanel(npPath,"background",800,60);
		cp= new CenterPanel(cpPath,"background",500,420,this);
		
		this.setLayout(new BorderLayout());			
		add(sp,BorderLayout.SOUTH);
		add(ep,BorderLayout.EAST);
		add(np,BorderLayout.NORTH);
		add(cp,BorderLayout.CENTER);		
		add(wp,BorderLayout.WEST);
		
		speedAni=new SpeedAni();
		fallingAniArray=new Vector<FallingAni>();
		run=new FallingRun(); //단어 생성 시작
	}
	
	public PlayPanel(String user,String character,int level,double speed){
		this();
		this.user=user;
		this.character=character;
		this.level=level;
		this.speed=speed;
		this.score=0;
		this.play=true;
		this.turn=true; //입력 차례 (한글,영문)
		this.count=10;
		
		wp.speedPanel.setSpeedText(this.speed.toString());
		np.levelPa.setLevelText(this.level.toString());
		np.scorePa.setScoreText(this.score.toString());
		ep.infoPanel.setUserText(this.user);
		
		speedAni.start();
		run.start();
	}
	
	public boolean getTurn(){return turn;}
	public Integer getLevel(){return level;}	
	public Double getSpeed(){return speed;}	
	public Integer getScore(){return score;}	
	public String getCharacter(){return character;}	
	public String getUser(){return user;}
	public boolean getPlay(){return play;}
	
	
	public void setSpeed(Double speed){this.speed=speed;}
	public void setlevel(int level){this.level=level;}
	public void speedUp(double up){this.speed+=up;} //속도 업
	public void setKoreanTurn(){this.turn=true;} //한글 입력 차례로
	public void setEnglishTurn(){this.turn=false;} //영문 입력 차례로
	
	
	public void levelUp(){
		level++;
		np.levelPa.setLevelText(level.toString());
		cp.clearLabel();
		System.out.println("레벨업!");
	}
	
	public void scoreUp(){//점수 증가
		count--;
		score+=level*5;
		np.scorePa.setScoreText(score.toString());
		if(count==0){
			levelUp();
			count=10;
		}
	} 
	
	@SuppressWarnings("deprecation")
	public void setStop(){
		this.play=false;
		speedAni.suspend();
		run.suspend();		
		for(int i=0; i<fallingAniArray.size(); i++)
			fallingAniArray.get(i).suspend();	
	}
	
	@SuppressWarnings("deprecation")
	public void setPlay(){
		this.play=true;
		speedAni.resume();
		run.resume();		
		for(int i=0; i<fallingAniArray.size(); i++)
			fallingAniArray.get(i).resume();
	}
	
	class SpeedAni extends Thread{
		public void run(){
			
			NumberFormat numberFormat=new DecimalFormat("##.##");
			while(true){
				speedUp(0.01);
				wp.speedPanel.setSpeedText(numberFormat.format(speed));
				
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class FallingRun extends Thread{
		public void run(){
			while(true){
				FallingAni fallingAni= new FallingAni();
				fallingAniArray.add(fallingAni);
				fallingAni.start();
	
				try{
					 sleep(4000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//라벨 하나 하나 떨어지는 쓰레드
	class FallingAni extends Thread {	
		
		public void run(){
			synchronized(this){
			//좌표값 설정
			int x=(int)(Math.random()*400);
			int y=50; 
				
			//단어를 랜덤하게 받아와 라벨 생성.
			FallWordLabel la = new FallWordLabel(MyDictionary.rand());
			
			//떨어지는 라벨 배열 추가
			cp.getLabelArray().add(la);
			
			//위치 설정
			la.setLocation(x, y);
				
			//패널에 추가
			cp.add(la);
						
			//y<410까지 떨어트림
			while(y<410 && !Thread.currentThread().isInterrupted()){
				y=(int)(y+speed);
				la.setLocation(x, y);
				try {						
						sleep(1000); //떨어지는 속도
				}				
				catch (InterruptedException e) {
						e.printStackTrace();
				}					
			}
				
			if(y>=410 && la.getValid()==true){					
				MainFrame.mf.playPa.cp.heartGagePa.heartgage.pain(); //체력 감소
				cp.getLabelArray().remove(la); //배열에서 제거
				
				//영어 라벨이 다 떨어지면, 한글 차례로
				if(la.getLanguage()==false) 
					turn=true;
			}	
			
			fallingAniArray.remove(this);
			this.interrupt(); //스레드 스톱
			}
		}
	}//FallingAni 클래스 끝
}
	
	
	/*
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
*/
