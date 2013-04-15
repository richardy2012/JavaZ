package javaz.util.tuple;

// begin TupleStatics
public final class TupleStatics {
 public static <Z, Y>
 Tuple<Z, Y> tuple(
  final Z z,
  final Y y
 ) {
  return
   new Tuple<>(z, y);
 }
}
// end



