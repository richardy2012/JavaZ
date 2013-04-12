package javaz.util.stream;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;
import javaz.util.function.Function;

public class StreamStatics {

 // begin more_StreamStatics_
 public static <Z>
 Stream<Z> more(
  final Z current,
  final Cbn<Stream<Z>> next
 ) {
  return
   new More<>(current, next);
 }
 // end

 // begin done_StreamStatics_
 public static <Z>
 Stream<Z> done(
 ) {
  return
   new Done<>();
 }
 // end

 // begin one_StreamStatics_
 public static <Z>
 Stream<Z> one(
  final Z z
 ) {
  return
   more(z, StreamStatics::done);
 }
 // end

 // begin zero_StreamStatics_
 public static <Z>
 Stream<Z> zero(
 ) {
  return
   done();
 }
 // end

 // begin lift_StreamStatics_
 // cbn version of one

 public static <Z>
 Cbn<Stream<Z>> lift(
  final Cbn<Z> z
 ) {
  return
   () -> one(z._());
 }
 // end

 // begin liftF_StreamStatics_
 // static version of bindF

 public static <Z, Y>
 Function<Stream<Z>, Stream<Y>> liftF(
  final Function<Z, Y> z2y
 ) {
  return
   sz ->
    sz.bindF(z2y);
 }
 // end

 // begin liftA_StreamStatics_
 // static version of bindA

 public static <Z, Y>
 Function<Stream<Z>, Stream<Y>> liftA(
  final Stream<Function<Z, Y>> s_z2y
 ) {
  return
   sz -> sz.bindA(s_z2y);
 }
 // end

 // begin liftBF_StreamStatics_
 public static <Z, Y, X>
 BiFunction<Stream<Z>, Cbn<Stream<Y>>, Stream<X>> liftBF(
  final BiFunction<Z, Cbn<Y>, X> zny2x
 ) {
  return
   (sz, sy) ->
    sz.bindA(
     sy._().bindF(y -> z ->
      zny2x._(z, () -> y)
     )
    );
 }
 // end

 // begin plus_StreamStatics_
 // static version of plus

 public static <Z>
 Stream<Z> plus(
  final Stream<Z> sz1,
  final Cbn<Stream<Z>> sz2
 ) {
  return
   sz1.plus(sz2);
 }
 // end

}
