package ex4.car;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import ex4.common.RentTableModel;
public class ModCarDialog  extends JDialog{
	public class ModifyListener {

	}
	JPanel     jPanel;
	JLabel     lCarNum, lCarName, lSize, lColor, lMaker, lMoney, lCc;
	JTextField tfCarNum, tfMoney, tfCarName, tfMaker, tfCc;
	String     sColor, sSize;

    JButton btnReg;    
    JComboBox<String> comColor;
    JComboBox<String> comSize;
  
    static JTable            rentTable;
    static RentTableModel    model;
    static String[][]        carItems;
    static String[]          columnNames={"차번호","차이름","크기","차색상","차제조사","렌트비용"};
    
    Car carController; 
    String[] CarSizes  = {"소형" ,"준중형" ,"중형" ,"준대형" ,"대형"};
    String[] CarColors = {"레드","블루","그린","옐로우","핑크","그레이","화이트","블랙","퍼플"};
    public ModCarDialog(String str){
    	setTitle(str);
    	init();
    }    
    private void init(){
    	carController = new CarImpl();
    	lCarNum       = new JLabel("차량번호");
       	lCarName      = new JLabel("차량명");
       	lSize         = new JLabel("크기");
       	lColor        = new JLabel("차색상");
       	lMaker        = new JLabel("차제조사");
       	lMoney        = new JLabel("렌트비용");
       	lCc           = new JLabel("배기량");
        tfCarNum      = new JTextField(20);
        tfCarName     = new JTextField(20);
        tfMaker       = new JTextField(20);
    	comColor      = new JComboBox<String>();
   	    for(int i=0;i<CarColors.length;i++) {comColor.addItem(CarColors[i]);}
   	    comColor.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		   JComboBox cb        = (JComboBox)e .getSource();
           int       deptIndex = cb.getSelectedIndex();
                     sColor    = CarColors[deptIndex];}
	    });
   	    
   	    comSize=new JComboBox<String>();
        for(int i=0;i<CarSizes.length;i++) {comSize.addItem(CarSizes[i]);}
     	comSize.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		   JComboBox cb        = (JComboBox)e .getSource();
           int       deptIndex = cb.getSelectedIndex();
                     sSize     = CarSizes[deptIndex];}
	    });
 
    	tfMoney       = new JTextField(20);
    	tfCc          = new JTextField(20);
    	btnReg        = new JButton("수정");
    	
    	btnReg.addActionListener(new ActionListener(){
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	String carNum = tfCarNum.getText().trim();
    	    	String carName = tfCarName.getText().trim();
    	    	String carMaker = tfMaker.getText().trim();
    	    	String carSize = sSize;
    	    	String carColor = sColor;
    	    	int carMoney;
    	    	if (tfMoney.getText().trim().equals("")) {
    	    	    carMoney = 0;
    	    	} else {
    	    	    carMoney = Integer.parseInt(tfMoney.getText().trim());
    	    	}
    	    	int carCc;
    	    	if (tfCc.getText().trim().equals("")) {
    	    	    carCc = 0;
    	    	} else {
    	    	    carCc = Integer.parseInt(tfCc.getText().trim());
    	    	}
    	    	CarVo vo = new CarVo(carNum, carName, carColor, carSize, carMaker, carMoney, carCc);
    	    	try {
    	    		carController.modCarInfo(vo);
    	            showMessage("차량을  수정했습니다.");
    	            SearchCarDialog.btnSearch.doClick();
    	            tfCarNum.setText("");
    	            tfCarName.setText("");
    	            tfMaker.setText("");
    	            tfMoney.setText("");
    	            tfCc.setText("");
    	    	} catch (Exception e1) {
    	            e1.printStackTrace();
    	            showMessage("오류가 발생했습니다.\n다시 수정해 주세요.");
    	        } 
    	    }
    	}); // 삭제된 괄호

   	 	
    	jPanel = new JPanel(new GridLayout(0,2));
    	jPanel.add(lCarNum);
    	jPanel.add(tfCarNum);
    	
    	jPanel.add(lCarName);
     	jPanel.add(tfCarName);
    	
    	jPanel.add(lSize);
    	jPanel.add(comSize);
    	
    	jPanel.add(lColor);
    	jPanel.add(comColor);
    	
    	jPanel.add(lMaker);
     	jPanel.add(tfMaker);
     	
    	jPanel.add(lMoney);
    	jPanel.add(tfMoney);
    	
    	jPanel.add(lCc);
    	jPanel.add(tfCc);
    	
    	add(jPanel,BorderLayout.NORTH);
    	add(btnReg,BorderLayout.SOUTH);
    	
        setLocation(400, 400);
        setSize(300, 240);
        setModal(true); 
        setVisible(true);
    }    
    

    private void showMessage(String msg){JOptionPane.showMessageDialog(this,msg, "메시지 박스",JOptionPane.INFORMATION_MESSAGE);}}
