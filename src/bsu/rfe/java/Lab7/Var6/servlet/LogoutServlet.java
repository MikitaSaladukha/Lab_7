package bsu.rfe.java.Lab7.Var6.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsu.rfe.java.Lab7.Var6.entity.ChatUser;

public class LogoutServlet extends ChatServlet { 

	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException { 
		String name = (String) request.getSession().getAttribute("name"); 
		// ���� � ������ ������� ��� ������������... 
		if (name!=null) 
		{ 
			// �������� ������, ����������� ������������ � ����� ������ 
			ChatUser aUser = activeUsers.get(name); 
			// ���� ������������� ������ ������������, ��������� ���  
			// ���� ������, ��������� � ��������������� ������  
			// ������������, ����������� ����� �� ���� 
			// (�.�. ������� ��� ��, ��� � ������) 
			if (aUser.getSessionId().equals((String) 
					request.getSession().getId())) { 
				// ������� ������������ �� ������ �������� 
				// �.�. ������� �������������� ������������,  
				// ����� ������������� 
				synchronized (activeUsers) { 
					activeUsers.remove(name); 
				} 
				// �������� ��� ������������ � ������ 
				request.getSession().setAttribute("name", null); 
				// �������� ID ������ � cookie 
				response.addCookie(new Cookie("sessionId", null)); 
				// ������������� �� ������� �������� 
				response.sendRedirect(response.encodeRedirectURL("/Lab_7/")); 
			} else { 
				// ������������ �������� ������������ ����� ������ �  
				// �� ������ ������ 
				response.sendRedirect(response.encodeRedirectURL("/Lab_7/view.htm"));   
			} 
		} else { 
			// ������������� ������������ �� ������� ���� ���� 
			response.sendRedirect(response.encodeRedirectURL("/Lab_7/view.htm")); 
		} 
	} 

} 