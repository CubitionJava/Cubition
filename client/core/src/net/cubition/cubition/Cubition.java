package net.cubition.cubition;

import net.cubition.cubition.view.LoadingScreenView;

public class Cubition extends ApplicationAdapter {
	
	public View view;
	
	@Override
	public void create () {
		// TODO Load client settings
		
		// Load view
		view = new LoadingScreenView ();
	}

	@Override
	public void render () {
		// Render the current view, nothing special
		// Nothing needs to be passed to .render(). Gtx is static public
		view.render ();
	}
}
