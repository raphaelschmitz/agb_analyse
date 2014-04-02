package com.bjr.agb_analyse;

	import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
	 
	@ManagedBean
	@SessionScoped
	public class Bean implements Serializable  {
	 
		private static final long serialVersionUID = 1L;
	 
		private String name;
		private String test = "test";
	 
		public String getTest() {
			return test;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@PostConstruct
	      public void initMyBean(){
	      /**This map contains all the params you submitted from the html form */
	      Map<String,String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	      requestParams.get("comment");
		}
    }
