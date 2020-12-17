package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DBConnect;
import model.Update;

public class updateController  implements Initializable{

    @FXML
    private TextField shopid;

    @FXML
    private TextField shoppassword;

    @FXML
    private TextField shopname;

    @FXML
    private TextField shoptel;

    @FXML
    private TextField shopaddress;

    @FXML
    private TextField img;

    @FXML
    private TextField type;

    @FXML
    private TableView<Update> tvupdate;

    @FXML
    private TableColumn<Update, String> tvshopid;

    @FXML
    private TableColumn<Update, String> tvshoppassword;

    @FXML
    private TableColumn<Update, String> tvshopname;

    @FXML
    private TableColumn<Update, String> tvshoptel;

    @FXML
    private TableColumn<Update, String> tvshopaddress;

    @FXML
    private TableColumn<Update, String> tvimg;
    
    @FXML
    private TableColumn<Update, Integer> tvtype;
    
    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    

    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

	// 버튼을 눌렀을때 ( 3개 인서트, 업데이트, 딜레이트 )
	@FXML
	public void handelButton(ActionEvent event) {
		//System.out.println("버튼을 눌렀음!");
		//showBooks();
		if(event.getSource() == btnInsert) { //btnInsert 버튼을 눌렀을때!!!
			insertRow();
		}
		else if(event.getSource() == btnUpdate) {
			updateRow();
		}
		else if(event.getSource() == btnDelete) {
			deleteRow();
		}

		showUpdate();		
	}
	
	
	
	// 2. books테이블의 모든 내용을 받아온다. 이때 테이블뷰에 입력하기 위해서 observablelist를 사용
	public ObservableList<Update> getUpdateList(){
		//fx에서 테이블뷰에 표시하기 위한 리스트로 ObservableList 사용
		ObservableList<Update> updateList = FXCollections.observableArrayList();
		//sql 작성
		String sql = "SELECT * FROM hairshop ORDER BY shopid";
		conn = connect.getConnection(); //위에서 만든 DB연결 메소드
		Statement stmt;   //DB에 보낼 쿼리 객체 선언
		ResultSet rs;     //DB에서 받아오는 결과객체 선언
		
		try {
			stmt = conn.createStatement(); //쿼리 객체 생성
			rs = stmt.executeQuery(sql); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴
			//결과를 한 행씩 읽어서 bookList에서 입력
			Update update; 
			while(rs.next()) {			
				update = new Update(rs.getString("shopid"), rs.getString("shoppassword"), rs.getString("shopname")
						, rs.getString("shoptel"), rs.getString("shopaddress"), rs.getString("img"),rs.getInt("type"));
				updateList.add(update); //북리스트에 하나의 book객체를 입력한다.
			}
		} catch (Exception e) {
			System.out.println("DB에서 sql문을 실행불가: "+e);
		}
		
		return updateList;	
	}
	
	public void showUpdate() {
		ObservableList<Update> list = getUpdateList();
		// 테이블뷰에 리스트를 넣고
		tvupdate.setItems(list);
		// 각각의 열에 데이터를 불러오는 코드를 작성
		tvshopid.setCellValueFactory(new PropertyValueFactory<Update, String>("shopid"));
		tvshoppassword.setCellValueFactory(new PropertyValueFactory<Update, String>("shoppassword"));
		tvshopname.setCellValueFactory(new PropertyValueFactory<Update, String>("shopname"));
		tvshoptel.setCellValueFactory(new PropertyValueFactory<Update, String>("shoptel"));
		tvshopaddress.setCellValueFactory(new PropertyValueFactory<Update, String>("shopaddress"));
		tvimg.setCellValueFactory(new PropertyValueFactory<Update, String>("img"));
		tvtype.setCellValueFactory(new PropertyValueFactory<Update, Integer>("type"));
		
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// 프로그램 실행시 아래 내용이 실행됨
		showUpdate();
		// 마우스로 테이블셀을 클릭시 그 행의 내용이 다르면 이벤트를 발생한다.
		tvupdate.getSelectionModel().selectedItemProperty().addListener(
				//테이블안의 내용중 행이 하나의 book객체이고 다른 행을 선택(마우스클릭시)
				//그전 행과 다를경우 이벤트 발생하고 showBookDetails를 실행한다.
				(obs,oldValue,newValue) -> showUpdateDetails(newValue)
				);	
	}
	
	private void showUpdateDetails(Update update) {
		if(update != null) {
			//널값이 아닐때
			tvshopid.setText(update.getShopid());
			tvshoppassword.setText(update.getShoppassword());
			tvshopname.setText(update.getShopname());
			tvshoptel.setText(update.getShoptel());
			tvshopaddress.setText(update.getShopaddress());
			tvimg.setText(update.getImg());
			tvtype.setText(Integer.toString(update.getType()));
			
		} else {
			//널값일때 => 모든 tf를 내용을 지운다.
			shopid.setText("");
			shoppassword.setText("");
			shopname.setText("");
			shoptel.setText("");
			shopaddress.setText("");
			img.setText("");
			type.setText("");
		}
		
	}

	// DB에 한줄 입력
	private void insertRow() {
		String sql = "INSERT INTO hairshop VALUES(?,?,?,?,?,?,?)";
		
		conn = connect.getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)
		
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, shopid.getText()); 					//title
			pstmt.setString(2, shoppassword.getText()); 					//title
			pstmt.setString(3, shopname.getText()); 				//author
			pstmt.setString(4, shoptel.getText()); 				//author
			pstmt.setString(5, shopaddress.getText()); 				//author
			pstmt.setString(6, img.getText()); 				//author
			pstmt.setInt(7, Integer.parseInt(type.getText()) );   //year

			//입력 준비됨
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 인서트 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("인서트 에러발생!");
		}
		shopid.setText("");
		shoppassword.setText("");
		shopname.setText("");
		shoptel.setText("");
		shopaddress.setText("");
		img.setText("");
		type.setText("");
	}
	
	// DB의 행을 수정한다.
	private void updateRow() {
		String sql = "UPDATE hairshop SET shoppassword=?, shopname=?, shoptel=? , shopaddress =?, img=?, type=? WHERE shopid=?";

		
		conn = connect.getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoppassword.getText()); 					//title
			pstmt.setString(2, shopname.getText()); 				//author
			pstmt.setString(3, shoptel.getText()); 				//author
			pstmt.setString(4, shopaddress.getText()); 				//author
			pstmt.setString(5, img.getText());
			pstmt.setInt(6, Integer.parseInt(type.getText()) );  //author
			pstmt.setString(7, shopid.getText()); 			
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 업데이트 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("업데이트중 에러발생!");
		}
	}
	
	private void deleteRow() {
		String sql = "DELETE FROM hairshop WHERE shopid=?";
		conn = connect.getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shopid.getText());     //아이디
			//입력 준비됨
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 삭제 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("삭제중 에러발생!");
		}
	}
	

}



	
	
	
	
	

