//import AdHocPolymorphism.{Adder, IntAdder, combine}

object AdHocPolymorphism {
  // intro - overloaded methods
  //https://blog.codecentric.de/en/2017/02/ad-hoc-polymorphism-scala-mere-mortals/
  /*
  def combine(x:Int, y:Int) :Int = x+y
  def combine(x:String, y:String): String = x+y

  // implementation not clear as we need to ensure that A is able to be added
  def combine[A](x:A ,y:A) : A = ??

  //let's assume we have the following trait
  trait Adder[A]{
    def add(x:A, y:A) :A
  }

  //we can use our helper trait in the combine method to help us with implementation
  def combine[A](x:A, y:A)(adder: Adder[A]):A = adder.add(x,y)

  //we declare Adder implementations for Int and
  //type class is a group of classes that satisfy a contract typically defined by a trait
  object IntAdder extends Adder[Int] {
    override def add(x:Int, y:Int) = x+y
  }
  val stringAdder = new Adder[String]{
    override def add(x:String, y:String) = x+y
  }

  //our combine function will now become
  combine(1,2)(IntAdder)
  */
  //at this point you can run VERSION1


  //we can make our dependency implicit
  /*
  implicit object IntAdder extends Adder[Int] {
    override def add(x:Int, y:Int) = x+y
  }
  implicit val stringAdder = new Adder[String]{
    override def add(x:String, y:String) = x+y
  }

  //and redefine our combine function
  def combine[A](x:A, y:A)(implicit adder:Adder[A]):A = adder.add(x,y)

  // the overall call will be simpler
  combine(1,2)
  combine("Hello", "World")

  //in fact, we can simplify the usage of the implicit parameter by using 'implicitly' function
  def combine[A](x:A, y:A)(implicit adder:Adder[A]):A = implicitly[Adder[A]].add(x,y)

  //there is obviously some syntactic sugar available
  def combine[A:Adder](x:A, y:A):A = implicitly[Adder[A]].add(x,y)
  */
  //at this point we may run VERSION2

  //VERSION 1
  /*
  def main(args: Array[String]): Unit = {
    println( combine(1,2)(IntAdder) )
    println( combine("Hello", " World")(stringAdder))
  }

  trait Adder[A]{
    def add(x:A, y:A) :A
  }
  def combine[A](x:A, y:A)(adder: Adder[A]):A = adder.add(x,y)
  object IntAdder extends Adder[Int] {
    override def add(x:Int, y:Int) = x+y
  }
  val stringAdder = new Adder[String]{
    override def add(x:String, y:String) = x+y
  }
  */
  //VERSION2
  /*
  def main(args: Array[String]): Unit = {

    trait Adder[A]{
      def add(x:A, y:A) :A
    }

    implicit object IntAdder extends Adder[Int] {
      override def add(x:Int, y:Int) = x+y
    }
    implicit val stringAdder = new Adder[String]{
      override def add(x:String, y:String) = x+y
    }

    def combine[A:Adder](x:A, y:A):A = implicitly[Adder[A]].add(x,y)

    println( combine(1,2) )
    println( combine("Hello", " World"))
  }
   */
}
