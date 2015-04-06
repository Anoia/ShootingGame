package com.stuckinadrawer.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stuckinadrawer.game.screens.GameScreen;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	public Texture creature;
	public Texture shot;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		creature = new Texture("creature.png");
		shot = new Texture("shot.png");

		this.setScreen(new GameScreen(this));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		 super.render();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		creature.dispose();
	}
}
