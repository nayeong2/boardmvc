package com.kitri.action.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;

public class MakeCategoryAction implements Action {
	private static MakeCategoryAction makeCategoryAction;
	private MakeCategoryAction() {}
	
	static {
		makeCategoryAction= new MakeCategoryAction();
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public static MakeCategoryAction getMakeCategoryAction() {
		return makeCategoryAction;
	}
}
