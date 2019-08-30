package bottlecap.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed, middlePressed, leftClicked, rightClicked;
    private int mouseX, mouseY;

    public MouseManager() {
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isMiddlePressed() { return middlePressed; }
    public boolean isRightPressed() {
        return rightPressed;
    }
    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = true;
        } if (e.getButton() == MouseEvent.BUTTON2) {
            middlePressed = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightClicked = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            middlePressed = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            middlePressed = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public boolean isLeftClicked() {
        if (leftClicked) {
            leftClicked = false;
            return true;
        } else {
            return false;
        }
    }

    public void setLeftClicked(boolean leftClicked) {
        this.leftClicked = leftClicked;
    }

    public boolean isRightClicked() {
        if (rightClicked) {
            rightClicked = false;
            return true;
        } else {
            return false;
        }
    }

    public void setRightClicked(boolean rightClicked) {
        this.rightClicked = rightClicked;
    }

}
