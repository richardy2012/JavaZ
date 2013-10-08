package javaz.apps.zeroOrOne;

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

import javaz.util.one.OneFactory;
import javaz.util.zeroOrOne.ZeroOrOne;

import static javaz.statics.Statics.integerAddition;
import static javaz.statics.Statics.stringConcatenation;
import static javaz.util.consumer.ConsumerFactory.printer;
import static javaz.util.zeroOrOne.ZeroOrOneFactory.*;

public class ZeroOrOneApp {
 private static ZeroOrOne<Integer> integerZero() {
  return zero();
 }

 private static ZeroOrOne<String> stringZero() {
  return zero();
 }

 public static void main(String[] args) {
  /**
   * computation
   * that binds 1 to z
   * and then binds 2 to y
   * and then yields z + y
   */
  printer().consume(
   one(1).bnd(z ->
    one(2).bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields
   * the integer addition of 2 and 1
   *
   * note
   *  - no intermediate variables used
   * order (from left to right)
   *  - first 1
   *  - second 2
   */
  printer().consume(
   one(1).and(one(2)
    .bind(integerAddition))
  );
  /**
   * computation
   * that yields
   * the integer addition of 2 and 1
   *
   * note
   *  - integerAddition is a reusable fragment
   *  - lift2 is a reusable template
   * order (from left to right)
   *  - first 2
   *  - second 1
   */
  printer().consume(
   lift2(integerAddition).apply(one(2)).apply(one(1))
  );
  /**
   * computation
   * that binds "z" to z
   * and then binds "y" to y
   * and then yields z + y
   */
  printer().consume(
   one("z").bnd(z ->
    one("y").bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields
   * the string concatenation of "y" and "z"
   *
   * note
   *  - no intermediate variables used
   * order (from left to right)
   *  - first "z"
   *  - second "y"
   */
  printer().consume(
   one("z").and(one("y")
    .bind(stringConcatenation))
  );
  /**
   * computation
   * that yields
   * the string concatenation of "y" and "z"
   *
   * note
   *  - stringConcatenation is a reusable fragment
   *  - lift2 is a reusable template
   * order (from left to right)
   *  - first "y"
   *  - second "z"
   */
  printer().consume(
   lift2(stringConcatenation).apply(one("y")).apply(one("z"))
  );
  /**
   * computation
   * that yields a computation
   * that yields 1
   */
  printer().consume(
   one(one(1))
  );
  /**
   * computation
   * that yields 1
   */
  printer().consume(
   join(one(one(1)))
  );
  /**
   * computation
   * that yields a computation
   * that yields "z"
   */
  printer().consume(
   one(one("z"))
  );
  /**
   * computation
   * that yields "z"
   */
  printer().consume(
   join(one(one("z")))
  );
  /**
   * zeroOrOnes
   */
  printer().consume(
   zeroOrOnes(one(one(1)))
  );
  /**
   * zeroOrOnes
   */
  printer().consume(
   zeroOrOnes(OneFactory.one(one(1)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   zero()
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   integerZero().bnd(z ->
    one(2).bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   one(1).bnd(z ->
    integerZero().bnd(y ->
     one(z + y)))
  );

  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   integerZero().bnd(z ->
    integerZero().bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   stringZero().bnd(z ->
    one("y").bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   one("z").bnd(z ->
    stringZero().bnd(y ->
     one(z + y)))
  );

  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   stringZero().bnd(z ->
    stringZero().bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   integerZero().and(one(2)
    .bind(integerAddition))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   one(1).and(integerZero()
    .bind(integerAddition))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   integerZero().and(integerZero()
    .bind(integerAddition))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   stringZero().and(one("y")
    .bind(stringConcatenation))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   one("z").and(stringZero()
    .bind(stringConcatenation))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   stringZero().and(stringZero()
    .bind(stringConcatenation))
  );
  /**
   * computation
   * that yields 1
   */
  printer().consume(
   one(1).filter(i -> i % 2 == 1)
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   one(1).filter(i -> i % 2 == 0)
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   integerZero().filter(i -> i % 2 == 0)
  );
  /**
   * computation
   * that yields "z"
   */
  printer().consume(
   one("z").filter(s -> s.equals("z"))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   one("z").filter(s -> s.equals("y"))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   stringZero().filter(s -> s.equals("y"))
  );
  /**
   * zeroOrOnes
   */
  printer().consume(
   zeroOrOnes(one(zero()))
  );
  printer().consume(
   zeroOrOnes(zero())
  );
  /**
   * zeroOrOnes
   */
  printer().consume(
   zeroOrOnes(OneFactory.one(zero()))
  );
  /**
   * computation
   * that yields 1
   */
  printer().consume(
   one(1).or(
    () -> one(2))
  );
  /**
   * computation
   * that yields 1
   */
  printer().consume(
   one(1).or(
    () -> zero())
  );
  /**
   * computation
   * that yields 2
   */
  printer().consume(
   zero().or(
    () -> one(2))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().consume(
   zero().or(
    () -> zero())
  );
 }
}
