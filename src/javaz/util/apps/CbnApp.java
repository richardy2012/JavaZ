package javaz.util.apps;

import javaz.util.function.Block;
import javaz.util.function.Cbn;
import javaz.util.function.Function;
import javaz.util.ref.Ref;

public class CbnApp {
 // begin while_CbnApp_
 private static Function<Block, Block> _while(
  Cbn<Boolean> cond
 ) {
  return
   block ->
    () -> {
     if (cond._()) {
      block._();
      _while(cond)._(block)._();
     }
     ;
    };
 }
 // end

 // begin example_CbnApp_
 // before 0
 // after 10

 public static void main(String[] args) {
  Ref<Integer> integerRef = new Ref<>();
  integerRef.assign(0);
  System.out.println("before " + integerRef.deref());
  _while(() ->
   (integerRef.deref() < 10))._(() -> {
   integerRef.assign(integerRef.deref() + 1);
  })._();
  System.out.println("after " + integerRef.deref());
 }
 // end

}
