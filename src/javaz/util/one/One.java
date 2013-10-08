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
import javaz.util.zeroOrOne.ZeroOrOne;
import javaz.util.zeroOrOne.ZeroOrOneFactory;

import static javaz.util.one.OneFactory.one;

/**
 * a One instance is a computation that
 * yields one value (of type Z)
 * <p/>
 * a One instance has a structure
 * consisting of one value
 */
public interface One<Z> {
 /**
  * the return value is the output obtained by
  * traversing the structure
  * and using the visited value
  * as the input that is transformed to an output by
  * the function argument
  */
 public <A> A traverse(
  final Function<Z, A> z2a
 );

 /**
  * the return value is the computaton obtained by
  * using the function argument
  * as argument of traverse
  * <p/>
  * mz.bnd(z -> nz) is a computation that
  * binds the values of the computation mz to variables z
  * and then continues with the computation nz
  * (which can make use of z)
  * <p/>
  * note that, for One, there is one value
  */
 default public <Y> One<Y> bnd(
  final Function<Z, One<Y>> z2my
 ) {
  return this.traverse(
   z2my
  );
 }

 /**
  * mz.and(m_z2y) is a computation
  * that binds the values of the computation mz to variables z
  * and then binds the function values of the computation m_z2y to variables z2y
  * and then yields values obtained by
  * transforming the input z to an output z2y.__(z) using the function z2y
  * <p/>
  * note that, for One,
  * there is
  * one value
  * and
  * one function value
  */
 default public <Y> One<Y> and(
  final One<Function<Z, Y>> m_z2y
 ) {
  return this.bnd(z ->
   m_z2y.bnd(z2y ->
    one(z2y.apply(z))));
 }

 /**
  * mz.bind(z2y) is a computation
  * that binds the values of the computation mz to variables z
  * and then yields values obtained by
  * transforming the input z to an output z2y.__(z) using the function z2y
  * <p/>
  * note that, for One, there is
  * one value
  * and
  * one function value
  */
 default public <Y> One<Y> bind(
  final Function<Z, Y> z2y
 ) {
  return this.and(one(z2y));
 }

 /**
  * the output computation is obtained by
  * traversing the structure
  * transforming the visited value to a computation
  * using the input function
  * and using that computation
  * as the input that is transformed to an output by
  * the lifted function argument
  */
 default public <Y, A>
 Function<Function<Z, One<Y>>, One<A>> traverseOnes(
  final Function<Y, A> y2a
 ) {
  return z2my -> traverse(
   z -> OneFactory.lift1(y2a).apply(z2my.apply(z))
  );
 }

 /**
  * the output computation is obtained by
  * traversing the structure
  * transforming the visited value to a computation
  * using the input function
  * and using that computation
  * as the input that is transformed to an output by
  * the lifted function argument
  */
 default public <Y, A>
 Function<Function<Z, ZeroOrOne<Y>>, ZeroOrOne<A>> traverseZeroOrOnes(
  final Function<Y, A> y2a
 ) {
  return z2my -> traverse(
   z -> ZeroOrOneFactory.lift1(y2a).apply(z2my.apply(z))
  );
 }

 default public String show() {
  return this.traverse(
   z -> "( " + z + " )"
  );
 }
}


