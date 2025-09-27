public class TransitCalculator {

    int transitUse;
    int numberOfRides;
    String[] fareOptions = {"Pay-per-ride", "7-day Unlimited Rides", "30-day Unlimited Rides"};
    double[] prices = {2.75, 33.00, 127.00};

    public TransitCalculator(int transitUse, int numberOfRides) {
        if (transitUse <= 30) {
            this.transitUse = transitUse;
        } else {
            System.out.println("Can't exceed 30 Days");
            this.transitUse = 30; // set a safe default
        }
        this.numberOfRides = numberOfRides;
    }

    public double unlimited7Price(int days, int rides) {
        int passes = (int) Math.ceil(days / 7.0);
        double cost = prices[1] * passes;
        return cost / rides;
    }

    public double[] getRidePrices() {
        double singleRidePrice = prices[0];                 // already per ride
        double sevenDayRidesPrice = unlimited7Price(transitUse, numberOfRides);
        double thirtyDayRides = prices[2] / numberOfRides;  // per-ride cost if buying a 30-day pass

        return new double[] { singleRidePrice, sevenDayRidesPrice, thirtyDayRides };
    }

    public String getBestFare() {
        double[] perRide = getRidePrices();
        double lowestPrice = perRide[0];
        int bestIndex = 0;

        for (int i = 1; i < perRide.length; i++) {
            if (perRide[i] < lowestPrice) {
                lowestPrice = perRide[i];
                bestIndex = i;
            }
        }

        String option = fareOptions[bestIndex];
        double rounded = Math.round(lowestPrice * 100.0) / 100.0;
        return "You should get the " + option + " at $" + String.format("%.2f", rounded) + " per ride.";
    }

    public static void main(String[] args) {
        TransitCalculator tc = new TransitCalculator(12, 20);
        System.out.println(tc.getBestFare());
    }
}