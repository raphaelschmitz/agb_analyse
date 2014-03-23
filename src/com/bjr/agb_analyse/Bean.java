package com.bjr.agb_analyse;

	import javax.faces.bean.ManagedBean;
	import javax.faces.bean.SessionScoped;
	import java.io.Serializable;
	 
	@ManagedBean
	@SessionScoped
	public class Bean implements Serializable {
	 
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
    }
