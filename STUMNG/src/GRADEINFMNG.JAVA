package stumng;

import javax.swing.*;
import java.awt.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class gradeInfMng extends JFrame {
  DBConnManager dbconnmanager = new DBConnManager();
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JLabel jLabel5 = new JLabel();
  JTextField jTextField5 = new JTextField();
  JButton jButton3 = new JButton();
  JLabel jLabel7 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();


  private int astu_id;         //学号
  private String sstu_id;
  private String aclass;        //班级号
  private float atotal= 0;         //总分
  //private String stotal;
  private float amath;         //数学成绩
  private String smath;
  private float aenglish;       //英语成绩
  private String senglish;
  private float achinese;         //语文成绩
  private String schinese;
  private float acomposite;       //总评
  private String scomposite;
  private boolean flag = false;


  public gradeInfMng() {
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
      this.setTitle("学生信息管理");
      //设置frame在屏幕上显示的位置
      this.setLocation( (screenSize.width - frameSize.width) / 2,
                       (screenSize.height - frameSize.height) / 2);
      this.setResizable(false);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }

    //添加学生成绩
    jButton1.addActionListener(new ActionListener()
    {
       public void actionPerformed( ActionEvent e)
       {
         sstu_id = jTextField1.getText();
         aclass = (String) jComboBox1.getSelectedItem();
         flag = dbconnmanager.checkStuid(sstu_id);
         if (sstu_id.equals("")) {
           JOptionPane.showMessageDialog(null, "请输入学号为不能为空");
         }
         else if (flag == false) {
           JOptionPane.showMessageDialog(null, "学号只能是0－9数字");
         }else {
           astu_id = Integer.parseInt(sstu_id);
           flag = dbconnmanager.gradeExist(astu_id, aclass);
           if (flag == true) {
             JOptionPane.showMessageDialog(null, "该学生已经存在");
           }
           else {
             flag = addgradeinf();
             atotal = 0;
             if (flag == true) JOptionPane.showMessageDialog(null, "添加成功");
             else JOptionPane.showMessageDialog(null, "添加失败");
           }
         }
       }
    });

    //修改学生成绩
    jButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        sstu_id = jTextField1.getText();
        flag = dbconnmanager.checkStuid(sstu_id);
        if (sstu_id.equals("")) {
          JOptionPane.showMessageDialog(null, "请输入学号为不能为空");
        }
        else if (flag == false) {
          JOptionPane.showMessageDialog(null, "学号只能是0－9数字");
        }
        else {
          astu_id = Integer.parseInt(sstu_id);
          aclass = (String) jComboBox1.getSelectedItem();
          flag = dbconnmanager.gradeExist(astu_id, aclass);
          if (flag == false) {
            JOptionPane.showMessageDialog(null, "该学生不存在");
          }
          else {
            flag = editgradeinf();
            atotal = 0;
            if (flag == true) JOptionPane.showMessageDialog(null, "修改成功");
            else JOptionPane.showMessageDialog(null, "修改失败");
          }
        }
       }
    });


    //退出frame
    jButton3.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent e)
      {
        jButton3_actionPerformed(e);
      }
    });
    }

  void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel1.setText("学号");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel2.setText("数学");
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel3.setText("语文");
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel4.setText("英语");
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("增加");
    jButton2.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setText("修改");
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel5.setText("总评");
    jTextField1.setText("");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField2.setText("");
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField4.setText("");
    jTextField4.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField5.setText("");
    jTextField5.setFont(new java.awt.Font("Dialog", 0, 13));
    xYLayout1.setWidth(518);
    xYLayout1.setHeight(406);
    jButton3.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton3.setText("退出");
    jLabel7.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel7.setText("班级");
    jComboBox1.addItem("A2241");
    jComboBox1.addItem("A2242");
    jComboBox1.addItem("A2243");
    jComboBox1.setFont(new java.awt.Font("Dialog", 0, 13));
    this.getContentPane().add(jTextField1,   new XYConstraints(147, 63, 94, 37));
    this.getContentPane().add(jLabel1,    new XYConstraints(78, 61, 45, 36));
    this.getContentPane().add(jTextField2, new XYConstraints(148, 142, 92, 38));
    this.getContentPane().add(jLabel3, new XYConstraints(302, 145, 38, 24));
    this.getContentPane().add(jButton1, new XYConstraints(96, 300, 88, 39));
    this.getContentPane().add(jButton2, new XYConstraints(222, 299, 95, 38));
    this.getContentPane().add(jButton3, new XYConstraints(353, 297, 91, 40));
    this.getContentPane().add(jLabel7, new XYConstraints(302, 57, 36, 39));
    this.getContentPane().add(jLabel4, new XYConstraints(81, 226, 47, 30));
    this.getContentPane().add(jLabel2, new XYConstraints(81, 140, 31, 39));
    this.getContentPane().add(jTextField4, new XYConstraints(146, 223, 92, 35));
    this.getContentPane().add(jLabel5, new XYConstraints(304, 223, 37, 27));
    this.getContentPane().add(jComboBox1,  new XYConstraints(363, 65, 94, 38));
    this.getContentPane().add(jTextField3,   new XYConstraints(366, 141, 92, 40));
    this.getContentPane().add(jTextField5,  new XYConstraints(368, 223, 93, 37));
  }

  private boolean addgradeinf()
  {
    schinese = jTextField3.getText().trim();
    if( schinese.equals("") )achinese = 0;
    else {
      achinese = Float.parseFloat(schinese);
      atotal = achinese;
    }
    smath =  jTextField2.getText().trim();
    if( smath.equals("") )amath = 0;
    else{
      amath = Float.parseFloat(smath);
      atotal = atotal+amath;
    }
    senglish = jTextField4.getText().trim();
    if( senglish.equals("") )aenglish = 0;
    else{
      aenglish = Float.parseFloat(senglish);
      atotal = atotal+aenglish;
    }
    scomposite = jTextField5.getText().trim();
    if( scomposite.equals("") )acomposite = 0;
    else{
      acomposite = Float.parseFloat(scomposite);
      atotal = atotal+acomposite;
    }
    flag = dbconnmanager.addGrade(aclass, astu_id, atotal, amath, achinese,
                            aenglish, acomposite);
    return  flag;
  }

  private boolean editgradeinf()
  {
    aclass = (String)jComboBox1.getSelectedItem();
    schinese = jTextField3.getText();
    smath =  jTextField2.getText();
    senglish = jTextField4.getText();
    scomposite = jTextField5.getText();
    flag = dbconnmanager.updateGrade(aclass, astu_id, smath, schinese,
                            senglish, scomposite);
    return  flag;
  }

  void jButton3_actionPerformed(ActionEvent e)
 {
   this.dispose();
   dbconnmanager.closeConns();
 }

}
