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
import model.Dupdate;

public class DupdateController implements Initializable {

    @FXML
    private TextField designername;

    @FXML
    private TextField closed;

    @FXML
    private TextField shopname;

    @FXML
    private TextField designerimg;

    @FXML
    private TableView<Dupdate> tvdupdate;

    @FXML
    private TableColumn<Dupdate, String> tvdesignername;

    @FXML
    private TableColumn<Dupdate, String> tvclosed;

    @FXML
    private TableColumn<Dupdate, String> tvshopname;

    @FXML
    private TableColumn<Dupdate, String> tvdesignerimg;

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

    @FXML
    void handelButton(ActionEvent event) {
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

    			showDupdate();

    }
    public ObservableList<Dupdate> getUpdateList(){
		//fx에서 테이블뷰에 표시하기 위한 리스트로 ObservableList 사용
		ObservableList<Dupdate> dupdateList = FXCollections.observableArrayList();
		//sql 작성
		String sql = "SELECT * FROM designer ORDER BY designername";
		conn = connect.getConnection(); //위에서 만든 DB연결 메소드
		Statement stmt;   //DB에 보낼 쿼리 객체 선언
		ResultSet rs;     //DB에서 받아오는 결과객체 선언
		
		try {
			stmt = conn.createStatement(); //쿼리 객체 생성
			rs = stmt.executeQuery(sql); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴
			//결과를 한 행씩 읽어서 bookList에서 입력
			Dupdate dupdate; 
			while(rs.next()) {			
				dupdate = new Dupdate(rs.getString("designername"), rs.getString("closed"), rs.getString("shopname"), rs.getString("designerimg"));
				dupdateList.add(dupdate); //북리스트에 하나의 book객체를 입력한다.
			}
		} catch (Exception e) {
			System.out.println("DB에서 sql문을 실행불가: "+e);
		}
		
		return dupdateList;	
	}
	
	public void showDupdate() {
		ObservableList<Dupdate> list = getUpdateList();
		// 테이블뷰에 리스트를 넣고
		tvdupdate.setItems(list);
		// 각각의 열에 데이터를 불러오는 코드를 작성
		tvdesignername.setCellValueFactory(new PropertyValueFactory<Dupdate, String>("designername"));
		tvclosed.setCellValueFactory(new PropertyValueFactory<Dupdate, String>("closed"));
		tvshopname.setCellValueFactory(new PropertyValueFactory<Dupdate, String>("shopname"));
		tvdesignerimg.setCellValueFactory(new PropertyValueFactory<Dupdate, String>("designerimg"));
		
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// 프로그램 실행시 아래 내용이 실행됨
		showDupdate();
		// 마우스로 테이블셀을 클릭시 그 행의 내용이 다르면 이벤트를 발생한다.
		tvdupdate.getSelectionModel().selectedItemProperty().addListener(
				//테이블안의 내용중 행이 하나의 book객체이고 다른 행을 선택(마우스클릭시)
				//그전 행과 다를경우 이벤트 발생하고 showBookDetails를 실행한다.
				(obs,oldValue,newValue) -> showDupdateDetails(newValue)
				);	
	}
	
	private void showDupdateDetails(Dupdate dupdate) {
		if(dupdate != null) {
			//널값이 아닐때
			tvdesignername.setText(dupdate.getDesignername());
			tvclosed.setText(dupdate.getClosed());
			tvshopname.setText(dupdate.getShopname());
			tvdesignerimg.setText(dupdate.getDesignerimg());

			
		} else {
			//널값일때 => 모든 tf를 내용을 지운다.
			designername.setText("");
			closed.setText("");
			shopname.setText("");
			designerimg.setText("");
	
		}
		
	}

	// DB에 한줄 입력
	private void insertRow() {
		String sql = "INSERT INTO designer VALUES(?,?,?,?)";
		
		conn = connect.getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)
		
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, designername.getText()); 					
			pstmt.setString(2, closed.getText()); 			
			pstmt.setString(3, shopname.getText()); 			
			pstmt.setString(4, designerimg.getText()); 				
		   //year

			//입력 준비됨
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 인서트 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("인서트 에러발생!");
		}
		designername.setText("");
		closed.setText("");
		shopname.setText("");
		designerimg.setText("");
	
	}
	
	// DB의 행을 수정한다.
	private void updateRow() {
		String sql = "UPDATE designer SET closed=?, shopname=?, designerimg=? WHERE designername=?";

		
		conn = connect.getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, closed.getText()); 				
			pstmt.setString(2, shopname.getText()); 			
			pstmt.setString(3, designerimg.getText()); 				
			pstmt.setString(4, designername.getText()); 		
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 업데이트 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("업데이트중 에러발생!");
		}
	}
	
	private void deleteRow() {
		String sql = "DELETE FROM designer WHERE designername=?";
		conn = connect.getConnection();
		PreparedStatement pstmt;    //쿼리 객체 선언(pstmt는 ?사용가능)
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, designername.getText());     //아이디
			//입력 준비됨
			pstmt.executeUpdate();  //리턴값이 없을경우 업데이트 => 삭제 실행
			conn.commit();         //한줄 입력하고 commit한다.
		} catch (Exception e) {
			System.out.println("삭제중 에러발생!");
		}
	}
	

}



