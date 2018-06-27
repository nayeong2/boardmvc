package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.action.admin.board.*;

public class BoardAdminActionFactory {
	//com.kitri.admin.board package �� �ִ� �͵�
	private static Action boardMenuAction;
	private static Action makeBoardAction;
	private static Action makeCategoryAction;
	
	//�����ɶ�����x ������ �ѹ���
	static {
		//Cannot make a static reference to the non-static field boardMenuAction
		//�׳� ����� �̷� ������ ���� > non static
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
