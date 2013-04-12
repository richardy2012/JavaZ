package javaz.util.apps;

import javaz.util.stream.Stream;
import javaz.util.stream.StreamStatics;

import static javaz.util.stream.StreamStatics.one;
import static javaz.util.stream.StreamStatics.zero;

public class StreamApp {

 private static final
 Stream<String> oneA_plus_oneB =
  one("a").plus(() -> one("b"));
 private static final
 Stream<String> oneC_plus_oneD =
  one("c").plus(() -> one("d"));

 private static final
 Stream<String> oneA_plus_zero =
  one("a").plus(() -> zero());
 private static final
 Stream<String> zero_plus_oneA =
  StreamStatics.<String>zero().plus(() -> one("a"));

 // begin infinitelyMany_StreamApp_
 private static <Z>
 Stream<Z> infinitelyMany(
  Z z
 ) {
  return
   one(z).plus(
    () -> infinitelyMany(z)
   );
 }
 // end

 // begin fibonacciNumbersFrom_StreamApp_
 private static Stream<Integer> fibonacciNumbersFrom(
  Integer fib0,
  Integer fib1
 ) {
  return
   one(fib0).plus(
    () -> fibonacciNumbersFrom(fib1, fib0 + fib1)
   );
 }
 // end

 public static void main(String[] args) {

  // begin example01_StreamApp_
  // example01 = one(abc) : zero

  Stream<String> example01 =
   one("a").bindM(a ->
    one("b").bindM(b ->
     one("c").bindM(c ->
      one(a + b + c)
     )
    )
   );
  // end
  System.out.println("example01 = " + example01);
  // begin example02_StreamApp_
  // example02 = one(abc) : zero

  Stream<String> example02 =
   one("a").bindM(a ->
    one("b").bindM(b ->
     one("c").bindF(c ->
      a + b + c
     )
    )
   );
  // end
  System.out.println("example02 = " + example02);
  // begin example03_StreamApp_
  // example03 = one(abc) : zero

  Stream<String> example03 =
   one("a").bindA(
    one("b").bindA(
     one("c").bindF(c -> b -> a ->
      a + b + c
     )
    )
   );
  // end
  System.out.println("example03 = " + example03);
  // begin example04_StreamApp_
  // example04 = zero

  Stream<String> example04 =
   one("a").bindM(a ->
    zero().bindM(z ->
     one("c").bindF(c ->
      a + z + c
     )
    )
   );
  // end
  System.out.println("example04 = " + example04);
  // begin example05_StreamApp_
  // example05 = zero

  Stream<String> example05 =
   one("a").bindA(
    zero().bindA(
     one("c").bindF(c -> z -> a ->
      a + z + c
     )
    )
   );
  // end
  System.out.println("example05 = " + example05);
  // begin example06_StreamApp_
  // example06 = one(c) : zero

  Stream<String> example06 =
   one("a").choice(a ->
    a.equals("b"),
    a -> one("b"),
    a -> one("c")
   );
  // end
  System.out.println("example06 = " + example06);
  // begin example07_StreamApp_
  // example07 = zero

  Stream<String> example07 =
   one("a").filter(a ->
    a.equals("b")
   );
  // end
  System.out.println("example07 = " + example07);
  // begin example08_StreamApp_
  // example08 = one(a) : one(b) : zero

  Stream<String> example08 =
   oneA_plus_oneB;
  // end
  System.out.println("example08 = " + example08);
  // begin example09_StreamApp_
  // example09 = one(a) : zero

  Stream<String> example09 =
   oneA_plus_zero;
  // end
  System.out.println("example09 = " + example09);
  // begin example10_StreamApp_
  // example10 = one(a) : zero

  Stream<String> example10 =
   zero_plus_oneA;
  // end
  System.out.println("example10 = " + example10);
  // begin example11_StreamApp_
  // example11 = one(ac) : one(ad) : one(bc) : one(bd) : zero

  Stream<String> example11 =
   oneA_plus_oneB.bindM(apb ->
    oneC_plus_oneD.bindF(cpd ->
     apb + cpd
    )
   );
  // end
  System.out.println("example11 = " + example11);
  // begin example12_StreamApp_
  // example12 = example12 = one(a) : one(b) : zero

  Stream<String> example12 =
   oneA_plus_oneB.identity();
  // end
  System.out.println("example12 = " + example12);
  // begin example13_StreamApp_
  // example13 = 2

  int example13 =
   oneA_plus_oneB.length();
  // end
  System.out.println("example13 = " + example13);
  // begin example14_StreamApp_
  // example14 = one(a) : one(a) : one(a) : one(a) : zero

  Stream<String> example14 =
   infinitelyMany("a").take(4);
  // end
  System.out.println("example14 = " + example14);
  // begin example15_StreamApp_
  // example15 = one(1) : one(2) : one(3) : one(5) : zero

  Stream<Integer> example15 =
   fibonacciNumbersFrom(1, 2).take(4);
  // end
  System.out.println("example15 = " + example15);
 }

}
