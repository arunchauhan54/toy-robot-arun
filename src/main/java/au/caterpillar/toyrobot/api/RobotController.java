package au.caterpillar.toyrobot.api;

import au.caterpillar.toyrobot.model.RobotDto;
import au.caterpillar.toyrobot.model.RobotMovement;
import au.caterpillar.toyrobot.service.RobotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RobotController implements RobotApi {

    private final RobotService robotService;

    @Override
    public UUID createRobot(RobotDto robotDto) {
        return robotService.createRobot(robotDto);
    }

    @Override
    public RobotDto getRobot(UUID id) {
        return robotService.getRobot(id);
    }

    @Override
    public void robotMovement(RobotMovement robotMovement, UUID id) {
        robotService.robotMovement(robotMovement, id);
    }
}
