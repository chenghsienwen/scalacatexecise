
package com.vpon.cat

trait Printable[A] {
   def format(a: A): String
}

object PrintableInstance {
    implicit val strPrint: Printable[String] = new Printable[String] {
        def format(a: String): String = a
    }
    implicit val intPrint: Printable[Int]  = new Printable[Int] {
        def format(a: Int): String = a.toString
    }
}

object Printable {
    def format[A](a: A)(implicit p: Printable[A]): String = p.format(a)   
}

object PrintableSyntax {
    implicit class PrintableOps[A](a: A)(implicit p: Printable[A]) {
        def show: String = p.format(a)
    }
}

