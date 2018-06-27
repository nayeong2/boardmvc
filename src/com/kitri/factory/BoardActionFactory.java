package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.action.reboard.*;

public class BoardActionFactory {
	private static Action reboardWriteAction;
	private static Action reboardViewAction;
	private static Action reboardReplyAction;
	private static Action reboardListAction;
	
	static {
		reboardWriteAction= ReboardWriteAction.getReboardWriteAction();
		reboardViewAction= ReboardViewAction.getReboardViewAction();
		reboardReplyAction= ReboardReplyAction.getReboardReplyAction();
		reboardListAction= ReboardListAction.getReboardListAction();
	}
	
	public static Action getReboardWriteAction() {
		return reboardWriteAction;
	}
	
	public static Action getReboardViewAction() {
		return reboardViewAction;
	}
	
	public static Action getReboardReplyAction() {
		return reboardReplyAction;
	}
	
	public static Action getReboardListAction() {
		return reboardListAction;
	}
}
