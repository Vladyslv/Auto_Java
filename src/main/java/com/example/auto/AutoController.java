package com.example.auto;

import com.example.auto.Auto;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.Set;

public class AutoController {
    @FXML private AnchorPane pane;
    @FXML private ToggleButton modeBtn;

    private Auto auto;
    private final Set<KeyCode> keys = new HashSet<>();

    @FXML
    public void initialize() {
        auto = new Auto(50, 150);
        pane.getChildren().add(auto);

        modeBtn.setFocusTraversable(false);
        modeBtn.setText("Mode auto");
        modeBtn.setOnAction(e -> {
            boolean manual = modeBtn.isSelected();
            auto.setManual(manual);
            modeBtn.setText(manual ? "Mode manual" : "Mode auto");
        });

        new AnimationTimer() {
            public void handle(long now) {
                double w = pane.getWidth(), h = pane.getHeight();
                int dx = (keys.contains(KeyCode.RIGHT) ? 1 : 0) - (keys.contains(KeyCode.LEFT) ? 1 : 0);
                int dy = (keys.contains(KeyCode.DOWN) ? 1 : 0) - (keys.contains(KeyCode.UP) ? 1 : 0);
                auto.moveManual(dx, dy, w, h);
                auto.updateAuto(w);
            }
        }.start();
    }

    public void setupKeys(Scene scene) {
        scene.setOnKeyPressed(e -> keys.add(e.getCode()));
        scene.setOnKeyReleased(e -> keys.remove(e.getCode()));
    }
}