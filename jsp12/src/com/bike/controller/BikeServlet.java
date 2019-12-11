package com.bike.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bike.dao.BikeDao;
import com.bike.vo.BikeVo;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/BikeServlet")
public class BikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BikeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[command] : " + command);
		
		BikeDao dao = new BikeDao();
		
		if(command.equals("first")) {
			response.sendRedirect("bike.jsp");
		}else if(command.equals("first_db")){
			dao.delete();
			
			String[] bikeList= request.getParameterValues("bike");
			//강남구/2301/현대고등학교 건너편/서울특별시/ .... /37.4564
			List<BikeVo> vos = new ArrayList<BikeVo>();
			for(int i=0; i<bikeList.length;i++) {
				String[] tmp = bikeList[i].split("/");
				BikeVo vo = new BikeVo(
						tmp[0], Integer.parseInt(tmp[1]),
						tmp[2],
						tmp[3],
						Integer.parseInt(tmp[4]),
						Double.parseDouble(tmp[5]),
						Double.parseDouble(tmp[6])	
						);
				vos.add(vo);
			}
			int res = dao.insert(vos);
			if(res>0) {
				System.out.println("\ninsert 성공!\n");
				response.sendRedirect("index.html");
			} else {
				System.out.println("\ninsert 실패!\n");
				response.sendRedirect("bike.jsp");
			}
			
			
			
		}else if(command.equals("second")) {
			response.sendRedirect("bike02.jsp");
		}else if(command.equals("second_db")) {
			int res = dao.delete();
			if(res>0) {
				System.out.println("삭제성공");
			}else {
				System.out.println("삭제 실패");
			}
			
			String obj = request.getParameter("obj");
			//System.out.println(obj);
			
			JsonParser paser = new JsonParser();
			JsonElement element = paser.parse(obj);
			
			List<BikeVo> vos = new ArrayList<>();
			
			for(int i=0; i<element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
//				System.out.println(element.getAsJsonObject().get("DATA").getAsJsonArray().get(i));
				System.out.println(element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject().get("new_addr") );
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
