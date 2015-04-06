package com.stuckinadrawer.game;

import com.badlogic.gdx.math.Vector2;

public class Projectile {
    public Vector2 pos;
    public Vector2 dir;
    public Vector2 range = new Vector2();
    float maxRange = 200;
    public boolean destroy = false;

    public Projectile(Vector2 pos, Vector2 dir){
        this.pos = pos;
        this.dir = dir;
    }

    public void move(float delta){
        dir.nor();
        dir.scl(delta * 600);
        range.add(dir);

        if(range.len() > maxRange){
            destroy = true;
            return;
        }
        pos.add(dir);
    }
}
