package br.com.carv.example.jsp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.carv.example.jsp.connection.ConnectionFactory;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@jakarta.servlet.annotation.WebFilter(urlPatterns = { "/principal/*" })
public class FilterAuthentication extends jakarta.servlet.http.HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	private Connection connection;
	
	public FilterAuthentication() {

	}
	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String user = (String) session.getAttribute("user");
			String urlToAuthenticate = req.getServletPath();

			if (user == null || (user != null && user.isEmpty()) && !urlToAuthenticate.contains("ServletLogin")) {
				RequestDispatcher redirect = request.getRequestDispatcher("/index.jsp?url=" + urlToAuthenticate);
				request.setAttribute("msg", "Por favor, realize o login");
				redirect.forward(request, response);
				return;
			} else {
				chain.doFilter(request, response);
			}

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirect = request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg", e.getMessage());
			redirect.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		connection = ConnectionFactory.getConnection();
	}

}
