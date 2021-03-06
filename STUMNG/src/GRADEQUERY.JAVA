package stumng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class gradeQuery extends JFrame {
  DBConnManager dbconnmanager = new DBConnManager();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JComboBox jComboBox1 = new JComboBox();
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  JButton jButton2 = new JButton();
  JTable jTable1 = new JTable();

  public gradeQuery() {
    try {
      jbInit();
      //设置frame的大小
      this.setSize(600, 400);
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
      this.setTitle("查询学生成绩");
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
       int astu_id = 0;
       String str = "";
       String aclass = "";
       boolean flag = false;

       headline.add("学号");
       headline.add("姓名");
       headline.add("语文成绩");
       headline.add("数学成绩");
       headline.add("英语成绩");
       headline.add("总评");
       headline.add("总分");
       headline.add("班级");

       jTable1 = new JTable();
       jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
       jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表

       str = (String) jComboBox1.getSelectedItem();
       aclass = jTextField1.getText();
       if (str.equals("班级学生成绩查询")) {
         flag = dbconnmanager.checkClass(aclass);
         if (aclass.equals("")) JOptionPane.showMessageDialog(null, "请输入班级号");
         else if (flag == false) JOptionPane.showMessageDialog(null,
             "班级号输入错误");
       }
       else  if(str.equals("学生个人成绩查询")){
         if (aclass.equals("")) JOptionPane.showMessageDialog(null, "请输入学号");
         else {
           flag = dbconnmanager.checkStuid(aclass);
           if (flag == false) JOptionPane.showMessageDialog(null,
               "学号只能是0－9的数据");
           else {
             astu_id = Integer.parseInt(jTextField1.getText());
             if(aclass.length()>5)
             {
               flag = dbconnmanager.userExist(astu_id,
                                              "A" + aclass.substring(0,4));
               if(flag == false )JOptionPane.showMessageDialog(null, "该学生不存在");
             }
             else  {
               JOptionPane.showMessageDialog(null, "学号输入错误");
               flag = false;
             }
           }
         }
       }
       if (flag == true) {
         result = dbconnmanager.stuGradeQuery(astu_id, aclass);
         if (result.size() == 0) JOptionPane.showMessageDialog(null, "无记录");
         else {
           for (int i = 0; i < result.size() / 8; i++) {
             Vector temp = new Vector();
             for (int j = 0; j <= 7; j++) {
               String str1 = (String) result.elementAt(j + i * 8);
               temp.add(str1);
             }
             resultdate.add(temp);
           }
           jTable1 = new JTable(resultdate, headline);
           jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
           jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表
         }
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
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField1.setText("");
    jTextField1.setColumns(10);
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("提交");
    jComboBox1.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox1.addItem("班级学生成绩查询");
    jComboBox1.addItem("学生个人成绩查询");
    jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setText("退出");
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jComboBox1, null);
    jPanel1.add(jTextField1, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
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
