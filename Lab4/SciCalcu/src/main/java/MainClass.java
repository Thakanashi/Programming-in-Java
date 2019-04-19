import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.event.*;
import java.text.MessageFormat;

public class MainClass {
    private static double last;
    private static CalculatorForm Calculator = new CalculatorForm();
    public static void main(String[] args) {

        new JListRenderer(Calculator.getFunctionsList(),Calculator.getFormulaInput(),Calculator.getHistoryTextArea());
        Calculator.getEvalButton().addActionListener(new MyAction());
        Calculator.getFormulaInput().addKeyListener(myKey);

}

    public static class MyAction implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Evaluate")){
                lastAction = Calculator.getFormulaInput().getText();
                Calculate();
            }
        }
    }

    public static void Calculate(){

        Expression expression = new Expression(Calculator.getFormulaInput().getText());
        String t = Calculator.getHistoryTextArea().getText();
        if (expression.checkSyntax()) {
            double result = expression.calculate();
            String tmp = Calculator.getFormulaInput().getText();
            String equals = " = ";
            last=result;
            Calculator.getFormulaInput().setText(null);
            Calculator.getHistoryTextArea().setText( MessageFormat.format(" {0} {1} {2} {3} \n",t,tmp,equals,result));
        }
        else {
            String errorMessage = expression.getErrorMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);

        }


    }


    static KeyListener myKey = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                lastAction = Calculator.getFormulaInput().getText();
                Calculate();
            }
            else if(e.getKeyCode()==KeyEvent.VK_UP){

                Calculator.getFormulaInput().setText(lastAction);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };
    static String lastAction;

    public static String getLast() {
        return String.valueOf(last);
    }
}



