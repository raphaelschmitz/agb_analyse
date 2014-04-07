package com.bjr.server;

public class Model {
	private static Model model;
	private String agb1;
	private String agb2;
	private String choice;
	private String muster;


	public static Model getInstance(){
		if(model == null){
			model = new Model();
		}
		return model;
	}


	public String getAgb1() {
		return agb1;
	}


	public void setAgb1(String agb1) {
		this.agb1 = agb1;
	}


	public String getAgb2() {
		return agb2;
	}


	public void setAgb2(String agb2) {
		this.agb2 = agb2;
	}


	public String getChoice() {
		return choice;
	}


	public void setChoice(String choice) {
		this.choice = choice;
	}


	public String getMuster() {
		return muster;
	}


	public void setMuster(String muster) {
		this.muster = muster;
	}
}
