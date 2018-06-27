package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;

public class ReboardViewAction implements Action {
	private static ReboardViewAction reboardViewAction;
	
	private ReboardViewAction() {}
	
	static {
		reboardViewAction= new ReboardViewAction();
	}

	public static ReboardViewAction getReboardViewAction() {
		return reboardViewAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
