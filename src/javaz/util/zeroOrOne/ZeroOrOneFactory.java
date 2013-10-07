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
import javaz.util.one.One;
import javaz.util.one.OneFactory;
import javaz.util.producer.Producer;

public class ZeroOrOneFactory {
 /**
  * return computation yields argument
  * <p/>
  * one(z) is a computation yielding the value z
  * (z is the value of the computation one(z))
  */
 public static <Z> ZeroOrOne<Z> one(
  final Z z
 ) {
  return new OneImpl<>(z);
 }

 /**
  * joining a computation of computations
  * to a computation
  */
 public static <Z> ZeroOrOne<Z> join(
  final ZeroOrOne<ZeroOrOne<Z>> mmz
 ) {
  return mmz.bnd(mz ->
   mz);
 }

 /**
  * lifts nullary functions
  * to the ZeroOrOne level
  */
 public static <Z>
 Producer<ZeroOrOne<Z>> lift0(
  final Producer<Z> z
 ) {
  return () -> one(z.__());
 }

 /**
  * lifts unary functions
  * to the ZeroOrOne level
  */
 public static <Z, Y>
 Function<ZeroOrOne<Z>, ZeroOrOne<Y>> lift1(
  final Function<Z, Y> z2y
 ) {
  return mz -> mz.and(
   one(z -> z2y.__(z)));
 }

 /**
  * lifts binary functions
  * to the ZeroOrOne level
  */
 public static <Z, Y, X>
 Function<ZeroOrOne<Z>, Function<ZeroOrOne<Y>, ZeroOrOne<X>>> lift2(
  final Function<Z, Function<Y, X>> z_2_y2x
 ) {
  return mz -> my -> mz.and(my.and(
   one(y -> z -> z_2_y2x.__(z).__(y))));
 }

 /**
  * converts a zero ore one of zero or ones
  * to a zero ore one of zero or ones
  * <p/>
  */
 public static <Z> ZeroOrOne<ZeroOrOne<Z>> zeroOrOnes(
  final ZeroOrOne<ZeroOrOne<Z>> nmz
 ) {
  return nmz.traverseZeroOrOnes(
   () -> ZeroOrOneFactory.<Z>zero(),
   (ZeroOrOne<Z> mz) -> mz
  ).__(lift1(ZeroOrOneFactory::one));
 }

 /**
  * converts a one of zero or ones
  * to a zero ore one of ones
  * <p/>
  */
 public static <Z> ZeroOrOne<One<Z>> zeroOrOnes(
  final One<ZeroOrOne<Z>> nmz
 ) {
  return nmz.traverseZeroOrOnes(
   (One<Z> mz) -> mz
  ).__(lift1(OneFactory::one));
 }

 /**
  * return computation yields zero values
  * <p/>
  * zero() is a computation yielding zero values
  */
 public static <Z> ZeroOrOne<Z> zero() {
  return new ZeroImpl<>();
 }

 /**
  * static version of or
  */
 public static <Z> Function<ZeroOrOne<Z>, ZeroOrOne<Z>> or(
  final ZeroOrOne<Z> mz1
 ) {
  return mz2 -> mz1.or(() -> mz2);
 }
}

