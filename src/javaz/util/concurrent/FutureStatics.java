package javaz.util.concurrent;

import java.util.concurrent.CountDownLatch;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;
import javaz.util.function.Function;
import javaz.util.function.FunctionStatics;

import javaz.util.ref.Ref;
import javaz.util.stream.Stream;
import javaz.util.stream.StreamStatics;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class FutureStatics {

 public static <Z>
 Function<Future<Future<Z>>, Future<Z>> join(
 ) {
  return
   ffz ->
    ffz.bindM(fz ->
     fz
    );
 }

 // begin one_FutureStatics_
 public static <Z>
 Future<Z> one(
  final Z z
 ) {
  return
   () ->
    es ->
     timeout ->
      z;
 }
 // end

 public static <Z>
 Cbn<Future<Z>> lift(
  final Cbn<Z> z
 ) {
  return
   () -> {
    System.out.println();
    return one(z._());
   };
 }

 public static <Z, Y>
 Function<Future<Z>, Future<Y>> liftF(
  final Function<Z, Y> z2y
 ) {
  return
   fz ->
    fz.bindF(z2y);
 }

 public static <Z, Y>
 Function<Future<Z>, Future<Y>> liftA(
  final Future<Function<Z, Y>> f_z2y
 ) {
  return
   fz -> fz.bindA(f_z2y);
 }

 public static <Z, Y, X>
 BiFunction<Future<Z>, Cbn<Future<Y>>, Future<X>> liftBF(
  final BiFunction<Z, Cbn<Y>, X> zny2x
 ) {
  return
   (fz, fy) ->
    fz.bindA(
     fy._().bindF(y -> z ->
      zny2x._(z, () -> y)
     )
    );
 }

 public static <Z>
 Function<Stream<Future<Z>>, Future<Stream<Z>>> sequenceOp(
  final Cbn<Stream<Z>> sz,
  final BiFunction<
   Stream<Z>, Cbn<Stream<Z>>,
   Stream<Z>
   > sznsz2sz
 ) {
  return
   sfz ->
    sfz.foreach(
     sz,
     sznsz2sz,
     FutureStatics::lift,
     FutureStatics::liftBF
    )._(
     liftF(StreamStatics::one)
    );
 }

 public static <Z>
 Function<Stream<Future<Z>>, Future<Stream<Z>>> sequenceAcc(
  final Cbn<Stream<Z>> sz,
  final BiFunction<Z, Cbn<Stream<Z>>, Stream<Z>> znsz2sz
 ) {
  return
   sfz ->
    sfz.foreach(
     sz,
     znsz2sz,
     FutureStatics::lift,
     FutureStatics::liftBF
    )._(
     liftF(FunctionStatics::identity)
    );
 }

 // begin forkDeclaration_FutureStatics_
 public static <Z>
 Future<Z> fork(
  final Cbn<Future<Z>> fz
 )
 // end

 // begin forkDefinitionPartOne_FutureStatics_
 {
  return
   () -> es -> {
    final CountDownLatch latch = new CountDownLatch(1);
    final Ref<Z> rz = new Ref<Z>();
    es.submit(
     () -> {
      try {
       rz.assign(fz._().open()._(es).get());
       latch.countDown();
      } catch (Exception e) {
       throw new IllegalStateException("not implemented");
      }
     }
    );
    // end
    // begin forkDefinitionPartTwo_FutureStatics_
    return
     timeout -> {
      try {
       latch.await(timeout, MILLISECONDS);
       return rz.deref();
      } catch (Exception e) {
       throw new IllegalStateException("not implemented");
      }
     };
   };
 }
 // end

 // begin async_FutureStatics_
 public static <Z>
 Future<Z> async(
  final Cbn<Z> z
 ) {
  return
   fork(
    () -> one(z._())
   );
 }
 // end

}
