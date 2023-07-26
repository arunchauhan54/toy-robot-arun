package au.caterpillar.toyrobot.service;

import au.caterpillar.toyrobot.api.ResourceNotFoundException;
import au.caterpillar.toyrobot.model.Robot;
import au.caterpillar.toyrobot.model.RobotDto;
import au.caterpillar.toyrobot.model.RobotMovement;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RobotService {

    private final Map<UUID, Robot> robotCache = new HashMap<>();

    public UUID createRobot(final RobotDto robotDto) {
        Robot robot = new Robot(robotDto.getX(), robotDto.getY(), robotDto.getDirection());
        UUID uuid = UUID.randomUUID();
        robotCache.put(uuid, robot);
        return uuid;
    }

    public RobotDto getRobot(final UUID id) {
        Robot robot = getRobotFromCache(id);
        return RobotDto.builder()
                .x(robot.getX())
                .y(robot.getY())
                .direction(robot.getDirection())
                .build();
    }

    public void robotMovement(final RobotMovement robotMovement, final UUID id) {
        RobotMovement.Movement movement = robotMovement.getMovement();
        Robot robot = getRobotFromCache(id);
        switch (movement) {
            case MOVE -> robot.move();
            case LEFT -> robot.left();
            case RIGHT -> robot.right();
        }
    }

    private Robot getRobotFromCache(UUID id) {
        return Optional.ofNullable(robotCache.get(id))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("not a valid id:%s", id)));
    }
}
