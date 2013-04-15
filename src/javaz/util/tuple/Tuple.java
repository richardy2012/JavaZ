package javaz.util.tuple;

// begin Tuple
// Tuple<Z, Y> zny = ...;
// Z z = zny._1;
// Y y = zny._2

public final class Tuple<Z, Y> {
 public final
 Z _1;
 public final
 Y _2;
// end

 Tuple(
  final Z _1,
  final Y _2
 ) {
  this._1 = _1;
  this._2 = _2;
 }

 @Override
 public String toString() {
  return
   ("(" + _1 + ", " + _2 + ")");
 }

}
