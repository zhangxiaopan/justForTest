package stumng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;

public class gradeRank extends JFrame {
  DBConnManager dbconnmanager = new DBConnManager();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JComboBox jComboBox1 = new JComboBox();
  TitledBorder titledBorder1;
  JComboBox jComboBox2 = new JComboBox();
  JScrollPane jScrollPane1 = new JScrollPane();
  JComboBox jComboBox3 = new JComboBox();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JTable jTable1 = new JTable();

  private String aclass = "";
  private String aschool = "";
  private String amath = "";
  private String achinese = "";
  private String aenglish = "";
  private String atotal = "";
  private String acomposite = "";
  private String bygrade = "";
  private int s =0;

  public gradeRank() {
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
      this.setTitle("学生成绩排名");
      //设置frame在屏幕上显示的位置
      this.setLocation( (screenSize.width - frameSize.width) / 2,
                       (screenSize.height - frameSize.height) / 2);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }

    //查询学生排名情况
    jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        //清空
        aclass = "";
        aschool = "";
        amath = "";
        achinese = "";
        aenglish = "";
        atotal = "";
        acomposite = "";
        bygrade = "";
        s = 0;
        Vector result = new Vector();
        Vector headline = new Vector();
        Vector resultdate = new Vector();

        jTable1 = new JTable();
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
        jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表


        aclass = (String) jComboBox2.getSelectedItem();
        if (!aclass.equals("班级号")) {
          aschool = (String)jComboBox2.getSelectedItem();
          aclass = "";
          jComboBox3.setEditable(false);
        }
        else aclass = (String)jComboBox3.getSelectedItem();
        bygrade = (String) jComboBox1.getSelectedItem();
        if( bygrade.equals("总分") )atotal = "总分";
        else if( bygrade.equals("语文成绩") )achinese = "语文成绩";
        else if(bygrade.equals("数学成绩"))amath = "数学成绩";
        else if(bygrade.equals("英语成绩"))aenglish= "英语成绩";
        else if(bygrade.equals("总评"))acomposite= "总评";
        headline.addElement("名次");
        headline.addElement("学号");
        headline.addElement(bygrade);
        headline.addElement("班级");
        result = dbconnmanager.getRank(aclass, aschool, amath, achinese, aenglish, acomposite, atotal);
        if(result != null)
        {
          for( int i =0; i<result.size()/3; i++  )
          {
            s = s+1;
            Vector temp = new Vector();
            temp.add(String.valueOf(s));
            for( int j =0; j<=2; j++ )
            {
              String str = (String)result.elementAt(j+i*3);
              temp.add(str);
            }
            resultdate.add(temp);
          }
          jTable1 = new JTable(resultdate, headline);
          jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //滚动条设置左右滚
          jScrollPane1.getViewport().add(jTable1, null); //在滚动条中放入表
        }else JOptionPane.showMessageDialog(null, "记录为空");
      }
    });
    jButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent e)
     {
       jButton2_actionPerformed(e);
     }

    }
     );
  }

  void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    this.getContentPane().setLayout(borderLayout1);
    jComboBox1.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox1.setAutoscrolls(false);
    jComboBox1.setBorder(BorderFactory.createLoweredBevelBorder());
    jComboBox1.setToolTipText("");
    jComboBox1.setActionCommand("comboBoxChanged");
    jComboBox1.addItem("总分");
    jComboBox1.addItem("数学成绩");
    jComboBox1.addItem("语文成绩");
    jComboBox1.addItem("英语成绩");
    jComboBox1.addItem("总评");
    jComboBox2.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox2.setMaximumRowCount(20);
    jComboBox2.addItem("全校");
    jComboBox2.addItem("班级号");
    jComboBox3.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox3.addItem("A2241");
    jComboBox3.addItem("A2242");
    jComboBox3.addItem("A2243");
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("提交");
    jButton2.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setVerifyInputWhenFocusTarget(true);
    jButton2.setText("退出");
    jButton2.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
    jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 15));
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jComboBox2, null);
    jPanel1.add(jComboBox3, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    jTable1.setFont(new java.awt.Font("Dialog", 0, 15));
    this.getContentPane().add(jComboBox1, BorderLayout.SOUTH);
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
