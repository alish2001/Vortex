package exige.supply.vortex;

import java.awt.Color;

public enum VortexType {

	ENTRANCE_1(0, 191, 255),
	EXIT_1(255, 140, 0),
	
	ENTRANCE_2(0, 255, 0),
	EXIT_2(220, 20, 60);

	private int r, g, b;

	private VortexType(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Color getColor() {
		return new Color(r, g, b);
	}

}