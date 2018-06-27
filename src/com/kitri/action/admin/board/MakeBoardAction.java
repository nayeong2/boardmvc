package com.kitri.action.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;

public class MakeBoardAction implements Action {
	private static MakeBoardAction makeBoardAction;

	private MakeBoardAction() {
	}

	static {
		makeBoardAction = new MakeBoardAction();
	}

	public static MakeBoardAction getMakeBoardAction() {
		return makeBoardAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
