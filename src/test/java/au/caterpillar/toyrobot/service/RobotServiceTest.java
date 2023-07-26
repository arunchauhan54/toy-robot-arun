package au.caterpillar.toyrobot.service;

import au.caterpillar.toyrobot.model.Direction;
import au.caterpillar.toyrobot.model.RobotDto;
import au.caterpillar.toyrobot.model.RobotMovement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static au.caterpillar.toyrobot.model.Direction.EAST;
import static au.caterpillar.toyrobot.model.Direction.NORTH;
import static au.caterpillar.toyrobot.model.Direction.WEST;

class RobotServiceTest {

    RobotService robotService;

    @BeforeEach
    void init() {
        robotService = new RobotService();
    }

    @Test
    void givenValidRequest_whenCreateRobot_thenRobotIdReturned() {
        UUID uuid = robotService.createRobot(RobotDto.builder()
                .x(0)
                .y(0)
                .direction(Direction.NORTH)
                .build());
        Assertions.assertNotNull(uuid);
    }

    @Test
    void givenInvalidRequest_whenCreateRobot_thenExceptionThrown() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> robotService.createRobot(RobotDto.builder()
                .x(5)
                .y(5)
                .direction(Direction.NORTH)
                .build()));
    }

    @Test
    void givenValidId_whenGetRobot_thenRobotReturned() {
        UUID uuid = robotService.createRobot(RobotDto.builder()
                .x(1)
                .y(1)
                .direction(EAST)
                .build());
        RobotDto robot = robotService.getRobot(uuid);
        Assertions.assertAll(() -> {
            Assertions.assertEquals(1, robot.getX());
            Assertions.assertEquals(1, robot.getY());
            Assertions.assertEquals(EAST, robot.getDirection());
        });
    }

    @Test
    void givenValidId_whenRobotMovementMove_thenRobotMoved() {
        UUID uuid = robotService.createRobot(RobotDto.builder()
                .x(0)
                .y(0)
                .direction(NORTH)
                .build());
        robotService.robotMovement(RobotMovement.builder().movement(RobotMovement.Movement.MOVE).build(), uuid);
        RobotDto robot = robotService.getRobot(uuid);
        Assertions.assertAll(() -> {
            Assertions.assertEquals(0, robot.getX());
            Assertions.assertEquals(1, robot.getY());
            Assertions.assertEquals(NORTH, robot.getDirection());
        });
    }

    @Test
    void givenValidId_whenRobotMovementLeft_thenRobotMoved() {
        UUID uuid = robotService.createRobot(RobotDto.builder()
                .x(0)
                .y(0)
                .direction(NORTH)
                .build());
        robotService.robotMovement(RobotMovement.builder().movement(RobotMovement.Movement.LEFT).build(), uuid);
        RobotDto robot = robotService.getRobot(uuid);
        Assertions.assertAll(() -> {
            Assertions.assertEquals(0, robot.getX());
            Assertions.assertEquals(0, robot.getY());
            Assertions.assertEquals(WEST, robot.getDirection());
        });
    }

    @Test
    void givenValidId_whenRobotMovementRight_thenRobotMoved() {
        UUID uuid = robotService.createRobot(RobotDto.builder()
                .x(0)
                .y(0)
                .direction(NORTH)
                .build());
        robotService.robotMovement(RobotMovement.builder().movement(RobotMovement.Movement.RIGHT).build(), uuid);
        RobotDto robot = robotService.getRobot(uuid);
        Assertions.assertAll(() -> {
            Assertions.assertEquals(0, robot.getX());
            Assertions.assertEquals(0, robot.getY());
            Assertions.assertEquals(EAST, robot.getDirection());
        });
    }
}