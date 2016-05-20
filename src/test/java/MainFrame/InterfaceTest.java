package MainFrame;

import static org.junit.Assert.*;

import org.junit.Test;

public class InterfaceTest {
	
	@Test
	public void getPlayTest() {
		assertEquals(Interface.getPlay(),false);
	}
	
	@Test
	public void getTurnTest() {
		assertEquals(Interface.getTurn(),true);
	}
	
	@Test
	public void getLevelTest() {
		Integer L1=1;
		assertEquals(Interface.getLevel(),L1);
	}

	@Test
	public void getSpeedTest() {
		Double L1=5.0;
		assertEquals(Interface.getSpeed(),L1);
	}
	
	@Test
	public void getGameTryTest() {
		Integer L1=1;
		assertEquals(Interface.getGameTry(),L1);
	}
	
	@Test
	public void getUserTest() {
		Interface.setUser("서송이");
		assertEquals(Interface.getUser(),"서송이");
	}
	
	@Test
	public void setPlayTest() {
		Interface.setPlay(false);
		assertEquals(Interface.getPlay(),false);
	}
	
	@Test
	public void setLebelTest() {
		Interface.setLevel(5);
		Integer L1=5;
		assertEquals(Interface.getLevel(),L1);
	}
	
	@Test
	public void setSpeedTest() {
		Interface.setSpeed(5.0);
		Double L1=5.0;
		assertEquals(Interface.getSpeed(),L1);
	}
	
	@Test
	public void setScoreTest() {
		Interface.setScore(5);
		Integer L1=5;
		assertEquals(Interface.getScore(),L1);
	}
	
	@Test
	public void setGameTryTest() {
		Interface.setGameTry(5);
		Integer L1=5;
		assertEquals(Interface.getGameTry(),L1);
	}
	
	@Test
	public void setUserTest() {
		Interface.setUser("이찬구");
		assertEquals(Interface.getUser(),"이찬구");
	}
	
	@Test
	public void speedUpTest() {
		Interface.speedUp(5.0);
		Double L1=10.0;
		assertEquals(Interface.getSpeed(),L1);
	}
	
	@Test
	public void setKoreanTurnTest() {
		Interface.setKoreanTurn();
		assertEquals(Interface.getTurn(),true);
	}
	
	@Test
	public void setEnglishTurnTest() {
		Interface.setEnglishTurn();
		assertEquals(Interface.getTurn(),true);
	}
}
