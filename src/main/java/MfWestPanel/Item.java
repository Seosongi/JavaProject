package MfWestPanel;

import GameInterface.BasicInterface;
import GameInterface.MyDictionary;
import MfCenterPanel.FallWordLabelArray;
import MfEastPanel.SuccessWordPanel;

public abstract class Item{
	private boolean enable;
	
	Item(){	enable=false;}	
	public abstract void call();	
	public boolean getEnable(){	return enable;}	//상태값 리턴
	public void setEnable(boolean enable){	this.enable=enable;	}//상태값 세팅
}

class Item1 extends Item{
	public void call() {
		//떨어지는 모든 단어를 보이지 않게  && 무효하게
		for(int index=0; index<FallWordLabelArray.getNumOfLabel();index++){
			FallWordLabelArray.get(index).setVisible(false);
			FallWordLabelArray.get(index).setValid(false);
		}
				
		//떨어지는 모든 단어를 성공 단어에 추가
		for(int index=0; index<FallWordLabelArray.getNumOfLabel();index++){
			//case 1: 한글 입력상태에서 아이템 사용
			//case 2: 영문 입력상테에서 아이템 사용
			String text=FallWordLabelArray.getText(index);
			String renderText=MyDictionary.render(text);
					
			//case2의 경우 - 모든 떨어지는 라벨 중 하나의 라벨은 영어를 가지고있음
			if(renderText==null){
				renderText=text;
				text=MyDictionary.renderReverse(renderText);
			}
			
			SuccessWordPanel.successWordTable.add(text,renderText);
		}
				
		//모든 떨어지는 라벨 제거
		FallWordLabelArray.removeAll();
				
		//item1 사용 불가 상태로
		setEnable(false);
	}
}

class Item2 extends Item{
	public void call() {
	}
}

class Item3 extends Item{
	public void call() {
		BasicInterface.speed-=10;
		//item3 사용 불가 상태로
		setEnable(false);
	}
}

class Item4 extends Item{
	public void call() {
	}	
}