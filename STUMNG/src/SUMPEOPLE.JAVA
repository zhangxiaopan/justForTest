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
      //����frame�Ĵ�С
    this.setSize(600, 500);
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
    this.setTitle("ѧ��ͳ��");
    //����frame����Ļ����ʾ��λ��
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
     jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //�������������ҹ�
     jScrollPane1.getViewport().add(jTable1, null); //�ڹ������з����


     str = (String) jComboBox1.getSelectedItem();

     if (!str.equals("�༶��")) aschool = str;
     else aclass = jTextField1.getText().trim();
     flag = dbconnmanager.checkClass(aclass);
     if(flag == false&&str.equals("�༶��") )JOptionPane.showMessageDialog(null, "�༶���������");
     else if (str.equals("�༶��") && aclass.equals(""))
       JOptionPane.showMessageDialog(null, "������༶��");
     else {
       str1 = (String) jComboBox2.getSelectedItem();
       if (str1.equals("��")) result = dbconnmanager.getpeopleSum(aclass,
           aschool, "��", "", null, null);
       else if (str1.equals("Ů")) result = dbconnmanager.getpeopleSum(
           aclass, aschool, "Ů", "", null, null);
       else if (str1.equals("��������")) {
         date_range1str = jFormattedTextField1.getText().trim();
         date_range2str = jFormattedTextField2.getText().trim();
         if (date_range1str.equals("") || date_range2str.equals(""))
         {
           JOptionPane.showMessageDialog(null, "������ͳ�Ƴ������ڷ�Χ");
           flag = false;
         }
         else {
           date_range1 = reverse(date_range1str);
           date_range2 = reverse(date_range2str);
           result = dbconnmanager.getpeopleSum(aclass, aschool, "��������", "",
               date_range1 ,
               date_range2 );
         }
       }
       else if (str1.equals("����")) {
         aethnic = JOptionPane.showInputDialog("����������");
         if (aethnic.equals("")) {
           JOptionPane.showMessageDialog(null, "����Ϊ��");
           flag = false;
         }
         else {
           aethnic = aethnic + "%";
           result = dbconnmanager.getpeopleSum(aclass, aschool, "����",
               aethnic, null, null);
         }
       }

       if (result.size() == 0&&flag == true) JOptionPane.showMessageDialog(null, "�޼�¼");
       else {
         for (int i = 0; i < result.size() / 4; i++) {
           Vector temp = new Vector();
           for (int j = 0; j <= 3; j++) {
             String str2 = (String) result.elementAt(j + i * 4);
             temp.add(str2);
           }
           resultdate.add(temp);
         }

         headline.add("ѧ��");
         headline.add("����");
         headline.add(str1);
         headline.add("�༶");

         jTable1 = new JTable(resultdate, headline);
         jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //�������������ҹ�
         jScrollPane1.getViewport().add(jTable1, null); //�ڹ������з����
       }
       if (str.equals("ȫУ")) {
         if (str1.equals("��������"))
           JOptionPane.showMessageDialog(null,
                                         "��ȫУ��Χ�ڣ��������ڴ�" + date_range1str + "��" +
                                         date_range2str + "��ѧ����" + resultdate.size() +
                                         "��");
         else if (str1.equals("��") || str1.equals("Ů"))
           JOptionPane.showMessageDialog(null,
                                         "��ȫУ��Χ�ڣ��Ա�Ϊ" + str1 + "��ѧ����" +
                                         resultdate.size() + "��");
         else if (str1.equals("����"))
           JOptionPane.showMessageDialog(null,
                                         "��ȫУ��Χ�ڣ�����Ϊ" + aethnic + "��ѧ����" +
                                         resultdate.size() + "��");
       }
       else if (str.equals("�༶��")) {
         if (str1.equals("��������")&&!date_range1str.equals("")&&!date_range2str.equals(""))
           JOptionPane.showMessageDialog(null,
                                         "��" + aclass + "��,�������ڴ�" + date_range1str +
                                         "��" + date_range2str + "��ѧ����" +
                                         resultdate.size() + "��");
         else if (str1.equals("��") || str1.equals("Ů"))
           JOptionPane.showMessageDialog(null,
                                         "��" + aclass + "��,�Ա�Ϊ" + str1 +
                                         "��ѧ����" + resultdate.size() + "��");
         else if (str1.equals("����")&&!aethnic.equals(""))
           JOptionPane.showMessageDialog(null,
                                         "��" + aclass + "��,����Ϊ" + aethnic +
                                         "��ѧ����" + resultdate.size() + "��");

       }
     }
   }
 });


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
    this.getContentPane().setLayout(borderLayout1);
    jComboBox1.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox1.addActionListener(new sumPeople_jComboBox1_actionAdapter(this));
    jComboBox2.setFont(new java.awt.Font("Dialog", 0, 13));
    jComboBox2.addActionListener(new sumPeople_jComboBox2_actionAdapter(this));
    jComboBox1.addItem("�༶��");
    jComboBox1.addItem("ȫУ");
    jComboBox2.addItem("��");
    jComboBox2.addItem("Ů");
    jComboBox2.addItem("��������");
    jComboBox2.addItem("����");
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField1.setText("");
    jTextField1.setColumns(8);
    jScrollPane1.setFont(new java.awt.Font("Dialog", 0, 15));
    jTable1.setFont(new java.awt.Font("Dialog", 0, 15));
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("�ύ");
    jButton2.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton2.setText("�˳�");
    jTable2.setFont(new java.awt.Font("Dialog", 0, 14));
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel1.setText("��");
    jFormattedTextField1.setColumns(10);
    jFormattedTextField1.setFont(new java.awt.Font("SansSerif", 0, 13));
    jFormattedTextField1.setText("");
    jLabel2.setEnabled(true);
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel2.setVerifyInputWhenFocusTarget(true);
    jLabel2.setText("��");
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
    if( str.equals("ȫУ") )jTextField1.setEditable(false);
    if( str.equals( "�༶��") )jTextField1.setEditable(true);
  }

  void jButton2_actionPerformed(ActionEvent e)
   {
     jTable1 = new JTable();
     jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //�������������ҹ�
     jScrollPane1.getViewport().add(jTable1, null); //�ڹ������з����

     this.dispose();
     dbconnmanager.closeConns();
   }

  void jComboBox2_actionPerformed(ActionEvent e) {
    str1 = (String)jComboBox2.getSelectedItem();
    if( str1.equals("��������") )
    {
      jFormattedTextField1.setEditable(true);
      jFormattedTextField2.setEditable(true);
    }else{
      jFormattedTextField1.setEditable(false);
      jFormattedTextField2.setEditable(false);
    }
  }

  //�������������ת��Ϊ#��/��/��#����ʽ
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