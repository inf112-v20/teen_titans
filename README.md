[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5c80f564765f4fd6a556adad72cfded7)](https://www.codacy.com/gh/inf112-v20/teen_titans?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v20/teen_titans&amp;utm_campaign=Badge_Grade)

[![Build Status](https://travis-ci.com/inf112-v20/teen_titans.svg?branch=master)](https://travis-ci.com/inf112-v20/teen_titans)

# INF112 Maven template 
Simple skeleton with libgdx. 

## To start the game
Clone the project to Intellij.

### Single player
Run the game once. Select "host game" (using the enter key). Enter your name when prompted. Press enter to start the game.

### Multiplayer
Click "edit run configurations". In the upper right corner, check the "allow parallell run" box. Run up to 4 iterations of the game. First, in one iteration, chose "host game", and enter your name when prompted. In the other iterations, choose "join game", enter adress "localhost" when prompted, then enter name when prompted. To start the game, all users must select a character.
Mute 1 of the windows so the music doesnt overlap (if playing on one computer).

### Controls
When in game, use the arrow keys to highlight a card. To select the card, press space.
FOR DEBUGGING: WASD will let you control your character, enter will cause character to take 1hp of damage.
WARNING: using WASD to control character will desync robots in multiplayer.

## Manuel Tests

0. Start the game and graphics appear means graphics work.
1. Step on the HOLE will make the character die
2. Step on the GEARS will make the character rotate
3. Step on the CONVEYOR BELT will make the character move towards the direction it points
4. Step in front of a PUSHER tile and it will PUSH the robot 1 tile in the direction of the PUSHER
5. Step out of the MAP and the player will die
6. Step on the GRILL and the player will loose HP
7. Step in front of a LASER and the player will loose HP
8. Step on a FLAG the player will gain HP
9. Step on a FLAG the player will get a new RESPAWN POINT
10. Do either step 1 or step 4 after step 7 or step 8, and the player will RESPAWN at the latest RESPAWN POINT
11. Die 3 times and the player will not be able to move
12. Step on all 4 FLAGS and you will win the game



## Known bugs
Currently throws "WARNING: An illegal reflective access operation has occurred", 
when the java version used is >8. This has no effect on function or performance, and is just a warning.







