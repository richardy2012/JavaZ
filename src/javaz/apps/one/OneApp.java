package javaz.apps.one;

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

import javaz.util.zeroOrOne.ZeroOrOneFactory;

import static javaz.statics.Statics.integerAddition;
import static javaz.statics.Statics.stringConcatenation;
import static javaz.util.consumer.ConsumerFactory.printer;
import static javaz.util.one.OneFactory.*;

public class OneApp {
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
   * ones
   */
  printer().consume(
   ones(one(one(1)))
  );
  printer().consume(
   ones(one(one("z")))
  );
  /**
   * ones
   */
  printer().consume(
   ones(ZeroOrOneFactory.one(one(1)))
  );
  printer().consume(
   ones(ZeroOrOneFactory.one(one("z")))
  );
  printer().consume(
   ones(ZeroOrOneFactory.zero())
  );
 }
}
