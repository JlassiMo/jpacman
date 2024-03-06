package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.sprite.AnimatedSprite;
import nl.tudelft.jpacman.sprite.Sprite;

import java.util.Map;

/**
 * A player operated unit in our game.
 */
public class Player extends Unit {

    private final Map<Direction, Sprite> sprites;
    private final AnimatedSprite deathSprite;
    private int score;
    private boolean alive;
    private Unit killer;
    private int lives;

    protected Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation, int initialLives) {
        this.score = 0;
        this.alive = true;
        this.sprites = spriteMap;
        this.deathSprite = deathAnimation;
        this.lives = initialLives;
        deathSprite.setAnimating(false);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean isAlive) {
        if (isAlive) {
            deathSprite.setAnimating(false);
            this.killer = null;
        } else {
            deathSprite.restart();
        }
        this.alive = isAlive;
    }

    public Unit getKiller() {
        return killer;
    }

    public void setKiller(Unit killer) {
        this.killer = killer;
    }

    public int getScore() {
        return score;
    }

    @Override
    public Sprite getSprite() {
        if (isAlive()) {
            return sprites.get(getDirection());
        }
        return deathSprite;
    }

    public void addPoints(int points) {
        score += points;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }
}
