package digishop;

public class User {
    private String userID;
    private String type;
    private String phoneNumber;
    private String email;
    private String address;
    private Long wallet;
    //--------------------------------------------------------------------------
    public User(String userID, String type, String phoneNumber, String email, String address, Long wallet) {
        this.userID = userID;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.wallet = wallet;
    }
    //--------------------------------------------------------------------------
    public String getAddress() {
        return address;
    }
    //--------------------------------------------------------------------------
    public String getEmail() {
        return email;
    }
    //--------------------------------------------------------------------------
    public String getPhoneNumber() {
        return phoneNumber;
    }
    //--------------------------------------------------------------------------
    public String getUserID() {
        return userID;
    }
    //--------------------------------------------------------------------------
    public void setAddress(String address) {
        this.address = address;
    }
    //--------------------------------------------------------------------------
    public void setEmail(String email) {
        this.email = email;
    }
    //--------------------------------------------------------------------------
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //--------------------------------------------------------------------------
    public void setType(String type) {
        this.type = type;
    }
    //--------------------------------------------------------------------------
    public void setUserID(String userID) {
        this.userID = userID;
    }
    //--------------------------------------------------------------------------
    public String getType() {
        return type;
    }
    //--------------------------------------------------------------------------
    public void setWallet(Long wallet) {
        this.wallet = wallet;
    }
    //--------------------------------------------------------------------------
    public Long getWallet() {
        return wallet;
    }
    //--------------------------------------------------------------------------
}
