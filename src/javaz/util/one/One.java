package javaz.util.one;

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

/**
 * One describes
 * computations returning (exactly) one value
 *
 * @param <Z> the type of the value returned by the computation
 */
public interface One<Z> {
 /**
  * foldAll traverses the structure of One
  * binding encountered values
  * (in this case (exactly) one)
  * to a function
  * in order to return a value
  *
  * @param <A> the type of the returned value
  * @param z2a the function the encountered values are bound to
  * @return the returned value
  */
 public <A> A foldAll(
  final Function1<Z, A> z2a
 );

 /**
  * bnd describes
  * binding a value returned by a computation to a function returning a computation
  * in order to return a computation
  *
  * @param z2my the function the value is bound to
  * @param <Y>  return type of the computation returned by the function
  * @return the returned computation
  */
 default public <Y> One<Y> bnd(
  final Function1<Z, One<Y>> z2my
 ) {
  return foldAll(
   z2my
  );
 }

 /**
  * and describes
  * binding a value returned by a computation to a function returned by a computation
  * in order to return a computation
  *
  * @param m_z2y the computation returning a function
  * @param <Y>   return type of the function returned by the computation
  * @return the returned computation
  *         <p/>
  *         note: and is defined in terms of bnd and one
  */
 default public <Y> One<Y> and(
  final One<Function1<Z, Y>> m_z2y
 ) {
  return this.bnd(z ->
   m_z2y.bnd(z2y ->
    one(z2y.__(z))));
 }
}
