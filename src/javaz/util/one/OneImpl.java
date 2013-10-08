package javaz.util.one;

//      ___________                                         ___________
//     /_______   /\                                       /_______   /\
//     \_____ /  / /  _________   ___     ___     _________\_____ /  / /
//           /  / /  /_____   /\ /__/\   /  /\   /_____   /\     /  / /
//          /  / /  _\____/  / / \  \ \ /  / /  _\____/  / /    /  / /
//         /  / /  /  ___   / /   \  \ /  / /  /  ___   / /    /  / /
//     ___/  / /  /  /__/  / /     \  /  / /  /  /__/  / /    /  /_/_____
//    /_____/ /  /________/ /       \/__/ /  /________/ /    /__________/\
//    \_____\/   \________\/         \__\/   \________\/     \__________\/
//
//                     Java Functional Programming Library
//                          Luc Duponcheel (ImagineJ)

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
  * the return value is the output obtained by
  * using the value of the computation as the input that is transformed to an output by
  * the function argument
  */
 @Override
 public <A> A traverse(
  final Function<Z, A> z2a
 ) {
  return z2a.apply(this.z);
 }

 @Override
 public String toString() {
  return this.show();
 }
}



