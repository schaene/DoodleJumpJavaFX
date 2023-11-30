# Doodle Jump Clone in JavaFX

CSCI-3331-002 Assignment
by: Schaene Trible
## Design Choices

 - Added Textures early in development so game logic would be tied to the scale of the original game.
 - Placed exit Icon in top left rather than bottom middle. Game is also able to be exited by pressing the Escape Key.
 - Rather than stopping all movement upon falling, added functionality for the platforms to rise and bring up a "Game Over" screen.
 - Implemented springs than can be placed atop regular platforms and moving platforms, rather than just a bouncy platform.

## Extra Functionality

 - Added textures to the player, and changes direction while moving
 - Added textures to the platforms, some changing when jumped upon.
 - Added sound effects for jumping on platforms and springs, as well as a falling sound effect.
 - Added smooth controls through a Controls class.
 - Added holes in the paper, that when touched, cause a game over.
 - Added breakable platforms. They break and fall when attempting to jump on them.

## UML Diagram
![Diagram](/UML_Diagram.png)

## Disclaimer
Assets and Sounds do not belong to me, and are owned by Lima Sky, the studio behind the original Doodle Jump. 
