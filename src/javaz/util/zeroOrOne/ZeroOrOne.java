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

import static javaz.util.zeroOrOne.ZeroOrOneFactory.one;

/**
 * a ZeroOrOne instance is a computation that
 * yields one value (of type Z)
 * or
 * yields zero values
 * <p/>
 * a ZeroOrOne instance has a structure
 * consisting of zero or one values
 */
public interface ZeroOrOne<Z> {
 /**
  * <p/>
  * the return value is the computation obtained by
  * traversing the structure
  * and, if no value is visited,
  * producing the value produced by
  * the producer argument
  * or, if a value is visited,
  * using that value as the input that is transformed to an output by
  * the function argument
  */
 public <A> A traverse(
  final Producer<A> a,
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
  * note that, for One,
  * there is
  * one value
  */
 default public <Y> ZeroOrOne<Y> bnd(
  final Function<Z, ZeroOrOne<Y>> z2my
 ) {
  return this.traverse(
   ZeroOrOneFactory::zero,
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
  * note that, for ZeroOrOne,
  * there are
  * zero or one values
  * and
  * zero or one function values
  */
 default public <Y> ZeroOrOne<Y> and(
  final ZeroOrOne<Function<Z, Y>> m_z2y
 ) {
  return this.bnd(z ->
   m_z2y.bnd(z2y ->
    one(z2y.__(z))));
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
 default public <Y> ZeroOrOne<Y> bind(
  final Function<Z, Y> z2y
 ) {
  return this.and(one(z2y));
 }

 /**
  * the output computation is obtained by
  * traversing the structure
  * transforming the visited value to a computation
  * using the input function
  * and, if no value is visited,
  * producing the value produced by
  * the lifted producer argument
  * or, if a value is visited,
  * using that value as the input that is transformed to an output by
  * the lifted function argument
  */
 default public <Y, A>
 Function<Function<Z, One<Y>>, One<A>> traverseOnes(
  final Producer<A> a,
  final Function<Y, A> y2a
 ) {
  return z2my -> traverse(
   OneFactory.lift0(a),
   z -> OneFactory.lift1(y2a).__(z2my.__(z))
  );
 }

 /**
  * the output computation is obtained by
  * traversing the structure
  * transforming the visited value to a computation
  * using the input function
  * and, if no value is visited,
  * producing the value produced by
  * the lifted producer argument
  * or, if a value is visited,
  * using that value as the input that is transformed to an output by
  * the lifted function argument
  */
 default public <Y, A>
 Function<Function<Z, ZeroOrOne<Y>>, ZeroOrOne<A>> traverseZeroOrOnes(
  final Producer<A> a,
  final Function<Y, A> y2a
 ) {
  return z2my -> traverse(
   ZeroOrOneFactory.lift0(a),
   z -> ZeroOrOneFactory.lift1(y2a).__(z2my.__(z))
  );
 }

 /**
  * the return computation is obtained by
  * traversing the structure
  * and, if no value is visited,
  * producing the computation produced by
  * the producer argument
  * or, if a value is visited,
  * using that value as the input that is transformed to an output by
  * one (used as a function)
  */
 default public ZeroOrOne<Z> or(
  final Producer<ZeroOrOne<Z>> mz
 ) {
  return traverse(
   mz,
   ZeroOrOneFactory::one
  );
 }

 default public String show() {
  return traverse(
   () -> "{ }",
   z -> "{ " + z + " }"
  );
 }
}
