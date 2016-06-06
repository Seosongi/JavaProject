package ScoreFrame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import PlayPanel.PlayPanel;

	public class FileIO {
		public PlayPanel playpanel;
		public static List<UserInfo> Users = new ArrayList<UserInfo>();
	
		FileIO() {
			
			try {
				writeScore();
				readPlayer();
				sortGrade();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
//		void setScore() throws IOException{
//			BufferedWriter out = new BufferedWriter(new FileWriter("resources/User.txt",true));
//			out.write(MainFrame.);
//		}
		
		FileIO(PlayPanel p) {
			this();
			this.playpanel=p;
			
		}
		//이긴 사람의 점수를 텍스트 파일에 저장하기 기능 없음
		void readPlayer() throws IOException{
			
			BufferedReader in = new BufferedReader(new FileReader("resources/Users.txt"));
			
			int i=0;
			String s;
				  
			while ((s = in.readLine()) != null) {
				String[] split = s.split("\t");
				
				if(split.length>=2)
				{
					Users.add(new UserInfo(split[0],split[1],Integer.parseInt(split[2]),Integer.parseInt(split[3])));
					i++;
				}
				else 
				{
					Users.add(new UserInfo(split[0],split[1],0,0));
					i++;
				}
				
			}

			in.close();
		
		}
		
		public void writeScore() throws IOException{				
			BufferedWriter out = new BufferedWriter(new FileWriter("resources/User.txt",true));
		    out.write(playpanel.getCharacter()+ "\t" + playpanel.getUser()+"\t"+playpanel.getLevel()+"\t"+playpanel.getScore());
		    out.newLine();
		   	  
		    out.close();
		}	
		
		void sortGrade() throws IOException{//점수 순서대로 정렬 
			Collections.sort(Users, new NoDescCompare());
			System.out.printf("\n\n===== 숫자 내림 차순 정렬 =====\n");
			for (UserInfo temp : Users) {
				System.out.println(temp.getName());
			}
		}
	
		static class NoDescCompare implements Comparator<UserInfo> {
			public int compare(UserInfo arg0, UserInfo arg1) {
				return arg0.getScore() > arg1.getScore() ? -1 : arg0.getScore() < arg1.getScore() ? 1:0;
			}
		}
		
}