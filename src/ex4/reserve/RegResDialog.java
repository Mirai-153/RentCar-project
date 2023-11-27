package ex4.reserve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ex4.car.Car;
import ex4.car.CarImpl;
import ex4.car.CarVo;

public class RegResDialog extends JDialog{
	
	JPanel     jPanel;
	JLabel     lCarNum,lid,luseBegin,lreturn;
	JTextField tfCarNum,tfID,tfResBegin,tfResReturn ;
    JButton    btnReg;    
    Reserve    resController;    
    public RegResDialog(String str){
    	setTitle(str);
    	init();
    	getContentPane().setBackground(Color.PINK);
    	getRootPane().setOpaque(false);
    }    
    private void init(){
    	resController   = new ReserveImpl();
    	lCarNum         = new JLabel("차량번호"); 	    
    	lid             = new JLabel("회원ID");
    	luseBegin       = new JLabel("차량대여일");
    	lreturn         = new JLabel("반납예정일");
    	tfCarNum        = new HintTextField("00가0000");
    	tfID            = new HintTextField("aaa1");
    	tfResBegin      = new HintTextField("YYYYDDMM");
    	tfResReturn     = new HintTextField("YYYYDDMM");
    	btnReg          = new JButton("예약");  
   	 	btnReg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean CarNumRsult     = tfCarNum.getText().matches("^[0-9]{2,3}[가-힣]{1}[0-9]{4}$");
				boolean idRsult         = tfID.getText().matches("^[a-zA-Z0-9]{4,12}$");
				boolean BeginDateRsult  = tfResBegin.getText().matches("^([1-9]\\d{3})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$");
				boolean returnDateRsult = tfResReturn.getText().matches("^([1-9]\\d{3})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$");
				if(tfCarNum.getText().equals("")||tfID.getText().equals("")||tfResBegin.getText().equals("")||tfResReturn.getText().equals("")) {
					showMessage("차량예약에 공백이 있습니다.");
				
				}else {
					if      (!CarNumRsult) {
						tfCarNum.setText("");
						showMessage("차량번호를 잘못 입력하셨습니다.\n 00가0000 OR 000가0000\n형태로 입력해주세요.");
						
					}else if (!idRsult) {
						tfID.setText("");
						showMessage("아이디 입력을 잘못하셨습니다.\n 영문,숫자포함 4~12로\n입력해주세요.");
						
					}else if(!BeginDateRsult) {
						tfResBegin.setText("");
						showMessage("차량대여일을 잘못 입력하셨습니다.\n YYYYDDMM\n형태로 입력해주세요.");
						
					}else if(!returnDateRsult) {
						tfResReturn.setText("");
						showMessage("반납예정일 잘못 입력하셨습니다.\n YYYYDDMM\n형태로 입력해주세요.");
					}else {
				    String resNo="";
				    String carNum=tfCarNum.getText().trim();
				    String resId=tfID.getText().trim();
                    String resDate="";
				    String useBeginDate=tfResBegin.getText().trim();
				    String returnDate=tfResReturn.getText().trim();
				
				    ResVo vo=new ResVo(resNo,carNum,resId,resDate,useBeginDate,returnDate);
				try {
					resController.reserveCar(vo);
					showMessage("차량예약을 등록했습니다.");
					SearchResDialog.btnSearch.doClick();
					tfCarNum.setText("");
					tfID.setText("");
					tfResBegin.setText("");
					tfResReturn.setText("");
					
				} catch (Exception e1) {
					e1.printStackTrace();
					showMessage("오류가 발생했습니다.\n다시 등록해 주세요.");
				}				
			  }				
			}
		}
        });    	
    	jPanel=new JPanel(new GridLayout(0,2));
    	
    	jPanel.add(lCarNum);
    	jPanel.add(tfCarNum);
    	
    	jPanel.add(lid);
    	jPanel.add(tfID);
    	
    	jPanel.add(luseBegin);
    	jPanel.add(tfResBegin);
    	
    	jPanel.add(lreturn);
    	jPanel.add(tfResReturn);
    	
    	add(jPanel,BorderLayout.NORTH);
    	add(btnReg,BorderLayout.SOUTH);
    	
        setLocation(400, 200);
        setSize(300,145);
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
class HintTextField extends JTextField implements FocusListener {
    
    private final String hint;
    private boolean showingHint;

    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.setForeground(Color.GRAY);
        this.showingHint = true;
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            this.setForeground(Color.BLACK);
            showingHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(hint);
            this.setForeground(Color.GRAY);
            showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}

 