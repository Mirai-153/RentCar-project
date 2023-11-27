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

public class DelResDialog extends JDialog{
	JPanel jPanel;
	JLabel lresNo,lCarNum,lid,lresDate,luseBegin,lreturn;
	JTextField tfresNo,tfCarNum,tfID,tfResDate,
				tfResBegin,tfResReturn ;
    JButton btnReg;    
    Reserve resController;    
    public DelResDialog(String str){
    	setTitle(str);
    	init();
    }    
    private void init(){
    	resController=new ReserveImpl();
    	lresNo       = new JLabel("예약번호");
    	tfresNo      =new JTextField(20);
    	btnReg       =new JButton("삭제");    	
   	 	btnReg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String resNo=tfresNo.getText().trim();
				String carNum="";
				String resId ="";
				String resDate="";
				String useBeginDate="";
				String returnDate="";
				
				ResVo vo=new ResVo(resNo,carNum,resId,resDate,useBeginDate,returnDate);
				try {
					resController.cancelReserveInfo(vo);
					SearchResDialog.btnSearch.doClick();
					showMessage("차량예약을  삭제했습니다.");
					tfresNo.setText("");
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
    	
    
    	add(jPanel,BorderLayout.NORTH);
    	add(btnReg,BorderLayout.SOUTH);
    	
        setLocation(400, 200);
        setSize(200,90);
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



