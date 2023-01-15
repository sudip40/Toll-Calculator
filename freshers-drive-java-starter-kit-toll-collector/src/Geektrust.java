import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Geektrust {
	public static void main(String[] args) {
		int cashTotal = 0;
		int fastTagTotal = 0;
		int totalFlatFee = 0;
		int totalDiscount = 0;

		try {
			List<VehicleDetails> vehicleDetails = new ArrayList<>();
			HashMap<String, Integer> vehicleCount = new HashMap<>();
			HashMap<String, Integer> vehicleAmount = new HashMap<>();
			FileInputStream fis = new FileInputStream(args[0]);
			Scanner sc = new Scanner(fis);
			while (sc.hasNextLine()) {
				String inputCommand = sc.nextLine();
				if (inputCommand.equals("PRINT_COLLECTION")) {

				} else {
					String[] str = inputCommand.split(" ");
					if (str[0].equals("FASTAG")) {
						vehicleDetails.add(new VehicleDetails(str[1], true, Integer.parseInt(str[2])));
					}

					else if (str[0].equals("COLLECT_TOLL")) {
						for (VehicleDetails v : vehicleDetails) {
							if (v.getVehicleNo().equals(str[2])) {
								v.chargeToll();
								if (vehicleCount.containsKey(v.getVehicleType())) {
									vehicleCount.put(v.getVehicleType(), vehicleCount.get(v.getVehicleType()) + 1);
								} else {
									vehicleCount.put(v.getVehicleType(), 1);
								}
							}
						}
					}
				}
			}
			for (VehicleDetails v : vehicleDetails) {
				cashTotal = cashTotal + v.getCashPayment();
				fastTagTotal = fastTagTotal + v.getFastTagPayment();
				totalFlatFee = totalFlatFee + v.getFlatFeeForCashPayment();
				totalDiscount = totalDiscount + v.getDiscount();
				if (vehicleAmount.containsKey(v.getVehicleType())) {
					vehicleAmount.put(v.getVehicleType(), vehicleAmount.get(v.getVehicleType()) + v.getCashPayment()
							+ v.getFastTagPayment() + v.getFlatFeeForCashPayment());
				} else {
					vehicleAmount.put(v.getVehicleType(),
							v.getCashPayment() + v.getFastTagPayment() + v.getFlatFeeForCashPayment());
				}
			}
			int cash = cashTotal + totalFlatFee;
			int totalAmountCollected = cashTotal + fastTagTotal + totalFlatFee;
			System.out.println("TOTAL_COLLECTION " + totalAmountCollected + " " + totalDiscount);
			System.out.println("PAYMENT_SUMMARY " + fastTagTotal + " " + cash);
			System.out.println("VEHICLE_TYPE_SUMMARY");
			List<Map.Entry<String, Integer>> sortedByAmountThenName = new ArrayList<Map.Entry<String, Integer>>(
					vehicleAmount.entrySet());
			Collections.sort(sortedByAmountThenName, new ValueThenKeyComparator<String, Integer>());
			for (Map.Entry<String, Integer> i : sortedByAmountThenName) {
				System.out.println(i.getKey() + " " + vehicleCount.get(i.getKey()));
			}
			sc.close();
		} catch (IOException e) {

		}
	}
}
