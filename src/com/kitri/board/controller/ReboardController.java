package com.kitri.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.factory.BoardActionFactory;
import com.kitri.util.*;

@WebServlet("/reboard")
public class ReboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act= request.getParameter("act");
		int bcode= ParameterCheck.naNToZero(request.getParameter("bcode"));
		int pg= ParameterCheck.naNToOne(request.getParameter("pg"));
		String key= ParameterCheck.nullToBlank(request.getParameter("key"));
		String word= ParameterCheck.nullToBlank(request.getParameter("word"));
		// jsp > controller 위의 값 5개
		// controller > jsp act빼고 나머지 다 갖고다님
		System.out.println("act는>>>"+act);
		
		String path= "/index.jsp";
		if("mvwrite".equals(act)) {
			path= "/reboard/write.jsp?bcode="+bcode+"&pg="+pg+"&key="+key+"&word="+word;
			PageMove.forward(request, response, path);
		} else if("writearticle".equals(act)) {
			path= BoardActionFactory.getReboardWriteAction().execute(request, response);
			
		} else if("".equals(act)) {
			
		} else if("".equals(act)) {
			
		} else if("".equals(act)) {
			
		} else if("".equals(act)) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(KitriConstance.ENCODE);
		doGet(request, response);
	}
}
