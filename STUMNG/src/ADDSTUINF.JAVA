package stumng;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.*;

public class addStuInf extends JFrame {
  DBConnManager dbconnmanager = new DBConnManager();
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField3 =  new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JFormattedTextField jTextField6 = new JFormattedTextField(DateFormat.getDateInstance());
  JLabel jLabel7 = new JLabel();
  JTextField jTextField7 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JComboBox jComboBox1 = new JComboBox();
  JComboBox jComboBox2 = new JComboBox();


  //定义变量
  int astu_id;     //学号
  String userid;   //接收界面传人的学号
  String aname;    //姓名
  String aclass;   //班级号
  String asex;     //性别
  Date abirthday;  //出生日期
  String sbirthday;  //接收界面传人的出生日期
  String anativeplace;  //籍贯
  String aethnic;       //民族
  boolean flag;

  public addStuInf () {
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
      this.setTitle("添加学生信息");
      //设置frame在屏幕上显示的位置
      this.setLocation( (screenSize.width - frameSize.width) / 2,
                       (screenSize.height - frameSize.height) / 2);
      this.setResizable(false);

    //添加响应事件
      jButton1.addActionListener(new ActionListener()
     {
       public void actionPerformed( ActionEvent event )
      {
        aname =  jTextField1.getText().trim();
        userid = jTextField3.getText().trim();
        flag = dbconnmanager.checkStuid(userid);
        if(userid.equals(""))
        {
          JOptionPane.showMessageDialog(null, "请输入学号为不能为空");
        }
        else if(userid.length()>8||userid.length()<5 )
        {
          JOptionPane.showMessageDialog(null, "学号输入错误");
        }
        else if( flag == false )
        {
           JOptionPane.showMessageDialog(null, "学号只能是0－9数字");
        }
        else if(aname.equals(""))
        {
          JOptionPane.showMessageDialog(null, "姓名不能为空");
        }
        else
        {
          astu_id = Integer.parseInt(userid);
          aclass = (String)jComboBox2.getSelectedItem();
          //检查该学生在此班是否存在，存在则不能添加
          flag = dbconnmanager.userExist(astu_id, aclass);
          if (flag == true) {
            JOptionPane.showMessageDialog(null, "该学生已经存在");
          }
          else {
            flag = addstuinf();
            jTextField6.setText("");
            if (flag == true) {
              JOptionPane.showMessageDialog(null, "添加成功");
            }
            else {
              JOptionPane.showMessageDialog(null, "添加失败");
            }
          }
        }
      }
    }
     );

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
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel1.setText("姓名");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel2.setText("性别");
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel3.setText("学号");
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel4.setText("民族");
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel5.setText("班级");
    jLabel6.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel6.setText("出生日期");
    jLabel7.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel7.setText("籍贯");
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("增加");
    xYLayout1.setWidth(524);
    xYLayout1.setHeight(375);
    jTextField1.setText("");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField4.setText("");
    jTextField4.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField6.setColumns(14);
    jTextField6.setText("");
    jTextField6.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField7.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField3.setEnabled(true);
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField3.setMinimumSize(new Dimension(11, 21));
    jTextField3.setText("224");
    jButton2.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setText("退出");
    jComboBox1.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox1.setRequestFocusEnabled(true);
    jComboBox1.setToolTipText("");
    jComboBox1.setVerifyInputWhenFocusTarget(true);
    jComboBox1.addItem("男");
    jComboBox1.addItem("女");
    jComboBox2.addItem("A2241");
    jComboBox2.addItem("A2242");
    jComboBox2.addItem("A2243");
    jComboBox2.setFont(new java.awt.Font("Dialog", 0, 13));
    this.getContentPane().add(jTextField4,      new XYConstraints(373, 110, 109, 30));
    this.getContentPane().add(jComboBox1,      new XYConstraints(373, 49, 109, 29));
    this.getContentPane().add(jTextField1,     new XYConstraints(129, 51, 125, 32));
    this.getContentPane().add(jTextField3,  new XYConstraints(129, 113, 124, 27));
    this.getContentPane().add(jComboBox2,  new XYConstraints(127, 179, 127, 30));
    this.getContentPane().add(jTextField6,          new XYConstraints(374, 180, 107, 28));
    this.getContentPane().add(jLabel7,  new XYConstraints(149, 259, 51, 26));
    this.getContentPane().add(jTextField7, new XYConstraints(218, 254, 180, 29));
    this.getContentPane().add(jButton1, new XYConstraints(181, 312, 69, 30));
    this.getContentPane().add(jButton2,  new XYConstraints(306, 310, 74, 31));
    this.getContentPane().add(jLabel1, new XYConstraints(66, 47, 55, 38));
    this.getContentPane().add(jLabel3, new XYConstraints(64, 107, 39, 38));
    this.getContentPane().add(jLabel5, new XYConstraints(64, 180, 38, 32));
    this.getContentPane().add(jLabel6, new XYConstraints(287, 181, 66, 23));
    this.getContentPane().add(jLabel4, new XYConstraints(303, 112, 45, 27));
    this.getContentPane().add(jLabel2, new XYConstraints(300, 48, 59, 30));
  }

  //添加学生基本信息 flag = true 则成功
  private boolean addstuinf()
  {
    asex =  (String)jComboBox1.getSelectedItem();
    aethnic =  jTextField4.getText().trim();
    aclass = (String)jComboBox2.getSelectedItem();
    sbirthday = jTextField6.getText().trim();
    if(sbirthday.equals("")) abirthday = null;
    else abirthday = Date.valueOf(sbirthday);
    anativeplace = jTextField7.getText().trim();
    flag = dbconnmanager.addUser(aname,astu_id,aclass,abirthday,asex,anativeplace,aethnic);
    return flag;
  }
  void jButton2_actionPerformed(ActionEvent e)
  {
    this.dispose();
    dbconnmanager.closeConns();
  }
}

