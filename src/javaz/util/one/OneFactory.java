package javaz.util.one;

/////////////////////////////////////////////////////////////////////////////////
//      ___________                                         ___________        //
//     /_______   /\                                       /_______   /\       //
//     \_____ /  / /  _________   ___     ___     _________\_____ /  / /       //
//           /  / /  /_____   /\ /__/\   /  /\   /_____   /\     /  / /        //
//          /  / /  _\____/  / / \  \ \ /  / /  _\____/  / /    /  / /         //
//         /  / /  /  ___   / /   \  \ /  / /  /  ___   / /    /  / /          //
//     ___/  / /  /  /__/  / /     \  /  / /  /  /__/  / /    /  /_/______     //
//    /_____/ /  /________/ /       \/__/ /  /________/ /    /___________/\    //
//    \_____\/   \________\/         \__\/   \________\/     \___________\/    //
//                                                                             //
//                     Java Functional Programming Library                     //
//                          Luc Duponcheel (ImagineJ)                          //
/////////////////////////////////////////////////////////////////////////////////

import javaz.util.function.Function;
import javaz.util.producer.Producer;

public class OneFactory {
 /**
  * one's return computation yields one's argument
  * <p/>
  * from our DSL point of view:
  * one(z) is a computation yielding the value z
  * (z is the value of the computation one(z))
  */
 public static <Z> One<Z> one(
  final Z z
 ) {
  return new OneImpl<>(z);
 }

 /**
  * lifts Z nullary functions
  * to One&lt;Z&gt; nullary functions
  */
 public static <Z>
 Producer<One<Z>> lift0(
  final Producer<Z> pz
 ) {
  return () -> one(pz.__());
 }

 /**
  * lifts Z to Y unary functions
  * to One&lt;Z&gt; to One&lt;Y&gt; unary functions
  */
 public static <Z, Y>
 Function<One<Z>, One<Y>> lift1(
  final Function<Z, Y> z2y
 ) {
  return mz -> mz.and(
   one(z -> z2y.__(z)));
 }

 /**
  * lifts Z to Y to X binary functions
  * to One&lt;Z&gt; to One&lt;Y&gt; to One&lt;Z&gt; binary functions
  */
 public static <Z, Y, X>
 Function<One<Z>, Function<One<Y>, One<X>>> lift2(
  final Function<Z, Function<Y, X>> z_2_y2x
 ) {
  return mz -> my -> mz.and(my.and(
   one(y -> z -> z_2_y2x.__(z).__(y))));
 }
}

/*







 */
