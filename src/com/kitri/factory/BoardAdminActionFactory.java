package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.action.admin.board.*;

public class BoardAdminActionFactory {
	//com.kitri.admin.board package 에 있는 것들
	private static Action boardMenuAction;
	private static Action makeBoardAction;
	private static Action makeCategoryAction;
	
	//생성될때마다x 최초의 한번만
	static {
		//Cannot make a static reference to the non-static field boardMenuAction
		//그냥 만들면 이런 오류가 난다 > non static
		boardMenuAction= BoardMenuAction.getBoardMenuAction();
		makeBoardAction= MakeBoardAction.getMakeBoardAction();
		makeCategoryAction= MakeCategoryAction.getMakeCategoryAction();
	}

	public static Action getBoardMenuAction() {
		return boardMenuAction;
	}

	public static Action getMakeBoardAction() {
		return makeBoardAction;
	}

	public static Action getMakeCategoryAction() {
		return makeCategoryAction;
	}
}
