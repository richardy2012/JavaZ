package javaz.util.consumer;

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

public interface ConsumerFactory {
 /**
  * prints string values
  */
 public static final Consumer<String> stringPrinter = new Printer<>();
 /**
  * prints integer values
  */
 public static final Consumer<Integer> integerPrinter = new Printer<>();
}
