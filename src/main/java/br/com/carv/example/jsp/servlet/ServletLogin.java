package br.com.carv.example.jsp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.carv.example.jsp.dao.ModelLoginDAO;
import br.com.carv.example.jsp.model.ModelLogin;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModelLoginDAO modelLoginDAO = new ModelLoginDAO();

	public ServletLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModelLogin auth = new ModelLogin();

		String login = request.getParameter("Email");
		String password = request.getParameter("Senha");
		String url = request.getParameter("url");

		try {

			if (!login.isEmpty() && login != null && !password.isEmpty() && password != null) {
				auth.setLogin(login);
				auth.setPassword(password);

				if (modelLoginDAO.validateAuthentication(auth)) {
					request.getSession().setAttribute("user", auth.getLogin());

					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}

					RequestDispatcher redirect = request.getRequestDispatcher(url);
					redirect.forward(request, response);

				} else {
					RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Login ou senha incorretos.");
					redirect.forward(request, response);
				}

			} else {
				RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Os campos são obrigatórios! Por favor, insira-os corretamente.");
				redirect.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg", e.getMessage());
			redirect.forward(request, response);
		}
	}

}
