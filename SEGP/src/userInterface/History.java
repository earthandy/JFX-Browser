package userInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.History_Managment;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * @author SEGP-group-3
 *
 */
/**
 * @author SEGP-Group3
 *
 */
public class History
{
	@FXML
	private TreeView Tree_View=new TreeView();
	@FXML
	private TreeTableColumn Date=new TreeTableColumn();
	@FXML
	private TreeTableColumn Links=new TreeTableColumn();
	@FXML
	private TreeTableColumn Time=new TreeTableColumn();
	
	HistoryTree hs=new HistoryTree();
	ArrayList children=hs.getStoreItems();
	TreeItem rootItem = new TreeItem("History");
	
	
	/**
	 * @param settingTab tab object to show settings interface in.
	 * @param borderpane root pane to set settings interface in it.
	 * @return javaFx tab object with fxml file loaded as history interface.
	 */
	/**
	 * @param settingTab tab content to show setting interface in.
	 * @param borderpane root pane to set setting interface in it.
	 * @return javaFX tab object with fxml file  loaded as history interface.
	 */
	public Tab getHistoryView(Tab settingTab, BorderPane borderpane)
	{
		
		rootItem.getChildren().addAll(children);
		Tree_View.setRoot(rootItem);
		borderpane.setLeft(Tree_View);
		
		try{
		borderpane.setCenter(FXMLLoader.load(getClass().getResource("History.fxml")));
		}	
		catch(Exception e1)
		{
		System.out.println("History NOT FOUND..!"+ " \n "+e1);
		e1.printStackTrace();
		}	
		settingTab.setContent(borderpane);
		Tree_View.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle); 
		return settingTab;
	}
	EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
    handleMouseClicked(event);
	};
	


	/**
	 * @param event mouse click event handler.
	 */
	/**
	 * @param event mouse event click handler
	 */
	private void handleMouseClicked(MouseEvent event) {
		Node node = event.getPickResult().getIntersectedNode();
	    // Accept clicks only on node cells, and not on empty spaces of the TreeView
	    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
	        String name = (String) ((TreeItem)Tree_View.getSelectionModel().getSelectedItem()).getValue();
	        if(name.equals("History"))
	        {
	        	ResultSet rs=History_Managment.showHistory();
	        	showAllHistory(rs);
	        }
	    }
	   
		
	}
	/**
	 * @param rs sql resultset to get data from the table. 
	 */
	/**
	 * @param rs sql resultSet to get data from sql table
	 */
	public void showAllHistory(ResultSet rs)
	{
		try {
			while(rs.next()) //loop for data fetching and pass it to GUI table view
			 {
				 String link1 =rs.getString(1);
				 String time1=rs.getString(2);
				 String date1=rs.getString(3);
				 System.out.println(link1);
				 System.out.println(time1);
				 System.out.println(date1);
			 } 
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}

	