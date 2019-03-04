package com.handson.javaee.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GreetingsServlet
 */
@WebServlet(urlPatterns = {
		"/GreetingsServlet" }, displayName = "GreetingsServlet", description = "This is a Greetings Servlet", loadOnStartup = 1)
@ServletSecurity(value = @HttpConstraint(rolesAllowed = { "app-admin",
		"app-user-role" }, transportGuarantee = TransportGuarantee.CONFIDENTIAL))
public final class GreetingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter printWriter = response.getWriter();
		printWriter.append(String.format("Hello %s !! Greeting's from %s <br/>", request.getUserPrincipal().getName(),
				request.getServletPath()));
		printWriter.append(String.format("User is having principle %s and instance of princple is %s",
				request.getUserPrincipal().getName(), request.getUserPrincipal().getClass().getName()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
