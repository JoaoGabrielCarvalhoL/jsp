package br.com.carv.example.jsp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.carv.example.jsp.connection.ConnectionFactory;

/**
 * Servlet Filter implementation class FilterAuthentication
 */
@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAuthentication extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	private Connection connection;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public FilterAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain

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
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		connection = ConnectionFactory.getConnection();
	}

}
