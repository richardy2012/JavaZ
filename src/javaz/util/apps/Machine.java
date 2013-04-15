package javaz.util.apps;

// begin Machine_StateApp_
public class Machine {
 Status status;
 int candies;
 int coins;
// end

 Machine(Status status, int candies, int coins) {
  this.status = status;
  this.candies = candies;
  this.coins = coins;
 }

 @Override
 public String toString() {
  return
   "status = " + status + "\n" +
    "candies = " + candies + "\n" +
    "coins = " + coins;
 }
}
