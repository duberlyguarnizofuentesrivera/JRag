package rag;

import components.Figure;
import components.Process;
import components.Relation;
import components.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class GraphGenerator {
    static final Set<Figure> figures = new HashSet<>();


    public static void main(String[] args) {
        //example initial block ...to be removed
        Process process1 = new Process(1, "P1", new Point(100, 200));
        Process process2 = new Process(2, "P2", new Point(200, 200));
        Process process3 = new Process(3, "P3", new Point(300, 300));
        Process process4 = new Process(4, "P4", new Point(400, 400));
        Resource resource1 = new Resource(1, "R1", new Point(500, 500), 2);
        Resource resource2 = new Resource(2, "R2", new Point(500, 200), 3);
        Resource resource3 = new Resource(3, "R3", new Point(300, 450), 1);
        Relation relation1 = new Relation(resource1.getInstances().get(0), process1);
        Relation relation2 = new Relation(process2, resource3.getInstances().get(0));
        Relation relation3 = new Relation(process4, resource1.getInstances().get(1));
        Relation relation4 = new Relation(process3, resource2.getInstances().get(2));
        Resource resource4 = new Resource(4, "R4", new Point(220, 100), 5);
        Resource resource5 = new Resource(5, "R5", new Point(67, 400), 2);
        Resource resource6 = new Resource(6, "R6", new Point(300, 100), 4);
        Process process5 = new Process(5, "P5", new Point(500, 328));
        Relation relation5 = new Relation(process2, resource4.getInstances().get(3));
        Relation relation6 = new Relation(process1, resource5.getInstances().get(1));
        Relation relation7 = new Relation(process3, process5);
        Relation relation8 = new Relation(process1, resource6.getInstances().get(3));
        figures.add(process1);
        figures.add(process2);
        figures.add(process3);
        figures.add(process4);
        figures.add(process5);
        figures.add(resource1);
        figures.add(resource2);
        figures.add(resource3);
        figures.add(resource4);
        figures.add(resource5);
        figures.add(resource6);
        figures.add(relation1);
        figures.add(relation2);
        figures.add(relation3);
        figures.add(relation4);
        figures.add(relation5);
        figures.add(relation6);
        figures.add(relation7);
        figures.add(relation8);
    // end of example initial block

        JFrame frame = new JFrame("Resource Allocation Graph - Configuration");
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JPanel infoPanel = new JPanel();
        JPanel controlsPanel = new JPanel(new GridLayout(2, 3));
        JPanel tablePanel = new JPanel(new GridLayout(1, 2));
        JPanel aboutPanel = new JPanel();
        DrawingPanel resultsPanel = new DrawingPanel(figures);

        //controls
        JLabel lblInfo = new JLabel("<html><p>Set number of processes and resources, complete the<br> table with the number of instances of each resource, and<br> click on the <b>Generate</b> button to generate the graph.</p></html>");
        JTextField txtNumberOfProcesses = new JTextField();
        txtNumberOfProcesses.setBorder(new TitledBorder("Number of Processes"));
        JTextField txtNumberOfResources = new JTextField();
        txtNumberOfResources.setBorder(new TitledBorder("Number of Resources"));
        JTextArea txtRelations = new JTextArea(5, 30);
        JScrollPane scrollRelations = new JScrollPane(txtRelations);
        scrollRelations.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRelations.setBorder(new TitledBorder("Relations"));
        JButton createGraph = new JButton("Create Graph");
        final String[] columnNames = {"Recurso", "Instancias"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        //Action listeners
        txtNumberOfResources.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTable();
            }

            void updateTable() {
                try {
                    int numberOfResources = Integer.parseInt(txtNumberOfResources.getText());
                    model.setRowCount(0);
                    for (int i = 0; i < numberOfResources; i++) {
                        model.addRow(new String[]{"R" + (i + 1), "0"});
                    }
                    container.repaint();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number of resources");
                }

            }
        });
        txtNumberOfResources.addActionListener(e -> {

        });
        createGraph.addActionListener(e -> {
            if (!txtNumberOfProcesses.getText().isEmpty() && !txtNumberOfResources.getText().isEmpty()) {
                int numberOfProcesses = Integer.parseInt(txtNumberOfProcesses.getText());
                int numberOfResources = Integer.parseInt(txtNumberOfResources.getText());
                String[] relations = txtRelations.getText().split("\n");
                figures.clear();
                for (int i = 0; i < numberOfProcesses; i++) {
                    figures.add(new Process(i + 1, "P" + (i + 1), new Point(20 + 100 * i, 100)));
                }
                for (int i = 0; i < numberOfResources; i++) {
                    figures.add(new Resource(i + 1, "R" + (i + 1), new Point(20 + 100 * i, 300), model.getValueAt(i, 1).toString().equals("0") ? 0 : Integer.parseInt(model.getValueAt(i, 1).toString())));
                }
                //TODO: refactor this
                for (String relation : relations) {
                    String[] relationParts = relation.split(" ");
                    String toText = relationParts[1];
                    String fromText = relationParts[0];
                    Figure to;
                    Figure from;
                    if (fromText.contains(".")) {
                        String fromName = fromText.substring(0, fromText.indexOf("."));
                        String instanceName = fromText.substring(fromText.indexOf(".") + 1);
                        try {
                            Integer.parseInt(instanceName);
                            System.out.println("INSTANCIA:" + instanceName);
                            from = figures.stream().filter(f -> f.getName().equals(fromName)).findFirst().get();
                            //TODO: check the case when instance is 0 -> should be treated as a resource
                            from = ((Resource) from).getInstances().get(Integer.parseInt(instanceName) - 1);
                        } catch (NumberFormatException error) {
                            System.out.println("Invalid instance name in first part of relation");
                            return;
                        }

                    } else {
                        from = figures.stream().filter(f -> f.getName().equals(fromText)).findFirst().get();
                    }
                    if (toText.contains(".")) {
                        String toName = toText.substring(0, toText.indexOf("."));
                        String instanceName = toText.substring(toText.indexOf(".") + 1);
                        try {
                            Integer.parseInt(instanceName);
                            System.out.println("INSTANCIA:" + instanceName);
                            to = figures.stream().filter(f -> f.getName().equals(toName)).findFirst().get();
                            to = ((Resource) to).getInstances().get(Integer.parseInt(instanceName) - 1);
                        } catch (NumberFormatException error) {
                            System.out.println("Invalid instance name in second part of relation");
                            return;
                        }

                    } else {
                        to = figures.stream().filter(f -> f.getName().equals(toText)).findFirst().get();
                    }

                    figures.add(new Relation(from, to));
                }
                resultsPanel.repaint();
            }
        });

        infoPanel.add(lblInfo);
        controlsPanel.add(txtNumberOfProcesses);
        controlsPanel.add(txtNumberOfResources);
        controlsPanel.add(createGraph);
        controlsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        tablePanel.add(scrollRelations);
        tablePanel.add(new JScrollPane(table), BorderLayout.EAST);
        tablePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        aboutPanel.add(new JLabel("Developed by: Duberly Guarnizo (duberlygfr@gmail.com)"));
        container.add(infoPanel);
        container.add(controlsPanel);
        container.add(tablePanel);
        container.add(aboutPanel);
        frame.setContentPane(container);
        //frame.pack();
        frame.setVisible(true);

        JFrame resultsFrame = new JFrame("Resource Allocation Graph - Results");
        resultsFrame.setSize(800, 800);
        resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultsFrame.setContentPane(resultsPanel);
        resultsFrame.setVisible(true);

    }

    private void saveImage(JPanel panel) {
        BufferedImage imageBuffer = null;
        try {
            imageBuffer = new Robot().createScreenCapture(panel.bounds());
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        assert imageBuffer != null;
        Graphics2D graphics2D = imageBuffer.createGraphics();
        panel.paint(graphics2D);
        try {
            ImageIO.write(imageBuffer, "jpeg", new File("save1.jpeg"));
        } catch (Exception e) {
            System.out.println("error");
        }
    }

}

