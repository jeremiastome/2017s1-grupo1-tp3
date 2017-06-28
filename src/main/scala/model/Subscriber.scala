package model

trait Subscriber[-T] {
  /**
    * Recibe un elemento nuevo
    *
    * @param t el elemento emitido
    */
  def onNext(t: T): Unit

  /**
    * Termina con error.
    *
    * @param t el throwable emitido
    */
  def onError(t: Throwable): Unit

  /**
    * Termina con success.
    * No se enviarán más eventos
    */
  def onComplete(): Unit
}

