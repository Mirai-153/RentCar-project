package ex4.member;

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

import ex4.car.CarVo;
import ex4.car.SearchCarDialog;
import ex4.common.RentTableModel;
import ex4.reserve.RegResDialog;
import ex4.reserve.SearchResDialog;

public class SearchMemDialog  extends JDialog{
	static int        deptIndex;
	JPanel            panelSearch,panelBtn;
	JLabel            lMemName;
	static JTextField        tf ;
	static boolean    btnSearchchk ;
    static JButton    btnSearch ;
    JButton           btnModify ;
    JButton           btnDelete ;
    JButton           btnSearchcar;
    JButton           btnSearchres;
    JButton           btnRegmem;
    JComboBox<String> comSearch;
    
    String[][]        memberItems ;
    JTable            memTable;
    RentTableModel    model;
    MemberImpl        memController;
    String[]          columnNames={"아이디", "비밀번호", "이름", "주소", "전화번호"};
    
    public SearchMemDialog(String str){
    	setTitle(str);
    	init();
    	
    }
    
    private void init(){
    	memTable=new JTable();
    	panelSearch=new JPanel();
    	panelBtn=new JPanel();
    	
    	memController =  new MemberImpl();
    	lMemName = new JLabel("회원명");
    	tf=new JTextField(20);
    	tf.setEditable(false);
    	btnSearch=new JButton("조회");
    	comSearch =new JComboBox<String>();
    	comSearch.addItem("전체");
    	comSearch.addItem(columnNames[0]);
    	comSearch.addItem(columnNames[2]);
    	comSearch.addItem(columnNames[3]);
    	comSearch.addItem(columnNames[4]);    	
    	
    	
    	comSearch.addActionListener(new ActionListener(){    		
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<MemberVo> memList=new ArrayList<MemberVo>();
				MemberVo vo=new MemberVo();
				JComboBox cb = (JComboBox)e .getSource();
                deptIndex = cb.getSelectedIndex();
                if(deptIndex == 0) {
                	tf.setText("");
                	tf.setEditable(false);
                	
                }else if(deptIndex == 1) {
                	tf.setText("");
					tf.setEditable(true);
					vo.setId(tf.getText());
					vo.getId();
				}else if(deptIndex == 2) {
					tf.setText("");
					tf.setEditable(true);
					vo.setName(tf.getText());
					vo.getName();
				}else if(deptIndex == 3) {
					tf.setText("");
					tf.setEditable(true);
					vo.setAddress(tf.getText());
					vo.getAddress();
				}else if(deptIndex == 4) {
					tf.setText("");
					tf.setEditable(true);
					vo.setPhoneNum(tf.getText());
					vo.getPhoneNum();
				}
                try {
					memList=memController.listMember(vo);
					loadTableData(memList);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    	});
    	btnSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<MemberVo> memList=new ArrayList<MemberVo>();
				MemberVo vo=new MemberVo();
				if(deptIndex == 0) {
					btnSearchchk = true;
				}else if(deptIndex == 1) {
					vo.setId(tf.getText());
					vo.getId();
					btnSearchchk = false;
				}else if(deptIndex == 2) {
					vo.setName(tf.getText());
					vo.getName();
					btnSearchchk = false;
				}else if(deptIndex == 3) {
					vo.setAddress(tf.getText());
					vo.getAddress();
					btnSearchchk = false;
				}else if(deptIndex == 4) {
					vo.setPhoneNum(tf.getText());
					vo.getPhoneNum();
					btnSearchchk = false;
				}
				
				try {
					memList=memController.listMember(vo);
					loadTableData(memList);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
    	});
    	panelSearch.add(comSearch);
    	panelSearch.add(tf);
    	panelSearch.add(btnSearch);
    	
    	btnSearchcar=new JButton("차량조회");
    	btnSearchcar.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		new SearchCarDialog("차량 조회창");   
    		    }
    		});
    	
    	btnSearchres=new JButton("예약조회");
    	btnSearchres.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		new SearchResDialog("예약 조회창");   
    		    }
    		});
    	btnRegmem=new JButton("등록");
    	btnRegmem.addActionListener(new ActionListener(){
         	@Override
         		public void actionPerformed(ActionEvent e) {
         		 new RegMemDialog("회원 등록창");   
         	  }
            }); 
    	
    	btnModify=new JButton("수정");
    	btnModify.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		new ModMemDialog("회원정보수정창");   
    		    }
    		});  
    	btnDelete=new JButton("삭제");
    	btnDelete.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    		new DelMemDialog("회원정보삭제창");   
    		  }
    		});  
    	
    	panelBtn.add(btnSearchcar);
    	panelBtn.add(btnSearchres);
    	panelBtn.add(btnRegmem);
    	panelBtn.add(btnModify);
    	panelBtn.add(btnDelete);
    	
    	add(panelSearch,BorderLayout.NORTH);
    	add(panelBtn,BorderLayout.SOUTH);
    	
    	memberItems = new String[0][5];
        model=new RentTableModel(memberItems,columnNames);
    	memTable.setModel(model);
        add(new JScrollPane(memTable),BorderLayout.CENTER);
        
        setLocation(300,100);//다이얼로그 출력 위치를 정한다.
        setSize(600,600);
        setModal(true); //항상 부모창 위에 보이게 한다.
        setVisible(true);
    }
    
    private void loadTableData(ArrayList<MemberVo> memList){
    	memberItems = new String[memList.size()][5];
    	for(int i=0; i<memList.size();i++){
    		MemberVo vo = memList.get(i);
    		memberItems[i][0]=vo.id;
    		memberItems[i][1]=vo.password;
    		memberItems[i][2]=vo.name;
    		memberItems[i][3]=vo.address;
    		memberItems[i][4]=vo.phoneNum;
    	}
    	
    	model=new RentTableModel(memberItems,columnNames);
    	memTable.setModel(model);
    }
}