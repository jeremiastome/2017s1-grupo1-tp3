package model

class Observable[T](val subscriptions: (Subscriber[T] => Unit)) {

  def subscribe(subscriber: Subscriber[T]) {
    subscriptions(subscriber)
  }

  def repeat(n:Int => Unit): Observable[T] = {
    return new Observable(this.subscriptions) {
      override def subscribe(subscriber: Subscriber[T]) {
        Range(1, n).foreach()
        }
      }
    }
  }
}

object Observable{

  def from[T](elems:List[T]) : Observable[T] = {
    return new Observable(subscriber => elems.foreach { num =>  subscriber.onNext(num)})
  }

  def just[T](elem:T): Observable[T] = {
    return new Observable(subscriber => subscriber.onNext(elem))
  }

  def empty[T] : Observable[T] = {
    return new Observable(subscriber => subscriber.onComplete())
  }

  def never[T] : Observable[T] = {
    return new Observable(subscriber => subscriber)
  }

  def error[T](t:Throwable) : Observable[T] = {
    return new Observable(subscriber => subscriber.onError(t))
  }

  def create[T](f:Subscriber[T] => Unit): Observable[T] = {
    return new Observable(f)
  }
}


