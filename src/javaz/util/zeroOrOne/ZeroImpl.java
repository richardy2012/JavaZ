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
public class ZeroImpl<Z>
 implements ZeroOrOne<Z> {
 /**
  * the return value is the computation obtained by
  * producing the value produced by
  * the producer argument
  */
 @Override
 public <A> A traverse(
  final Producer<A> a,
  final Function<Z, A> z2a
 ) {
  return a.produce();
 }

 @Override
 public String toString() {
  return this.show();
 }
}


