package com.intermacs.images.model.enums;

public enum ETipoEntidad {	
	 USER (1,"Usuario");//, OTRO(),....;

	    private final int id;   //
	    private final String name; // in meters
	    ETipoEntidad(int id, String name) {
	        this.id = id;
	        this.name = name;
	    }
	    public int getId() { return id; }
	    public String getName() { return name; }

	   
}
