package ex4.member;

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

import ex4.car.CarVo;

public class RegMemDialog extends JDialog {
	JPanel     jPanel;
	JLabel     lId, lName, lPassword, lAddress, lPhoneNum;
	JTextField tfId, tfName, tfPassword, tfAddress, tfPhoneNum;
	JButton    btnReg;
	Member     memController;
	public RegMemDialog(String str) {
		setTitle(str);
		init();
	}
	private void init() {
		memController = new MemberImpl();
		lId           = new JLabel("아이디");
		lPassword     = new JLabel("비밀번호");
		lName         = new JLabel("이름");
		lAddress      = new JLabel("주소");
		lPhoneNum     = new JLabel("휴대폰번호");
		tfId          = new HintTextField("aaa1");
		tfPassword    = new HintTextField("aaaaaa1!");
		tfName        = new HintTextField("홍길동");
		tfAddress     = new HintTextField("서울특별시");
		tfPhoneNum    = new HintTextField("01000000000");
		btnReg        = new JButton("등록");
		btnReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean idRsult         = tfId.getText().matches("^[a-zA-Z0-9]{4,12}$");
				boolean passwordResult  = tfPassword.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{8,12}$");
				boolean NameResult      = tfName.getText().matches("^[가-힣]{2,20}$");
				boolean phoneNumResult  = tfPhoneNum.getText().matches("^[0-1]{3}[0-9]{4}[0-9]{4}$");
				if(tfId.getText().equals("")||tfPassword.getText().equals("")||tfName.getText().equals("")||tfAddress.getText().equals("")||tfPhoneNum.getText().equals("")) {
					showMessage("차량등록창에 공백이 있습니다.");	
				}else {
					if      (!idRsult) {
						tfId.setText("");
						showMessage("아이디 입력을 잘못하셨습니다.\n 영문,숫자포함 4~12로\n입력해주세요.");
					}else if(!passwordResult) {
						tfPassword.setText("");
						showMessage("비밀번호 입력을 잘못하셨습니다.\n 영문,특수문자,숫자포함 8~12로\n입력해주세요.");	
						
					}else if(!NameResult) {
						tfName.setText("");
						showMessage("이름을 잘못 입력하셨습니다.\n 한글로 2~20글자\n내로 입력해주세요.");
						
					}else if(!phoneNumResult) {
						tfPhoneNum.setText("");
						showMessage("휴대폰번호를 잘못 입력하셨습니다.\n 숫자만 12글자\n내로 입력해주세요.");
						
					}else {
				    String id       = tfId.getText().trim();
				    String password = tfPassword.getText().trim();
				    String name     = tfName.getText().trim();
				    String address  = tfAddress.getText().trim();
				    String phoneNum = tfPhoneNum.getText().trim();
				    MemberVo vo=new MemberVo(id,password,name,address,phoneNum);
				    try {
					memController.regMember(vo);
					showMessage("새 회원을  등록했습니다.");
					SearchMemDialog.btnSearch.doClick();
					tfId.setText("");
					tfPassword.setText("");
					tfName.setText("");
					tfAddress.setText("");
					tfPhoneNum.setText("");
				    }catch (Exception e1){
					e1.printStackTrace();
				 	showMessage("오류가 발생했습니다.\n다시 등록해 주세요.");
				   }
				}
				// dispose();
			}
			}
		});
		jPanel = new JPanel(new GridLayout(0, 2));
		jPanel.add(lId);
		jPanel.add(tfId);
		
		jPanel.add(lPassword);
		jPanel.add(tfPassword);
		
		jPanel.add(lName);
		jPanel.add(tfName);
		
		jPanel.add(lAddress);
		jPanel.add(tfAddress);
		
		jPanel.add(lPhoneNum);
		jPanel.add(tfPhoneNum);
		add(jPanel, BorderLayout.NORTH);
		add(btnReg, BorderLayout.SOUTH);
		setLocation(400, 200);
		setSize(300, 170);
		setModal(true); // 항상 부모창 위에 보이게 한다.
		setVisible(true);
	}

	private void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
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
	
}
