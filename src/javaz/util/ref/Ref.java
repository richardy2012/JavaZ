package javaz.util.ref;

// begin Ref
public class Ref<Z> {
 private Z value;

 public Z deref() {
  return value;
 }

 public void assign(Z value) {
  this.value = value;
 }
}
// end
