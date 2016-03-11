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

  int astu_id; //ѧ��
  String userid; //���ս��洫�˵�ѧ��
  String aname; //����
  String aclass; //�༶
  private boolean flag = false;
  private int opt = 0;

  public delStuInf() {
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
      this.setTitle("ɾ��ѧ����Ϣ");
      //����frame����Ļ����ʾ��λ��
      this.setLocation( (screenSize.width - frameSize.width) / 2,
                       (screenSize.height - frameSize.height) / 2);
      this.setResizable(false);
      //������Ӧ�¼�
      jButton1.addActionListener(new ActionListener()
     {
       public void actionPerformed( ActionEvent event )
      {
        userid = jTextField3.getText().trim();
        aname =  jTextField1.getText().trim();
        //���ѧ������ĺϷ���
        flag = dbconnmanager.checkStuid(userid);
        //ѧ�Ų���Ϊ��
        if(userid.equals(""))
        {
          JOptionPane.showMessageDialog(null, "������ѧ��Ϊ����Ϊ��");
        }
        else if( flag == false )
        {
           JOptionPane.showMessageDialog(null, "ѧ��ֻ����0��9����");
        }
        else if(aname.equals(""))         //��������Ϊ��
        {
          JOptionPane.showMessageDialog(null, "��������Ϊ��");
        }
        else
        {
          //ȷ���Ƿ�Ҫɾ����ѧ��
          JOptionPane pane = new JOptionPane("��ɾ��"+aname+"ͬѧ�Ļ�����Ϣ���ɼ���Ϣ��������?");
          JDialog dialog = pane.createDialog(null,"ɾ��ѧ��������Ϣ");
          pane.setOptionType(pane.YES_NO_OPTION);
          dialog.show();
          Object selectedvalue = pane.getValue();
          opt = ((Integer)selectedvalue).intValue();
          if(pane.YES_OPTION == opt )    //�����û�ѡ��"��"
          {
            astu_id = Integer.parseInt(userid);
            aclass = (String) jComboBox2.getSelectedItem();
            //����ѧ���ڴ˰��Ƿ����
            flag = dbconnmanager.userExist(astu_id, aclass);
            if (flag == false) {
              JOptionPane.showMessageDialog(null, "�ð಻���ڸ�ѧ��");
            }
            else {
              flag = removestuinf();   //ɾ��
              if (flag == true) {
                JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
              }
              else {
                JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
              }
            }
          }
        }
      }
    }
     );

     //�˳�frame
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
    jLabel1.setText("����");
    this.getContentPane().setLayout(xYLayout1);
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel3.setText("ѧ��");
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel5.setText("�༶");
    jButton1.setFont(new java.awt.Font("Dialog", 0, 13));
    jButton1.setText("ɾ��");
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
    jButton2.setText("�˳�");
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

  //ɾ��ѧ��������Ϣ ������ɼ��ļ�¼ flag = true ��ɹ�
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