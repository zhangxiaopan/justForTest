package stumng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;


public class sumPeople extends JFrame {
  DBConnManager dbconnmanager = new DBConnManager();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JComboBox jComboBox2 = new JComboBox();
  JComboBox jComboBox1 = new JComboBox();
  JTextField jTextField1 = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable();
  JPanel jPanel2 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JTable jTable2 = new JTable();
  JLabel jLabel1 = new JLabel();
  JFormattedTextField jFormattedTextField1 = new JFormattedTextField(DateFormat.getDateInstance());
  JLabel jLabel2 = new JLabel();
  JFormattedTextField jFormattedTextField2 = new JFormattedTextField(DateFormat.getDateInstance());

  private String aclass ;
  private String aschool;
  private String date_range1;
  private String date_range2;
  private String date_range1str;
  private String date_range2str;
  private String aethnic;
  private String str ;
  private String str1;
  private boolean flag ;

  public sumPeople() {
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
    this.setTitle("学生统计");
    //设置frame在屏幕上显示的位置
    this.setLocation( (screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);


   jButton1.addActionListener(new ActionListener()
 {
   public void actionPerformed(ActionEvent e)
   {
     Vector result = new Vector();
     Vector resultdate = new Vector();
     Vector headline = new Vector();

     aclass = "";
     aschool = "";
     date_range1 = "";
     date_range2 = "";
     aethnic = "";
     str1 = "";
     flag = true;

     jTable1 = new JTable();
     jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
     jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表


     str = (String) jComboBox1.getSelectedItem();

     if (!str.equals("班级号")) aschool = str;
     else aclass = jTextField1.getText().trim();
     flag = dbconnmanager.checkClass(aclass);
     if(flag == false&&str.equals("班级号") )JOptionPane.showMessageDialog(null, "班级号输入错误");
     else if (str.equals("班级号") && aclass.equals(""))
       JOptionPane.showMessageDialog(null, "请输入班级号");
     else {
       str1 = (String) jComboBox2.getSelectedItem();
       if (str1.equals("男")) result = dbconnmanager.getpeopleSum(aclass,
           aschool, "男", "", null, null);
       else if (str1.equals("女")) result = dbconnmanager.getpeopleSum(
           aclass, aschool, "女", "", null, null);
       else if (str1.equals("出生日期")) {
         date_range1str = jFormattedTextField1.getText().trim();
         date_range2str = jFormattedTextField2.getText().trim();
         if (date_range1str.equals("") || date_range2str.equals(""))
         {
           JOptionPane.showMessageDialog(null, "请输入统计出生日期范围");
           flag = false;
         }
         else {
           date_range1 = reverse(date_range1str);
           date_range2 = reverse(date_range2str);
           result = dbconnmanager.getpeopleSum(aclass, aschool, "出生日期", "",
               date_range1 ,
               date_range2 );
         }
       }
       else if (str1.equals("民族")) {
         aethnic = JOptionPane.showInputDialog("请输入民族");
         if (aethnic.equals("")) {
           JOptionPane.showMessageDialog(null, "不能为空");
           flag = false;
         }
         else {
           aethnic = aethnic + "%";
           result = dbconnmanager.getpeopleSum(aclass, aschool, "民族",
               aethnic, null, null);
         }
       }

       if (result.size() == 0&&flag == true) JOptionPane.showMessageDialog(null, "无记录");
       else {
         for (int i = 0; i < result.size() / 4; i++) {
           Vector temp = new Vector();
           for (int j = 0; j <= 3; j++) {
             String str2 = (String) result.elementAt(j + i * 4);
             temp.add(str2);
           }
           resultdate.add(temp);
         }

         headline.add("学号");
         headline.add("姓名");
         headline.add(str1);
         headline.add("班级");

         jTable1 = new JTable(resultdate, headline);
         jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
         jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表
       }
       if (str.equals("全校")) {
         if (str1.equals("出生日期"))
           JOptionPane.showMessageDialog(null,
                                         "在全校范围内，出生日期从" + date_range1str + "到" +
                                         date_range2str + "的学生有" + resultdate.size() +
                                         "人");
         else if (str1.equals("男") || str1.equals("女"))
           JOptionPane.showMessageDialog(null,
                                         "在全校范围内，性别为" + str1 + "的学生有" +
                                         resultdate.size() + "人");
         else if (str1.equals("民族"))
           JOptionPane.showMessageDialog(null,
                                         "在全校范围内，民族为" + aethnic + "的学生有" +
                                         resultdate.size() + "人");
       }
       else if (str.equals("班级号")) {
         if (str1.equals("出生日期")&&!date_range1str.equals("")&&!date_range2str.equals(""))
           JOptionPane.showMessageDialog(null,
                                         "在" + aclass + "班,出生日期从" + date_range1str +
                                         "到" + date_range2str + "的学生有" +
                                         resultdate.size() + "人");
         else if (str1.equals("男") || str1.equals("女"))
           JOptionPane.showMessageDialog(null,
                                         "在" + aclass + "班,性别为" + str1 +
                                         "的学生有" + resultdate.size() + "人");
         else if (str1.equals("民族")&&!aethnic.equals(""))
           JOptionPane.showMessageDialog(null,
                                         "在" + aclass + "班,民族为" + aethnic +
                                         "的学生有" + resultdate.size() + "人");

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
    jComboBox1.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox1.addActionListener(new sumPeople_jComboBox1_actionAdapter(this));
    jComboBox2.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox2.addActionListener(new sumPeople_jComboBox2_actionAdapter(this));
    jComboBox1.addItem("班级号");
    jComboBox1.addItem("全校");
    jComboBox2.addItem("男");
    jComboBox2.addItem("女");
    jComboBox2.addItem("出生日期");
    jComboBox2.addItem("民族");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField1.setText("");
    jTextField1.setColumns(8);
    jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 15));
    jTable1.setFont(new java.awt.Font("Dialog", 0, 15));
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("提交");
    jButton2.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setText("退出");
    jTable2.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel1.setText("从");
    jFormattedTextField1.setColumns(10);
    jFormattedTextField1.setFont(new java.awt.Font("SansSerif", 0, 13));
    jFormattedTextField1.setText("");
    jLabel2.setEnabled(true);
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel2.setVerifyInputWhenFocusTarget(true);
    jLabel2.setText("到");
    jFormattedTextField2.setColumns(10);
    jFormattedTextField2.setFont(new java.awt.Font("SansSerif", 0, 13));
    jFormattedTextField2.setText("");
    jPanel1.add(jComboBox1, null);
    jPanel1.add(jTextField1, null);
    jPanel1.add(jComboBox2, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jFormattedTextField1, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jFormattedTextField2, null);
    this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTable2, null);
    this.getContentPane().add(jPanel2,  BorderLayout.SOUTH);
    jPanel2.add(jButton1, null);
    jPanel2.add(jButton2, null);
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
  }

  void jComboBox1_actionPerformed(ActionEvent e) {
    str = (String)jComboBox1.getSelectedItem();
    if( str.equals("全校") )jTextField1.setEditable(false);
    if( str.equals( "班级号") )jTextField1.setEditable(true);
  }

  void jButton2_actionPerformed(ActionEvent e)
   {
     jTable1 = new JTable();
     jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
     jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表

     this.dispose();
     dbconnmanager.closeConns();
   }

  void jComboBox2_actionPerformed(ActionEvent e) {
    str1 = (String)jComboBox2.getSelectedItem();
    if( str1.equals("出生日期") )
    {
      jFormattedTextField1.setEditable(true);
      jFormattedTextField2.setEditable(true);
    }else{
      jFormattedTextField1.setEditable(false);
      jFormattedTextField2.setEditable(false);
    }
  }

  //将输入的日期型转换为#月/日/年#的形式
  public String reverse(String  datestr)
  {
    int i = datestr.lastIndexOf("-");
    String querystr = "";
    String ee = datestr.substring(0,i);
    int j = ee.lastIndexOf("-");
    querystr = "#" + ee.substring(j+1,ee.length())+"/"+datestr.substring(i+1,datestr.length())+"/"+datestr.substring(0,j)+"#";
    return querystr;
  }
}

class sumPeople_jComboBox1_actionAdapter implements java.awt.event.ActionListener {
  sumPeople adaptee;

  sumPeople_jComboBox1_actionAdapter(sumPeople adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jComboBox1_actionPerformed(e);
  }
}

class sumPeople_jComboBox2_actionAdapter implements java.awt.event.ActionListener {
  sumPeople adaptee;

  sumPeople_jComboBox2_actionAdapter(sumPeople adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jComboBox2_actionPerformed(e);
    }
  }
