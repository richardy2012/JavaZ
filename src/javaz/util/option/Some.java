package javaz.util.option;

import javaz.util.function.Cbn;
import javaz.util.function.Function;

// begin Some
final public class Some<Z>
 implements Option<Z> {

 final Z value;
// end

 Some(
  final Z value
 ) {
  this.value = value;
 }

 // begin fold_Some_
 // uses z2y

 @Override
 public <Y>
 Y fold(
  final Function<Z, Y> z2y,
  final Cbn<Y> y
 ) {
  return
   z2y._(value);
 }
 // end

 @Override
 public String toString() {
  return
   show();
 }

}
