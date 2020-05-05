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
		String time = request.getParameter("am_pm");

		String rhour = request.getParameter("rhour");
		String rminutes = request.getParameter("rminutes");
		String rtime = request.getParameter("ram_pm");

		String ftime = hour + " : " + minutes + " " + time;
		String rtimme = rhour + " : " + rminutes + " " + rtime;

		FeedingSchedule fs = new FeedingSchedule(id, ftime, rtimme, food, notes);

		if (request.getParameter("add") != null) {
			try {
				new FeedingScheduleDaoImpl().addFeeding(fs);
				request.getSession().setAttribute("message", "Feeding schedule successfully created");
				request.getSession().setAttribute("messageClass", "alert-success");
				request.getRequestDispatcher("addFeeding.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("message", "Operation not successful: " + e);
				request.getSession().setAttribute("messageClass", "alert-danger");
				request.getRequestDispatcher("addFeeding.jsp").forward(request, response);

			}

		}
		if (request.getParameter("update") != null) {
			try {
				new FeedingScheduleDaoImpl().updateFeeding(id, fs);
				request.getSession().setAttribute("message", "Feeding schedule successfully created");
				request.getSession().setAttribute("messageClass", "alert-success");
				request.getRequestDispatcher("addFeeding.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("message", "Operation not successful: " + e);
				request.getSession().setAttribute("messageClass", "alert-danger");
				request.getRequestDispatcher("addFeeding.jsp").forward(request, response);
			}
		}
	}
}