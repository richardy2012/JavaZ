package javaz.util.stream;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;
import javaz.util.function.Function;

import javaz.util.option.Option;
import javaz.util.option.OptionStatics;

import static javaz.util.stream.StreamStatics.more;
import static javaz.util.stream.StreamStatics.one;
import static javaz.util.stream.StreamStatics.plus;
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

 // begin foreachDeclaration_Stream_
 default public <Y, X, MY, MX>
 Function<Function<Z, MY>, MX> foreach(
  final Cbn<X> x,
  final BiFunction<Y, Cbn<X>, X> ynx2x,
  final Function<Cbn<X>, Cbn<MX>> lift,
  final Function<
   BiFunction<Y, Cbn<X>, X>,
   BiFunction<MY, Cbn<MX>, MX>
   > liftBF
 )
 // end

 // begin foreachDefinition_Stream_
 {
  return
   z2my ->
    fold(
     (z, mx) -> liftBF._(ynx2x)._(z2my._(z), mx),
     lift._(x)
    );
 }
 // end

 // begin foreachToOption_Stream_
 default public <Y, X>
 Function<Function<Z, Option<Y>>, Option<X>>
 foreachToOption(
  final Cbn<X> x,
  final BiFunction<Y, Cbn<X>, X> ynx2x
 ) {
  return
   foreach(
    x,
    ynx2x,
    OptionStatics::lift,
    OptionStatics::liftBF
   );
 }
 // end

 // begin constructiveForeachToOption_Stream_
 default public <Y>
 Function<
  Function<Z, Option<Y>>,
  Option<Stream<Y>>
  > constructiveForeachToOption(
 ) {
  return
   foreachToOption(
    StreamStatics::done,
    StreamStatics::more
   );
 }
 // end

 // begin additiveForeachToOption_Stream_
 default public <Y>
 Function<
  Function<Z, Option<Stream<Y>>>,
  Option<Stream<Y>>
  > additiveForeachToOption(
 ) {
  return
   foreachToOption(
    StreamStatics::zero,
    StreamStatics::plus
   );
 }
 // end

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
