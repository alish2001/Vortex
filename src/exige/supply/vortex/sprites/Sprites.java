package exige.supply.vortex.sprites;

public enum Sprites {

	GRASS(new Sprite(16, 0, 0, SpritesManager.getInstance().getSheet(0)));

	private Sprite spriteClass;

	Sprites(Sprite spriteClass) {
		this.spriteClass = spriteClass;
	}

	public Sprite getSpriteClass() {
		return spriteClass;
	}
}