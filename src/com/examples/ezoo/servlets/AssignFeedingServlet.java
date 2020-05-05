package com.examples.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDaoImpl;
import com.examples.ezoo.dao.FeedingScheduleDaoImpl;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AssignFeedingServlet
 */
@WebServlet("/assignFeeding")
public class AssignFeedingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AssignFeedingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("assignFeeding.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long fid = Long.parseLong(request.getParameter("fid"));

		long aid = Long.parseLong(request.getParameter("aid"));

		FeedingSchedule fs = null;
		Animal animal = null;
		try {
			fs = new FeedingScheduleDaoImpl().getFeeding(fid);
			animal = new AnimalDaoImpl().getAnimal(aid);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (request.getParameter("assign") != null) {
			if (fs != null && animal != null) {
				try {
					new FeedingScheduleDaoImpl().assignAnimalFeeding(fs, animal);

					request.getSession().setAttribute("message", "Feeding schedule successfully assigned");
					request.getSession().setAttribute("messageClass", "alert-success");
					request.getRequestDispatcher("assignFeeding.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("message", "Operation not successful: " + e);
					request.getSession().setAttribute("messageClass", "alert-danger");
					request.getRequestDispatcher("assignFeeding.jsp").forward(request, response);

				}
			}

		}

		if (request.getParameter("unassign") != null) {

			if (fs != null && animal != null) {
				try {
					new FeedingScheduleDaoImpl().unassignAnimalFeeding(fs, animal);

					request.getSession().setAttribute("message", "Feeding schedule successfully assigned");
					request.getSession().setAttribute("messageClass", "alert-success");
					request.getRequestDispatcher("assignFeeding.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("message", "Operation not successful: " + e);
					request.getSession().setAttribute("messageClass", "alert-danger");
					request.getRequestDispatcher("assignFeeding.jsp").forward(request, response);

				}
			}

		}
	}
}