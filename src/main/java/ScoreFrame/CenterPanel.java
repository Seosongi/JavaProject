package ScoreFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Graphics.GameFontB;
import Graphics.GameFontP;
import Graphics.GlobalGraphic;
import Graphics.GraphicPanel;
import PlayPanel.PlayPanel;



class CenterPanel extends JPanel{
	String path="images/ScoreFrame/CenterPanel/";
	public PlayPanel p;
	
		CenterPanel(PlayPanel p){
			this.p=p;
			setVisible(true);
		
			setBackground(GlobalGraphic.character);
			
			setLayout(null);
			setPreferredSize(new Dimension(500,420));
			
			GradePanel gp=new GradePanel(path,"frame",390,410,this);
			MyGradePanel mgp=new MyGradePanel(path,"myScore",320,290,this);
			add(gp);
			add(mgp);
			
			String myCharacter="APEACH"; //캐릭터 이미지
			ImageIcon myChimage = new ImageIcon(path+myCharacter+"스코어.gif");
			JLabel myChLabel=new JLabel(myChimage);
			myChLabel.setLocation(630, 300);
			myChLabel.setSize(130,130);
			
			add(myChLabel);
		}
		
		
	class GradePanel extends GraphicPanel{
			
			int num=4;//전체 화면에 표시할 등수 표시 갯수
			
			public GradePanel(String path, String FILENAME, int width, int height,CenterPanel p ){
				super(path,FILENAME,width,height);
				
				setVisible(true);
				setBackground(Color.white);
				
				setLayout(null);
				
				setGrade();
				
				setLocation(30,30);
				
			}
		
			void setGrade(){
			
			if(ScoreFrame.fIO.getUsersSize()<4)
				num=ScoreFrame.fIO.getUsersSize();
				
			ImageIcon images[]=new ImageIcon[num];
			ImageIcon gradeImg []=new ImageIcon[num];
			
			JLabel faceLabel[]=new JLabel[num];
			JLabel scores[]=new JLabel[num];
			JLabel gradeLabel[]=new JLabel[num];
			JLabel nameLabel[]=new JLabel[num];
			
			String name=" ";//이름 저장
			String faceType=" ";//캐릭터 타입 저장
		
				
				for(int i=0;i<num;i++){
				
				gradeImg[i]=new ImageIcon(path+"트로피.png");
				gradeLabel[i]=new JLabel(gradeImg[i]);
				
				name=ScoreFrame.sf.fIO.Users.get(i).getName();
				
				faceType=ScoreFrame.sf.fIO.Users.get(i).getCharacter();
				images[i]=new ImageIcon(path+faceType+"Face.png");//faceType 별로 이모티콘 분류
				faceLabel[i]=new JLabel(images[i]);
				
				System.out.println(faceType);
			
				scores[i]=new JLabel(ScoreFrame.sf.fIO.Users.get(i).getScore().toString());
				scores[i].setSize(100,100);
				scores[i].setLocation(300, i*100);
				scores[i].setFont(new GameFontB(15));

				faceLabel[i].setSize(100,100);
				faceLabel[i].setLocation(10,i*100);
				
				gradeLabel[i].setSize(100,100);
				gradeLabel[i].setLocation(85,i*100);
			
				nameLabel[i]=new JLabel(name);
				nameLabel[i].setSize(100,100);
				nameLabel[i].setLocation(180, i*100);
				nameLabel[i].setFont(new GameFontB(15));
			
				add(faceLabel[i]);
				add(gradeLabel[i]);
				add(scores[i]);
				add(nameLabel[i]);
				}
			}
		}//GradePanel end
	
	class MyGradePanel  extends GraphicPanel{
	
		MyGradePanel(String path, String FILENAME, int width, int height,CenterPanel p ){
			super(path,FILENAME,width,height);
			
			setVisible(true);
			setBackground(Color.white);

			setLayout(new GridLayout(3,1));
			
			setLocation(450,30);
		
			setMyGrade();
			
			
			
		}
		
		public void setMyGrade(){
		
			/*	스레드 문제 - Exception in thread "main" java.lang.NullPointerException 
			 * playpanel 의 값을 받아올수 없음 
			 * String myCharacter = p.getCharacter();
			Integer myScore=p.getScore();
			String myName=p.getName();
			Integer myLevel=p.getLavel();
			*/
			
			Integer myScore=new Integer(300); //스코어점수
			JLabel myScoreLabel=new JLabel(myScore.toString());
			setGradeLabel(myScoreLabel,250,60,50);
		
			String myName="s"; //캐릭터 이름 + 레벨
			JLabel myNameLabel=new JLabel("userName : "+myName);
			setGradeLabel(myNameLabel,150,50,20);
			
			String mylevel="1"; //캐릭터 이름 + 레벨
			JLabel myLevelLabel=new JLabel("userlevel : "+mylevel);
			setGradeLabel(myLevelLabel,100,50,20);
			
			
		

		}
		public void setGradeLabel(JLabel source,int x,int y,int fontSize){
			source.setSize(x,y);
			source.setForeground(GlobalGraphic.character);
			source.setHorizontalAlignment(JLabel.CENTER);
			source.setVerticalAlignment(JLabel.CENTER);
			source.setFont(new GameFontP(fontSize));
			add(source);
		}
	}//MyGradePanel end
	
}
	

