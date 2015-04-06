package com.stuckinadrawer.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.stuckinadrawer.game.MyGdxGame;
import com.stuckinadrawer.game.Projectile;
import com.stuckinadrawer.game.input.GameInputProcessor;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private MyGdxGame myGdxGame;
    Vector2 pos = new Vector2();
    Vector2 direction = new Vector2();
    Vector2 vel = new Vector2();

    public OrthographicCamera camera;

    ArrayList<Projectile> projectiles;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(new GameInputProcessor(this));
        projectiles = new ArrayList<Projectile>();
        pos.x = 100;
        pos.y = 100;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        processKeys();
        move(delta);
        renderShape();
        camera.update();
    }

    private void renderShape() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        myGdxGame.batch.begin();
        myGdxGame.batch.draw(myGdxGame.creature, pos.x, pos.y);
        for(Projectile projectile : projectiles){
            myGdxGame.batch.draw(myGdxGame.shot, projectile.pos.x, projectile.pos.y);
        }
        myGdxGame.batch.end();
    }

    private void processKeys() {
        if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D)){
            direction.x = 0;
        }else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            direction.x = -1;
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            direction.x = +1;
        }else {
            direction.x = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)){
            direction.y = 0;
        }else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            direction.y = +1;
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            direction.y = -1;
        }else {
            direction.y = 0;
        }

        direction.nor();
    }

    private void move(float delta) {
        direction.scl(delta * 100);
        pos.add(direction);

        ArrayList<Projectile> destroyedProjectiles = new ArrayList<Projectile>();
        for(Projectile projectile : projectiles){
            projectile.move(delta);
            if(projectile.destroy){
                destroyedProjectiles.add(projectile);
            }
        }
        projectiles.removeAll(destroyedProjectiles);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void shoot(Vector2 shot) {
        Vector2 dir = shot.sub(pos);
        dir.nor();
        Projectile projectile = new Projectile(pos.cpy(), dir);
        projectiles.add(projectile);
    }
}
