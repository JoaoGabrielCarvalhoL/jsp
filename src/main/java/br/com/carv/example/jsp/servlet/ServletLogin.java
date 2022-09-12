package br.com.carv.example.jsp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.carv.example.jsp.model.ModelLogin;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ServletLogin() {
        super();  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModelLogin auth = new ModelLogin();
		
		String login = request.getParameter("Email");
		String password = request.getParameter("Senha");
		
		if (!login.isEmpty() && login != null && !password.isEmpty() && password != null) {
			auth.setLogin(login);
			auth.setPassword(password);
			
			/**/
			request.getSession().setAttribute("user", auth.getLogin());
			RequestDispatcher redirect = request.getRequestDispatcher("main/main.jsp");
			redirect.forward(request, response);
			
		} else { 
			RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Os campos são obrigatórios! Por favor, insira-os corretamente.");
			redirect.forward(request, response);
		}
	}

}
