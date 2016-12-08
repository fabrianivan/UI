package Inter;
import java.sql.*;
import java.util.Vector;

public class DBkasir {

	private Connection conn;
		
		public DBkasir() throws Exception {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/kasir_restoran",
					"root", "");
		}
		
		public boolean isConnected(){
			return (conn != null);
		}
		
		public void insertuser(
				Integer id,
				String fullname,
				String gender,
				String address,
				Integer phone,
				String username,
				String password
				)throws Exception {
			String query = 	
					"INSERT INTO `user`(`id`, `fullname`, `gender`, `address`, `phone`, `username`, `password`) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setInt(1, id);
					stmt.setString(2, fullname);
					stmt.setString(3, gender);
					stmt.setString(4, address);
					stmt.setInt(5, phone);
					stmt.setString(6, username);
					stmt.setString(7, password);
					stmt.execute();
					stmt.close();
		}

		
		public void resgisuser(
				String fullname,
				String gender,
				String address,
				Integer phone,
				String username,
				String password
				)throws Exception {
			String query = 	
					"INSERT INTO `user`(`fullname`, `gender`, `address`, `phone`, `username`, `password`) VALUES (?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, fullname);
					stmt.setString(2, gender);
					stmt.setString(3, address);
					stmt.setInt(4, phone);
					stmt.setString(5, username);
					stmt.setString(6, password);
					stmt.execute();
					stmt.close();
		}
		
		
	    public boolean cekID(String id)throws Exception{
	        String query = "SELECT * FROM user WHERE id = ?";
	        PreparedStatement a;
	        a = conn.prepareStatement(query);
	        a.setString(1, id);
	        ResultSet rs = a.executeQuery();

	        return (!rs.next());
	    }
	    public boolean cekUser(String user)throws Exception{
	        String query = "SELECT * FROM user WHERE username = ?";
	        PreparedStatement a;
	        a = conn.prepareStatement(query);
	        a.setString(1, user);
	        ResultSet rs = a.executeQuery();

	        return (!rs.next());
	    }
	    
		public void selectuser()throws Exception {
			String query = 				
					"SELECT * FROM `user`";
					Statement stm = conn.createStatement();
					ResultSet rst = stm.executeQuery(query);
					System.out.println("--------------------------");
					while(rst.next()){
						System.out.println("ID: " + rst.getInt("id"));
						System.out.println("Fullname: " + rst.getString("fullname"));
						System.out.println("Gender: " + rst.getString("gender"));
						System.out.println("Address: " + rst.getString("address"));
						System.out.println("Phone: " + rst.getInt("phone"));
						System.out.println("Username: " + rst.getString("username"));
						System.out.println("Password: " + rst.getString("password"));
						System.out.println("--------------------------");
						}
		}
		public Vector<Vector<Object>> cari_id(int id) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `user` where id = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, id);

	         ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getInt("id"));
	            v.add(rs.getString("fullname"));
	            v.add(rs.getString("gender"));
	            v.add(rs.getString("address"));
	            v.add(rs.getInt("phone"));
	            v.add(rs.getString("username"));
	            v.add(rs.getString("password"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
		public Vector<Vector<Object>> cari_fullname(String fullname) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `user` where fullname LIKE  ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, fullname);

	         ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getInt("id"));
	            v.add(rs.getString("fullname"));
	            v.add(rs.getString("gender"));
	            v.add(rs.getString("address"));
	            v.add(rs.getInt("phone"));
	            v.add(rs.getString("username"));
	            v.add(rs.getString("password"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
		public Vector<Vector<Object>> cari_gender(String gender) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `user` where gender = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, gender);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getInt("id"));
	            v.add(rs.getString("fullname"));
	            v.add(rs.getString("gender"));
	            v.add(rs.getString("address"));
	            v.add(rs.getInt("phone"));
	            v.add(rs.getString("username"));
	            v.add(rs.getString("password"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
		public Vector<Vector<Object>> cari_address(String address) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	       String query ="SELECT * FROM `user` where address LIKE ?";
	       PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, address);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getInt("id"));
	            v.add(rs.getString("fullname"));
	            v.add(rs.getString("gender"));
	            v.add(rs.getString("address"));
	            v.add(rs.getInt("phone"));
	            v.add(rs.getString("username"));
	            v.add(rs.getString("password"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
		public Vector<Vector<Object>> cari_username(String username) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `user` where username LIKE ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, username);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getInt("id"));
	            v.add(rs.getString("fullname"));
	            v.add(rs.getString("gender"));
	            v.add(rs.getString("address"));
	            v.add(rs.getInt("phone"));
	            v.add(rs.getString("username"));
	            v.add(rs.getString("password"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
		public Vector<Vector<Object>> cari_phone(int phone) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `user` where phone = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, phone);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getInt("id"));
	            v.add(rs.getString("fullname"));
	            v.add(rs.getString("gender"));
	            v.add(rs.getString("address"));
	            v.add(rs.getInt("phone"));
	            v.add(rs.getString("username"));
	            v.add(rs.getString("password"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
		
	    public void deleteUSER(
	            int id
	    )throws Exception{
	        String query = "DELETE from `user` WHERE id = ?";
	        PreparedStatement a;
	        a = conn.prepareStatement(query);
	        a.setInt(1, id);
	        a.executeUpdate();
	    }
		
		public void updateuser(
				Integer id,
				String fullname,
				String gender,
				String address,
				Integer phone,
				String username,
				String password
				)throws Exception {
			try {
				conn.setAutoCommit(false);
				String query = 				
					"UPDATE `user` SET `fullname`=?,`gender`=?,`address`=?,`phone`=?,`username`=?,`password`=? WHERE id=?";
					PreparedStatement upbuku = conn.prepareStatement(query);
					upbuku.setString(1, fullname);
					upbuku.setString(2, gender);
					upbuku.setString(3, address);
					upbuku.setInt(4, phone);
					upbuku.setString(5, username);
					upbuku.setString(6, password);
					upbuku.setInt(7, id);				
					upbuku.executeUpdate();
					
					conn.commit();
			}
			catch (Exception e){
				conn.rollback();
			}
		}
		
		public Vector<Vector<Object>> selectUSER() throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM `user`");

	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getInt("id"));
	            v.add(rs.getString("fullname"));
	            v.add(rs.getString("gender"));
	            v.add(rs.getString("address"));
	            v.add(rs.getInt("phone"));
	            v.add(rs.getString("username"));
	            v.add(rs.getString("password"));
	            data.add(v);
	        }
	        return data;
	    }
	 
		public Vector<Vector<Object>> selectMENU() throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM `menu`");

	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getString("code"));
	            v.add(rs.getString("name"));
	            v.add(rs.getInt("price"));
	            v.add(rs.getInt("stock"));
	          
	            data.add(v);
	        }
	        return data;
	    }
	 
	 public void updatemenu(
				String code,
				String name,
				Integer price,
				Integer stock
				
				)throws Exception {
			try {
				conn.setAutoCommit(false);
				String query = 				
					"UPDATE `menu` SET `name`=?,`price`=?,`stock`=? WHERE code=?";
					PreparedStatement upbuku = conn.prepareStatement(query);
					upbuku.setString(1, name);
					upbuku.setInt(2, price);
					upbuku.setInt(3, stock);
					upbuku.setString(4, code);
									
					upbuku.executeUpdate();
					
					conn.commit();
			}
			catch (Exception e){
				conn.rollback();
			}
			
		}
	 public Vector<Vector<Object>> cari_codemenu(String code) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `menu` where code LIKE ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, code);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getString("code"));
	            v.add(rs.getString("name"));
	            v.add(rs.getInt("price"));
	            v.add(rs.getInt("stock"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
	 public Vector<Vector<Object>> cari_namemenu(String name) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `menu` where name LIKE ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, name);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getString("code"));
	            v.add(rs.getString("name"));
	            v.add(rs.getInt("price"));
	            v.add(rs.getInt("stock"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
	 public Vector<Vector<Object>> cari_pricemenu(int price) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `menu` where price = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, price);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getString("code"));
	            v.add(rs.getString("name"));
	            v.add(rs.getInt("price"));
	            v.add(rs.getInt("stock"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
	 public Vector<Vector<Object>> cari_stockmenu(int stock) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `menu` where stock = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, stock);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getString("code"));
	            v.add(rs.getString("name"));
	            v.add(rs.getInt("price"));
	            v.add(rs.getInt("stock"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
	 public Vector<Vector<Object>> select_pesanan(int id_user, int completed) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT * FROM `order_menu` where id_user = ? and completed = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, id_user);
	        pst.setInt(2, completed);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getString("id"));
	            v.add(rs.getString("id_user"));
	            v.add(rs.getString("menu"));
	            v.add(rs.getInt("price"));
	            v.add(rs.getInt("quantity"));
	            v.add(rs.getInt("total"));

	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
	 public Vector<Vector<Object>> cari_totalharga(int id_user, int completed) throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        String query = "SELECT SUM(total) FROM `order_menu` where id_user = ? and completed = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, id_user);
	        pst.setInt(2, completed);
	        ResultSet rs = pst.executeQuery();
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getString("total"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	 }
	//Bagian History ============================================================================================
	//========================================================================================================================================================================================
			 public void inserthistory(
						String barang,
						Integer jumlah,
						Integer satuan,
						Integer harga
						)throws Exception {
					String query = 	
							"INSERT INTO `history`(`barang`, `jumlah`, `satuan`, `harga`) VALUES (?, ?, ?, ?)";
							PreparedStatement stmt = conn.prepareStatement(query);
							stmt.setString(1, barang);
							stmt.setInt(2, jumlah);
							stmt.setInt(3, satuan);
							stmt.setInt(4, harga);
							stmt.execute();
							stmt.close();
				}
			 
			 public Vector<Vector<Object>> selectHISTORY() throws SQLException {
			        Statement stmt = this.conn.createStatement();
			        ResultSet rs = stmt.executeQuery("SELECT * FROM `history`");

			        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
			        while (rs.next()) {
			            Vector<Object> v = new Vector<Object>();
			            v.add(rs.getInt("no_pembelian"));
			            v.add(rs.getString("barang"));
			            v.add(rs.getString("jumlah"));
			            v.add(rs.getString("satuan"));
			            v.add(rs.getInt("harga"));
			         
			            data.add(v);
			        }
			        return data;
			    }
			 
			 public void deleteHISTORY(
			            Integer id
			    )throws Exception{
			        String query = "DELETE from `history` WHERE no_pembelian = ?";
			        PreparedStatement a;
			        a = conn.prepareStatement(query);
			        a.setInt(1, id);
			        a.executeUpdate();
			    }
			 
			 public void updateHISTORY(
						Integer no_pembelian,
						String barang,
						Integer jumlah,
						Integer satuan,
						Integer harga
						)throws Exception {
					try {
						conn.setAutoCommit(false);
						String query = 				
							"UPDATE `history` SET `barang`=?,`jumlah`=?,`satuan`=?,`harga`=? WHERE no_pembelian=?";
							PreparedStatement upbuku = conn.prepareStatement(query);
							upbuku.setString(1, barang);
							upbuku.setInt(2, jumlah);
							upbuku.setInt(3, satuan);
							upbuku.setInt(4, harga);
						
							upbuku.setInt(5, no_pembelian);				
							upbuku.executeUpdate();
							
							conn.commit();
					}
					catch (Exception e){
						conn.rollback();
					}
				}
			 
			 public Vector<Vector<Object>> cari_nomor(int id) throws SQLException {
			        Statement stmt = this.conn.createStatement();
			        String query = "SELECT * FROM `history` where no_pembelian = ?";
			        PreparedStatement pst = conn.prepareStatement(query);
			        pst.setInt(1, id);

			         ResultSet rs = pst.executeQuery();
			        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
			        while (rs.next()) {
			            Vector<Object> v = new Vector<Object>();
			            v.add(rs.getInt("no_pembelian"));
			            v.add(rs.getString("barang"));
			            v.add(rs.getInt("jumlah"));
			            v.add(rs.getInt("satuan"));
			            v.add(rs.getInt("harga"));
			           
			            data.add(v);
			        }
			        stmt.close();
			        return data;
			    }
			 
			 public Vector<Vector<Object>> cari_barang(String fullname) throws SQLException {
			        Statement stmt = this.conn.createStatement();
			        String query = "SELECT * FROM `history` where barang LIKE  ?";
			        PreparedStatement pst = conn.prepareStatement(query);
			        pst.setString(1, fullname);

			         ResultSet rs = pst.executeQuery();
			        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
			        while (rs.next()) {
			            Vector<Object> v = new Vector<Object>();
			            v.add(rs.getInt("no_pembelian"));
			            v.add(rs.getString("barang"));
			            v.add(rs.getString("jumlah"));
			            v.add(rs.getString("satuan"));
			            v.add(rs.getInt("harga"));
			          
			            data.add(v);
			        }
			        stmt.close();
			        return data;
			    }
			 
			 public Vector<Vector<Object>> cari_jumlah(int id) throws SQLException {
			        Statement stmt = this.conn.createStatement();
			        String query = "SELECT * FROM `history` where jumlah = ?";
			        PreparedStatement pst = conn.prepareStatement(query);
			        pst.setInt(1, id);

			         ResultSet rs = pst.executeQuery();
			        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
			        while (rs.next()) {
			            Vector<Object> v = new Vector<Object>();
			            v.add(rs.getInt("no_pembelian"));
			            v.add(rs.getString("barang"));
			            v.add(rs.getInt("jumlah"));
			            v.add(rs.getInt("satuan"));
			            v.add(rs.getInt("harga"));
			           
			            data.add(v);
			        }
			        stmt.close();
			        return data;
			    }
			 


			 public Vector<Vector<Object>> cari_satuan(int id) throws SQLException {
			        Statement stmt = this.conn.createStatement();
			        String query = "SELECT * FROM `history` where satuan = ?";
			        PreparedStatement pst = conn.prepareStatement(query);
			        pst.setInt(1, id);

			         ResultSet rs = pst.executeQuery();
			        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
			        while (rs.next()) {
			            Vector<Object> v = new Vector<Object>();
			            v.add(rs.getInt("no_pembelian"));
			            v.add(rs.getString("barang"));
			            v.add(rs.getInt("jumlah"));
			            v.add(rs.getInt("satuan"));
			            v.add(rs.getInt("harga"));
			           
			            data.add(v);
			        }
			        stmt.close();
			        return data;
			    }
			 
			 
			 public Vector<Vector<Object>> cari_harga(int id) throws SQLException {
			        Statement stmt = this.conn.createStatement();
			        String query = "SELECT * FROM `history` where harga = ?";
			        PreparedStatement pst = conn.prepareStatement(query);
			        pst.setInt(1, id);

			         ResultSet rs = pst.executeQuery();
			        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
			        while (rs.next()) {
			            Vector<Object> v = new Vector<Object>();
			            v.add(rs.getInt("no_pembelian"));
			            v.add(rs.getString("barang"));
			            v.add(rs.getInt("jumlah"));
			            v.add(rs.getInt("satuan"));
			            v.add(rs.getInt("harga"));
			           
			            data.add(v);
			        }
			        stmt.close();
			        return data;
			    }
			 public void buymenu(Integer qty, Integer code)throws Exception {
					try {
						conn.setAutoCommit(false);
						String query = 				
							"UPDATE `menu` SET `stock`= (stock-"+ qty + ") WHERE ( code=? )";
							PreparedStatement upbuku = conn.prepareStatement(query);
							upbuku.setInt(1, code);			
							upbuku.executeUpdate();
							
							conn.commit();
					}
					catch (Exception e){
						conn.rollback();
					}
				}
			 public void updatemenu(
						Integer code,
						String name,
						Integer price,
						Integer stock
						)throws Exception {
					try {
						conn.setAutoCommit(false);
						String query = 				
							"UPDATE `menu` SET name=?, price=?,`stock`=? WHERE code=?";
							PreparedStatement upbuku = conn.prepareStatement(query);
							upbuku.setString(1, name);
							upbuku.setInt(2, price);
							upbuku.setInt(3, stock);		
							upbuku.setInt(4, code);		
							upbuku.executeUpdate();
							
							conn.commit();
					}
					catch (Exception e){
						conn.rollback();
					}
				}	 	
			 //return currently menu qty 
			 public int currentqty(Integer code)throws Exception {
				 ResultSet rs = null;
					try {
						conn.setAutoCommit(false);
						String query = 
							"SELECT * FROM `menu` WHERE ( code=? )";
							PreparedStatement upbuku = conn.prepareStatement(query);
							upbuku.setInt(1, code);
							upbuku.executeQuery();
							conn.commit();					
							rs = upbuku.executeQuery();
					        }			
					catch (Exception e){
						conn.rollback();
					}
					Integer i = (Integer) rs.getInt("stock");
			        return i;
				}
			 
			 public int pricemenu(Integer code)throws Exception {
			        ResultSet rs = null;
			        PreparedStatement upbuku = null;
			        Integer i = null;
					try {
						conn.setAutoCommit(false);
						String query = 				
							"SELECT * FROM `menu` WHERE code = ?";
							upbuku = conn.prepareStatement(query);
							upbuku.setInt(1, code);			
							rs = upbuku.executeQuery();			
					        i = (Integer) rs.getInt("price");
					        }			
					catch (Exception e){
						conn.rollback();
					}finally {
				        if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
				        if (upbuku != null) try { upbuku.close(); } catch (SQLException ignore) {}
				    }
			        return i;
				}
			 
			 public String pricemenu2(Integer code) throws SQLException {
				 Statement stmt = null;
				 String i = null;
				 try{
					conn.setAutoCommit(false);
			        stmt = this.conn.createStatement();
			        String query = "SELECT * FROM `menu` where code = ?";
			        PreparedStatement pst = conn.prepareStatement(query);
			        pst.setInt(1, code);
			        ResultSet rs = pst.executeQuery();
			        i = Integer.toString(rs.getInt("price"));
				 	}			
					catch (Exception e){
						conn.rollback();
					}finally {
				        if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
				    }
			        stmt.close();
			        return i;
			    }
			 public Vector<Vector<Object>> cariprice(Integer code) throws SQLException {
			        Statement stmt = this.conn.createStatement();
			        String query = "SELECT PRICE FROM `menu` where code = ?";
			        PreparedStatement pst = conn.prepareStatement(query);
			        pst.setInt(1, code);
			        ResultSet rs = pst.executeQuery();
			        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
			        while (rs.next()) {
			            Vector<Object> v = new Vector<Object>();
			            v.add(rs.getInt("price"));
			            data.add(v);
			        }
			        stmt.close();
			        return data;
			    }
}


