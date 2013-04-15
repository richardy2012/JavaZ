package javaz.util.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

// begin SimpleFuture
public interface SimpleFuture<Z>
 extends Future<Z> {
 public Z compute(long total);

 default public Z get() {
  return
   compute(Long.MAX_VALUE);
 }

 default public Z get(long total, TimeUnit timeUnit) {
  return
   compute(MILLISECONDS.convert(total, timeUnit));
 }
 // end

 // begin notImplemented
 default public boolean isDone() {
  throw
   new IllegalStateException("not implemented");
 }

 default public boolean isCancelled() {
  throw
   new IllegalStateException("not implemented");
 }

 default public boolean cancel(boolean interruptable) {
  throw
   new IllegalStateException("not implemented");
 }
 // end

}
