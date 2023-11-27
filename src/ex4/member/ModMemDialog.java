package ex4.member;

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

public class ModMemDialog extends JDialog {
	JPanel     jPanel;
	JLabel     lId, lName, lPassword, lAddress, lPhoneNum;
	JTextField tfId, tfName, tfPassword, tfAddress, tfPhoneNum;
	JButton    btnReg;
	Member     memController;
	
	public ModMemDialog(String str) {
		setTitle(str);
		init();
	}
	private void init() {
		memController =new MemberImpl();
		lId           = new JLabel("아이디");
		lPassword     = new JLabel("비밀번호");
		lName         = new JLabel("이름");
		lAddress      = new JLabel("주소");
		lPhoneNum     = new JLabel("전화번호");
		tfId       	  = new JTextField(20);
		tfPassword    = new JTextField(20);
		tfName        = new JTextField(20);
		tfAddress     = new JTextField(20);
		tfPhoneNum    = new JTextField(20);
		btnReg        = new JButton("수정");
		btnReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id       = tfId.getText().trim();
				String password = tfPassword.getText().trim();
				String name     = tfName.getText().trim();
				String address  = tfAddress.getText().trim();
				String phoneNum = tfPhoneNum.getText().trim();
				MemberVo vo=new MemberVo(id,password,name,address,phoneNum);
				try {
					memController.modMember(vo);
					showMessage("회원 정보를 수정했습니다.");
					SearchMemDialog.btnSearch.doClick();
					tfId.setText("");
					tfPassword.setText("");
					tfName.setText("");
					tfAddress.setText("");
					tfPhoneNum.setText("");
				} catch (Exception e1) {
					e1.printStackTrace();
					showMessage("오류가 발생했습니다.\n다시 등록해 주세요.");
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
}
