package com.javatts;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewFactory {

    private Point2D anchorPt;
    private Point2D previousLocation;

    public void initMovablePlayer(Stage primaryStage) {

        Scene scene = primaryStage.getScene();
        Parent root = (Pane) scene.getRoot();
        root.setPickOnBounds(true);
        // starting initial anchor point
        root.setOnMousePressed(mouseEvent -> {

                    anchorPt = new Point2D(mouseEvent.getScreenX(),
                            mouseEvent.getScreenY());
                    primaryStage.setOpacity(1);
                }

        );

        // Dragging the stage by moving its x,y
        // based on the previous location.
        root.setOnMouseDragged(mouseEvent -> {
            if (anchorPt != null && previousLocation != null) {
                primaryStage.setOpacity(0.955f);
                primaryStage.setX(previousLocation.getX()
                        + mouseEvent.getScreenX()
                        - anchorPt.getX());
                primaryStage.setY(previousLocation.getY()
                        + mouseEvent.getScreenY()
                        - anchorPt.getY());
            }
        });

        // Set the new previous to the current mouse x,y coordinate
        root.setOnMouseReleased(mouseEvent -> {


                    previousLocation = new Point2D(primaryStage.getX(),
                            primaryStage.getY());
                    primaryStage.setOpacity(1);
                }
        );

        // Initialize previousLocation after Stage is shown
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN,
                (WindowEvent t) -> {
                    previousLocation = new Point2D(primaryStage.getX(),
                            primaryStage.getY());
                });
    }
}
