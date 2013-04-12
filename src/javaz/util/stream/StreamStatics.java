package javaz.util.stream;

import javaz.util.function.Cbn;

public class StreamStatics {

 // begin more_StreamStatics_
 public static <Z>
 Stream<Z> more(
  final Z current,
  final Cbn<Stream<Z>> next
 ) {
  return
   new More<>(current, next);
 }
 // end

 // begin done_StreamStatics_
 public static <Z>
 Stream<Z> done(
 ) {
  return
   new Done<>();
 }
 // end

 // begin one_StreamStatics_
 public static <Z>
 Stream<Z> one(
  final Z z
 ) {
  return
   more(z, StreamStatics::done);
 }
 // end

 // begin zero_StreamStatics_
 public static <Z>
 Stream<Z> zero(
 ) {
  return
   done();
 }
 // end

}
