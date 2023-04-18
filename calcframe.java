
package calculator;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;
import java.util.*;
import javax.swing.*;
/**
 *
 */
public class calcframe implements ActionListener{
    
    
    String num1="",num2="",operator="";//Seperate the Input
    ArrayList<JButton> buttons=new ArrayList<>();//List of buttons
    
    //Main frame
    JFrame frame=new JFrame("Calculator");
    JPanel panel=new JPanel();
    JLabel textarea=new JLabel();
    
    calcframe(){
        frame.setBounds(100, 50, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        
        //Intro Label
        JLabel intro =new JLabel("CALCULATOR");
        intro.setBounds(75, 150, 400, 60);
        intro.setFont(new Font("Arial",Font.PLAIN,50));
        frame.add(intro);
        frame.setVisible(true);
        try {
            sleep(2000);
        } catch (InterruptedException ex) {}
        frame.remove(intro);
        frame.setVisible(false);
        
        //Display area
        textarea.setBounds(10, 10, 460, 80);
        textarea.setOpaque(true);
        textarea.setBackground(Color.WHITE);
        textarea.setFont(new Font("Arial",Font.PLAIN,30));
        textarea.setVerticalAlignment(JLabel.BOTTOM);
        textarea.setHorizontalAlignment(JLabel.RIGHT);
        frame.add(textarea);
        
        panel.setLayout(new GridLayout(5,3));
        panel.setBounds(10, 100, 465, 355);
        
        //Inserting Button
       String[] texts={"+","-","*","/","1","2","3","%","4","5","6",".","7","8","9","^","AC","0","Clear","="};
       for(int i=0;i<texts.length;i++){
           JButton button=new JButton((texts[i]));
           
           button.setSize(new Dimension(40,40));
           button.setFont(new Font("Arial",Font.PLAIN,22));
           buttons.add(i,button);
       }
        for(JButton b:buttons){
           b.setSize(new Dimension(30,30));
           b.setFont(new Font("Arial",Font.PLAIN,22));
           b.addActionListener(this);
           panel.add(b);
        }
        frame.add(panel);
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args){
        calcframe frame=new calcframe();
    }

    @Override
    
    
    public void actionPerformed(ActionEvent e) {
        for(JButton button:buttons){
            if(e.getSource()==button){
                
                boolean display=true;
                double value=(num1.length()!=0)?Double.parseDouble(num1):0;
                String text=textarea.getText();
                String buttontext=button.getText();
                
                //click "=" way to Calulation and TextArea value correct or not
                if(buttontext.equals("=") && num1!="" && num2!="" && operator!=""){
                   value=calculation(num1,num2,operator);
                }
                //Empty TextArea
                else if(buttontext.equals("AC")){
                    display=false;
                    num1="";num2="";operator="";   
                    textarea.setText("");
                }
                //Clear the Last value of the TextArea 
                else if(buttontext.equals("Clear") ){
                    display=false;
                    if(text.length()!=0)
                        textarea.setText(text.substring(0, text.length()-1));
                }
                //Save the Operator in variable
                else if(Arrays.asList("+","-","*","/","%","^").contains(buttontext)){
                    //Text area contain Operator 
                    display=(operator!="" || num1=="")?false:true;
                    operator=buttontext;
                }
                //variabe for first number or Before the Operator
                else if(Arrays.asList("1","2","3","4","5","6","7","8","9","0",".").contains(buttontext) && operator.equals("")){
                   num1+=button.getText();
                }
                //Second Variable
                else if(Arrays.asList("1","2","3","4","5","6","7","8","9","0",".").contains(buttontext)){
                   num2+=button.getText();
                }
                if(display){
                    if(!buttontext.equals("="))
                        textarea.setText(text+buttontext);
                    else{
                        num1=value+"";
                        int len=num1.length();
                        num2="";operator="";
                        num1=(num1.substring(len-2,len).equals(".0"))?num1.substring(0, len-2):num1;
                        textarea.setText(num1);//Display the Result in TextArea
                    }
                }
            }
        }
    }
    //Arithmetic Operations
    static double calculation(String num1,String num2,String operator){
        
        switch(operator){
            case "+":
                return Double.parseDouble(num1)+Double.parseDouble(num2);
            case "-":
                return Double.parseDouble(num1)-Double.parseDouble(num2);
            case "*":
                return Double.parseDouble(num1)*Double.parseDouble(num2);
            case "/":
                return Double.parseDouble(num1)/Double.parseDouble(num2);
            case "%":
                return Double.parseDouble(num1)%Double.parseDouble(num2);
            case "^":
                return Math.pow(Double.parseDouble(num1),Double.parseDouble(num2));
            default:
                return 0;
        }
    }
}
