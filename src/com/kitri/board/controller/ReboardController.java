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
		int seq= ParameterCheck.naNToZero(request.getParameter("seq"));
		// jsp > controller 위의 값 5개
		// controller > jsp act빼고 나머지 다 갖고다님
		
		String queryString= "bcode="+bcode+"&pg="+pg+"&key="+key+"&word="+Encoder.urlUtf(word);
		
		String path= "/index.jsp";
		System.out.println("act>>"+ act+ "\tqueryString>>"+ queryString);
		
		if(bcode== 0) {
			//전체목록으로 가야함
			PageMove.forward(request, response, path); //현재는 임의로 인덱스페이지
		} else {
			if("mvwrite".equals(act)) {
				path= "/reboard/write.jsp?"+ queryString;
				PageMove.forward(request, response, path);
			} else if("writearticle".equals(act)) {
				path= BoardActionFactory.getReboardWriteAction().execute(request, response);
				//writeok.jsp?seq=seq;
				
				path+= queryString;
				PageMove.redirect(request, response, path);
			} else if("listarticle".equals(act)) {
				path= BoardActionFactory.getReboardListAction().execute(request, response);
				path+= queryString;
				
				PageMove.forward(request, response, path);
			} else if("viewarticle".equals(act)) {
				path= BoardActionFactory.getReboardViewAction().execute(request, response);
				path+= queryString;
				PageMove.forward(request, response, path);
			} else if("".equals(act)) {
				
			} else if("".equals(act)) {
				
			} else if("".equals(act)) {

			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(KitriConstance.ENCODE);
		doGet(request, response);
	}
}
