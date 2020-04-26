package com.examples.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.FeedingScheduleDaoImpl;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AddFeedingServlet
 */
@WebServlet("/addFeeding")
public class AddFeedingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddFeedingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("addFeeding.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = Long.parseLong(request.getParameter("id"));

		String food = request.getParameter("food");

		String notes = request.getParameter("notes");
		String hour = request.getParameter("hour");

		String minutes = request.getParameter("minutes");

		String rhour = request.getParameter("rhour");
		String rminutes = request.getParameter("rminutes");

		String ftime = hour + ": " + minutes;
		String rtimme = hour + ": " + minutes;

		FeedingSchedule fs = new FeedingSchedule(id, ftime, rtimme, food, notes);

		if (request.getParameter("add") != null) {
			// Perform add.
			new FeedingScheduleDaoImpl().addFeeding(fs);

		} else if (request.getParameter("update") != null) {
			// Perform remove.
			new FeedingScheduleDaoImpl().updateFeeding(id, fs);

		} else if (request.getParameter("delete") != null) {
			// Perform remove.
			new FeedingScheduleDaoImpl().deleteFeeding(id);

		}
		request.setAttribute("feedSchedule", fs);
		response.sendRedirect("addFeeding");

	}

}
