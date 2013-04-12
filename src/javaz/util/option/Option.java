package javaz.util.option;

import javaz.util.function.Cbn;
import javaz.util.function.Function;
import javaz.util.function.FunctionStatics;

import javaz.util.stream.Stream;
import javaz.util.stream.StreamStatics;

import static javaz.util.option.OptionStatics.one;
import static javaz.util.option.OptionStatics.zero;

// begin Option
public interface Option<Z> {
// end

 // begin fold_Option_
 // abstract method
 // follows the structure of Option

 public <Y>
 Y fold(
  final Function<Z, Y> z2y,
  final Cbn<Y> y
 );
 // end

 // begin bindM_Option_
 // binding is fundamental (functional) programming concept

 default public <Y>
 Option<Y> bindM(
  final Function<Z, Option<Y>> z2oy
 ) {
  return
   fold(
    z2oy,
    OptionStatics::none
   );
 }
 // end

 // begin bindF_Option_
 // typically used at the end of a binding chain

 default public <Y>
 Option<Y> bindF(
  final Function<Z, Y> z2y
 ) {
  return
   bindM(z ->
    one(z2y._(z))
   );
 }
 // end

 // begin bindA_Option_
 // multiplicative method
 // note: z is only used within the scope of bindF

 default public <Y>
 Option<Y> bindA(
  final Option<Function<Z, Y>> o_z2y
 ) {
  return
   bindM(z ->
    o_z2y.bindF(z2y ->
     z2y._(z)
    )
   );
 }
 // end

 // begin choice_Option_
 default public Option<Z> choice(
  final Function<Z, Boolean> z2b,
  final Function<Z, Option<Z>> t_z2oz,
  final Function<Z, Option<Z>> f_z2oz
 ) {
  return
   bindM(z ->
    z2b._(z)
     ? t_z2oz._(z)
     : f_z2oz._(z)
   );
 }
 // end

 // begin filter_Option_
 default public Option<Z> filter(
  final Function<Z, Boolean> z2b
 ) {
  return
   choice(
    z2b,
    z -> one(z),
    z -> zero()
   );
 }
 // end

 // begin plus_Option_
 // additive method

 default public Option<Z> plus(
  final Cbn<Option<Z>> _2oz
 ) {
  return
   fold(
    OptionStatics::some,
    _2oz
   );
 }
 // end

 // begin foreachDeclaration_Option_
 default public <Y, X, MY, MX>
 Function<Function<Z, MY>, MX> foreach(
  final Cbn<X> x,
  final Function<Y, X> y2x,
  final Function<Cbn<X>, Cbn<MX>> lift,
  final Function<Function<Y, X>, Function<MY, MX>> liftF
 )
 // end

 // begin foreachDefinition_Option_
 {
  return
   z2my ->
    fold(
     z -> liftF._(y2x)._(z2my._(z)),
     lift._(x)
    );
 }
 // end

 // begin foreachToStream_Option_
 default public <Y, X>
 Function<
  Function<Z, Stream<Y>>,
  Stream<X>
  > foreachToStream(
  final Cbn<X> x,
  final Function<Y, X> y2x
 ) {
  return
   foreach(
    x,
    y2x,
    StreamStatics::lift,
    StreamStatics::liftF
   );
 }
 // end

 // begin additiveForeachToStream_Option_
 default public <Y>
 Function<
  Function<Z, Stream<Option<Y>>>,
  Stream<Option<Y>>
  > additiveForeachToStream(
 ) {
  return
   foreachToStream(
    OptionStatics::zero,
    FunctionStatics::identity
   );
 }
 // end

 // begin identity_Option_
 default public Option<Z> identity() {
  return
   fold(
    OptionStatics::some,
    OptionStatics::none
   );
 }
 // end

 // begin length_Option_
 default public int length() {
  return
   fold(
    z -> 1,
    () -> 0
   );
 }
 // end

 default public String show() {
  return
   fold(
    z -> new String("one(" + z + ")"),
    () -> new String("zero")
   );
 }

}
