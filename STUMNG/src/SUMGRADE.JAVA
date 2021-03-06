package stumng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class sumGrade extends JFrame {
  DBConnManager dbconnmanager = new DBConnManager();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JComboBox jComboBox1 = new JComboBox();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JComboBox jComboBox2 = new JComboBox();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jPanel2 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JTable jTable1 = new JTable();

  private String str1 = "";
  private String str2 = "";
  private boolean flag = true;

  public sumGrade() {
    try {
      jbInit();
      //设置frame的大小
     this.setSize(600, 500);
     //得到当前屏幕大小
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     //得到frame的大小
     Dimension frameSize = this.getSize();
     if (frameSize.height > screenSize.height) {
       frameSize.height = screenSize.height;
     }
     if (frameSize.width > screenSize.width) {
       frameSize.width = screenSize.width;
     }
     this.setTitle("学生成绩统计");
     //设置frame在屏幕上显示的位置
     this.setLocation( (screenSize.width - frameSize.width) / 2,
                      (screenSize.height - frameSize.height) / 2);

    //按学号查询该学生的基本信息
    jButton1.addActionListener(new ActionListener()
   {
     public void actionPerformed(ActionEvent e)
     {
       Vector result = new Vector();
       Vector resultdate = new Vector();
       Vector headline = new Vector();
       float range1 = 0;
       float range2 = 0;
       String aschool = "";
       String str = "";
       String aclass = "";
       String grade = "";
       String gradestr = "";

       jTable1 = new JTable();
       jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
       jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表

       str = (String) jComboBox2.getSelectedItem();
       if (str.equals("班级号")) {
         aclass = jTextField2.getText();
         flag = dbconnmanager.checkClass(aclass);
         if (aclass.equals("")) JOptionPane.showMessageDialog(null, "请输入班级号");
         else if (flag == false) JOptionPane.showMessageDialog(null,
             "班级号输入错误");
       }
       else aschool = "全校";

       str1 = jTextField3.getText().trim();
       str2 = jTextField1.getText().trim();
       if (str1.equals("")&&flag == true) JOptionPane.showMessageDialog(null, "请输入最低分数线");
       else if(flag ==true ){
         range1 = Float.parseFloat(str1);
         if (str2.equals("")) range2 = 1000;
         else range2 = Float.parseFloat(str2);

         gradestr = (String) jComboBox1.getSelectedItem();
         if (gradestr.equals("语文成绩")){
           result = dbconnmanager.getSum(aclass, aschool, "chinese", range1,
                                         range2);
           grade = "语文成绩";
         }
         else if (gradestr.equals("数学成绩")){
           result = dbconnmanager.getSum(aclass, aschool, "math", range1,
                                         range2);
           grade = "数学成绩";
         }
         else if (gradestr.equals("英语成绩")){
           result = dbconnmanager.getSum(aclass, aschool, "english", range1,
                                         range2);
           grade = "英语成绩";
         }
         else if (gradestr.equals("总评")){
           result = dbconnmanager.getSum(aclass, aschool, "composite",
                                         range1, range2);
           grade = "总评";
         }
         else if (gradestr.equals("总分")){
           result = dbconnmanager.getSum(aclass, aschool, "total", range1,
                                         range2);
           grade = "总分";
         }

         headline.add("学号");
         headline.add("姓名");
         headline.add(grade);
         headline.add("班级");

         if (result.size() == 0) JOptionPane.showMessageDialog(null, "无记录");
         else {
           for (int i = 0; i < result.size() / 4; i++) {
             Vector temp = new Vector();
             for (int j = 0; j <= 3; j++) {
               String str1 = (String) result.elementAt(j + i *4);
               temp.add(str1);
             }
             resultdate.add(temp);
           }
           jTable1 = new JTable(resultdate, headline);
           jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
           jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表
         }
       }
       if (str.equals("全校")&&!str1.equals("")&&flag == true) {
         if(range2<1000)
           JOptionPane.showMessageDialog(null,
                                         "在全校范围内，学生"+grade+"从" + range1 + "到" +
                                         range2 + "的学生有" + resultdate.size() +
                                         "人");
         else  JOptionPane.showMessageDialog(null,
                                      "在全校范围内,学生"+grade+"大于" + range1+
                                      "的学生有" + resultdate.size() +"人");

       }
       else if (str.equals("班级号")&&!str1.equals("")&&flag == true) {
         if(range2<1000)
           JOptionPane.showMessageDialog(null,
                                        "在"+aclass+"内,学生"+grade+"从" + range1 + "到" +
                                        range2 + "的学生有" + resultdate.size() +
                                        "人");
        else if(range2 ==1000)
          JOptionPane.showMessageDialog(null,
                                        "在"+aclass+"内,学生"+grade+"大于" + range1+
                                        "的学生有" + resultdate.size() +"人");
       }

     }
   });


    //退出frame
     jButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton2_actionPerformed(e);
      }
    });

    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.getContentPane().setLayout(borderLayout1);
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel1.setText("从");
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel2.setText("到");
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField2.setText("");
    jTextField2.setColumns(5);
    jPanel1.setPreferredSize(new Dimension(288, 37));
    jComboBox1.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox1.setMaximumSize(new Dimension(32767, 32767));
    jComboBox2.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox2.addActionListener(new sumGrade_jComboBox2_actionAdapter(this));
    jComboBox2.addItem("班级号");
    jComboBox2.addItem("全校");
    jComboBox1.addItem("语文成绩");
    jComboBox1.addItem("数学成绩");
    jComboBox1.addItem("英语成绩");
    jComboBox1.addItem("总评");
    jComboBox1.addItem("总分");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField1.setText("");
    jTextField1.setColumns(8);
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField3.setText("");
    jTextField3.setColumns(8);
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("提交");
    jButton2.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setText("退出");
    jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 15));
    jTable1.setEnabled(false);
    jTable1.setFont(new java.awt.Font("Dialog", 0, 15));
    jPanel1.add(jComboBox2, null);
    jPanel1.add(jTextField2, null);
    jPanel1.add(jComboBox1, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jTextField3, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jTextField1, null);
    this.getContentPane().add(jScrollPane1,  BorderLayout.CENTER);
    this.getContentPane().add(jPanel2,  BorderLayout.SOUTH);
    jPanel2.add(jButton1, null);
    jPanel2.add(jButton2, null);
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
  }

  void jComboBox2_actionPerformed(ActionEvent e) {
    String str = (String)jComboBox2.getSelectedItem();
    if( str.equals("全校") )jTextField2.setEditable(false);
    if( str.equals( "班级号") )jTextField2.setEditable(true);
  }

  void jButton2_actionPerformed(ActionEvent e)
  {
    jTable1 = new JTable();
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
    jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表

    this.dispose();
    dbconnmanager.closeConns();
  }

}

class sumGrade_jComboBox2_actionAdapter implements java.awt.event.ActionListener {
  sumGrade adaptee;

  sumGrade_jComboBox2_actionAdapter(sumGrade adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jComboBox2_actionPerformed(e);
  }
}
