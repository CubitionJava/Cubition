package net.cubition.api;

public interface Material {
	public Mod getMod ();
	public String getTranslatableName (byte damage);
	public String getItemTextureName (byte damage);
}
