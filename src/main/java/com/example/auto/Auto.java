package com.example.auto;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Auto extends Rectangle {
    private boolean manual = false;
    private boolean running = true;
    private double dx = 3;
    private double speed = 4;

    public Auto(double x, double y) {
        super(x, y, 80, 40);
        setFill(Color.RED);
        setOnMouseClicked(e -> running = !running);
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public void updateAuto(double w) {
        if (!running || manual) return;
        setX(getX() + dx);
        if (getX() <= 0) { setX(0); dx = Math.abs(dx); }
        if (getX() + getWidth() >= w) { setX(w - getWidth()); dx = -Math.abs(dx); }
    }

    public void moveManual(int dirX, int dirY, double w, double h) {
        if(!running || !manual) return;
        double nx = getX() + dirX * speed;
        double ny = getY() + dirY * speed;
        setX(Math.max(0, Math.min(nx, w - getWidth())));
        setY(Math.max(0, Math.min(ny, h - getHeight())));
    }
}