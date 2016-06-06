package PlayPanel.CenterPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JProgressBar;

import Graphics.GameFontB;
import Graphics.GraphicPanel;
import PlayPanel.PlayPanel;
import ScoreFrame.ScoreFrame;

public class HeartGagePanel extends GraphicPanel{
	public HeartGage heartgage=new HeartGage();
	public PlayPanel playpanel;
	HeartGagePanel(String path, String FILENAME, int width, int height,PlayPanel p){
		super(path,FILENAME,width,height);
		heartgage=new HeartGage();
		heartgage.setFont(new GameFontB(10));
		add(heartgage);
		
		this.playpanel=p;
	}
	
	public class HeartGage extends JProgressBar{
		int max=100;
		int min=0;
		Integer value=100;
		
		HeartGage(){
			setMinimum(min);
			setMaximum(max);
			setForeground(new Color(255,100,100));
			setStringPainted(true);
			setString(value.toString()+"%");
			setPreferredSize(new Dimension(120,17));
			setValue(value);
		
		}
		
		public void pain(){ //체력 감소
			value-=20;
			setValue(value);
			setString(value.toString()+"%");	
			
			if(value==0){ //체력이 없을 경우	
				{	
					new ScoreFrame(playpanel);
					return;
				}
			}
		}
		
		public void fullgain(){
			setValue(max);
			setString(100+"%");
		}
	
	}
}
