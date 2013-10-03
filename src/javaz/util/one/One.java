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
 * a One instance is a computation
 * resulting in exactly one value of type Z
 * <p/>
 * producing a value of type Z is not part of our DSL
 */
public interface One<Z>
 extends Producer<Z> {
 /**
  * travereseUsing's return value
  * is obtained by traversing the structure
  * of the resulting value of a One computation
  * and transforming the visited value to an output value
  * by using it as the input value of
  * travereseUsing's function parameter value
  */
 public <A> A travereseUsing(
  final Function<Z, A> z2a
 );

 /**
  * bnd's return computation value
  * is obtained by using bnd's function parameter value
  * as travereseUsing's function argument value
  * <p/>
  * from our DSL point of view:
  * mz.bnd(z -> nz) is a computation that
  * binds the resulting value of the computation mz to a variable z
  * and then continues with the computation nz (which can make use of the variable z)
  */
 default public <Y> One<Y> bnd(
  final Function<Z, One<Y>> z2my
 ) {
  return this.travereseUsing(
   z2my
  );
 }

 /**
  * from our DSL point of view:
  * mz.and(m_z2y) is a computation
  * that binds the resulting value of the computation mz to a variable z
  * and then binds the resulting function value of the computation m_z2y to a variable z2y
  * and then results in the value
  * obtained by transforming the input value z to an output value z2y.__(z)
  * using the function value z2y
  */
 default public <Y> One<Y> and(
  final One<Function<Z, Y>> m_z2y
 ) {
  return this.bnd(z ->
   m_z2y.bnd(z2y ->
    one(z2y.__(z))));
 }
}

