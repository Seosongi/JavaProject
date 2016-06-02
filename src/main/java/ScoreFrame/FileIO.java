package ScoreFrame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;
import ScoreFrame.UserInfo;
import javax.swing.JLabel;

import Interface.GameSet;

	public class FileIO {
		
		public static List<UserInfo> Users = new ArrayList<UserInfo>();
	
		FileIO() {
			
			try {
				readPlayer();
				sortGrade();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	
		//이긴 사람의 점수를 텍스트 파일에 저장하기 기능 없음
		void readPlayer() throws IOException{
			
			BufferedReader in = new BufferedReader(new FileReader("Score.txt"));

			int i=0;
			String s;
				  
			while ((s = in.readLine()) != null) {
				String[] split = s.split("\t");
		
				Users.add(new UserInfo(split[0],split[1],Integer.parseInt(split[2])));
				i++;
				
			}

			in.close();
		
		}
		
		void sortGrade() throws IOException{//점수 순서대로 정렬 
			Collections.sort(Users, new NoDescCompare());
		}
	
		static class NoDescCompare implements Comparator<UserInfo> {
			public int compare(UserInfo arg0, UserInfo arg1) {
				return arg0.getScore() > arg1.getScore() ? -1 : arg0.getScore() < arg1.getScore() ? 1:0;
			}
		}
		
}