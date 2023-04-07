package digishop;

//از اين كلاس براي نمايش در جدول تاييد واريز در منوی ادمين استفاده مي کنيم

public class DepositRequest {
    private String RequestDate;
    private String UserID;
    private Long Amount;
    //--------------------------------------------------------------------------    
    public DepositRequest(String RequestDate, String UserID, Long Amount) {
        this.RequestDate = RequestDate;
        this.UserID = UserID;
        this.Amount = Amount;
    }
    //--------------------------------------------------------------------------    
    public String getRequestDate() {
        return RequestDate;
    }

    //--------------------------------------------------------------------------    
    public String getUserID() {
        return UserID;
    }

    //--------------------------------------------------------------------------    
    public Long getAmount() {
        return Amount;
    }
    
    //--------------------------------------------------------------------------    
    public void setRequestDate(String RequestDate){
        this.RequestDate = RequestDate;
    }
    
    //--------------------------------------------------------------------------    
    public void setUserID(String UserID){
        this.UserID = UserID;
    }
    
    //--------------------------------------------------------------------------    
    public void setAmount(Long Amount){
        this.Amount = Amount;
    }
    
}
