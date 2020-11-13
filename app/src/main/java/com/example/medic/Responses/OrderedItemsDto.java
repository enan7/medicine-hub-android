package com.example.medic.Responses;

        public class OrderedItemsDto {
        private Long itemId;
        private String medicineName;
        private Double medicinePrice;
        private String discount;
        private int noOfOrders;
        private int noOfItemsAvailable;
        private Double totalPrice;
        public Long getItemId() {
        return itemId;
        }
        public void setItemId(Long itemId) {
        this.itemId = itemId;
        }
        public String getMedicineName() {
        return medicineName;
        }
        public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
        }
        public Double getMedicinePrice() {
        return medicinePrice;
        }
        public void setMedicinePrice(Double medicinePrice) {
        this.medicinePrice = medicinePrice;
        }

        public String getDiscount() {
        return discount;
        }
        public void setDiscount(String discount) {
        this.discount = discount;
        }
        public int getNoOfOrders() {
        return noOfOrders;
        }
        public void setNoOfOrders(int noOfOrders) {
        this.noOfOrders = noOfOrders;
        }
        public int getNoOfItemsAvailable() {
        return noOfItemsAvailable;
        }
        public void setNoOfItemsAvailable(int noOfItemsAvailable) {
        this.noOfItemsAvailable = noOfItemsAvailable;
        }
        public Double getTotalPrice() {
        return totalPrice;
        }
        public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        }



        }
