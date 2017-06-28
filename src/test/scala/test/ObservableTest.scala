package test

import model.Observable
import org.scalatest.{BeforeAndAfter, FlatSpec}

class ObservableTest extends FlatSpec with BeforeAndAfter{

  "Un pokemon con energia maxima 100 que descansa" should "tener su energia en 100" in {
    val o:Observable[String] = Observable.from("Hola");
    o.subscribe( n => print(n))
  }
}
