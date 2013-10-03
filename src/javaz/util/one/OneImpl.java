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

/**
 * OneImpl is the only implementation of One
 */
public class OneImpl<Z>
 implements One<Z> {
 /**
  * the resulting value of the computation
  */
 final Z one;

 OneImpl(
  final Z one
 ) {
  this.one = one;
 }

 /**
  * transforms the resulting value
  * to an output value of type A
  * by using it as the input value of
  * the function parameter value of type Function<Z, A>
  */
 @Override
 public <A> A travereseUsing(
  final Function<Z, A> z2a
 ) {
  return z2a.__(this.one);
 }
}



