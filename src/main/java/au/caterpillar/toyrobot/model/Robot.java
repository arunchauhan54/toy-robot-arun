package au.caterpillar.toyrobot.model;


import lombok.Getter;

import java.util.function.Function;

import static au.caterpillar.toyrobot.model.Direction.EAST;
import static au.caterpillar.toyrobot.model.Direction.NORTH;
import static au.caterpillar.toyrobot.model.Direction.SOUTH;
import static au.caterpillar.toyrobot.model.Direction.WEST;

@Getter
public class Robot {
    private int x;
    private int y;
    private Direction direction;
    private final int TABLE_MAX_SIZE = 4; // for 5*5 table axis range is from 0 to 4

    public Robot(int x, int y, Direction direction) {
        if (inRange().apply(x) && inRange().apply(y)) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        } else {
            throw new IllegalArgumentException(String.format("parameter x=%s, y=%s are not in range", x, y));
        }
    }


    public void move() {
        switch (direction) {
            case NORTH -> {
                if (notCrossingMax(y)) {
                    y++;
                }
            }
            case SOUTH -> {
                if (notCrossingMin(y)) {
                    y--;
                }
            }
            case EAST -> {
                if (notCrossingMax(x)) {
                    x++;
                }
            }
            case WEST -> {
                if (notCrossingMin(x)) {
                    x--;
                }
            }
        }
    }

    public void left() {
        switch (direction) {
            case NORTH -> direction = WEST;
            case SOUTH -> direction = EAST;
            case EAST -> direction = NORTH;
            case WEST -> direction = SOUTH;
        }
    }

    public void right() {
        switch (direction) {
            case NORTH -> direction = EAST;
            case SOUTH -> direction = WEST;
            case EAST -> direction = SOUTH;
            case WEST -> direction = NORTH;
        }
    }

    public String report() {
        return x + "," + y + "," + direction;
    }

    private boolean notCrossingMax(int value) {
        return value < TABLE_MAX_SIZE;
    }

    private boolean notCrossingMin(int value) {
        return value > 0;
    }

    private Function<Integer, Boolean> inRange() {
        return value -> value >= 0 && value <= TABLE_MAX_SIZE;
    }
}