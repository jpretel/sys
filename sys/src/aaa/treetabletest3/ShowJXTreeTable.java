package aaa.treetabletest3;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ShowJXTreeTable {
	public static void main(String[] args) {
		JFrame testFrame = new JFrame();
		List<String[]> content = new ArrayList<>();
		
		content.add(new String[] {"Cabecera 1"});
		content.add(new String[] {"Sub 1", "Sub 2", "Sub 3", "Sub 3"});
		content.add(new String[] {"Cabecera 2"});
		content.add(new String[] {"Sub 21", "Sub 22", "Sub 33", "Sub 3"});
		content.add(new String[] {"Cabecera 3"});
		content.add(new String[] {"Sub 31", "Sub 32", "Sub 33", "Sub 3"});
		
		
		TreeTable  treeTable = new TreeTable(content);
		
		testFrame.setSize(500, 500);
		
		testFrame.setLayout(new BorderLayout());
		
		testFrame.add( new JScrollPane(treeTable.getTreeTable()), BorderLayout.CENTER);
		
		testFrame.setVisible(true);
	}
}
