package ScoreFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Graphics.GlobalGraphic;


class CenterPanel extends JPanel{
	
		CenterPanel(){
			setVisible(true);
			setBackground(GlobalGraphic.character);
			setLayout(null);
			setPreferredSize(new Dimension(500,420));
			
			GradePanel gp=new GradePanel();
			MyGradePanel mgp=new MyGradePanel();
			add(gp);
			add(mgp);
			
		}
		
	class GradePanel extends JPanel{
			String path="images/ScoreFrame/CenterPanel/";
			int num=4;//전체 화면에 표시할 등수 표시 갯수
			
			GradePanel(){
				setVisible(true);
				setBackground(Graphics.GlobalGraphic.basic);
				setLayout(null);
				
				setGrade();
				
				setSize(300,400);
				setLocation(30,30);

			}
			
			void setGrade(){
			
			ImageIcon images[]=new ImageIcon[num];
			ImageIcon gradeImg []=new ImageIcon[num];
			
			JLabel faceLabel[]=new JLabel[num];
			JLabel scores[]=new JLabel[num];
			JLabel gradeLabel[]=new JLabel[num];
			JLabel nameLabel[]=new JLabel[num];
			
			String name=" ";//이름 저장
			String faceType=" ";//캐릭터 타입 저장
		
				
				for(int i=0;i<num;i++){
				
				gradeImg[i]=new ImageIcon(path+"등수.png");
				gradeLabel[i]=new JLabel(gradeImg[i]);
				
				name=ScoreFrame.sf.fIO.Users.get(i).getName();
				
				faceType=ScoreFrame.sf.fIO.Users.get(i).getCharacter();
				images[i]=new ImageIcon(path+faceType+"Face.png");//faceType 별로 이모티콘 분류
				faceLabel[i]=new JLabel(images[i]);
				
				System.out.println(faceType);
			
				scores[i]=new JLabel(ScoreFrame.sf.fIO.Users.get(i).getScore().toString());
				scores[i].setSize(100,100);
				scores[i].setLocation(240, i*100);
				scores[i].setFont(new Graphics.GameFontB(15));

				
				faceLabel[i].setSize(100,100);
				faceLabel[i].setLocation(0,i*100);
				
				gradeLabel[i].setSize(100,100);
				gradeLabel[i].setLocation(70,i*100);
				

				nameLabel[i]=new JLabel(name);
				nameLabel[i].setSize(100,100);
				nameLabel[i].setLocation(180, i*100);
				nameLabel[i].setFont(new Graphics.GameFontB(15));
				
			
			
				add(faceLabel[i]);
				add(gradeLabel[i]);
				add(scores[i]);
				add(nameLabel[i]);
				}
			}
		}//GradePanel end
	
	class MyGradePanel extends JPanel{
		String path="images/ScoreFrame/CenterPanel/";
		
		MyGradePanel(){
			setVisible(true);
			setBackground(Color.white);
			setLayout(new BorderLayout());
			
			setSize(380,400);
			setLocation(370,50);
			
			JLabel info=new JLabel("나의 점수는?",JLabel.CENTER);
			add(info,BorderLayout.NORTH);
		}
	}//MyGradePanel end
	
}
	

