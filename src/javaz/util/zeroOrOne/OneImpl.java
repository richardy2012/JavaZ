package javaz.util.zeroOrOne;

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
import javaz.util.producer.Producer;

/**
 * OneImpl is one of both implementations of ZeroOrOne
 */
public class OneImpl<Z>
 implements ZeroOrOne<Z> {
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
  * the return value is the cmputation obtained by
  * using the value of the computation as the input that is transformed to an output by
  * the function argument
  */
 @Override
 public <A> A traverse(
  final Producer<A> a,
  final Function<Z, A> z2a
 ) {
  return z2a.apply(this.z);
 }

 @Override
 public String toString() {
  return this.show();
 }
}

