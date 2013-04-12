package javaz.util.option;

public class OptionStatics {

 // begin some_OptionStatics_
 // factory method (used in library code)
 // not part of application programmer DSL

 public static <Z>
 Option<Z> some(
  final Z value
 ) {
  return
   new Some<>(value);
 }
 // end

 // begin none_OptionStatics_
 // factory method (used in library code)
 // not part of application programmer DSL

 public static <Z>
 Option<Z> none(
 ) {
  return
   new None<Z>();
 }
 // end

 // begin one_OptionStatics_
 // multiplicative method

 public static <Z>
 Option<Z> one(
  final Z z
 ) {
  return
   some(z);
 }
 // end

 // begin zero_OptionStatics_
 // additive method

 public static <Z>
 Option<Z> zero(
 ) {
  return
   none();
 }
 // end

}
