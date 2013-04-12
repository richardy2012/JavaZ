package javaz.util.stream;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;

import static javaz.util.stream.StreamStatics.done;

// begin Done
final public class Done<Z>
 implements Stream<Z> {
// end


 // begin take_Done_
 @Override
 public Stream<Z> take(
  int n
 ) {
  return
   done();
 }
 // end

 // begin fold_Done_
 @Override
 public <Y>
 Y fold(
  final BiFunction<Z, Cbn<Y>, Y> zny2y,
  final Cbn<Y> y
 ) {
  return
   y._();
 }
 // end

 @Override
 public String toString() {
  return
   show();
 }

}