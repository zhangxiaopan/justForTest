package stumng;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>Title:ѧ������ϵͳ </p>
 * <p>Description: </p>
 * <p>Copyright:Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class stuInfMng extends JFrame {
  JPanel contentPane;
  addStuInf addstuinf = new addStuInf();            //����ѧ��������Ϣ
  editStuInf editstuinf = new editStuInf();         //�޸�ѧ��������Ϣ
  delStuInf delstuinf = new delStuInf();            //ɾ��ѧ��������Ϣ
  gradeInfMng gradeinfmng = new gradeInfMng();      //ѧ���ɼ�����
  stuQueryId stuqueryid = new stuQueryId();          //��ѧ�Ų�ѯѧ��������Ϣ
  stuQueryName stuqueryname = new stuQueryName();    //��������ѯѧ��������Ϣ
  stuQueryClass stuqueryclass = new stuQueryClass(); //���༶��ѯѧ��������Ϣ
  gradeRank graderank = new gradeRank();             //ѧ���ɼ�����
  gradeQuery gradequery = new gradeQuery();           //ѧ���ɼ���ѯ
  sumGrade sumgrade = new sumGrade();                 //�ɼ�ͳ��
  sumPeople sumpeople = new sumPeople();               //ѧ��ͳ��
  StuMngsAbout stumngsabout = new StuMngsAbout();      //����
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu stuInfMngMenu = new JMenu();
  JMenuItem addStuInfMenuItem = new JMenuItem();
  JMenu stuGradeMngMenu = new JMenu();
  JMenuItem gradeMngMenuItem = new JMenuItem();
  BorderLayout borderLayout1 = new BorderLayout();
  JMenu QueryMenu = new JMenu();
  JMenu sumMenu = new JMenu();
  JMenu HelpMenu = new JMenu();
  JMenu exitMenu = new JMenu();
  JMenuItem editStuInfMenuItem = new JMenuItem();
  JMenuItem delStuInfMenuItem = new JMenuItem();
  JMenuItem gradeQueryMenuItem = new JMenuItem();
  JMenuItem sumStuMenuItem = new JMenuItem();
  JMenuItem sumGradeMenuItem = new JMenuItem();
  JMenuItem helpMenuItem = new JMenuItem();
  JMenu stuQueryMenu = new JMenu();
  JMenuItem stuQueryNameMenuItem = new JMenuItem();
  JMenuItem stuQueryIdMenuItem = new JMenuItem();
  JMenuItem stuQueryClassMenuItem = new JMenuItem();
  JMenuItem exitsystemMenuItem = new JMenuItem();
  JMenuItem totalrankMenuItem = new JMenuItem();
  JMenuItem mathrankMenuItem = new JMenuItem();
  JMenuItem englishrankMenuItem = new JMenuItem();
  JMenuItem chineserankMenuItem = new JMenuItem();
  JMenuItem commentrankMenuItem = new JMenuItem();
  JMenuItem gradeRankMenuItem = new JMenuItem();
  JLabel jLabel1 = new JLabel();

  //Construct the frame
  public stuInfMng() {

  //���Ӳ˵���Ӧ�¼�
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
      exitsystemMenuItem.addActionListener(new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
        {
          System.exit(0);
        }
      }
      );

      addStuInfMenuItem.addActionListener(new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
       {
         addstuinf.show();
       }
     }
      );

      editStuInfMenuItem.addActionListener(new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
       {
         editstuinf.show();
       }
     }
      );

      delStuInfMenuItem.addActionListener(new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
       {
         delstuinf.show();
       }
     }
      );

      gradeMngMenuItem.addActionListener(new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
       {
         gradeinfmng.show();
       }
     }
      );

      stuQueryNameMenuItem.addActionListener(new ActionListener()
      {
        public void actionPerformed( ActionEvent event )
       {
         stuqueryname.show();
       }
     }
      );

      stuQueryIdMenuItem.addActionListener(new ActionListener()
     {
       public void actionPerformed( ActionEvent event )
      {
        stuqueryid.show();
      }
    }
     );

     stuQueryClassMenuItem.addActionListener(new ActionListener()
     {
       public void actionPerformed( ActionEvent event )
      {
        stuqueryclass.show();
      }
    }
     );

     gradeRankMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent event )
     {
       graderank.show();
     }
   }
    );

    gradeQueryMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent event )
     {
       gradequery.show();
     }
   }
    );

    sumGradeMenuItem.addActionListener(new ActionListener()
   {
     public void actionPerformed( ActionEvent event )
    {
      sumgrade.show();
    }
  }
   );

   sumStuMenuItem.addActionListener(new ActionListener()
   {
     public void actionPerformed( ActionEvent event )
    {
      sumpeople.show();
    }
  }
   );


   helpMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed( ActionEvent event )
     {
       stumngsabout.show();
     }
   }
    );

    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(400, 300));
    this.setTitle("Frame Title");
    stuInfMngMenu.setFont(new java.awt.Font("Dialog", 0, 13));
    stuInfMngMenu.setText("ѧ����Ϣ����");
    addStuInfMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    addStuInfMenuItem.setText("����ѧ����Ϣ");
    stuGradeMngMenu.setFont(new java.awt.Font("Dialog", 0, 13));
    stuGradeMngMenu.setText("ѧ���ɼ�����");
    gradeMngMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    gradeMngMenuItem.setForeground(Color.black);
    gradeMngMenuItem.setText("ѧ���ɼ��ۺϹ���");
    QueryMenu.setFont(new java.awt.Font("Dialog", 0, 13));
    QueryMenu.setText("��ѯ");
    sumMenu.setFont(new java.awt.Font("Dialog", 0, 13));
    sumMenu.setText("ͳ��");
    HelpMenu.setFont(new java.awt.Font("Dialog", 0, 13));
    HelpMenu.setText("����");
    exitMenu.setFont(new java.awt.Font("Dialog", 0, 13));
    exitMenu.setText("�˳�");
    editStuInfMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    editStuInfMenuItem.setText("�޸�ѧ����Ϣ");
    delStuInfMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    delStuInfMenuItem.setText("ɾ��ѧ����Ϣ");
    gradeQueryMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    gradeQueryMenuItem.setActionCommand("ѧ���ɼ���ѯ");
    gradeQueryMenuItem.setText("�ɼ���ѯ");
    sumStuMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    sumStuMenuItem.setText("��Աͳ��");
    sumGradeMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    sumGradeMenuItem.setText("�ɼ�ͳ��");
    helpMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    helpMenuItem.setText("����");
    stuQueryMenu.setFont(new java.awt.Font("Dialog", 0, 13));
    stuQueryMenu.setActionCommand("");
    stuQueryMenu.setText("ѧ��������Ϣ��ѯ");
    stuQueryNameMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    stuQueryNameMenuItem.setText("������");
    stuQueryIdMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    stuQueryIdMenuItem.setText("��ѧ��");
    stuInfMngMenu.addSeparator();
    stuQueryClassMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    stuQueryClassMenuItem.setText("���༶");
    exitsystemMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    exitsystemMenuItem.setText("�˳�ϵͳ");
    totalrankMenuItem.setText("��  ��  ��");
    mathrankMenuItem.setText("����ѧ�ɼ�");
    englishrankMenuItem.setText("��Ӣ��ɼ�");
    chineserankMenuItem.setText("�����ĳɼ�");
    commentrankMenuItem.setText("��  �ܳɼ�");
    gradeRankMenuItem.setFont(new java.awt.Font("Dialog", 0, 13));
    gradeRankMenuItem.setText("��              ��");
    contentPane.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 25));
    jLabel1.setForeground(Color.red);
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel1.setText("��ӭ����ѧ������ϵͳ");
    stuInfMngMenu.add(addStuInfMenuItem);
    stuInfMngMenu.addSeparator();
    stuInfMngMenu.add(editStuInfMenuItem);
    stuInfMngMenu.addSeparator();
    stuInfMngMenu.add(delStuInfMenuItem);
    stuGradeMngMenu.addSeparator();
    stuGradeMngMenu.add(gradeMngMenuItem);
    stuGradeMngMenu.addSeparator();
    stuGradeMngMenu.add(gradeRankMenuItem);
    jMenuBar1.add(stuInfMngMenu);
    jMenuBar1.add(stuGradeMngMenu);
    jMenuBar1.add(QueryMenu);
    jMenuBar1.add(sumMenu);
    jMenuBar1.add(HelpMenu);
    jMenuBar1.add(exitMenu);
    QueryMenu.addSeparator();
    QueryMenu.add(stuQueryMenu);
    QueryMenu.addSeparator();
    QueryMenu.add(gradeQueryMenuItem);
    sumMenu.addSeparator();
    sumMenu.add(sumStuMenuItem);
    sumMenu.addSeparator();
    sumMenu.add(sumGradeMenuItem);
    HelpMenu.addSeparator();
    HelpMenu.add(helpMenuItem);
    stuQueryMenu.add(stuQueryNameMenuItem);
    stuQueryMenu.addSeparator();
    stuQueryMenu.add(stuQueryIdMenuItem);
    stuQueryMenu.addSeparator();
    stuQueryMenu.add(stuQueryClassMenuItem);
    exitMenu.add(exitsystemMenuItem);
    contentPane.add(jLabel1, BorderLayout.CENTER);
    this.setJMenuBar(jMenuBar1);
  }
}
