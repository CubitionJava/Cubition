package net.cubition.api;

public interface Material {
	Mod getMod();
	String getTranslatableName(byte damage);
	String getItemTextureName(byte damage);
}
