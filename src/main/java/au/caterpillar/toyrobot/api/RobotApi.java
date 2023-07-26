package au.caterpillar.toyrobot.api;

import au.caterpillar.toyrobot.model.RobotDto;
import au.caterpillar.toyrobot.model.RobotMovement;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public interface RobotApi {

    @PostMapping(value = "/robot")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    UUID createRobot(@Validated @RequestBody RobotDto robotDto);

    @GetMapping(value = "/robot/{id}")
    @ResponseBody
    RobotDto getRobot(@PathVariable UUID id);

    @PostMapping(value = "/robot-movement/{id}")
    @ResponseBody
    void robotMovement(@Validated @RequestBody RobotMovement robotMovement, @PathVariable UUID id);

}
