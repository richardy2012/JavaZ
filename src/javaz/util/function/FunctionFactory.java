package javaz.util.function;

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

public class FunctionFactory {
 /**
  * id's return value is the same as id's argument
  */
 public static <Z> Z id(final Z z) {
  return z;
 }
}
