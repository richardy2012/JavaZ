package javaz.util.option;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;
import javaz.util.function.Function;
import javaz.util.function.FunctionStatics;
import javaz.util.stream.Stream;
import javaz.util.stream.StreamStatics;

public class OptionStatics {

 // begin some_OptionStatics_
 // specific factory method
 // not part of generic DSL

 public static <Z>
 Option<Z> some(
  final Z value
 ) {
  return
   new Some<>(value);
 }
 // end

 // begin none_OptionStatics_
 // specific factory method
 // not part of generic DSL

 public static <Z>
 Option<Z> none(
 ) {
  return
   new None<Z>();
 }
 // end

 // begin join_OptionStatics_
 public static <Z>
 Function<Option<Option<Z>>, Option<Z>> join(
 ) {
  return
   ooz ->
    ooz.bindM(oz ->
     oz
    );
 }
 // end

 // begin one_OptionStatics_
 // multiplicative method

 public static <Z>
 Option<Z> one(
  final Z z
 ) {
  return
   some(z);
 }
 // end

 // begin zero_OptionStatics_
 // additive method

 public static <Z>
 Option<Z> zero(
 ) {
  return
   none();
 }
 // end

 public static <Z>
 Cbn<Option<Z>> lift(
  final Cbn<Z> z
 ) {
  return
   () -> one(z._());
 }

 public static <Z, Y>
 Function<Option<Z>, Option<Y>> liftF(
  final Function<Z, Y> z2y
 ) {
  return
   oz ->
    oz.bindF(z2y);
 }

 public static <Z, Y>
 Function<Option<Z>, Option<Y>> liftA(
  final Option<Function<Z, Y>> o_z2y
 ) {
  return
   oz -> oz.bindA(o_z2y);
 }

 public static <Z, Y, X>
 BiFunction<Option<Z>, Cbn<Option<Y>>, Option<X>> liftBF(
  final BiFunction<Z, Cbn<Y>, X> zny2x
 ) {
  return
   (oz, oy) ->
    oz.bindA(
     oy._().bindF(y -> z ->
      zny2x._(z, () -> y)
     )
    );
 }

 // begin plus_OptionStatics_
 // static version of plus

 public static <Z>
 Option<Z> plus(
  final Option<Z> oz1,
  final Cbn<Option<Z>> oz2
 ) {
  return
   oz1.plus(oz2);
 }
 // end

 // begin sequenceOpDeclaration_OptionStatics_
 public static <Z>
 Function<Stream<Option<Z>>, Option<Stream<Z>>> sequenceOp(
  final Cbn<Stream<Z>> sz,
  final BiFunction<
   Stream<Z>, Cbn<Stream<Z>>,
   Stream<Z>
   > sznsz2sz
 )
 // end

 // begin sequenceOpDefinition_OptionStatics_
 {
  return
   soz ->
    soz.foreach(
     sz,
     sznsz2sz,
     OptionStatics::lift,
     OptionStatics::liftBF
    )._(
     liftF(StreamStatics::one)
    );
 }
 // end

 // begin sequenceAccDeclaration_OptionStatics_
 public static <Z>
 Function<Stream<Option<Z>>, Option<Stream<Z>>> sequenceAcc(
  final Cbn<Stream<Z>> sz,
  final BiFunction<Z, Cbn<Stream<Z>>, Stream<Z>> znsz2sz
 )
 // end

 // begin sequenceAccDefinition_OptionStatics_
 {
  return
   soz ->
    soz.foreach(
     sz,
     znsz2sz,
     OptionStatics::lift,
     OptionStatics::liftBF
    )._(
     liftF(FunctionStatics::identity)
    );
 }
 // end
}
