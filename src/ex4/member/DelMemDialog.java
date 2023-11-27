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

import ex4.car.SearchCarDialog;
public class DelMemDialog extends JDialog {
	JPanel     jPanel;
	JLabel     lId, lName, lPassword, lAddress, lPhoneNum;
	JTextField tfId, tfName, tfPassword, tfAddress, tfPhoneNum;
	JButton    btnReg;
	Member     memController;
	public DelMemDialog(String str) {
		setTitle(str);
		init();
	}
	private void init() {
		memController = new MemberImpl();
		lId           = new JLabel("아이디");
		tfId          = new JTextField(20);
		btnReg        = new JButton("삭제");
		
		btnReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id       = tfId.getText().trim();
				String password = "";
				String name     = ""; 
				String address  = "";
				String phoneNum = "";
				MemberVo vo=new MemberVo(id,password,name,address,phoneNum);
				try {
					memController.delMember(vo);
					showMessage("회원정보를 삭제했습니다.");
					SearchMemDialog.btnSearch.doClick();
					tfId.setText("");
					
				} catch (Exception e1) {
					e1.printStackTrace();
					showMessage("오류가 발생했습니다.\n다시 등록해 주세요.");
				}			
			}
		});
		jPanel = new JPanel(new GridLayout(0, 2));
		jPanel.add(lId);
		jPanel.add(tfId);
		
		add(jPanel, BorderLayout.NORTH);
		add(btnReg, BorderLayout.SOUTH);
		setLocation(400, 200);
		setSize(200, 90);
		setModal(true); // 항상 부모창 위에 보이게 한다.
		setVisible(true);
	}
	private void showMessage(String msg) {JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);}
}
