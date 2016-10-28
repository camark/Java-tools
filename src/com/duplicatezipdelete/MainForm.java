import org.omg.CORBA.Environment;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/10/10.
 */
public class MainForm extends JFrame implements ILog {
    public MainForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        setTitle("Scan Dir and del extracted compress file");

        JPanel centerPanel=new JPanel();
        JTextField edt_Dir=new JTextField(20);
        JButton btn_Browse=new JButton();
        btn_Browse.setText("Browse...");

        btn_Browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setApproveButtonText("确定");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  //设置只选择目录
                int returnVal = chooser.showOpenDialog(MainForm.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    edt_Dir.setText(chooser.getSelectedFile().getAbsolutePath());
                    // System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
                }
            }
        });
        //centerPanel.setLayout(new FlowLayout());
        centerPanel.add(edt_Dir,BorderLayout.CENTER);
        centerPanel.add(btn_Browse,BorderLayout.WEST);
        add(centerPanel,BorderLayout.NORTH);

        JPanel bottomPanel=new JPanel();
        Button btnBegin=new Button();
        btnBegin.setLabel("Begin Scan...");
        bottomPanel.add(btnBegin);

        btnBegin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dir=edt_Dir.getText();

                if(dir.length()!=0){
                    DelDuplicate delDuplicate=new DelDuplicate();
                    delDuplicate.setLog(MainForm.this);
                    delDuplicate.scanAndDel(dir);
                }
            }
        });
        add(bottomPanel,BorderLayout.CENTER);

        logArea=new JTextArea();
        logArea.setRows(5);
        add(logArea,BorderLayout.SOUTH);
    }

    JTextArea logArea;

    public void showWindows(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int horizontal = dim.width/2-this.getSize().width/2;
        int vertical = dim.height/2-this.getSize().height/2;
        setLocation(horizontal, vertical);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainForm mainForm=new MainForm();
        mainForm.showWindows();
    }

    @Override
    public void Log(String s) {
        logArea.append(s+System.getProperty("line.separator"));
    }
}
