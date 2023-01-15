
public class VehicleDetails {
	private String vehicleNo;
	private boolean fastTag;
	private int fastTagBalance;
	private String vehicleType;
	private String vehicleCategory;
	private int tollCharge;
	private boolean returnJurney = false;
	private int cashPayment = 0;
	private int fastTagPayment = 0;
	private int flatFeeForCashPayment = 0;
	private int discount = 0;

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getCashPayment() {
		return cashPayment;
	}

	public void setCashPayment(int cashPayment) {
		this.cashPayment = cashPayment;
	}

	public int getFastTagPayment() {
		return fastTagPayment;
	}

	public void setFastTagPayment(int fastTagPayment) {
		this.fastTagPayment = fastTagPayment;
	}

	public int getFlatFeeForCashPayment() {
		return flatFeeForCashPayment;
	}

	public void setFlatFeeForCashPayment(int flatFeeForCashPayment) {
		this.flatFeeForCashPayment = flatFeeForCashPayment;
	}

	public String getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(String vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public int getTollCharge() {
		return tollCharge;
	}

	public void setTollCharge(int tollCharge) {
		this.tollCharge = tollCharge;
	}

	public boolean isReturnJurney() {
		return returnJurney;
	}

	public void setReturnJurney(boolean returnJurney) {
		this.returnJurney = returnJurney;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public boolean isFastTag() {
		return fastTag;
	}

	public void setFastTag(boolean fastTag) {
		this.fastTag = fastTag;
	}

	public int getFastTagBalance() {
		return fastTagBalance;
	}

	public void setFastTagBalance(int fastTagBalance) {
		this.fastTagBalance = fastTagBalance;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void chargeToll() {
		if (this.returnJurney == false) {
			if (this.fastTagBalance >= this.tollCharge) {
				this.fastTagBalance = this.fastTagBalance - this.tollCharge;
				this.fastTagPayment = this.fastTagPayment + this.tollCharge;
			} else {
				this.cashPayment = this.cashPayment + this.tollCharge - this.fastTagBalance;
				this.fastTagPayment = this.fastTagPayment + this.fastTagBalance;
				this.flatFeeForCashPayment = this.flatFeeForCashPayment + 40;
				this.fastTagBalance = 0;
			}
			this.returnJurney = true;
		} else {
			int tollcharge = this.tollCharge / 2;
			if (this.fastTagBalance >= tollcharge) {
				this.fastTagBalance = this.fastTagBalance - tollcharge;
				this.fastTagPayment = this.fastTagPayment + tollcharge;
			} else {
				this.cashPayment = this.cashPayment + tollcharge - this.fastTagBalance;
				this.fastTagPayment = this.fastTagPayment + this.fastTagBalance;
				this.flatFeeForCashPayment = this.flatFeeForCashPayment + 40;
				this.fastTagBalance = 0;
			}
			this.discount = this.discount + tollcharge;
			this.returnJurney = false;
		}
	}

	@Override
	public String toString() {
		return "VehicleDetails [vehicleNo=" + vehicleNo + ", fastTag=" + fastTag + ", fastTagBalance=" + fastTagBalance
				+ ", vehicleType=" + vehicleType + ", vehicleCategory=" + vehicleCategory + ", tollCharge=" + tollCharge
				+ ", returnJurney=" + returnJurney + ", cashPayment=" + cashPayment + ", fastTagPayment="
				+ fastTagPayment + ", flatFeeForCashPayment=" + flatFeeForCashPayment + ", discount=" + discount + "]";
	}

	public VehicleDetails(String vehicleNo, boolean fastTag, int fastTagBalance) {
		super();
		this.vehicleNo = vehicleNo;
		this.fastTag = fastTag;
		this.fastTagBalance = fastTagBalance;
		char c = vehicleNo.charAt(0);
		if (c == 'T') {
			this.vehicleType = "TRUCK";
			this.tollCharge = 200;
			this.vehicleCategory = "Heavy Vehicle";
		} else if (c == 'B') {
			this.vehicleType = "BUS";
			this.tollCharge = 200;
			this.vehicleCategory = "Heavy Vehicle";
		} else if (c == 'V') {
			this.vehicleType = "VAN";
			this.tollCharge = 100;
			this.vehicleCategory = "Light Vehicle";
		} else if (c == 'C') {
			this.vehicleType = "CAR";
			this.tollCharge = 100;
			this.vehicleCategory = "Light Vehicle";
		} else if (c == 'R') {
			this.vehicleType = "RICKSHAW";
			this.tollCharge = 100;
			this.vehicleCategory = "Light Vehicle";
		} else if (c == 'S') {
			this.vehicleType = "SCOOTER";
			this.tollCharge = 50;
			this.vehicleCategory = "Two Wheeler";
		} else if (c == 'M') {
			this.vehicleType = "MOTORBIKE";
			this.tollCharge = 50;
			this.vehicleCategory = "Two Wheeler";
		}
	}

}
