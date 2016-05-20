package MainFrame.EastPanel;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuccessWordPanelTest {

	@Test
	public void newTest(){
		new SuccessWordPanel();
	}

	@Test
	public void addTest(){
	
		SuccessWordPanel S1 = new SuccessWordPanel();
		String [] content={"테스트","Test"};
		S1.successWordTable.getModel().insertRow(0, content);
		
		SuccessWordPanel S2 = new SuccessWordPanel();
		S2.successWordTable.add("테스트","Test");

		assertEquals(S1.successWordTable.getModel().getValueAt(0, 0),
				S2.successWordTable.getModel().getValueAt(0, 0));
		assertEquals(S1.successWordTable.getModel().getValueAt(0, 1),
				S2.successWordTable.getModel().getValueAt(0, 1));
		
	}

}
