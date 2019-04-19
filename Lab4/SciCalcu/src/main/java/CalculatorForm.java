import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class CalculatorForm  {

    private JPanel mainPanel;
    private JButton evalButton;
    private JTextField formulaInput;
    private JList functionsList;
    private JTextArea historyTextArea;
    private JScrollPane scrollContainerPane;

    public JButton getEvalButton() {
        return evalButton;
    }

    public JTextField getFormulaInput() {
        return formulaInput;
    }

    public JList getFunctionsList() {
        return functionsList;
    }

    public JTextArea getHistoryTextArea() {
        return historyTextArea;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JMenuBar menuBar;
        JMenuItem menuItem;
        JMenuItem menu;
        mainPanel = new JPanel();
        JFrame calculatorFrame = new JFrame();

        calculatorFrame.setTitle("Scientific Calculator");
        calculatorFrame.setSize(800, 600);
        calculatorFrame.setLocation(screenSize.width/2-calculatorFrame.getSize().width/2,screenSize.height/2-calculatorFrame.getSize().height/2);
        calculatorFrame.setContentPane(mainPanel);
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setVisible(true);


        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        menu.setMnemonic(KeyEvent.VK_0);
        menuBar.add(menu);
        menuItem = new JMenuItem("Reset", KeyEvent.VK_R);
        menuItem.addActionListener(event -> {
            formulaInput.setText(null);
            historyTextArea.setText(null);
        }); // <-to

        menu.add(menuItem);

        menuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        menuItem.addActionListener((event -> {
            System.exit(0);
        })); // <- to

        menu.add(menuItem);
        calculatorFrame.setJMenuBar(menuBar);






    }













}
