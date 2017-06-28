package model


class Observable[T](val subscriptions: T*){

  def subscribe[T]( f:Any => Unit) {
    subscriptions.foreach( s => f.apply(s))
  }
}

object Observable{
  def from[T](elem:T): Observable[T] = {
    return new Observable[T](elem)
  }
}


