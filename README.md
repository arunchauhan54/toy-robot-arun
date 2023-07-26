# Coding Challenge 1: Toy Robot

## Requirements
The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 x 5 units. There are no other obstructions on the table surface.
The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement that would result in the robot
falling from the table must be prevented, however further valid movement commands must still be allowed.

Create an application that can read in commands of the following form:

*  PLACE X,Y,F
*  MOVE
*  LEFT
*  RIGHT
*  REPORT

PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.

The origin(0,0) can be considered to be the SOUTH WEST most corner.

MOVE will move the toy robot one unit forward in the direction it is currently facing.

LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.

REPORT will announce the X,Y and F of the robot.

Expectations of your application:
* The application must be a Spring-Boot-Application
* Input must be realised over the REST-API, take care when designing the REST-API
* The robot that is not on the table can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands.
* The robot must not fall off the table during movement. This also includes the initial placement of the toy robot.
* Any move that would cause the robot to fall must be ignored.
* It is not required to provide any graphical output showing the movement of the toy robot.


# Running Application
Application have separate endpoints for PLACE, MOVEMENT and REPORT. Since Rest endpoints are stateless PLACE 
(post /robot) returns a robotId. MOVEMENT and REPORT commands take this robotId as input to identify robot and
its current state. an in-memory caching is being done at robotService level to store state of the robot. 

Rest endpoints are state


### start App
```./gradlew bootRun```


#### command to endpoint interpretation

*  PLACE X,Y,F
```shell
curl --location 'localhost:8080/robot' \
--header 'Content-Type: application/json' \
--data '{
    "x": 4,
    "y": 4,
    "direction": "SOUTH"
}'
```

will return a robotId


*  REPORT
```shell
curl --location 'localhost:8080/robot/{{id}}'
```

*  MOVE

```shell
curl --location 'localhost:8080/robot-movement/{{id}}' \
--header 'Content-Type: application/json' \
--data '{
    "movement": "MOVE"
}'
```

*  LEFT

```shell
curl --location 'localhost:8080/robot-movement/{{id}}' \
--header 'Content-Type: application/json' \
--data '{
    "movement": "LEFT"
}'
```

*  RIGHT
```shell
curl --location 'localhost:8080/robot-movement/{{id}}' \
--header 'Content-Type: application/json' \
--data '{
    "movement": "RIGHT"
}'
```
