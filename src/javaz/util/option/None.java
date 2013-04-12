package javaz.util.option;

import javaz.util.function.Cbn;
import javaz.util.function.Function;

// begin None
final public class None<Z>
 implements Option<Z> {
// end

 // begin fold_None_
 // uses y

 @Override
 public <Y>
 Y fold(
  final Function<Z, Y> z2y,
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