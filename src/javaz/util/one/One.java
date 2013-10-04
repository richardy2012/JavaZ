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

import static javaz.util.one.OneFactory.one;

/**
 * a One instance is a computation that
 * does nothing but yielding exactly one value (of type Z)
 * <p/>
 * a One computation also produces a value (of type Z),
 * but, producing a value is NOT part of the computation DSL,
 * instead, it is supposed to be used by applications to run the computation
 */
public interface One<Z>
 extends Producer<Z> {
 /**
  * a One instance has a structure
  * (consisting of exactly one value)
  * <p/>
  * the return value is obtained by
  * traversing this structure
  * and using the visited value
  * as the input that is transformed to an output by
  * the function argument
  */
 public <A> A traverseUsing(
  final Function<Z, A> z2a
 );

 /**
  * bnd's return computation is obtained by
  * using bnd's function argument
  * as traverseUsing's function argument
  * <p/>
  * from our DSL point of view:
  * mz.bnd(z -> nz) is a computation that
  * binds the values of the computation mz to a variable z
  * and then continues with the computation nz
  * (which can make use of the variable z)
  * <p/>
  * note that, for One, there is exactly one such value
  */
 default public <Y> One<Y> bnd(
  final Function<Z, One<Y>> z2my
 ) {
  return this.traverseUsing(
   z2my
  );
 }

 /**
  * from our DSL point of view:
  * mz.and(m_z2y) is a computation
  * that binds the values of the computation mz to a variable z
  * and then binds the function values of the computation m_z2y to a variable z2y
  * and then yields values obtained by
  * transforming the input z to an output z2y.__(z) using the function z2y
  */
 default public <Y> One<Y> and(
  final One<Function<Z, Y>> m_z2y
 ) {
  return this.bnd(z ->
   m_z2y.bnd(z2y ->
    one(z2y.__(z))));
 }

 /**
  * general looping method
  * <p/>
  * the return value is obtained by
  * traversing the One structure,
  * transforming the visited value to a One computation
  * and using that computation
  * as the input computation that is transformed to an output computation by
  * the lifted function argument
  */
 default public <Y, A>
 Function<Function<Z, One<Y>>, One<A>> traverseOnesUsing(
  final Function<Y, A> y2a
 ) {
  return z2my -> traverseUsing(
   z -> OneFactory.lift1(y2a).__(z2my.__(z))
  );
 }
}


