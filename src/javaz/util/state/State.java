package javaz.util.state;

import javaz.util.function.Function;
import javaz.util.tuple.Tuple;

import static javaz.util.state.StateStatics.one;

// begin State
// note: functional interface

public interface State<S, Z> {

 public Function<S, Tuple<Z, S>> open();
// end

 default public <Y>
 State<S, Y> bindF(
  final Function<Z, Y> z2y
 ) {
  return
   bindM(z ->
    one(z2y._(z))
   );
 }

 default public <Y>
 State<S, Y> bindA(
  final State<S, Function<Z, Y>> s_z2y
 ) {
  return
   bindM(z ->
    s_z2y.bindF(z2y ->
     z2y._(z)
    )
   );
 }

 // begin bindM_State_
 // note: function literal

 default public <Y>
 State<S, Y> bindM(
  final Function<Z, State<S, Y>> z2sy
 ) {
  return
   () ->
    s -> {
     Tuple<Z, S> zns = open()._(s);
     return
      z2sy._(zns._1).open()._(zns._2);
    };
 }
 // end

 default public State<S, Z> choice(
  final Function<Z, Boolean> z2b,
  final Function<Z, State<S, Z>> t_z2sz,
  final Function<Z, State<S, Z>> f_z2sz
 ) {
  return
   bindM(z ->
    z2b._(z)
     ? t_z2sz._(z)
     : f_z2sz._(z)
   );
 }

}

