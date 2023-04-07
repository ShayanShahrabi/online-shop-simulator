package digishop;

import java.sql.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DataProcess {
    
    String connectionString = "jdbc:sqlite:digital_shop.db";

    //--------------------------------------------------------------------------    
    public void Insert2Users(String userID, String password, String type, String email, String address, String phoneNumber) throws SQLException{

        String SQLInsert = "Insert Into Users (user_id, pass, type, email, Address, phone_number, wallet) Values( '" +
                           userID.trim() + "', '" + password.trim() + "', '" + type.trim() + "', '" + email.trim() + "', '" + 
                           address.trim() + "', '" + phoneNumber + "', 0)";
        
        String SQLSelect = "SELECT user_id FROM Users WHERE user_id = ?";       
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Util.showAlert(Alert.AlertType.ERROR, "Duplicated ID!");
                return;
            }            
            // 
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQLInsert);
            //
            Util.showAlert(Alert.AlertType.INFORMATION, "User added successfully.");
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                
    }
    //--------------------------------------------------------------------------    
    public String IfUserExist(String userID, String pass){
        
        String Result = "";
        
        String SQLSelect = "SELECT type FROM Users WHERE user_id = ? and pass = ?";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            pstmt.setString(1, userID);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Result = rs.getString("type").trim();
            }            
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                        
        return Result;
    }
    //--------------------------------------------------------------------------
    public void insert2Transactions (String userID, String reqDate, Long amount, String type, boolean confirmed){
        String SQLInsert = "Insert Into Transactions (user_id, req_date, amount, type, confirmed) Values( '" +
                           userID.trim() + "', '" + reqDate.trim() + "', " + amount + ", '" + type.trim() + "', " + confirmed + ")";

        Connection conn = null;    
        PreparedStatement pstmt = null;
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);            
            // 
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQLInsert);
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                
    }
    //--------------------------------------------------------------------------
    public void getProductList(ComboBox cb){
        
        String SQLSelect = "SELECT product_name FROM products Order by product_name";       
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cb.getItems().add(rs.getString("product_name").trim());
            }            
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                    
    }

    //--------------------------------------------------------------------------
    public void getSellerList(ComboBox cb){
        
        String SQLSelect = "SELECT user_id FROM users Where type = 'Seller' Order by user_id";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cb.getItems().add(rs.getString("user_id").trim());
            }            
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                    
    }
    
    //--------------------------------------------------------------------------
    public void fillSellerListDependOnProduct(String ProductName, ComboBox cbSeller){
        
        //ابتدا ليست خالي شود
        cbSeller.getItems().clear();
        
        String SQLSelect = "SELECT seller_id FROM products Where product_name = '" + ProductName + "' Order by seller_id";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cbSeller.getItems().add(rs.getString("seller_id").trim());
            }            
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
    }
    
    //--------------------------------------------------------------------------
    public int getInventory(String productName, String sellerID){
        int inventory = 0;        
        
        String SQLSelect = "SELECT quantity From products Where product_name = '" + productName + "' And seller_id = '" + sellerID + "'";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                inventory = rs.getInt("quantity");
            }
            else{
                inventory = -1;
            }                
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
        return inventory;
    }
    
    //--------------------------------------------------------------------------
    public Long getProductPrice(String productName, String sellerID){
        Long price = 0L;
        
        String SQLSelect = "SELECT price From products Where product_name = '" + productName + "' And seller_id = '" + sellerID + "'";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                price = rs.getLong("price");
            }
            else{
                price = -1L;
            }                
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
        return price;
    }
    
    //--------------------------------------------------------------------------
    public void insert2Orders(String userID, String productName, String sellerID, Integer quantity, 
                              String todaysDate, Long price, Boolean purchased, Long websiteProfit){
        
        String SQLInsert = "Insert Into Orders (user_id, product_name, seller_id, quantity, order_date, price, purchased, website_profit)" + 
                           "Values( '" + userID.trim() + "', '" + productName.trim() + "', '" + sellerID.trim() + "', " + quantity + ", '" + todaysDate + "', " + 
                           price + ", " + purchased + ", " + websiteProfit + ")";
        
        Connection conn = null;    
        PreparedStatement pstmt = null;
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);            
            // 
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQLInsert);
            //
            Util.showAlert(Alert.AlertType.INFORMATION, "Order added to your cart!");
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                
    }

    //--------------------------------------------------------------------------
    public void fillObservableListOfOrders(ObservableList observableList, String userID){
        
        String SQLSelect = "SELECT * From Orders Where user_id = '" + userID + "' And purchased = 0 Order By order_date, product_name";
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                String date = rs.getString("order_date").trim();
                String productName = rs.getString("product_name").trim();
                String sellerID = rs.getString("seller_id").trim();
                Integer quantity = rs.getInt("quantity");
                Long price = rs.getLong("price");
                observableList.add(new Order(productName, sellerID, quantity, date, price));
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
    }
    
    //--------------------------------------------------------------------------
    public Boolean isEnventoryEnough(String userID){
        Long sum, wallet;
        Boolean result = false;
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            //يافتن جمع سفارشات
            String SQLSelect = "SELECT Sum(price) As Result From Orders Where user_id = '" + userID + "' And purchased = 0";
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sum = rs.getLong("Result");
            }
            else{
                sum = 0L;
            }
            //يافتن موجودی كاربر
            SQLSelect = "SELECT Wallet From Users Where user_id = '" + userID + "'";
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                wallet = rs.getLong("Wallet");
            }
            else{
                wallet = 0L;
            }
            //حالا موجودي و جمع شفارش مقايسه شود
            if(sum < wallet){
                result = true;
            }
            else{
                result = false;
            }
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
        return result;    
    }


    //--------------------------------------------------------------------------
    //اين روتين تست ميكند آيا موجودي سفارشات در حاي حاضر كافي هست يا خير. زيرا تنها با ثبت سفارش موجودي كالا كسر نميشود
    //اگر موجودي كافي نباشد نام كالا برگردانده ميشود در غير اينصورت بلنك بازگردانه ميشود
    public String isOrderdProductsExist(String userID)    
    {
        String result = "", productName, sellerID;
        Integer orderQuantity, productQuantity;
        
                                
        Connection conn = null;    
        PreparedStatement pstmtOrder = null;
        ResultSet rsOrder = null;  
        PreparedStatement pstmtProduct = null;
        ResultSet rsProduct = null;
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            String SQLSelect = "SELECT * From Orders Where user_id = '" + userID + "' And purchased = 0 Order By order_date, product_name";
            pstmtOrder = conn.prepareStatement(SQLSelect);
            rsOrder = pstmtOrder.executeQuery();
            while (rsOrder.next()) {
                productName = rsOrder.getString("product_name").trim();
                sellerID = rsOrder.getString("seller_id").trim();
                orderQuantity = rsOrder.getInt("quantity");
                //حالا ببينيم در جدول پروداكز موجودي چقدر است
                SQLSelect = "SELECT Quantity From Products Where product_name = '" + productName + "' And seller_id = '" +
                            sellerID + "'";

                pstmtProduct = conn.prepareStatement(SQLSelect);
                
                rsProduct = pstmtProduct.executeQuery();
                if((rsProduct.next())){
                    productQuantity = rsProduct.getInt("quantity");
                }
                else{
                    productQuantity = 0;
                }
                if(productQuantity < orderQuantity)
                {
                    return productName;
                }
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rsOrder != null) rsOrder.close();
                if (rsProduct != null) rsProduct.close();
                if (pstmtOrder != null) pstmtOrder.close();
                if (pstmtProduct != null) pstmtProduct.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
        //اگر كد به اينجا برسد يعني موجودي سفارشات به اندازه كافي هست
        return "";
    }

    //--------------------------------------------------------------------------
    public Boolean doOrders(String userID){
        
        String productName, sellerID;        
        
        Long sum = 0L, wallet;
        
        Integer quantity;
                                
        Connection conn = null;    

        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;  

        PreparedStatement pstmt2 = null;
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            //يافتن جمع سفارشات
            String SQLStr = "SELECT Sum(price) As Result From Orders Where user_id = '" + userID + "' And purchased = 0";
            pstmt1 = conn.prepareStatement(SQLStr);
            rs1 = pstmt1.executeQuery();
            if (rs1.next()) {
                sum = rs1.getLong("Result");
            }
            else{
                sum = 0L;
            }
            
            pstmt1.close();
            rs1.close();
            
            //موجودي كالا اصلاح شود
            String SQLSelect = "SELECT * From Orders Where user_id = '" + userID + "' And purchased = 0";
            pstmt1 = conn.prepareStatement(SQLSelect);
            rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                productName = rs1.getString("product_name").trim();
                sellerID = rs1.getString("seller_id").trim();
                quantity = rs1.getInt("quantity");
                //حالا موجودي جدول پروداكتز اصلاح شود
                SQLSelect = "Update Products Set quantity = quantity - " + String.valueOf(quantity).trim() + " Where " +
                            "product_name = '" + productName + "' And seller_id = '" + sellerID + "'";
                pstmt2 = conn.prepareStatement(SQLSelect);
                pstmt2.executeUpdate();
            }
            rs1.close();
            pstmt1.close();
            pstmt2.close();
            //حالا سفارشات به خريد قطي تبديل شد
            SQLStr = "Update Orders Set purchased = 1 Where user_id = '" + userID + "' And purchased = 0";
            pstmt1 = conn.prepareStatement(SQLStr);
            pstmt1.executeUpdate();
            pstmt1.close();
            //حالا موجودی ريالي كاربر اصلاح شود
            SQLStr = "Update Users Set Wallet = Wallet - " + String.valueOf(sum) + " Where user_id = '" + userID + "'";
            pstmt1 = conn.prepareStatement(SQLStr);
            pstmt1.executeUpdate();
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs1 != null) rs1.close();
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
        if(sum == 0){
            return false;
        }
        else{
            return true;
        }
    }

    //--------------------------------------------------------------------------
    public void fillObservableListDepositRequest(ObservableList observableList){
        
        String SQLSelect = "SELECT * From Transactions Where type = 'deposit' And confirmed = 0 Order By req_date, user_id";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                String date = rs.getString("req_date").trim();
                String userID = rs.getString("user_id").trim();
                Long amount = rs.getLong("amount");
                observableList.add(new DepositRequest(date, userID, amount));
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
    }
    //--------------------------------------------------------------------------
    //اين روتين تست مي كند آيا تقاضاي واريز وجه وجود دارد يا خير
    public Boolean isDepositRequestsExist()    
    {
        Boolean result;
        String productName, sellerID;
        Integer orderQuantity, productQuantity;
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        result = false;
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            String SQLSelect = "SELECT * From Transactions Where type = 'deposit' And confirmed = 0";
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                result = true;
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
        return result;
    }
    //--------------------------------------------------------------------------
    // this method checks wheather there is any withdraw reqs or not
    public Boolean isWithdrawRequestsExist()    
    {
        Boolean result;
        String productName, sellerID;
        Integer orderQuantity, productQuantity;
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        result = false;
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            String SQLSelect = "SELECT * From Transactions Where type = 'withdraw' And confirmed = 0";
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                result = true;
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
        return result;
    }
    //--------------------------------------------------------------------------
    public void doDepositRequests(){
        
        String userID;        
        
        String SQLSelect, SQLStr;
        
        Long amount;
     
        Connection conn = null;    

        PreparedStatement pstmt1 = null;
        ResultSet rs = null;  

        PreparedStatement pstmt2 = null;
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);

            //موجودي كيف كاربران اصلاح شود
            SQLSelect = "SELECT * From Transactions Where type = 'deposit' And confirmed = 0";
            pstmt1 = conn.prepareStatement(SQLSelect);
            rs = pstmt1.executeQuery();
            while (rs.next()) {
                userID = rs.getString("user_id").trim();
                amount = rs.getLong("amount");
                //حالا موجودي جدول كاربران اصلاح شود
                SQLStr = "Update users Set wallet = wallet + " + String.valueOf(amount).trim() + " Where " +
                            "user_id = '" + userID + "'";
                pstmt2 = conn.prepareStatement(SQLStr);
                pstmt2.executeUpdate();
            }
            rs.close();
            pstmt1.close();
            //حالا تقاضاهاي واريز تاييد شود
            SQLStr = "Update Transactions Set confirmed = 1 Where type = 'deposit' And confirmed = 0";
            pstmt1 = conn.prepareStatement(SQLStr);
            pstmt1.executeUpdate();
            pstmt1.close();
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
    }
    //--------------------------------------------------------------------------
    public void doWithdrawRequests(){
        
        String userID;        
        
        String SQLSelect, SQLStr;
        
        Long amount;
     
        Connection conn = null;    

        PreparedStatement pstmt1 = null;
        ResultSet rs = null;  

        PreparedStatement pstmt2 = null;
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);

            //موجودي كيف كاربران اصلاح شود
            SQLSelect = "SELECT * From Transactions Where type = 'withdraw' And confirmed = 0";
            pstmt1 = conn.prepareStatement(SQLSelect);
            rs = pstmt1.executeQuery();
            while (rs.next()) {
                userID = rs.getString("user_id").trim();
                amount = rs.getLong("amount");
                //حالا موجودي جدول كاربران اصلاح شود
                SQLStr = "Update users Set wallet = wallet - " + String.valueOf(amount).trim() + " Where " +
                            "user_id = '" + userID + "'";
                pstmt2 = conn.prepareStatement(SQLStr);
                pstmt2.executeUpdate();
            }
            rs.close();
            pstmt1.close();
            //حالا تقاضاهاي واريز تاييد شود
            SQLStr = "Update Transactions Set confirmed = 1 Where type = 'withdraw' And confirmed = 0";
            pstmt1 = conn.prepareStatement(SQLStr);
            pstmt1.executeUpdate();
            pstmt1.close();
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
    }
    //--------------------------------------------------------------------------    
    //ابتدا ركورد مربوطه با پاك می كند سپس ركورد جديد را اضافه مي كند
    public void insert2Products(String sellerID, String productName, Long price, Integer quantity, String comment) {

        String SQLStr;
        
        Connection conn = null;    
        PreparedStatement pstmt = null;
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            SQLStr = "Delete From Products Where seller_id = '" + sellerID + "' And product_name = '" + productName + "'";
            pstmt = conn.prepareStatement(SQLStr);
            pstmt.executeUpdate();
            
            SQLStr = "Insert Into Products (seller_id, product_name, price, quantity, comment) Values( '" +
                           sellerID.trim() + "', '" + productName.trim() + "', " + price.toString().trim() + ", " + 
                           quantity.toString().trim() + ", '" + comment + "')";
            pstmt = conn.prepareStatement(SQLStr);
            pstmt.executeUpdate();
            //
            Util.showAlert(Alert.AlertType.INFORMATION, "product added successfully.");
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                
    }
    //--------------------------------------------------------------------------
    public void fillObservableListUsers(ObservableList observableList){
        
        String SQLSelect = "SELECT * From users Order By type, user_id";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                 
                String userID = rs.getString("user_id").trim();
                String type = rs.getString("type").trim();
                String phoneNum = rs.getString("phone_number").trim();
                String email = rs.getString("email").trim();
                String address = rs.getString("Address").trim();
                Long wallet = rs.getLong("wallet");
                
                observableList.add(new User(userID, type, phoneNum, email, address, wallet));
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
    }
    //--------------------------------------------------------------------------
    public void fillObservableListOfProducts(ObservableList observableList, String sellerID){
        
        String SQLSelect = "SELECT * From products where seller_id = '"+ sellerID + "' Order By product_name";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                 
                String productName = rs.getString("product_name").trim();
                Long price = rs.getLong("price");
                int quantity = rs.getInt("quantity");
                String comment = rs.getString("comment").trim();
                
                observableList.add(new Product(productName, price, quantity, comment));
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
    }
    //--------------------------------------------------------------------------
    public void fillSearchProductTable(ObservableList observableList, String searchStr){
        
        String SQLSelect = "select * from products where product_name like'%"+ searchStr.trim() + "%' or seller_id like '%" + searchStr.trim() + "%'";
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                 
                String productName = rs.getString("product_name").trim();
                String sellerID = rs.getString("seller_id").trim();
                int quantity = rs.getInt("quantity");
                Long price = rs.getLong("price");
                String comment = rs.getString("comment").trim();
                observableList.add(new Product(productName, price,quantity, comment, sellerID));
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }
    //--------------------------------------------------------------------------
    public void fillProfile(String ID, TextField phoneNumber, TextField email, TextField address, TextField wallet ){
        String SQLSelect = "select * from users where user_id = '" + ID.trim() + "'";

        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                 
                ID = rs.getString("user_id").trim();
                phoneNumber.setText(rs.getString("phone_number").trim());
                email.setText(rs.getString("email").trim());
                address.setText(rs.getString("address").trim());
                wallet.setText(Long.toString(rs.getLong("wallet")));
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }
    //--------------------------------------------------------------------------
    public Long getWallet(String userID){
        Long result, wallet;
        result = 0L;
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            //يافتن جمع سفارشات
            String SQLSelect = "SELECT wallet From users Where user_id = '" + userID + "'";
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getLong("wallet");
            }
        } 
        catch (Exception e) {
            Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
        return result;  
    }
    //--------------------------------------------------------------------------
    public void fillObservableListWithdrawRequest(ObservableList observableList){
        
        String SQLSelect = "SELECT * From Transactions Where type = 'withdraw' And confirmed = 0 Order By req_date, user_id";
                                
        Connection conn = null;    
        PreparedStatement pstmt = null;
        ResultSet rs = null;  
        
        try {
            // Connect to the SQLite database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionString);
            
            pstmt = conn.prepareStatement(SQLSelect);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                String date = rs.getString("req_date").trim();
                String userID = rs.getString("user_id").trim();
                Long amount = rs.getLong("amount");
                observableList.add(new DepositRequest(date, userID, amount));
            }
        } 
        catch (Exception ex) {
            Util.showAlert(Alert.AlertType.ERROR, ex.getStackTrace().toString());
        }        
        finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                Util.showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }                            
    }    
    //--------------------------------------------------------------------------
}
