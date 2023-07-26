package au.caterpillar.toyrobot.api;

import au.caterpillar.toyrobot.model.Direction;
import au.caterpillar.toyrobot.model.RobotDto;
import au.caterpillar.toyrobot.model.RobotMovement;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static au.caterpillar.toyrobot.model.RobotMovement.Movement.MOVE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RobotControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper;

    RobotDto robotDto;

    @BeforeEach
    void init() {
        mapper = new ObjectMapper();
        robotDto = RobotDto.builder().x(0).y(0).direction(Direction.NORTH).build();
    }

    @Test
    void givenValidRobotCreateRequest_whenMoveRobot_thenGetRobotRetrunsValidResponse() throws Exception {
        String robotDtoString = mapper.writeValueAsString(robotDto);

        // create robot
        MvcResult mvcResult = mockMvc.perform(
                        post("/robot")
                                .content(robotDtoString)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        String id = mvcResult.getResponse().getContentAsString().replaceAll("\"", "");


        // move robot
        String movementString = mapper.writeValueAsString(RobotMovement.builder().movement(MOVE).build());
        mockMvc.perform(
                        post("/robot-movement/" + id)
                                .content(movementString)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // get robot
        MvcResult mvcResultRobot = mockMvc.perform(
                        get("/robot/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        RobotDto robotDtoResponse = mapper.readValue(mvcResultRobot.getResponse().getContentAsString(), RobotDto.class);

        // validate
        Assertions.assertAll(() -> {
            Assertions.assertEquals(0, robotDtoResponse.getX());
            Assertions.assertEquals(1, robotDtoResponse.getY());
            Assertions.assertEquals(Direction.NORTH, robotDtoResponse.getDirection());
        });

    }

}