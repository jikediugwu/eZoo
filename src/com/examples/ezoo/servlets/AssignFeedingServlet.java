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
		// String aname = request.getParameter("aname");

		FeedingSchedule fs = new FeedingScheduleDaoImpl().getFeeding(fid);
		Animal animal = new AnimalDaoImpl().getAnimal(aid);

		if (request.getParameter("assign") != null) {
			if (fs != null && animal != null) {
				new FeedingScheduleDaoImpl().assignAnimalFeeding(fs, animal);
			}
		}

		if (request.getParameter("unassign") != null) {
			if (fs != null && animal != null) {
				new FeedingScheduleDaoImpl().unassignAnimalFeeding(fs, animal);
			}

		}
		response.sendRedirect("assignFeeding");

	}
}