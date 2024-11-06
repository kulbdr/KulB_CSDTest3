package com.example.kulb_csd214test3;

public class order {
    private int customerId;
    private String customerName;
    private String mobileNumber;
    private String pizzaSize;
    private String toppingNumber;
    private String totalBill;

    public order(int customerId, String customerName, String mobileNumber, String pizzaSize, String toppingNumber, String totalBill) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.pizzaSize = pizzaSize;
        this.toppingNumber = toppingNumber;
        this.totalBill = totalBill;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getToppingNumber() {
        return toppingNumber;
    }

    public void setToppingNumber(String toppingNumber) {
        this.toppingNumber = toppingNumber;
    }

    public String getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }
}
