package javaz.util.stream;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;
import javaz.util.function.Function;

import static javaz.util.stream.StreamStatics.done;
import static javaz.util.stream.StreamStatics.more;

// begin More
final public class More<Z>
 implements Stream<Z> {

 final Z current;
 final Cbn<Stream<Z>> next;
// end

 More(
  final Z current,
  final Cbn<Stream<Z>> next
 ) {
  this.current = current;
  this.next = next;
 }

 // begin take_More_
 @Override
 public Stream<Z> take(
  int n
 ) {
  return
   (n > 0)
    ? more(
    current,
    () -> next._().take(n - 1)
   )
    : done();
 }
 // end

 // begin fold_More_
 @Override
 public <Y>
 Y fold(
  final BiFunction<Z, Cbn<Y>, Y> zny2y,
  final Cbn<Y> y
 ) {
  return
   zny2y._(
    current,
    () -> next._().fold(zny2y, y)
   );
 }
 // end

 @Override
 public String toString() {
  return
   show();
 }

}
