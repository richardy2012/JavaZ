package javaz.util.apps;

import javaz.util.option.Option;
import javaz.util.option.OptionStatics;
import javaz.util.stream.Stream;
import javaz.util.stream.StreamStatics;

import static javaz.util.option.OptionStatics.one;
import static javaz.util.option.OptionStatics.zero;

public class OptionApp {

 // begin someOptions_OptionApp_
 private static final
 Option<String> oneA_plus_oneB =
  one("a").plus(() -> one("b"));
 private static final
 Option<String> oneC_plus_oneD =
  one("c").plus(() -> one("d"));
 // end

 // begin someOptionsUsingZero_OptionApp_
 private static final
 Option<String> oneA_plus_zero =
  one("a").plus(() -> zero());
 // note: sometimes we have to help the type inferencer
 private static final
 Option<String> zero_plus_oneA =
  OptionStatics.<String>zero().plus(() -> one("a"));
 // end

 // begin someStreamsOfOptions_OptionApp_
 private static final
 Stream<Option<String>> oneOA_plus_oneOB =
  StreamStatics.one(one("a")).plus(() ->
   StreamStatics.one(one("b"))
  );
 private static final
 Stream<Option<String>> oneZ_plus_oneOB =
  StreamStatics.<Option<String>>one(zero()).plus(() ->
   StreamStatics.one(one("b"))
  );
 private static final
 Stream<Option<String>> oneOA_plus_oneZ =
  StreamStatics.one(one("a")).plus(() ->
   StreamStatics.one(zero())
  );
 // end

 public static void main(String[] args) {

  // begin example01_OptionApp_
  // example01 = one(abc)

  Option<String> example01 =
   one("a").bindM(a ->
    one("b").bindM(b ->
     one("c").bindM(c ->
      one(a + b + c)
     )
    )
   );
  // end
  System.out.println("example01 = " + example01);
  // begin example02_OptionApp_
  // example02 = one(abc)

  Option<String> example02 =
   one("a").bindM(a ->
    one("b").bindM(b ->
     one("c").bindF(c ->
      a + b + c
     )
    )
   );
  // end
  System.out.println("example02 = " + example02);
  // begin example03_OptionApp_
  // example03 = one(abc)

  Option<String> example03 =
   one("a").bindA(
    one("b").bindA(
     one("c").bindF(c -> b -> a ->
      a + b + c
     )
    )
   );
  // end
  System.out.println("example03 = " + example03);
  // begin example04_OptionApp_
  // example04 = zero

  Option<String> example04 =
   one("a").bindM(a ->
    zero().bindM(z ->
     one("c").bindF(c ->
      a + z + c
     )
    )
   );
  // end
  System.out.println("example04 = " + example04);
  // begin example05_OptionApp_
  // example05 = zero

  Option<String> example05 =
   one("a").bindA(
    zero().bindA(
     one("c").bindF(c -> z -> a ->
      a + z + c
     )
    )
   );
  // end
  System.out.println("example05 = " + example05);
  // begin example06_OptionApp_
  // example06 = one(c)

  Option<String> example06 =
   one("a").choice(a ->
    a.equals("b"),
    a -> one("b"),
    a -> one("c")
   );
  // end
  System.out.println("example06 = " + example06);
  // begin example07_OptionApp_
  // example07 = zero

  Option<String> example07 =
   one("a").filter(a ->
    a.equals("b")
   );
  // end
  System.out.println("example07 = " + example07);
  // begin example08_OptionApp_
  // example08 = one(a)

  Option<String> example08 =
   oneA_plus_oneB;
  // end
  System.out.println("example08 = " + example08);
  // begin example09_OptionApp_
  // example09 = one(a)

  Option<String> example09 =
   oneA_plus_zero;
  // end
  System.out.println("example09 = " + example09);
  // begin example10_OptionApp_
  // example10 = one(a)

  Option<String> example10 =
   zero_plus_oneA;
  // end
  System.out.println("example10 = " + example10);
  // begin example11_OptionApp_
  // example11 = one(ac)

  Option<String> example11 =
   oneA_plus_oneB.bindM(apb ->
    oneC_plus_oneD.bindF(cpd ->
     apb + cpd
    )
   );
  // end
  System.out.println("example11 = " + example11);
  // begin example12_OptionApp_
  // example12 = one(a)

  Option<String> example12 =
   oneA_plus_oneB.identity();
  // end
  System.out.println("example12 = " + example12);
  // begin example13_OptionApp_
  // example13 = 1

  int example13 =
   oneA_plus_oneB.length();
  // end
  System.out.println("example13 = " + example13);
  // begin example14_OptionApp_
  // example14 = one(one(a) : one(b) : zero)

  Option<Stream<String>> example14 =
   OptionStatics.<String>constructiveSequence()._(
    oneOA_plus_oneOB
   );
  // end
  System.out.println("example14 = " + example14);
  // begin example15_OptionApp_
  // example15 = zero

  Option<Stream<String>> example15 =
   OptionStatics.<String>constructiveSequence()._(
    oneZ_plus_oneOB
   );
  // end
  System.out.println("example15 = " + example15);
  // begin example16_OptionApp_
  // example16 = zero

  Option<Stream<String>> example16 =
   OptionStatics.<String>constructiveSequence()._(
    oneOA_plus_oneZ
   );
  // end
  System.out.println("example16 = " + example16);
  // begin example17_OptionApp_
  // example17 = one(one(A)) : zero
  Stream<Option<String>> example17 =
   oneA_plus_oneB.<String>additiveForeachToStream()._(s ->
    StreamStatics.one(
     one(s.toUpperCase())
    )
   );
  System.out.println("example17 = " + example17);
  // end
 }

}
