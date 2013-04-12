package javaz.util.stream;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;
import javaz.util.function.Function;

import static javaz.util.stream.StreamStatics.one;
import static javaz.util.stream.StreamStatics.more;
import static javaz.util.stream.StreamStatics.zero;

// begin Stream
public interface Stream<Z> {
// end

 // begin take_Stream_
 // note: a stream can be infinite

 public Stream<Z> take(
  int n
 );
 // end

 // begin fold_Stream_
 // abstract method
 // follows the recursive structure of Stream

 public <Y>
 Y fold(
  final BiFunction<Z, Cbn<Y>, Y> zny2y,
  final Cbn<Y> y
 );
 // end

 // begin plus_Stream_
 default public Stream<Z> plus(
  final Cbn<Stream<Z>> sz
 ) {
  return
   fold(
    StreamStatics::more,
    sz
   );
 }
 // end

 // begin bindM_Stream_
 default public <Y>
 Stream<Y> bindM(
  final Function<Z, Stream<Y>> z2sy
 ) {
  return
   fold(
    (z, sy) -> z2sy._(z).plus(sy),
    StreamStatics::zero
   );
 }
 // end

 default public <Y>
 Stream<Y> bindF(
  final Function<Z, Y> z2y
 ) {
  return
   bindM(z ->
    one(z2y._(z))
   );
 }

 default public <Y>
 Stream<Y> bindA(
  final Stream<Function<Z, Y>> s_z2y
 ) {
  return
   bindM(z ->
    s_z2y.bindF(z2y ->
     z2y._(z)
    )
   );
 }

 default public Stream<Z> choice(
  final Function<Z, Boolean> z2b,
  final Function<Z, Stream<Z>> t_z2sz,
  final Function<Z, Stream<Z>> f_z2sz
 ) {
  return
   bindM(z ->
    z2b._(z)
     ? t_z2sz._(z)
     : f_z2sz._(z)
   );
 }

 default public Stream<Z> filter(
  final Function<Z, Boolean> z2b
 ) {
  return
   choice(
    z2b,
    z -> one(z),
    z -> zero()
   );
 }

 // begin identity_Stream_
 default public Stream<Z> identity() {
  return
   fold(
    StreamStatics::more,
    StreamStatics::done
   );
 }
 // end

 // begin length_Stream_
 default public Integer length() {
  return
   fold(
    (z, l) -> 1 + l._(),
    () -> 0
   );
 }
 // end

 default public String show() {
  return
   fold(
    (z, s) -> "one(" + z + ") : " + s._(),
    () -> new String("")
   ) + new String("zero");
 }

}
