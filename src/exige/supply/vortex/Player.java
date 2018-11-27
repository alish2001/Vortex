package exige.supply.vortex;

public class Player {
	
	private String name;
	private int number;
	
	public Player (String name, int number) {
		this.setName(name);
		this.setNumber(number);
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}