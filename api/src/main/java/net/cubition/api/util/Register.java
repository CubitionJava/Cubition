package net.cubition.api.util;

import java.util.HashMap;

public class Register<T> {

	private HashMap<String, T> members = new HashMap<String, T> ();
	
	public boolean isRegistered (String name) {
		return members.containsKey (name);
	}
	
	public void register (String name, T object) throws IllegalArgumentException {
		if (!members.containsKey (name))
			members.put (name, object);
		else
			throw new IllegalArgumentException ("This entry already exists.");
	}
	
	public T get (String name) {
		if (members.containsKey(name))
			return members.get (name);
		else
			throw new IllegalArgumentException ("This entry does not exist.");
	}
	
}
