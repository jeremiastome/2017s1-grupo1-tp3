package test

import model.Observable
import model.Subscriber
import org.scalatest.{BeforeAndAfter, FlatSpec}

class ObservableTest extends FlatSpec with BeforeAndAfter{

  "Un Observable saludador" should "imprimir Hola en consola" in {
    val o:Observable[String] = Observable.from(List("Hola"));
    o.subscribe(new Subscriber[String] {

      override def onNext(t: String): Unit = println(t)

      override def onError(t: Throwable): Unit = throw t

      override def onComplete(): Unit = {}
    })

  }

  "Create Observable con un rango de uno a cuatro" should "imprimir 1,2,3,4" in {
    val o = Observable.create[Int](subscriber =>
      try {
        Range(1,5).foreach { num =>
          // se emite el elemento
          subscriber.onNext(num)
        // se completa exitosamente
        subscriber.onComplete()
        }
      }
      catch {
        case e:Exception => subscriber.onError(e)
        //si hubo un fallo se emite el error
      }
    )

    o.subscribe ( new Subscriber[Int] {

      override def onNext(t: Int): Unit = println(t)

      override def onError(t: Throwable): Unit = throw t

      override def onComplete(): Unit = {}
    }
    )
  }
}
