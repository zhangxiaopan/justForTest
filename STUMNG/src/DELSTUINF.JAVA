package stumng;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class delStuInf extends JFrame {
  DBConnManager dbconnmanager = new DBConnManager();
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JComboBox jComboBox2 = new JComboBox();

  int astu_id; //学号
  String userid; //接收界面传人的学号
  String aname; //姓名
  String aclass; //班级
  private boolean flag = false;
  private int opt = 0;

  public delStuInf() {
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
      this.setTitle("删除学生信息");
      //设置frame在屏幕上显示的位置
      this.setLocation( (screenSize.width - frameSize.width) / 2,
                       (screenSize.height - frameSize.height) / 2);
      this.setResizable(false);
      //添加响应事件
      jButton1.addActionListener(new ActionListener()
     {
       public void actionPerformed( ActionEvent event )
      {
        userid = jTextField3.getText().trim();
        aname =  jTextField1.getText().trim();
        //检查学号输入的合法性
        flag = dbconnmanager.checkStuid(userid);
        //学号不能为空
        if(userid.equals(""))
        {
          JOptionPane.showMessageDialog(null, "请输入学号为不能为空");
        }
        else if( flag == false )
        {
           JOptionPane.showMessageDialog(null, "学号只能是0－9数字");
        }
        else if(aname.equals(""))         //姓名不能为空
        {
          JOptionPane.showMessageDialog(null, "姓名不能为空");
        }
        else
        {
          //确认是否要删除该学生
          JOptionPane pane = new JOptionPane("将删出"+aname+"同学的基本信息及成绩信息，继续吗?");
          JDialog dialog = pane.createDialog(null,"删除学生基本信息");
          pane.setOptionType(pane.YES_NO_OPTION);
          dialog.show();
          Object selectedvalue = pane.getValue();
          opt = ((Integer)selectedvalue).intValue();
          if(pane.YES_OPTION == opt )    //假如用户选择"是"
          {
            astu_id = Integer.parseInt(userid);
            aclass = (String) jComboBox2.getSelectedItem();
            //检查该学生在此班是否存在
            flag = dbconnmanager.userExist(astu_id, aclass);
            if (flag == false) {
              JOptionPane.showMessageDialog(null, "该班不存在该学生");
            }
            else {
              flag = removestuinf();   //删除
              if (flag == true) {
                JOptionPane.showMessageDialog(null, "删除成功");
              }
              else {
                JOptionPane.showMessageDialog(null, "删除失败");
              }
            }
          }
        }
      }
    }
     );

     //退出frame
     jButton2.addActionListener(new java.awt.event.ActionListener()
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
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel3.setText("学号");
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel5.setText("班级");
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("删除");
    xYLayout1.setWidth(524);
    xYLayout1.setHeight(375);
    jTextField1.setText("");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField3.setEnabled(true);
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField3.setMinimumSize(new Dimension(11, 21));
    jTextField3.setToolTipText("");
    jTextField3.setText("");
    jButton2.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setText("退出");
    jComboBox2.addItem("A2241");
    jComboBox2.addItem("A2242");
    jComboBox2.addItem("A2243");
    jComboBox2.setFont(new java.awt.Font("Dialog", 0, 13));
    this.getContentPane().add(jButton1, new XYConstraints(172, 261, 69, 30));
    this.getContentPane().add(jTextField1, new XYConstraints(223, 51, 125, 32));
    this.getContentPane().add(jLabel1, new XYConstraints(130, 48, 55, 38));
    this.getContentPane().add(jLabel3, new XYConstraints(126, 108, 39, 38));
    this.getContentPane().add(jLabel5, new XYConstraints(127, 180, 38, 32));
    this.getContentPane().add(jButton2, new XYConstraints(299, 259, 74, 31));
    this.getContentPane().add(jComboBox2, new XYConstraints(221, 178, 127, 30));
    this.getContentPane().add(jTextField3,  new XYConstraints(221, 113, 128, 31));
  }

  //删除学生基本信息 包括其成绩的记录 flag = true 则成功
  private boolean removestuinf()
  {
    boolean flag;
    aclass = (String)jComboBox2.getSelectedItem();
    flag = dbconnmanager.removeUser(astu_id, aname, aclass);
    return flag;
  }
  void jButton2_actionPerformed(ActionEvent e)
  {
    this.dispose();
    dbconnmanager.closeConns();
  }

}
