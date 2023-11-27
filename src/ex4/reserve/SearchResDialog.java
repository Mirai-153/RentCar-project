package ex4.reserve;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ex4.car.CarImpl;
import ex4.car.CarVo;
import ex4.car.SearchCarDialog;
import ex4.common.RentTableModel;
import ex4.member.SearchMemDialog;
import ex4.reserve.ResException;

public class SearchResDialog extends JDialog{
	static int        deptIndex;
	JPanel            panelSearch,panelBtn;
	JLabel            lCarNum;
	static JTextField        tf ;
    static JButton           btnSearch ;
    static boolean    		 btnSearchchk ;
    JButton           btnModify;
    JButton           btnDelete;
    JButton           btnRegres;
    JButton           btnSearchcar;
    JButton           btnSearchmember;
    JComboBox<String> comSearch;
    
    String[][]     resItems ;
    JTable         rentTable;
    RentTableModel model;
    Reserve        resController;
    String[]       columnNames = {"예약번호","차량번호","회원ID","예약등록일","대여시작일","반납예정일"};
    
    public SearchResDialog(String str){
    	setTitle(str);
    	init();
    }
    
    private void init(){
    	resController =new ReserveImpl();
    	rentTable     = new JTable();
    	panelSearch   = new JPanel();
    	panelBtn      = new JPanel();
      
    	lCarNum       = new JLabel("차량번호");
    	tf            = new JTextField(20);
    	tf.setEditable(false);
    	btnSearch     = new JButton("조회");
    	
      	comSearch =new JComboBox<String>();
    	comSearch.addItem("전체");
    	comSearch.addItem(columnNames[0]);
    	comSearch.addItem(columnNames[1]);
    	comSearch.addItem(columnNames[2]);
    	comSearch.addItem(columnNames[3]);    	
    	
    	comSearch.addActionListener(new ActionListener(){    		
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<ResVo> resList=new ArrayList<ResVo>();
				ResVo vo=new ResVo();
				JComboBox cb = (JComboBox)e .getSource();
                deptIndex = cb.getSelectedIndex();
                if(deptIndex == 0) {                	
                	tf.setText("");
                	tf.setEditable(false);
                	
                }else if(deptIndex == 1) {                	
                	tf.setText("");
					tf.setEditable(true);
					vo.setResNo(tf.getText());
					vo.getResNo();
				}else if(deptIndex == 2) {
					tf.setText("");
					tf.setEditable(true);
					vo.setResCarNumber(tf.getText());
					vo.getResCarNumber();
				}else if(deptIndex == 3) {
					tf.setText("");
					tf.setEditable(true);
					vo.setResID(tf.getText());
					vo.getResID();
				}else if(deptIndex == 4) {
					tf.setText("");
					tf.setEditable(true);
					vo.setResDate(tf.getText());
					vo.getResDate();
				}
                try {
					resList=resController.listReserve(vo);
					loadTableData(resList);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    	});

    	btnSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<ResVo> resList=new ArrayList<ResVo>();
				ResVo vo=new ResVo();
				if(deptIndex == 0) {					
					btnSearchchk = true;
				}else if(deptIndex == 1) {					
					vo.setResNo(tf.getText());
					vo.getResNo();
					btnSearchchk = false;
				}else if(deptIndex == 2) {
					vo.setResCarNumber(tf.getText());
					vo.getResCarNumber();
					btnSearchchk = false;
				}else if(deptIndex == 3) {
					vo.setResID(tf.getText());
					vo.getResID();
					btnSearchchk = false;
				}else if(deptIndex == 4) {
					vo.setResDate(tf.getText());
					vo.getResDate();
					btnSearchchk = false;
				}
				try {
					resList=resController.listReserve(vo);
					loadTableData(resList);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    	});     	
    	panelSearch.add(comSearch);
    	panelSearch.add(tf);
    	panelSearch.add(btnSearch);
    	
    	btnSearchcar=new JButton("차량 조회");
    	btnSearchcar.addActionListener(new ActionListener(){
         	@Override
         		public void actionPerformed(ActionEvent e) {
         		 new SearchCarDialog("차량 조회창");   
         	  }
            });
    	btnSearchmember=new JButton("회원 조회");
    	btnSearchmember.addActionListener(new ActionListener(){
         	@Override
         		public void actionPerformed(ActionEvent e) {
         		 new SearchMemDialog("회원 조회창");   
         	  }
            });
    	btnRegres=new JButton("등록");
    	btnRegres.addActionListener(new ActionListener(){
         	@Override
         		public void actionPerformed(ActionEvent e) {
         		 new RegResDialog("예약 등록창");   
         	  }
            }); 
    	
     	btnModify=new JButton("수정");
     	btnModify.addActionListener(new ActionListener(){
     	@Override
     		public void actionPerformed(ActionEvent e) {
     		 new ModResDialog("예약정보수정창");   
     	  }
        }); 
    	btnDelete=new JButton("삭제");
    	btnDelete.addActionListener(new ActionListener(){
    	@Override
    		public void actionPerformed(ActionEvent e) {
    	     new DelResDialog("예약정보삭제창");   
    		  }
    		       });
    	
    	
    	panelBtn.add(btnSearchcar);
    	panelBtn.add(btnSearchmember);
    	panelBtn.add(btnRegres);
    	panelBtn.add(btnModify);
    	panelBtn.add(btnDelete);
    	
      	add(panelSearch,BorderLayout.NORTH);
    	add(panelBtn,BorderLayout.SOUTH);
      	resItems = new String[0][6];
        model=new RentTableModel(resItems,columnNames);
    	rentTable.setModel(model);
        add(new JScrollPane(rentTable),BorderLayout.CENTER);
        setLocation(300,100);//���̾�α� ��� ��ġ�� ���Ѵ�.
        setSize(600,600);
        setModal(true); //�׻� �θ�â ���� ���̰� �Ѵ�.
        setVisible(true);
    }
     private void loadTableData(ArrayList<ResVo > resList){
    	resItems = new String[resList.size()][6];
    	for(int i=0; i<resList.size();i++){
    		ResVo vo=resList.get(i);
    		resItems[i][0]=vo.resNo;
    		resItems[i][1]=vo.resCarNumber;
    		resItems[i][2]=vo.resID;
    		resItems[i][3]=vo.resDate;
    		resItems[i][4]=vo.useBeginDate;
    		resItems[i][5]=vo.returnDate;
    	}
    	System.out.println(columnNames[0]);
    	model=new RentTableModel(resItems,columnNames);
    	rentTable.setModel(model);
	}    
}
