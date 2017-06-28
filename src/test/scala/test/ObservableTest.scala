package test

import model.Observable
import org.scalatest.{BeforeAndAfter, FlatSpec}

class ObservableTest extends FlatSpec with BeforeAndAfter{

  "Un Observable saludador" should "imprimir Hola en consola" in {
    val o:Observable[String] = Observable.from("Hola");
    o.subscribe( n => print(n))
  }
}
