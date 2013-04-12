package javaz.util.function;

// begin Function
// Z z = ...;
// Function<Z, Y> z2y = ...;
// Y y = z2y._(z);

public interface Function<Z, Y> {
 Y _(Z z);
}
// end
