package treetabletest;


import java.util.*;

import javax.swing.*;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.TreeTableModel;

public class TreeTableTest extends JFrame {

    private JXTreeTable treeTable;

    public TreeTableTest() {
        List<Department> departmentList = new ArrayList<Department>();

        List<Employee> empList = new ArrayList<Employee>();
        empList.add(new Employee(1, "Ram", Calendar.getInstance().getTime(), "emp2"));
        empList.add(new Employee(2, "Krishna", Calendar.getInstance().getTime(), "emp3"));

        //create and add the first department with its list of Employee objects
        departmentList.add(new Department(1, "Sales", empList));

        List<Employee> empList2 = new ArrayList<Employee>();
        empList2.add(new Employee(3, "Govind", Calendar.getInstance().getTime(), "emp2"));
        empList2.add(new Employee(4, "Kiran", Calendar.getInstance().getTime(), "emp1"));
        empList2.add(new Employee(5, "Karthik", Calendar.getInstance().getTime(), "emp2"));

        //create and add the first department with its list of Employee objects
        departmentList.add(new Department(2, "Marketing", empList2));
        
        //we use a no root model
        NoRootTreeTableModel  noRootTreeTableModel = new NoRootTreeTableModel(departmentList);
        treeTable = new JXTreeTable(noRootTreeTableModel);
        treeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        

        add(new JScrollPane(treeTable));

        setTitle("JXTreeTable Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {        
                new TreeTableTest();
            }
        });
    }
}
