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
 * a One instance describes a computation
 * resulting in exactly one value of type Z
 *
 * @param <Z> the type of the resulting value of the computation
 */
public interface One<Z> {
 /**
  * the travereseUsing method traverses
  * the structure of the resulting value of a One computation
  * and returns a value of type A
  * by transforming the visited value of type Z
  * to an output value of type A
  * by using it as the input value of
  * the function parameter value of type Function<Z, A>
  *
  * @param <A> the type of the return value
  * @param z2a the function parmeter value
  * @return the return value
  */
 public <A> A travereseUsing(
  final Function<Z, A> z2a
 );
}
