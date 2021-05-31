 import java.awt.Dimension;
                import java.awt.GridBagConstraints;
                import java.awt.GridBagLayout;
                import java.awt.Insets;
                import java.awt.event.ActionEvent;
                import java.awt.event.ActionListener;
                import java.util.ArrayList;
                import java.util.List;
                import javax.swing.JButton;
                import javax.swing.JFrame;
                import javax.swing.JLabel;
                import javax.swing.JPanel;
                import javax.swing.JTextField;
                import javax.swing.border.LineBorder;

                public class CreateFormFields {
                    // Field members

                    static JPanel panel = new JPanel();
                    static Integer indexer = 1;
                    static List<JLabel> listOfLabels = new ArrayList<JLabel>();
                    static List<JTextField> listOfTextFields = new ArrayList<JTextField>();
                    static List<JTextField> listDataType = new ArrayList<JTextField>();
                    static List<JTextField> listRange = new ArrayList<JTextField>();
                    static List<JTextField> listLable = new ArrayList<JTextField>();

                    public static void main(String[] args) {
                        // Construct frame
                        JFrame frame = new JFrame();
                        frame.setLayout(new GridBagLayout());
                        frame.setPreferredSize(new Dimension(990, 990));
                        frame.setTitle("Form Creator");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        // Frame constraints
                        GridBagConstraints frameConstraints = new GridBagConstraints();

                        // Construct button
                        JButton addButton = new JButton("Add");
                         JButton createButton = new JButton("Create Form");
                        addButton.addActionListener(new ButtonListener());
                 createButton.addActionListener(new CreateForm());
                        // Add button to frame
                        frameConstraints.gridx = 0;
                        frameConstraints.gridy = 0;
                        frame.add(addButton, frameConstraints);
                         frameConstraints.gridx = 0;
                        frameConstraints.gridy = 1;
                         frame.add(createButton, frameConstraints);

                        // Construct panel
                        panel.setPreferredSize(new Dimension(900, 900));
                        panel.setLayout(new GridBagLayout());
                        panel.setBorder(LineBorder.createBlackLineBorder());

                        // Add panel to frame
                        frameConstraints.gridx = 0;
                        frameConstraints.gridy = 2;
                        frameConstraints.weighty = 1;
                        frame.add(panel, frameConstraints);

                        // Pack frame
                        frame.pack();

                        // Make frame visible
                        frame.setVisible(true);
                    }

                    static class ButtonListener implements ActionListener {

                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            // Clear panel
                            panel.removeAll();

                            // Create label and text field
                            JTextField jTextField = new JTextField();
                            jTextField.setSize(5, 20);

                            JTextField jTextField2 = new JTextField();
                            jTextField2.setSize(6, 20);

                            JTextField jTextField3 = new JTextField();
                            jTextField3.setSize(7, 20);

                            JTextField jTextField4 = new JTextField();
                            jTextField4.setSize(8, 20);

                            listOfTextFields.add(jTextField);
                            listDataType.add(jTextField2);
                            listRange.add(jTextField3);
                            listLable.add(jTextField4);
                            listOfLabels.add(new JLabel("LableName | FieldName | DataType | Range "));

                            // Create constraints
                            GridBagConstraints textFieldConstraints = new GridBagConstraints();
                            GridBagConstraints labelConstraints = new GridBagConstraints();
                            GridBagConstraints gridlistDataType = new GridBagConstraints();
                            GridBagConstraints gridlistRange = new GridBagConstraints();
                            GridBagConstraints gridlistLable = new GridBagConstraints();

                            // Add labels and text fields
                            for (int i = 0; i < indexer; i++) {
                                // Label constraints
                                labelConstraints.gridx = 0;
                                labelConstraints.gridy = i;
                                labelConstraints.insets = new Insets(10, 10, 10, 10);

                                // Text field constraints
                                textFieldConstraints.gridx = 1;
                                textFieldConstraints.fill = 1;//GridBagConstraints.HORIZONTAL;
                                textFieldConstraints.weightx = 0.5;
                                textFieldConstraints.insets = new Insets(10, 10, 10, 10);
                                textFieldConstraints.gridy = i;


                                gridlistDataType.gridx = 2;
                                gridlistDataType.fill = 1;//GridBagConstraints.HORIZONTAL;
                                gridlistDataType.weightx = 0.5;
                                gridlistDataType.insets = new Insets(10, 10, 10, 10);
                                gridlistDataType.gridy = i;


                                gridlistRange.gridx = 3;
                                gridlistRange.fill = 1;//GridBagConstraints.HORIZONTAL;
                                gridlistRange.weightx = 0.5;
                                gridlistRange.insets = new Insets(10, 10, 10, 10);
                                gridlistRange.gridy = i;

                                gridlistLable.gridx = 4;
                                gridlistLable.fill = 1;//GridBagConstraints.HORIZONTAL;
                                gridlistLable.weightx = 0.5;
                                gridlistLable.insets = new Insets(10, 10, 10, 10);
                                gridlistLable.gridy = i;





                                // Add them to panel
                                panel.add(listOfLabels.get(i), labelConstraints);
                                panel.add(listOfTextFields.get(i), textFieldConstraints);
                                panel.add(listDataType.get(i), gridlistDataType);
                                panel.add(listRange.get(i), gridlistRange);
                                panel.add(listLable.get(i), gridlistLable);



                            }

                            // Align components top-to-bottom
                            GridBagConstraints c = new GridBagConstraints();
                            c.gridx = 0;
                            c.gridy = indexer;
                            c.weighty = 1;
                            panel.add(new JLabel(), c);

                            // Increment indexer
                            indexer++;
                            panel.updateUI();
                        }
                    }




                      static class CreateForm implements ActionListener {

                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            // Clear panel
                            panel.removeAll();

                            // Create label and text field
                            JTextField jTextField = new JTextField();
                            jTextField.setSize(5, 20);

                            JTextField jTextField2 = new JTextField();
                            jTextField2.setSize(6, 20);

                            JTextField jTextField3 = new JTextField();
                            jTextField3.setSize(7, 20);

                            JTextField jTextField4 = new JTextField();
                            jTextField4.setSize(8, 20);

                            listOfTextFields.add(jTextField);
                            listDataType.add(jTextField2);
                            listRange.add(jTextField3);
                            listLable.add(jTextField4);
                            listOfLabels.add(new JLabel("LableName | FieldName | DataType | Range "));

                            // Create constraints
                            GridBagConstraints textFieldConstraints = new GridBagConstraints();
                            GridBagConstraints labelConstraints = new GridBagConstraints();
                            GridBagConstraints gridlistDataType = new GridBagConstraints();
                            GridBagConstraints gridlistRange = new GridBagConstraints();
                            GridBagConstraints gridlistLable = new GridBagConstraints();

                            // Add labels and text fields
                            for (int i = 0; i < indexer; i++) {
                                // Label constraints
                                labelConstraints.gridx = 0;
                                labelConstraints.gridy = i;
                                labelConstraints.insets = new Insets(10, 10, 10, 10);

                                // Text field constraints
                                textFieldConstraints.gridx = 1;
                                textFieldConstraints.fill = 1;//GridBagConstraints.HORIZONTAL;
                                textFieldConstraints.weightx = 0.5;
                                textFieldConstraints.insets = new Insets(10, 10, 10, 10);
                                textFieldConstraints.gridy = i;


                                gridlistDataType.gridx = 2;
                                gridlistDataType.fill = 1;//GridBagConstraints.HORIZONTAL;
                                gridlistDataType.weightx = 0.5;
                                gridlistDataType.insets = new Insets(10, 10, 10, 10);
                                gridlistDataType.gridy = i;


                                gridlistRange.gridx = 3;
                                gridlistRange.fill = 1;//GridBagConstraints.HORIZONTAL;
                                gridlistRange.weightx = 0.5;
                                gridlistRange.insets = new Insets(10, 10, 10, 10);
                                gridlistRange.gridy = i;

                                gridlistLable.gridx = 4;
                                gridlistLable.fill = 1;//GridBagConstraints.HORIZONTAL;
                                gridlistLable.weightx = 0.5;
                                gridlistLable.insets = new Insets(10, 10, 10, 10);
                                gridlistLable.gridy = i;





                                // Add them to panel
                                panel.add(listOfLabels.get(i), labelConstraints);
                                panel.add(listOfTextFields.get(i), textFieldConstraints);
                                panel.add(listDataType.get(i), gridlistDataType);
                                panel.add(listRange.get(i), gridlistRange);
                                panel.add(listLable.get(i), gridlistLable);



                            }

                            // Align components top-to-bottom
                            GridBagConstraints c = new GridBagConstraints();
                            c.gridx = 0;
                            c.gridy = indexer;
                            c.weighty = 1;
                            panel.add(new JLabel(), c);

                            // Increment indexer
                            indexer++;
                            panel.updateUI();
                        }
                    }

                }