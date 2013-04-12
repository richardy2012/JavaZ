package javaz.util.function;

// begin BiFunction
// Z z = ...;
// Y y = ...;
// BiFunction<Z, Y, X> zny2x = ...;
// X x = zny2x._(z, y);

public interface BiFunction<Z, Y, X> {
 X _(Z z, Y y);
}
// end
