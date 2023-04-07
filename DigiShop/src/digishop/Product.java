package digishop;

public class Product {
    String productName;
    long price;
    int quantity;
    String comment;
    String sellerID;
    //--------------------------------------------------------------------------
    // if seller wants to see his prosucts, this constructor is used
    public Product( String productName, long price, int quantity, String comment) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
    }
    //--------------------------------------------------------------------------
    // overload initializer: if customer wants to search a product, this constructor is used
    public Product( String productName, long price, int quantity, String comment, String sellerID) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
        this.sellerID = sellerID;
    }
    //--------------------------------------------------------------------------
    public int getQuantity() {
        return quantity;
    }
    //--------------------------------------------------------------------------
    public String getComment() {
        return comment;
    }
    //--------------------------------------------------------------------------
    public long getPrice() {
        return price;
    }
    //--------------------------------------------------------------------------
    public String getProductName() {
        return productName;
    }
    //--------------------------------------------------------------------------
    public void setComment(String comment) {
        this.comment = comment;
    }
    //--------------------------------------------------------------------------
    public void setPrice(long price) {
        this.price = price;
    }
    //--------------------------------------------------------------------------
    public void setProductName(String productName) {
        this.productName = productName;
    }
    //--------------------------------------------------------------------------
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //--------------------------------------------------------------------------   
    public String getSellerID() {
        return sellerID;
    }
    //--------------------------------------------------------------------------
    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }
    //--------------------------------------------------------------------------
}
