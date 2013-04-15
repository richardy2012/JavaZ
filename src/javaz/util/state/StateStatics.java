package javaz.util.state;

import javaz.util.function.BiFunction;
import javaz.util.function.Cbn;
import javaz.util.function.Function;
import javaz.util.unit.Unit;

import static javaz.util.tuple.TupleStatics.tuple;
import static javaz.util.unit.UnitStatics.unit;

public class StateStatics {

 public static <S, Z>
 Function<State<S, State<S, Z>>, State<S, Z>> join(
 ) {
  return
   ssz ->
    ssz.bindM(sz ->
     sz
    );
 }

 // begin one_StateStatics_
 public static <S, Z>
 State<S, Z> one(
  final Z z
 ) {
  return
   () ->
    s ->
     tuple(z, s);
 }
 // end

 public static <S, Z>
 Cbn<State<S, Z>> lift(
  final Cbn<Z> z
 ) {
  return
   () -> one(z._());
 }

 static <S, Z, Y>
 Function<State<S, Z>, State<S, Y>> liftF(
  final Function<Z, Y> z2y
 ) {
  return
   sz ->
    sz.bindF(z2y);
 }

 static <S, Z, Y>
 Function<State<S, Z>, State<S, Y>> liftA(
  final State<S, Function<Z, Y>> l_z2y
 ) {
  return
   sz ->
    sz.bindA(l_z2y);
 }

 public static <S, Z, Y, X>
 BiFunction<State<S, Z>, Cbn<State<S, Y>>, State<S, X>> liftBF(
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

 // begin get_StateStatics_
 public static <S>
 State<S, S> get(
 ) {
  return
   () ->
    s ->
     tuple(s, s);
 }
 // end

 // begin set_StateStatics_
 static <S>
 State<S, Unit> set(
  final S newS
 ) {
  return
   () ->
    oldS ->
     tuple(unit, newS);
 }
 // end

 // begin exec_StateStatics_
 public static <S>
 State<S, Unit> exec(
  final Function<S, S> s2s
 ) {
  return
   StateStatics.<S>get().bindM(s ->
    set(s2s._(s))
   );
 }
 // end

}
