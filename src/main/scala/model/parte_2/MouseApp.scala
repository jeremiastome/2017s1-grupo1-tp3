package model.parte_2

import javafx.application.Application
import javafx.scene.canvas.Canvas
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.paint.Color
import javafx.scene.{Group, Scene}
import javafx.stage.Stage

object MouseApp {
  def main(args: Array[String]) {
    Application.launch(classOf[MouseApp], args: _*)
  }
}

class MouseApp extends Application {

  def start(primaryStage: Stage): Unit = {
    val root = new Group()
    var fillColor1 = Color.rgb(0xF0, 0x50, 0x60)
    var fillColor2 = Color.rgb(0x60, 0xF0, 0x50)
    var fillColor = fillColor2
    var diameter = 20
    primaryStage.setScene(new Scene(root, 800, 600))

    val canvas = new Canvas(800, 600)
    val gc = canvas.getGraphicsContext2D

    gc.setLineWidth(5)

    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, { keyEvent: KeyEvent =>
      if (keyEvent.getCode eq KeyCode.SPACE) {
        fillColor = fillColor1
        keyEvent.consume()
      } else if ((keyEvent.getCode eq KeyCode.Z) && diameter > 10) {
        diameter -= 2
      } else if ((keyEvent.getCode eq KeyCode.X) && diameter < 80) {
        diameter += 2
      }
    })

    primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, { keyEvent: KeyEvent =>
      if (keyEvent.getCode eq KeyCode.SPACE) {
        fillColor = fillColor2
        keyEvent.consume()
      }
    })

    root.getChildren.add(canvas)

    canvas.setOnMouseDragged { event =>
      gc.setFill(fillColor)
      gc.fillOval(event.getSceneX, event.getSceneY, diameter, diameter)
    }

    primaryStage.show()
  }
}