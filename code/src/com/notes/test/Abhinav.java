package com.notes.test;

public enum Abhinav {

	MONDAY(1,"mon","day"),
	TUESDAY(0,"tue","day");
	
	

	int f1;
	String f2;
	String f3;
	static Abhinav[] ar = new Abhinav[Abhinav.values().length];
	
	static {
		for(int i=0;i<ar.length;i++) {
			ar[values()[i].f1] = values()[i];
		}
	}
	private Abhinav(int f1,String f2,String f3) {
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
	}
	
	public static Abhinav fromCode( int code ) {

		if(code>=ar.length) return null;
		else return ar[code];
	}
}
