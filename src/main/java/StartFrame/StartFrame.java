package StartFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import MainFrame.Interface;
import MainFrame.MainFrame;

public class StartFrame extends JFrame{
	public static MainFrame mf;
	private JPanel inputUserPanel;
	private ChoiceLevelTable choiceLevelTable;
	private Window c=this;
	
	private JButton start;
	private JTable choiceTable;
	private JTextField userInput;
		
	public StartFrame(){
		
		setSize(300,400);
		setResizable(false);
		setVisible(true);
		setBackground(Color.WHITE);
		
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);

		createInputUserPanel();
		choiceLevelTable=new ChoiceLevelTable();
		JScrollPane scroll=new JScrollPane(choiceLevelTable);			
		add(scroll);
		
		start=new JButton("GAME START!!!!!");
		
		add(inputUserPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(start,BorderLayout.SOUTH);
		
		start.addActionListener(new startActionListener());
		
	}
	
	private void createInputUserPanel(){
	
		inputUserPanel = new JPanel();
		inputUserPanel.setPreferredSize(new Dimension(100,60));
		inputUserPanel.setLayout(new FlowLayout());
		inputUserPanel.setBackground(new Color(240,240,255));

		Border inputUserPanelBorder= BorderFactory.createEtchedBorder();
		inputUserPanelBorder=BorderFactory.createTitledBorder("USER");
		inputUserPanel.setBorder(inputUserPanelBorder);
		
		userInput= new JTextField("",15);
		inputUserPanel.add(userInput);
	}	
	
	
	class ChoiceLevelTable extends JTable{
		
		ChoiceLevelTable(){
			this.setSelectionMode(1);
			String[] header={"LEVEL", "SPEED"};
			DefaultTableModel model= new DefaultTableModel(header,0);
			setModel(model);
						
			//WordList에 모든 단어 add
			for(Integer level=1; level<=30;level++){
				String content[]=new String[2];
				content[0]=level.toString();
				content[1]=((Integer)(level*5)).toString();
				model.addRow(content);
			}
			
			setCenter();
		}
		
		void setCenter(){
			// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
			DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			 
			// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			 
			// 정렬할 테이블의 ColumnModel을 가져옴
			TableColumnModel tcmSchedule = getColumnModel();
			 
			// 반복문을 이용하여 테이블을 가운데 정렬로 지정
			for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
		}
		
		//더블클릭수정불가
		public boolean isCellEditable(int i, int c){
			return false;
		}
	}
	
	class startActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {			
					
			String user=userInput.getText();
			
			int rows[]=choiceLevelTable.getSelectedRows();
			
			if(user.equals("")){
				JOptionPane.showMessageDialog(null,"이름을 입력해주세요!","경고",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if(rows.length!=1){
				JOptionPane.showMessageDialog(null,"하나의 레벨을 선택해주세요!","경고",JOptionPane.WARNING_MESSAGE);
				return;
			}	
			
			int row=rows[0];
			String level= (String) choiceLevelTable.getValueAt(row,0);
			System.out.println(level);
			Interface.setUser(user);
			Interface.setLevel(Integer.parseInt(level));
			Interface.setSpeed((double)(Integer.parseInt(level)*5));
			
			c.setVisible(false);
			c.dispose();
			mf=new MainFrame();
		}
		
	}
	
	public static void main(String[] args){
		new StartFrame();		
	}
}
