package ex4.car;

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

public class DelCarDialog extends JDialog{
	JPanel     jPanel;
	JLabel     lCarNum,lCarName,lSize,lColor,lMaker,lMoney,lCc;
	JTextField tfCarNum,tfCarName,tfSize,tfColor,tfMaker,tfMoney,tfCc ;
    JButton    btnDel;    
    Car        carController;    
    public DelCarDialog(String str){
    	setTitle(str);
    	init();
    }    
    private void init(){
    	System.out.println("화면출력");
   	 	carController = new CarImpl();
   	 	lCarNum       = new JLabel("차량번호");
    	tfCarNum      = new JTextField(20);
    	btnDel        = new JButton("삭제");    	
   	 	btnDel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String carNum   = tfCarNum.getText().trim();
				String carName  = "";
				String carSizes = "";
				String carColor = "";
				String carMaker = "";
				int carMoney    = 0;
				int carCc       = 0;
				
				CarVo vo=new CarVo(carNum,carName,carColor,carSizes,carMaker,carMoney,carCc);
				System.out.println(vo.carNumber);
				try {
					carController.delCarInfo(vo);
					showMessage("차량을  삭제했습니다.");
					SearchCarDialog.btnSearch.doClick();
					tfCarNum.setText("");
				} catch (Exception e1) {
					e1.printStackTrace();
					showMessage("오류가 발생했습니다.\n다시 삭제해 주세요.");
				}				
				//dispose();				
			}
        });    	
    	jPanel=new JPanel(new GridLayout(0,2));
    	jPanel.add(lCarNum);
    	jPanel.add(tfCarNum);
	
    	add(jPanel,BorderLayout.NORTH);
    	add(btnDel,BorderLayout.SOUTH);
    	
    	setLocation(400, 200);
		setSize(200, 90);
        setModal(true); 
        setVisible(true);
    }    
    private void showMessage(String msg){JOptionPane.showMessageDialog(this,msg, "메시지 박스",JOptionPane.INFORMATION_MESSAGE);}
}