package bsu.rfe.java.Lab7.Var6.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsu.rfe.java.Lab7.Var6.entity.ChatMessage;
import bsu.rfe.java.Lab7.Var6.entity.ChatUser;

public class NewMessageServlet extends ChatServlet 
{ 
	private static final long serialVersionUID = 1L; 

	protected void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException 
	{ 
		// �� ��������� ������������ ��������� ISO-8859. ��� ��� ��  
		// �������� ������ � ��������� UTF-8 
		// �� ���������� ���������� ��������������� ��������� HTTP-������� 
		request.setCharacterEncoding("UTF-8");     
		// ������� �� HTTP-������� �������� 'message' 
		String message = (String)request.getParameter("message"); 
		int type = Integer.parseInt(request.getParameter("labeled"));
		// ���� ��������� �� ������, �� 
		if (message!=null && !"".equals(message)) 
		{ 
			switch(type)
			{
				case 1:
						message = message.toLowerCase();
						message = "<em>"+message+"</em>"; 
						break;
				case 3: 
						message = message.toUpperCase();
						message = "<font color='red'>!!!!!!!!!"+message+"!!!!!!!</font>"; 
						break;
				default:
						break;	
						
			}
			
			// �� ����� �� ������ �������� ������ �� ������ ChatUser 
			ChatUser author = activeUsers.get((String) 
					request.getSession().getAttribute("name"));  
			synchronized (messages) 
			{ 
				// �������� � ������ ��������� ����� 
				 messages.add(new ChatMessage(message, author, 
						 Calendar.getInstance().getTimeInMillis())); 
			} 
		} 
		// ������������� ������������ �� �������� � ������ ��������� 
		response.sendRedirect("/Lab_7/compose_message.htm"); 
	}

	private void swith(int type) {
		// TODO Auto-generated method stub
		
	} 

} 