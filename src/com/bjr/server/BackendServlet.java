package com.bjr.server;

//Import required java libraries
import java.io.*;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.*;
import javax.servlet.http.*;

import com.bjr.agb_analyse.Bean;

import java.util.*;

//Extend HttpServlet class
@ManagedBean
public class BackendServlet extends HttpServlet implements Serializable  {
	
// Method to handle GET method request.
public void doGet(HttpServletRequest request,
                 HttpServletResponse response)
         throws ServletException, IOException
{
   // Set response content type
   response.setContentType("application/json");

   PrintWriter out = response.getWriter();
   if (request.getParameter("agb1") != null)
   {
   Model.getInstance().agb1 = request.getParameter("agb1");
   }
   if (request.getParameter("agb2") != null)
   {
   Model.getInstance().agb2 = request.getParameter("agb2");
   }
   out.println(Model.getInstance().agb1);
   System.out.println(Model.getInstance().agb1);
   out.println(Model.getInstance().agb2);
   System.out.println(Model.getInstance().agb2);
}
// Method to handle POST method request.
public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
   throws ServletException, IOException {
  doGet(request, response);
}
}