package ScoreFrame;

public class UserInfo {
	private String character;
	private String name;
	private Integer score;
	private Integer level;
	
	
	UserInfo(){ };
	public UserInfo(String ch,String name,Integer score,Integer level){
		this.character=ch;
		this.name=name;
		this.score=score;
		this.level=level;
		
	}
	
	public String getCharacter(){//캐릭터 타입 리턴
		return character;
	}
	
	public String getName(){//이름 리턴
		return name;
	}
	
	public Integer getScore(){//점수 리턴
		return score;
	}
	public Integer getLevel(){
		return level;
	}

}
