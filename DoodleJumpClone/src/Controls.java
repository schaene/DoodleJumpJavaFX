// Controls class to make movement of the character more fluid and allow better interpretation of the controls

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Controls {
    // array of buttons being pressed or not pressed
    private boolean[] buttonStatus;

    // easier references for the buttons boolean array
    public final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, ENTER = 4, ESCAPE = 5;

    public Controls(Scene scene){
        // make the array
        buttonStatus = new boolean[6];

        // for the key presses
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        scene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));
    }

    //public getter
    public boolean[] getButtonStatus(){
        return buttonStatus;
    }

    // store the keypresses when pressed
    private void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT:
                buttonStatus[LEFT] = true;
                break;
            case RIGHT:
                buttonStatus[RIGHT] = true;
                break;
            case DOWN:
                buttonStatus[DOWN] = true;
                break;
            case UP:
                buttonStatus[UP] = true;
                break;
            case ENTER:
                buttonStatus[ENTER] = true;
                break;
            case ESCAPE:
                buttonStatus[ESCAPE] = true;
                System.exit(0);
                break;
            default:
                break;
        }
    }
    // remove the key presses when removed
    private void handleKeyRelease(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT:
                buttonStatus[LEFT] = false;
                break;
            case RIGHT:
                buttonStatus[RIGHT] = false;
                break;
            case DOWN:
                buttonStatus[DOWN] = false;
                break;
            case UP:
                buttonStatus[UP] = false;
                break;
            case ENTER:
                buttonStatus[ENTER] = false;
                break;
            case ESCAPE:
                buttonStatus[ESCAPE] = false;
                break;
            default:
                break;
        }
    }
}
