package javaz.util.apps;

import javaz.util.function.Cbn;
import javaz.util.function.Function;

import javaz.util.state.State;
import javaz.util.state.StateStatics;
import javaz.util.stream.Stream;
import javaz.util.stream.StreamStatics;
import javaz.util.unit.Unit;

import static javaz.util.state.StateStatics.exec;
import static javaz.util.stream.StreamStatics.one;
import static javaz.util.unit.UnitStatics.unit;


import javaz.util.apps.Machine;

import static javaz.util.apps.Action.COIN;
import static javaz.util.apps.Action.CANDY;
import static javaz.util.apps.Status.FREE;
import static javaz.util.apps.Status.BUSY;

public class StateApp {
 // begin actions_StateApp_
 private static final Stream<Action> coin =
  one(COIN);
 private static final Stream<Action> candy =
  one(CANDY);
 private static final Stream<Action> actions =
  coin.plus(
   () -> candy
  ).plus(
   () -> coin
  ).plus(
   () -> candy
  );
 // end

 // begin stateMachinePartOne_StateApp_
 private static State<Machine, Unit> stateMachine(
  final Stream<Action> actions
 ) {
  return
   actions.<
    Unit, Unit,
    State<Machine, Unit>, State<Machine, Unit>
    >foreach(
    () -> unit, (u, v) -> unit,
    StateStatics::lift, StateStatics::liftBF
   )
    // end

    // begin stateMachinePartTwo_StateApp_
    ._(a ->
     exec(
      m ->
       m.candies == 0 ||
        a == COIN && m.status == BUSY ||
        a == CANDY && m.status == FREE
        ? m
        : a == COIN && m.status == FREE
        ? new Machine(BUSY, m.candies, m.coins + 1)
        : a == CANDY && m.status == BUSY
        ? new Machine(FREE, m.candies - 1, m.coins)
        : null // this should never happen
     )
    );
 }
 // end

 // begin main_StateApp_
 public static void main(String[] args) {
  System.out.println(stateMachine(actions).open()._(
   new Machine(FREE, 10, 0)
  )._2
  );
 }
 // end

}
