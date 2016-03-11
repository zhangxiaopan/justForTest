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


  //�������
  int astu_id;     //ѧ��
  String userid;   //���ս��洫�˵�ѧ��
  String aname;    //����
  String aclass;   //�༶��
  String asex;     //�Ա�
  Date abirthday;  //��������
  String sbirthday;  //���ս��洫�˵ĳ�������
  String anativeplace;  //����
  String aethnic;       //����
  boolean flag;

  public addStuInf () {
    try {
      jbInit();
      //����frame�Ĵ�С
      this.setSize(600, 400);
      //�õ���ǰ��Ļ��С
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //�õ�frame�Ĵ�С
      Dimension frameSize = this.getSize();
      if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
      }
      if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
      }
      this.setTitle("����ѧ����Ϣ");
      //����frame����Ļ����ʾ��λ��
      this.setLocation( (screenSize.width - frameSize.width) / 2,
                       (screenSize.height - frameSize.height) / 2);
      this.setResizable(false);

    //������Ӧ�¼�
      jButton1.addActionListener(new ActionListener()
     {
       public void actionPerformed( ActionEvent event )
      {
        aname =  jTextField1.getText().trim();
        userid = jTextField3.getText().trim();
        flag = dbconnmanager.checkStuid(userid);
        if(userid.equals(""))
        {
          JOptionPane.showMessageDialog(null, "������ѧ��Ϊ����Ϊ��");
        }
        else if(userid.length()>8||userid.length()<5 )
        {
          JOptionPane.showMessageDialog(null, "ѧ���������");
        }
        else if( flag == false )
        {
           JOptionPane.showMessageDialog(null, "ѧ��ֻ����0��9����");
        }
        else if(aname.equals(""))
        {
          JOptionPane.showMessageDialog(null, "��������Ϊ��");
        }
        else
        {
          astu_id = Integer.parseInt(userid);
          aclass = (String)jComboBox2.getSelectedItem();
          //����ѧ���ڴ˰��Ƿ���ڣ�������������
          flag = dbconnmanager.userExist(astu_id, aclass);
          if (flag == true) {
            JOptionPane.showMessageDialog(null, "��ѧ���Ѿ�����");
          }
          else {
            flag = addstuinf();
            jTextField6.setText("");
            if (flag == true) {
              JOptionPane.showMessageDialog(null, "���ӳɹ�");
            }
            else {
              JOptionPane.showMessageDialog(null, "����ʧ��");
            }
          }
        }
      }
    }
     );

     //�˳�frame
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
    jLabel1.setText("����");
    this.getContentPane().setLayout(xYLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel2.setText("�Ա�");
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel3.setText("ѧ��");
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel4.setText("����");
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel5.setText("�༶");
    jLabel6.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel6.setText("��������");
    jLabel7.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel7.setText("����");
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("����");
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
    jButton2.setText("�˳�");
    jComboBox1.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox1.setRequestFocusEnabled(true);
    jComboBox1.setToolTipText("");
    jComboBox1.setVerifyInputWhenFocusTarget(true);
    jComboBox1.addItem("��");
    jComboBox1.addItem("Ů");
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

  //����ѧ��������Ϣ flag = true ��ɹ�
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
