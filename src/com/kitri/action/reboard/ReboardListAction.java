package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;

public class ReboardListAction implements Action {
	private static ReboardListAction reboardListAction;
	
	private ReboardListAction() {}
	
	static {
		reboardListAction= new ReboardListAction();
	}
	
	public static ReboardListAction getReboardListAction() {
		return reboardListAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
