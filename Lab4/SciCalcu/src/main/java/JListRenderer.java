import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JListRenderer extends MainClass {

    public JListRenderer(final JList<ListElement> lista,final JTextField field,final JTextArea textArea){

        ListElement sinusFun = new ListElement("Sinus","sin()");
        ListElement cosinusFun = new ListElement("Cosinus","cos()");
        ListElement TangensFun = new ListElement("Tangens","tan()");
        ListElement CotangensFun = new ListElement("Cotangens","ctan()");
        ListElement logarithmFun = new ListElement("Logarithm","ln()");
        ListElement PIFunction =new ListElement("Pi","pi");
        ListElement eFunction =new ListElement("e","e");
        ListElement pnFunction =new ListElement("PN","[PN]");
        ListElement additionFunction =new ListElement("Addition","+");
        ListElement exponentiationFunction =new ListElement("Exponentiation","^");
        ListElement moduloFunction =new ListElement("Modulo","#");
        ListElement lastResult =new ListElement("Last result","");

        final DefaultListModel<ListElement> listModel = new DefaultListModel<>();
        listModel.addElement(sinusFun);
        listModel.addElement(cosinusFun);
        listModel.addElement(TangensFun);
        listModel.addElement(CotangensFun);
        listModel.addElement(logarithmFun);
        listModel.addElement(PIFunction);
        listModel.addElement(eFunction);
        listModel.addElement(pnFunction);
        listModel.addElement(additionFunction);
        listModel.addElement(exponentiationFunction);
        listModel.addElement(moduloFunction);
        listModel.addElement(lastResult);
        lista.setModel(listModel);
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() ==2){
                    if(lista.isSelectedIndex(11))
                        field.setText(getLast());
                    field.setText(field.getText()+lista.getSelectedValue().getExpression());
                    if(lista.getSelectedValue().getExpression().contains("()")){
                        String s = field.getText();
                        int i=s.lastIndexOf("(");
                        field.requestFocus();
                        field.setCaretPosition(i+1);

                    }

                }
            }
        });
    }


}
