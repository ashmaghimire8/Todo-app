import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Define the Task class
class Task {
    private String name;
    private int number;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setNumber(int number){
        this.number= number;
    }
    public int getNumber(){
        return number;
    }

    @Override
    public String toString() {
        return  number + "." + name; // Return the task name when toString() is called
    }
}
class ToDo extends JFrame{
    private DefaultListModel<Task> listModel;
    private JList<Task> taskList;

    public ToDo(){
     setTitle("To-Do List");
     setSize(300,300);
     //centers the JFrame
     setLocationRelativeTo(null);
     setVisible(true);

     listModel = new DefaultListModel<>();
     taskList = new JList<>(listModel);

     JScrollPane scrollPane = new JScrollPane(taskList);
     add(scrollPane, BorderLayout.CENTER);

     //panel to add buttons
     JPanel buttonPanel = new JPanel();
     buttonPanel.setLayout(new GridLayout(1, 2));

     JButton addButton = new JButton("Add Task");
     addButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String taskName = JOptionPane.showInputDialog(null,"Enter the task name");
             if(taskName != null && !taskName.isEmpty()){
                 //new task object is created and added to list Model
                 Task task = new Task(taskName);
                 //setting task number dynamically based on position
                 task.setNumber(listModel.getSize()+1);
                 listModel.addElement(task);


             }
         }
     });
     buttonPanel.add(addButton);

     JButton deleteButton = new JButton("Delete Task");
     deleteButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             int selectedIndex = taskList.getSelectedIndex();
             if(selectedIndex != -1){
                 listModel.remove(selectedIndex);
             } else {
                 JOptionPane.showMessageDialog(null, "Please select a task to remove");
             }
         }
     });
     buttonPanel.add(deleteButton);
     add(buttonPanel, BorderLayout.SOUTH);
    }




}
public class Main {
    public static void main(String args[]) {
        //avoids concurrency issues
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToDo todoListGUI = new ToDo();
                todoListGUI.setVisible(true);
            }
        });
    }
}