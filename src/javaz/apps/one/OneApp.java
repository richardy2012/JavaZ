package javaz.apps.one;

/////////////////////////////////////////////////////////////////////////////////
//      ___________                                         ___________        //
//     /_______   /\                                       /_______   /\       //
//     \_____ /  / /  _________   ___     ___     _________\_____ /  / /       //
//           /  / /  /_____   /\ /__/\   /  /\   /_____   /\     /  / /        //
//          /  / /  _\____/  / / \  \ \ /  / /  _\____/  / /    /  / /         //
//         /  / /  /  ___   / /   \  \ /  / /  /  ___   / /    /  / /          //
//     ___/  / /  /  /__/  / /     \  /  / /  /  /__/  / /    /  /_/______     //
//    /_____/ /  /________/ /       \/__/ /  /________/ /    /___________/\    //
//    \_____\/   \________\/         \__\/   \________\/     \___________\/    //
//                                                                             //
//                     Java Functional Programming Library                     //
//                          Luc Duponcheel (ImagineJ)                          //
/////////////////////////////////////////////////////////////////////////////////

import static javaz.statics.Statics.integerAddition;
import static javaz.statics.Statics.stringConcatenation;
import static javaz.util.consumer.ConsumerFactory.integerPrinter;
import static javaz.util.consumer.ConsumerFactory.stringPrinter;
import static javaz.util.one.OneFactory.*;

public class OneApp {
 public static void main(String[] args) {
  /**
   * computation
   * that binds 1 to z
   * and then binds 2 to y
   * and then yields z + y
   */
  integerPrinter.__(
   one(1).bnd(z ->
    one(2).bnd(y ->
     one(z + y))
   ).__()
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
  integerPrinter.__(
   one(1).and(one(2).and(
    one(integerAddition))
   ).__()
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
  integerPrinter.__(
   lift2(integerAddition).__(one(2)).__(one(1)).__()
  );
  /**
   * computation
   * that binds "z" to z
   * and then binds "y" to y
   * and then yields z + y
   */
  stringPrinter.__(
   one("z").bnd(z ->
    one("y").bnd(y ->
     one(z + y))
   ).__()
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
  stringPrinter.__(
   one("z").and(one("y").and(
    one(stringConcatenation))
   ).__()
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
  stringPrinter.__(
   lift2(stringConcatenation).__(one("y")).__(one("z")).__()
  );
  /**
   * computation
   * that yields a computation
   * that yields 1
   */
  integerPrinter.__(
   one(one(1)).__().__()
  );
  /**
   * swapOnes is the identity static method
   */
  integerPrinter.__(
   swapOnes(one(one(1))).__().__()
  );
  /**
   * computation
   * that yields a computation
   * that yields "z"
   */
  stringPrinter.__(
   one(one("z")).__().__()
  );
  /**
   * swapOnes is the identity static method
   */
  stringPrinter.__(
   swapOnes(one(one("z"))).__().__()
  );
 }
}
