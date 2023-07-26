package au.caterpillar.toyrobot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RobotDto {
    @Range(min = 0, max = 4)
    private int x;
    @Range(min = 0, max = 4)
    private int y;
    private Direction direction;
}
