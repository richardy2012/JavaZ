package javaz.util.apps;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import javaz.util.concurrent.Future;
import javaz.util.concurrent.FutureStatics;
import javaz.util.concurrent.SimpleFuture;
import javaz.util.stream.Stream;
import javaz.util.stream.StreamStatics;

import static javaz.util.concurrent.FutureStatics.async;
import static javaz.util.stream.StreamStatics.one;

public class FutureApp {

 // begin sleep_FutureApp_
 private static void sleep(long time) {
  try {
   Thread.sleep(time);
  } catch (Exception e) {
   throw new IllegalStateException("not implemented");
  }
 }
 // end

 // begin mkExecutorService_FutureApp_
 private static final ExecutorService executorService =
  Executors.newScheduledThreadPool(10);
 // end

 // begin fourTimesZero_FutureApp_
 private final static
 Stream<Integer> fourTimesZero =
  one(0).plus(
   () -> one(0)
  ).plus(
   () -> one(0)
  ).plus(
   () -> one(0)
  );
 // end

 // begin future_FutureApp_
 private static Future<Integer> future(int i) {
  return
   async(() -> {
    sleep(5000);
    return 10;
   });
 }
 // end

 private static void foreachTest() {
  // begin foreachTest_FutureApp_
  // one(10) : one(10) : one(10) : one(10) : zero

  System.out.println(
   fourTimesZero.<
    Integer, Stream<Integer>,
    Future<Integer>, Future<Stream<Integer>>
    >foreach(
    StreamStatics::done, StreamStatics::more,
    FutureStatics::lift, FutureStatics::liftBF
   )._(
    i -> future(10)
   ).open()._(executorService).get()
  );
  // end
 }

 // begin fourTimesFuture_FutureApp_
 private static final
 Stream<Future<Integer>> fourTimesFutureTen =
  one(future(10)).plus(
   () -> one(future(10))
  ).plus(
   () -> one(future(10))
  ).plus(
   () -> one(future(10))
  );
 // end


 private static void sequenceTest() {
  // begin sequenceTest_FutureApp_
  // one(10) : one(10) : one(10) : one(10) : zero

  System.out.println(
   FutureStatics.<Integer>sequenceAcc(
    StreamStatics::done, StreamStatics::more
   )._(
    fourTimesFutureTen
   ).open()._(executorService).get()
  );
  // end
 }

 public static void main(String[] args) throws Exception {
  foreachTest();
  sequenceTest();
  executorService.shutdown();
 }

}
