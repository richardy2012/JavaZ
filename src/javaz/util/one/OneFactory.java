package javaz.util.one;

public class OneFactory {
 /**
  * one's return computation value
  * is a computation resulting in
  * one's parameter value
  * <p/>
  * from our DSL point of view:
  * one(z) is a computation resulting in the value z
  */
 public static <Z> One<Z> one(
  final Z z
 ) {
  return new OneImpl<>(z);
 }
}
