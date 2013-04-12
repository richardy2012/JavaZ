package javaz.util.option;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;
import javaz.util.function.Function;
import javaz.util.function.FunctionStatics;

import javaz.util.stream.Stream;
import javaz.util.stream.StreamStatics;

public class OptionStatics {

 // begin some_OptionStatics_
 // factory method (used in library code)
 // not part of application programmer DSL

 public static <Z>
 Option<Z> some(
  final Z value
 ) {
  return
   new Some<>(value);
 }
 // end

 // begin none_OptionStatics_
 // factory method (used in library code)
 // not part of application programmer DSL

 public static <Z>
 Option<Z> none(
 ) {
  return
   new None<Z>();
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

 // begin sequenceOp_OptionStatics_
 public static <Z>
 Function<Stream<Option<Z>>, Option<Stream<Z>>> sequenceOp(
  final Cbn<Stream<Z>> sz,
  final BiFunction<
   Stream<Z>, Cbn<Stream<Z>>,
   Stream<Z>
   > sznsz2sz
 ) {
  return
   soz ->
    soz.foreachToOption(sz, sznsz2sz)._(
     liftF(StreamStatics::one)
    );
 }
 // end

 // begin sequenceAcc_OptionStatics_
 public static <Z>
 Function<Stream<Option<Z>>, Option<Stream<Z>>> sequenceAcc(
  final Cbn<Stream<Z>> sz,
  final BiFunction<Z, Cbn<Stream<Z>>, Stream<Z>> znsz2sz
 ) {
  return
   soz ->
    soz.foreachToOption(sz, znsz2sz)._(
     liftF(FunctionStatics::identity)
    );
 }
 // end

 // begin constructiveSequence_OptionStatics_
 public static <Z>
 Function<Stream<Option<Z>>, Option<Stream<Z>>>
 constructiveSequence(
 ) {
  return
   sequenceAcc(
    StreamStatics::done,
    StreamStatics::more
   );
 }
 // end

 // begin additiveSequence_OptionStatics_
 public static <Z>
 Function<Stream<Option<Z>>, Option<Stream<Z>>>
 additiveSequence(
 ) {
  return
   sequenceOp(
    StreamStatics::zero,
    StreamStatics::plus
   );
 }
 // end

}
