package com.bjr.server;

public class Model {
	public static Model model;
	public static String agb1;
	public static String agb2;


	public static Model getInstance(){
		if(model == null){
			model = new Model();
		}
		return model;
	}
}
