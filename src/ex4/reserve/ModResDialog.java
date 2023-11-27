package ex4.reserve;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ex4.car.Car;
import ex4.car.CarImpl;
import ex4.car.CarVo;
import ex4.car.SearchCarDialog;

public class ModResDialog extends JDialog{
	JPanel jPanel;
	JLabel lresNo,luseBegin,lreturn;
	JTextField tfresNo,tfCarNum,tfID,tfResDate,
				tfResBegin,tfResReturn ;
    JButton btnReg;    
    Reserve resController;    
    public ModResDialog(String str){
    	setTitle(str);
    	init();
    }
    private void init(){
    	resController=new ReserveImpl();
    	lresNo = new JLabel("예약번호");
    	luseBegin = new JLabel("차량대여일");
    	lreturn = new JLabel("차량반납일");
    	tfresNo =new JTextField(20);
    	tfCarNum=new JTextField(20);
    	tfID=new JTextField(20);
    	tfResDate=new JTextField(20);
    	tfResBegin=new JTextField(20);
    	tfResReturn=new JTextField(20);    	
    	btnReg=new JButton("수정");    	
   	 	btnReg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String resNo=tfresNo.getText().trim();
				String carNum=tfCarNum.getText().trim();
				String resId=tfID.getText().trim();
				String resDate=tfResDate.getText().trim();
				String useBeginDate=tfResBegin.getText().trim();
				String returnDate=tfResReturn.getText().trim();
				
				ResVo vo=new ResVo(resNo,carNum,resId,resDate,useBeginDate,returnDate);
				try {
					resController.modReserveInfo(vo);
					showMessage("차량예약을  수정했습니다.");
					SearchResDialog.btnSearch.doClick();
					tfresNo.setText("");
					tfResBegin.setText("");
					tfResReturn.setText("");
					
				} catch (Exception e1) {
					e1.printStackTrace();
					showMessage("오류가 발생했습니다.\n다시 등록해 주세요.");
				}				
				//dispose();				
			}
        });    	
    	jPanel=new JPanel(new GridLayout(0,2));
    	jPanel.add(lresNo);
    	jPanel.add(tfresNo);
    	
    	jPanel.add(luseBegin);
    	jPanel.add(tfResBegin);
    	
    	jPanel.add(lreturn);
    	jPanel.add(tfResReturn);
    	
    	add(jPanel,BorderLayout.NORTH);
    	add(btnReg,BorderLayout.SOUTH);
    	
        setLocation(400, 200);
        setSize(300,130);
        setModal(true); //항상 부모창 위에 보이게 한다.
        setVisible(true);
    }
    private void showMessage(String msg){
    	JOptionPane.showMessageDialog(this,
    			msg, 
    			"메시지 박스",
               JOptionPane.INFORMATION_MESSAGE);
    }
}


