package indicators.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowIndicatorJPanel extends JPanel implements ActionListener {
    private final JButton jbtnOK;
    private final JRadioButton jrbStore;
    private final JRadioButton jrbBranch;
    private final JLabel jlabSelectIndicator;
    private final JLabel jlabDate;
    private final JLabel jlabPrice;
    private final Border border;
    private final JComboBox indicatortList;
    private final ButtonGroup buttonGroup;
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    private final JPanel panel4;
    private final String[] indicatorTitles = new String[]{"aaaaaaaaaa", "bbbbbbbbb"};
    private final String[] storeTitles = new String[]{"aaaaaaaaaaaaa", "bbbbbbbbbbbb"};
    private static final String euro = "\u20ac";

    public ShowIndicatorJPanel() {
        super(new GridLayout(4, 1));

        jlabSelectIndicator = new JLabel("Επιλέξτε Δείκτη:");
        indicatortList = new JComboBox<>(indicatorTitles);
        indicatortList.setPreferredSize(new Dimension(300, 30));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel1.add(jlabSelectIndicator);
        panel1.add(indicatortList);


        jrbStore = new JRadioButton("Εταιρία", false);
        jrbStore.addActionListener(this);
        jrbBranch = new JRadioButton("Υποκατάστημα", true);
        jrbBranch.addActionListener(this);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jrbStore);
        buttonGroup.add(jrbBranch);
        JComboBox<String> storeBranchList = new JComboBox<>(storeTitles);
        storeBranchList.setPreferredSize(new Dimension(300, 30));
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 15));
        panel2.add(jrbStore);
        panel2.add(jrbBranch);
        panel2.add(storeBranchList);

        jlabDate = new JLabel("Ημερομηνία:");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        DateFormatter df = new DateFormatter(format);
        JFormattedTextField dateTextField = new JFormattedTextField(df);
        dateTextField.setPreferredSize(new Dimension(100, 25));
        dateTextField.setValue(new Date());
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 15));
        panel3.add(jlabDate);
        panel3.add(dateTextField);

        jbtnOK = new JButton("OK");
        border = BorderFactory.createLineBorder(Color.BLACK);
        jlabPrice = new JLabel("Τιμή:");
        jlabPrice.setBorder(border);
        jlabPrice.setPreferredSize(new Dimension(130, 25));
        //jlabPrice.setEnabled(false);
        panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 22, 15));
        panel4.add(jbtnOK);
        panel4.add(jlabPrice);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        // calculate the Price
        jbtnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jlabPrice.setText("ssasdsd");
            }
        });

        // for enter click
        dateTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    // change the storeBranchList
    public void actionPerformed(ActionEvent e) {
        if (jrbStore.isSelected()) {
        } else {
        }
    }

} 
