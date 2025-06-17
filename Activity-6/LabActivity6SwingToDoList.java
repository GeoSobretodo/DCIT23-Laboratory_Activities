import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LabActivity6SwingToDoList {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LabActivity6SwingToDoList().createMainWindow());
    }

    private JFrame mainFrame;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private JFrame formFrame = null; // Only one instance at a time

    private void createMainWindow() {
        mainFrame = new JFrame("To-Do List Viewer");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());

        // Ito yung table
        String[] columns = {"Task Name", "Task Description", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        taskTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(taskTable);

        // Ito yung butttons
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(e -> openFormWindow());

        // ito yung layout
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(addTaskButton);

        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(tableScroll, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    private void openFormWindow() {
        if (formFrame != null && formFrame.isDisplayable()) {
            formFrame.toFront();
            return;
        }

        // window
        formFrame = new JFrame("Add New Task");
        formFrame.setSize(400, 300);
        formFrame.setLocationRelativeTo(mainFrame);
        formFrame.setLayout(new BorderLayout());

        // inputs
        JTextField taskNameField = new JTextField(20);
        JTextArea taskDescriptionArea = new JTextArea(4, 20);
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{
                "Not Started", "Ongoing", "Completed"
        });

        // layout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Task Name:"));
        formPanel.add(taskNameField);
        formPanel.add(Box.createVerticalStrut(5));

        formPanel.add(new JLabel("Task Description:"));
        JScrollPane descScroll = new JScrollPane(taskDescriptionArea);
        formPanel.add(descScroll);
        formPanel.add(Box.createVerticalStrut(5));

        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusCombo);
        formPanel.add(Box.createVerticalStrut(10));

        // save button
        JButton saveButton = new JButton("Save Task");
        saveButton.addActionListener(e -> {
            String name = taskNameField.getText().trim();
            String desc = taskDescriptionArea.getText().trim();
            String status = (String) statusCombo.getSelectedItem();

            // if condition kung empty yung name at desc
            if (name.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(formFrame, "Please fill in Task Name and Descripiton.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // add to table & close window
            tableModel.addRow(new Object[]{name, desc, status});
            formFrame.dispose();
        });

        formFrame.add(formPanel, BorderLayout.CENTER);
        formFrame.add(saveButton, BorderLayout.SOUTH);
        formFrame.setVisible(true);
    }
}
