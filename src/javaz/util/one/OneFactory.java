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
import javaz.util.producer.Producer;
import javaz.util.zeroOrOne.ZeroOrOne;
import javaz.util.zeroOrOne.ZeroOrOneFactory;

public class OneFactory {
 /**
  * return computation yields argument
  * <p/>
  * one(z) is a computation yielding the value z
  * (z is the value of the computation one(z))
  */
 public static <Z> One<Z> one(
  final Z z
 ) {
  return new OneImpl<>(z);
 }

 /**
  * joining a computation of computations
  * to a computation
  */
 public static <Z> One<Z> join(
  final One<One<Z>> mmz
 ) {
  return mmz.bnd(mz ->
   mz);
 }

 /**
  * lifts nullary functions
  * to the One level
  */
 public static <Z>
 Producer<One<Z>> lift0(
  final Producer<Z> z
 ) {
  return () -> one(z.produce());
 }

 /**
  * lifts unary functions
  * to the One level
  */
 public static <Z, Y>
 Function<One<Z>, One<Y>> lift1(
  final Function<Z, Y> z2y
 ) {
  return mz -> mz.and(
   one(z -> z2y.apply(z)));
 }

 /**
  * lifts binary functions
  * to the One level
  */
 public static <Z, Y, X>
 Function<One<Z>, Function<One<Y>, One<X>>> lift2(
  final Function<Z, Function<Y, X>> z_2_y2x
 ) {
  return mz -> my -> mz.and(my.and(
   one(y -> z -> z_2_y2x.apply(z).apply(y))));
 }

 /**
  * converts a one of ones
  * to a one of ones
  * <p/>
  */
 public static <Z> One<One<Z>> ones(
  final One<One<Z>> nmz
 ) {
  return nmz.traverseOnes(
   (One<Z> mz) -> mz).apply(
   lift1(OneFactory::one));
 }

 /**
  * converts a zero or one of ones
  * to a one of zero or ones
  * <p/>
  */
 public static <Z> One<ZeroOrOne<Z>> ones(
  final ZeroOrOne<One<Z>> nmz
 ) {
  return nmz.traverseOnes(
   () -> ZeroOrOneFactory.<Z>zero(),
   (ZeroOrOne<Z> mz) -> mz).apply(
   lift1(ZeroOrOneFactory::one));
 }
}


