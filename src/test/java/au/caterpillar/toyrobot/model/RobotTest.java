package au.caterpillar.toyrobot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {

    @Test
    void givenInvalidRange_whenInitialiseRobot_thenExceptionThrown() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Robot(5, 0, Direction.NORTH));

        assertEquals("parameter x=5, y=0 are not in range", exception.getMessage());
    }

    @Test
    void givenValidRange_whenInitialiseRobot_thenRobotCreated() {
        assertDoesNotThrow(() -> new Robot(4, 4, Direction.NORTH));
    }

    @Test
    void givenValidMove_whenInstructed_thenMoveSuccessfully() {
        Robot robot = new Robot(0, 0, Direction.NORTH);
        robot.move();
        assertEquals("0,1,NORTH", robot.report());
    }

    @Test
    void givenInvalidMoveXMin_whenInstructed_thenMoveIgnored() {
        Robot robot = new Robot(0, 0, Direction.WEST);
        robot.move();
        assertEquals("0,0,WEST", robot.report());
    }

    @Test
    void givenInvalidMoveXMax_whenInstructed_thenMoveIgnored() {
        Robot robot = new Robot(4, 0, Direction.EAST);
        robot.move();
        assertEquals("4,0,EAST", robot.report());
    }

    @Test
    void givenInvalidMoveYMin_whenInstructed_thenMoveIgnored() {
        Robot robot = new Robot(0, 0, Direction.SOUTH);
        robot.move();
        assertEquals("0,0,SOUTH", robot.report());
    }

    @Test
    void givenInvalidMoveYMax_whenInstructed_thenMoveIgnored() {
        Robot robot = new Robot(0, 4, Direction.NORTH);
        robot.move();
        assertEquals("0,4,NORTH", robot.report());
    }

    @Test
    void givenValidPosition_whenMoveLeft_thenDirectionUpdated() {
        Robot robot = new Robot(0, 0, Direction.NORTH);
        robot.left();
        assertEquals("0,0,WEST", robot.report());
        robot.left();
        assertEquals("0,0,SOUTH", robot.report());
        robot.left();
        assertEquals("0,0,EAST", robot.report());
        robot.left();
        assertEquals("0,0,NORTH", robot.report());
    }

    @Test
    void givenValidPosition_whenMoveRight_thenDirectionUpdated() {
        Robot robot = new Robot(0, 0, Direction.NORTH);
        robot.right();
        assertEquals("0,0,EAST", robot.report());
        robot.right();
        assertEquals("0,0,SOUTH", robot.report());
        robot.right();
        assertEquals("0,0,WEST", robot.report());
        robot.right();
        assertEquals("0,0,NORTH", robot.report());
    }

}