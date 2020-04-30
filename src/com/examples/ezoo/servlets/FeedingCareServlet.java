package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDaoImpl;
import com.examples.ezoo.dao.FeedingScheduleDaoImpl;
import com.examples.ezoo.model.FeedingAnimal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AnimalCareServlet
 */
@WebServlet(description = "This servlet is the main interface into the Animal Care System", urlPatterns = {
		"/feedingCare" })
public class FeedingCareServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<FeedingSchedule> fs = new FeedingScheduleDaoImpl().getAllFeeding();
		List<FeedingAnimal> list = new FeedingScheduleDaoImpl().showAssignedAnimalFeeding();

		request.getSession().setAttribute("feeding", fs);
		request.getSession().setAttribute("list", list);

		request.getRequestDispatcher("feedingCareHome.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("del") != null) {
			String value = request.getParameter("del");
			FeedingSchedule feeding = new FeedingScheduleDaoImpl().getFeeding(Integer.parseInt(value.strip()));

			Map<Integer, Integer> mapDBView = new FeedingScheduleDaoImpl().getDBView();

			int success = remove(feeding, mapDBView);

			if (success > 0) {
				new FeedingScheduleDaoImpl().deleteFeeding(feeding.getScheduleID());

			}

			List<FeedingSchedule> fs = new FeedingScheduleDaoImpl().getAllFeeding();
			List<FeedingAnimal> list = new FeedingScheduleDaoImpl().showAssignedAnimalFeeding();

			request.getSession().setAttribute("feeding", fs);
			request.getSession().setAttribute("list", list);

		}

		request.getRequestDispatcher("feedingCareHome.jsp").forward(request, response);

	}

	private int remove(FeedingSchedule feeding, Map<Integer, Integer> map) {
		int success = 0;

		for (Entry<Integer, Integer> m : map.entrySet()) {
			if (feeding.getScheduleID() == m.getValue()) {
				new FeedingScheduleDaoImpl().unassignAnimalFeeding(feeding, new AnimalDaoImpl().getAnimal(m.getKey()));
				success++;
			}
		}
		return success;
	}
}