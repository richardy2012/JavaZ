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
  * the value of the computation
  */
 final Z z;

 OneImpl(
  final Z z
 ) {
  this.z = z;
 }

 /**
  * produces a value by
  * returning the value of the computation
  */
 public Z __() {
  return z;
 }

 /**
  * uses the value of the computation
  * as the input of traverseUsing's function argument
  */
 @Override
 public <A> A traverseUsing(
  final Function<Z, A> z2a
 ) {
  return z2a.__(this.z);
 }
}



