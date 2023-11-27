package ex4.car;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ex4.common.RentTableModel;
import ex4.member.RegMemDialog;
import ex4.member.SearchMemDialog;
import ex4.reserve.SearchResDialog;

public class SearchCarDialog  extends JDialog{
	
	static int        deptIndex;
	static boolean    btnSearchchk ;
	JPanel            panelSearch,panelBtn;
	JTextField        tf;
	
    static JButton    btnSearch;
    JButton           btnModify;
    JButton           btnDelete;
    JButton           btnSearchmember;
    JButton           btnSearchres;
    JButton           btnRegcar;
    
    JComboBox<String> comSearch;
    
    String[][]        carItems;
    JTable            rentTable;
    
    RentTableModel    model;
    CarImpl           carController;
    String[]          columnNames = {"차량번호","차량명","크기","차색상","차제조사","렌트비용","배기량"};
    
    public SearchCarDialog(String str){
    	setTitle(str);
    	init();
    }
    
    private void init(){
    	carController = new CarImpl();
    	rentTable     = new JTable();
    	panelSearch   = new JPanel();
    	panelBtn      = new JPanel();
    	tf            = new JTextField(20);
    	btnSearch     = new JButton("조회");
    	comSearch     = new JComboBox<String>();
    	
    	tf.setEditable(false);
    	
    	comSearch.addItem("전체");
    	comSearch.addItem(columnNames[0]);
    	comSearch.addItem(columnNames[1]);
    	comSearch.addItem(columnNames[4]);
    	
    	comSearch.addActionListener(new ActionListener(){	
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<CarVo> carList=new ArrayList<CarVo>();
				CarVo vo=new CarVo();
				JComboBox cb = (JComboBox)e .getSource();
                deptIndex    = cb.getSelectedIndex();
                if(deptIndex == 0) {
                    	tf.setText("");
      					tf.setEditable(false);
      					
                }else if(deptIndex == 1) {
                	tf.setText("");
					tf.setEditable(true);
                	vo.setCarNumber(tf.getText());
					vo.getCarNumber();
				}else if(deptIndex == 2) {
					tf.setText("");
					tf.setEditable(true);
					vo.setCarName(tf.getText());
					vo.getCarName();
				
				}else if(deptIndex == 3) {
					tf.setText("");
					tf.setEditable(true);
					vo.setCarMaker(tf.getText());
					vo.getCarMaker();
				}
                  	try {		
    					carList=carController.listCarInfo(vo);
    					loadTableData(carList);
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                  	
			}
    	});

    	btnSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<CarVo> carList=new ArrayList<CarVo>();
				CarVo vo=new CarVo();
				

				      if(deptIndex == 0) {
				    btnSearchchk =true;
			    }else if(deptIndex == 1) {
					vo.setCarNumber(tf.getText());
					vo.getCarNumber();
					btnSearchchk =false;
				}else if(deptIndex == 2) {
					vo.setCarName(tf.getText());
					vo.getCarName();
					btnSearchchk =false;
				}else if(deptIndex == 3) {
					vo.setCarMaker(tf.getText());
					vo.getCarMaker();
					btnSearchchk =false;
				}			
				try {		
					carList=carController.listCarInfo(vo);
					loadTableData(carList);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
    	});    	
    	    	
    	panelSearch.add(comSearch);
    	panelSearch.add(tf);
    	panelSearch.add(btnSearch);
    	btnSearchmember=new JButton("회원 조회");
    	btnSearchmember.addActionListener(new ActionListener(){
         	@Override
         		public void actionPerformed(ActionEvent e) {
         		 new SearchMemDialog("회원 조회창");   
         	  }
            });
    	btnSearchres=new JButton("예약 조회");
    	btnSearchres.addActionListener(new ActionListener(){
         	@Override
         		public void actionPerformed(ActionEvent e) {
         		 new SearchResDialog("예약 조회창");   
         	  }
            });
    	
    	btnRegcar=new JButton("등록");
    	btnRegcar.addActionListener(new ActionListener(){
         	@Override
         		public void actionPerformed(ActionEvent e) {
         		 new RegCarDialog("차량등록창");   
         	  }
            });
   
     	btnModify = new JButton("수정");
     	btnModify.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			new ModCarDialog("차량정보수정창");	
			}
    	});  
     	
    	btnDelete = new JButton("삭제");
    	
    	btnDelete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			new DelCarDialog("차량정보삭제창");	
			}
    	});
    	
    	panelBtn.add(btnSearchmember);
    	panelBtn.add(btnSearchres);
    	panelBtn.add(btnRegcar);
    	panelBtn.add(btnModify);
    	panelBtn.add(btnDelete);
    	
      	add(panelSearch,BorderLayout.NORTH);
    	add(panelBtn,BorderLayout.SOUTH);
    	
      	carItems = new String[0][7];
        model=new RentTableModel(carItems,columnNames);
    	rentTable.setModel(model);
        add(new JScrollPane(rentTable),BorderLayout.CENTER);
        setLocation(300,100);
        setSize(600,600);
        setModal(true); 
        setVisible(true);
    }
     private void loadTableData(ArrayList<CarVo > carList){
    	carItems = new String[carList.size()][7];
    	for(int i=0; i<carList.size();i++){
    		CarVo vo=carList.get(i);
    		carItems[i][0]=vo.carNumber;
    		carItems[i][1]=vo.carName;
    		carItems[i][2]=vo.carSize;
    		carItems[i][3]=vo.carColor;
    		carItems[i][4]=vo.carMaker;
    		carItems[i][5]=Integer.toString(vo.carMoney);
    		carItems[i][6]=Integer.toString(vo.carCc);
    	}
    	
    	model = new RentTableModel(carItems,columnNames);
    	rentTable.setModel(model);
	}    
}
