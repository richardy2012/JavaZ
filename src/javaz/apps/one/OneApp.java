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
import static javaz.util.one.OneFactory.one;
import static javaz.util.one.OneFactory.lift2;

public class OneApp {
 public static void main(String[] args) {
  /**
   * computation
   * that binds "z" to z
   * and then binds "y" to y
   * and then results in z + y
   */
  stringPrinter.__(
   one("z").bnd(z ->
    one("y").bnd(y ->
     one(z + y))).__()
  );
  /**
   * computation
   * that results in
   * the string concatenation of "y" and "z"
   */
  stringPrinter.__(
   one("z").and(one("y").and(
    one(stringConcatenation))).__()
  );
  /**
   * computation
   * that results in
   * the string concatenation of "y" and "z"
   */
  stringPrinter.__(
   lift2(stringConcatenation).__(one("y")).__(one("z")).__()
  );
  /**
   * computation
   * that binds 1 to z
   * and then binds 2 to y
   * and then results in z + y
   */
  integerPrinter.__(
   one(1).bnd(z ->
    one(2).bnd(y ->
     one(z + y))).__()
  );
  /**
   * computation
   * that results in
   * the integer addition of 2 and 1
   */
  integerPrinter.__(
   one(1).and(one(2).and(
    one(integerAddition))).__()
  );
  /**
   * computation
   * that results in
   * the integer addition of 2 and 1
   */
  integerPrinter.__(
   lift2(integerAddition).__(one(2)).__(one(1)).__()
  );
 }
}
