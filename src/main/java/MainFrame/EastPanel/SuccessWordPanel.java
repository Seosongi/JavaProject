package MainFrame.EastPanel;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class SuccessWordPanel extends JPanel{
	public SuccessWordTable successWordTable;
	
	SuccessWordPanel(){
		Border ListBorder= BorderFactory.createEtchedBorder();
		ListBorder=BorderFactory.createTitledBorder("Success Word");
		setBorder(ListBorder);
		setBackground(null);
		
		successWordTable=new SuccessWordTable();
		
		JScrollPane scroll=new JScrollPane(successWordTable);
		scroll.setPreferredSize(new Dimension(130,220));
		
		add(scroll);
	}
	
	public class SuccessWordTable extends JTable{
		private DefaultTableModel model;
		
		SuccessWordTable(){
			
			String[] header={"Korean","English"};
			model= new DefaultTableModel(header,0);
			this.setModel(model);
		}
		
		//테이블에 행 추가
		public void add(String korean, String english){
			String[] content=new String[2];
			content[0]=korean;
			content[1]=english;
			model.insertRow(0, content);		
		}
		
		public DefaultTableModel getModel(){
			return model;
		}
	}
}