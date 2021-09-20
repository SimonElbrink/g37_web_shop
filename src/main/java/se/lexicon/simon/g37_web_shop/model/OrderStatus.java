package se.lexicon.simon.g37_web_shop.model;

public enum OrderStatus {
    PENDING("Pending", "Customer started the checkout process but did not complete it.", 1),
    AWAITING_PAYMENT("Awaiting Payment", "Customer has completed the checkout process, but payment has yet to be confirmed.", 2),
    AWAITING_FULFILLMENT("Awaiting Fulfillment", "Customer has completed the checkout process and payment has been confirmed.", 3),
    AWAITING_SHIPMENT("Awaiting Shipment", "Order has been pulled and packaged and is awaiting collection from a shipping provider.", 4),
    AWAITING_PICKUP("Awaiting Pickup", " Order has been packaged and is awaiting customer pickup from a seller-specified location.", 5),
    PARTIALLY_SHIPPED("Partially Shipped", "Only some items in the order have been shipped.", 6),
    COMPLETED("Completed","Order has been shipped/picked up, and receipt is confirmed.", 7),
    SHIPPED("Shipped", "Order has been shipped, but receipt has not been confirmed", 8),
    CANCELLED("Cancelled","Order has been cancelled, due to a stock inconsistency or other reasons.", 9),
    DECLINED("Declined","Seller has marked the order as declined.", 10),
    REFUNDED("Refunded", "Seller has used the Refund action to refund the whole order.", 11),
    MANUAL_VERIFICATION_REQUIRED("Manual Verification Required", "Order on hold while some aspect, such as tax-exempt documentation, is manually confirmed.", 12),
    PARTIALLY_REFUNDED("Partially Refunded","Seller has partially refunded the order.", 13);

    private final String name;
    private final String description;
    private final int orderStatusNumber;

    OrderStatus(String name, String description, int orderStatusNumber) {
        this.name = name;
        this.description = description;
        this.orderStatusNumber = orderStatusNumber;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getOrderStatusNumber() {
        return orderStatusNumber;
    }

}
