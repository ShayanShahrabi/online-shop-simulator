package digishop;
//از اين کلاس براي نمايش در جدول سفارشات در منوی مشتري استفاده مي کنيم


public class Order {
    private String ProductName;
    private String SellerID;
    private Integer Quantity;
    private String OrderDate;
    private Long Price;
    //--------------------------------------------------------------------------    
    public Order(String ProductName, String SellerID, Integer Quantity, String OrderDate, Long Price) {
        this.ProductName = ProductName;
        this.SellerID = SellerID;
        this.Quantity = Quantity;
        this.OrderDate = OrderDate;
        this.Price = Price;
    }
    //--------------------------------------------------------------------------    
    public String getProductName() {
        return ProductName;
    }

    //--------------------------------------------------------------------------    
    public String getSellerID() {
        return SellerID;
    }

    //--------------------------------------------------------------------------    
    public Integer getQuantity() {
        return Quantity;
    }

    //--------------------------------------------------------------------------    
    public String getOrderDate() {
        return OrderDate;
    }

    //--------------------------------------------------------------------------    
    public Long getPrice() {
        return Price;
    }
    
    //--------------------------------------------------------------------------    
    public void setProductName(String productName){
        this.ProductName = productName;
    }
    
    //--------------------------------------------------------------------------    
    public void setSellerID(String sellerID){
        this.SellerID = sellerID;
    }
    
    //--------------------------------------------------------------------------    
    public void setQuantity(Integer quantity){
        this.Quantity = quantity;
    }

    //--------------------------------------------------------------------------    
    public void setOrderDate(String OrderDate){
        this.OrderDate = OrderDate;
    }

    //--------------------------------------------------------------------------    
    public void setProductName(Long price){
        this.Price = price;
    }
}
