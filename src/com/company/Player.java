package com.company;

import com.company.classes.EntityA;
import com.company.classes.EntityB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Slon on 01.03.2016.
 */
public class Player extends GameObject implements EntityA{

    private double velX = 0;
    private double velY = 0;
    private Textures tex;
    private Game game;
    private Controller c;
    Animation anim;
    Sound collision;

    public Player(double x, double y, Textures tex, Game game, Controller c){
        super(x, y);
        this.tex = tex;
        this.game = game;
        this.c = c;
        collision = new Sound(new File("res/sfx/collision.wav"));
        anim = new Animation(5, tex.player[0], tex.player[1], tex.player[2]);
    }

    public void tick(){
        x += velX;
        y += velY;
        if(x <= 0){
            x = 0;
        }
        if(x >= 640 - 32){
            x = 640 - 32;
        }
        if(y < 0){
            y = 0;
        } if(y >= 480 - 48){
            y = 480 - 48;
        }
        for (int i = 0; i < game.eB.size(); i++){
            EntityB tempEnt = game.eB.get(i);
            if(Physics.Collision(this, tempEnt)){
                Game.health -= 10;
                Game.score++;
                collision.play();
                c.removeEntity(tempEnt);
                game.setEnemyKilled(game.getEnemyKilled() + 1);
            }
        }
        anim.runAnimation();
    }

    public void render(Graphics g){
        anim.drawAnimation(g, x, y, 0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
