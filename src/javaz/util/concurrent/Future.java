package javaz.util.concurrent;

import javaz.util.function.Function;

import java.util.concurrent.ExecutorService;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static javaz.util.concurrent.FutureStatics.one;

// begin Future
public interface Future<Z> {

 public Function<ExecutorService, SimpleFuture<Z>> open();
// end

 // begin bindF_Future_
 // note: has specific default definition

 default public <Y>
 Future<Y> bindF(
  final Function<Z, Y> z2y
 ) {
  return
   bindA(
    one(z2y)
   );
 }
 // end

 // begin bindADeclaration_Future_
 // note: has specific default definition

 default public <Y>
 Future<Y> bindA(
  final Future<Function<Z, Y>> f_z2y
 )
 // end

 // begin bindADefinitionPartOne_Future_
 {
  return
   () -> es -> {
    final SimpleFuture<Z> sfz =
     open()._(es);
    final SimpleFuture<Function<Z, Y>> sf_z2y =
     f_z2y.open()._(es);
    // end

    // begin bindADefinitionPartTwo_Future_
    return
     totalTime -> {
      try {
       final long start = currentTimeMillis();
       final Z z = sfz.get(totalTime, MILLISECONDS);
       final long stop = currentTimeMillis();
       final long remainingTime = totalTime - (stop - start);
       final Function<Z, Y> z2y =
        sf_z2y.get(remainingTime, MILLISECONDS);
       return z2y._(z);
      } catch (Exception e) {
       throw new IllegalStateException("not implemented");
      }
     };
   };
 }
 // end

 // begin bindMDeclaration_Future_
 default public <Y>
 Future<Y> bindM(
  final Function<Z, Future<Y>> z2fy
 )
 // end

 // begin bindMDefinition_Future_
 {
  return
   () -> es -> totalTime -> {
    try {
     final long start = currentTimeMillis();
     final Z z = open()._(es).get(totalTime, MILLISECONDS);
     final long stop = currentTimeMillis();
     final long remainingTime = totalTime - (stop - start);
     final Future<Y> fy = z2fy._(z);
     return
      fy.open()._(es).get(remainingTime, MILLISECONDS);
    } catch (Exception e) {
     throw new IllegalStateException("not implemented");
    }
   };
 }
 // end

 default public Future<Z> choice(
  final Function<Z, Boolean> z2b,
  final Function<Z, Future<Z>> t_z2fz,
  final Function<Z, Future<Z>> f_z2fz
 ) {
  return
   bindM(z ->
    z2b._(z)
     ? t_z2fz._(z)
     : f_z2fz._(z)
   );
 }

}
