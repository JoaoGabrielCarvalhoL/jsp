package br.com.carv.example.jsp.servlet;

import java.io.IOException;

import br.com.carv.example.jsp.dao.ModelLoginDAO;
import br.com.carv.example.jsp.model.ModelLogin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ServletUserController")
public class ServletUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ModelLoginDAO modelLoginDAO = new ModelLoginDAO();
 
    public ServletUserController() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
			String id = request.getParameter("id");
			String name = request.getParameter("name"); 
			String email = request.getParameter("email");
			String login = request.getParameter("login"); 
			String password = request.getParameter("password");
	
			ModelLogin modelLogin = new ModelLogin(name, email, login, password);
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			
			modelLoginDAO.saveUser(modelLogin);
			
			request.setAttribute("msg", "Usuário salvo com sucesso!");
			request.setAttribute("modelLogin", modelLogin);
			
			RequestDispatcher redirect = request.getRequestDispatcher("principal/form-user.jsp");
			redirect.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg", e.getMessage());
			redirect.forward(request, response);
		}
		
		
		

		
	}

}
