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
  printer().__(
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
  printer().__(
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
  printer().__(
   lift2(integerAddition).__(one(2)).__(one(1))
  );
  /**
   * computation
   * that binds "z" to z
   * and then binds "y" to y
   * and then yields z + y
   */
  printer().__(
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
  printer().__(
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
  printer().__(
   lift2(stringConcatenation).__(one("y")).__(one("z"))
  );
  /**
   * computation
   * that yields a computation
   * that yields 1
   */
  printer().__(
   one(one(1))
  );
  /**
   * computation
   * that yields 1
   */
  printer().__(
   join(one(one(1)))
  );
  /**
   * computation
   * that yields a computation
   * that yields "z"
   */
  printer().__(
   one(one("z"))
  );
  /**
   * computation
   * that yields "z"
   */
  printer().__(
   join(one(one("z")))
  );
  /**
   * zeroOrOnes
   */
  printer().__(
   zeroOrOnes(one(one(1)))
  );
  /**
   * zeroOrOnes
   */
  printer().__(
   zeroOrOnes(OneFactory.one(one(1)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   zero()
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   integerZero().bnd(z ->
    one(2).bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   one(1).bnd(z ->
    integerZero().bnd(y ->
     one(z + y)))
  );

  /**
   * computation
   * that yields zero values
   */
  printer().__(
   integerZero().bnd(z ->
    integerZero().bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   stringZero().bnd(z ->
    one("y").bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   one("z").bnd(z ->
    stringZero().bnd(y ->
     one(z + y)))
  );

  /**
   * computation
   * that yields zero values
   */
  printer().__(
   stringZero().bnd(z ->
    stringZero().bnd(y ->
     one(z + y)))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   integerZero().and(one(2)
    .bind(integerAddition))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   one(1).and(integerZero()
    .bind(integerAddition))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   integerZero().and(integerZero()
    .bind(integerAddition))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   stringZero().and(one("y")
    .bind(stringConcatenation))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   one("z").and(stringZero()
    .bind(stringConcatenation))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   stringZero().and(stringZero()
    .bind(stringConcatenation))
  );
  /**
   * zeroOrOnes
   */
  printer().__(
   zeroOrOnes(one(zero()))
  );
  printer().__(
   zeroOrOnes(zero())
  );
  /**
   * zeroOrOnes
   */
  printer().__(
   zeroOrOnes(OneFactory.one(zero()))
  );
  /**
   * computation
   * that yields 1
   */
  printer().__(
   one(1).or(
    () -> one(2))
  );
  /**
   * computation
   * that yields 1
   */
  printer().__(
   one(1).or(
    () -> zero())
  );
  /**
   * computation
   * that yields 2
   */
  printer().__(
   zero().or(
    () -> one(2))
  );
  /**
   * computation
   * that yields zero values
   */
  printer().__(
   zero().or(
    () -> zero())
  );
 }
}
