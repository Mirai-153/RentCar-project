package ex4.main;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ex4.car.DelCarDialog;
import ex4.car.ModCarDialog;
import ex4.car.RegCarDialog;
import ex4.car.SearchCarDialog;
import ex4.member.DelMemDialog;
import ex4.member.ModMemDialog;
import ex4.member.RegMemDialog;
import ex4.member.SearchMemDialog;
import ex4.reserve.DelResDialog;
import ex4.reserve.ModResDialog;
import ex4.reserve.RegResDialog;
import ex4.reserve.SearchResDialog;

public class RentMainWindow extends JFrame {
	JFrame    f;
	JMenuBar  menuBar;
	JMenu     carMenu, memberMenu, rentMenu, helpMenu,reserveMenu; // 메인 메뉴 항목
	// 서브 메뉴 항목
	JMenuItem carMenu11, carMenu12, carMenu13, carMenu14;
	JMenuItem memMenu21, memMenu22, memMenu23, memMenu24;
	JMenuItem resMenu31, resMenu32, resMenu33, resMenu34;
	JMenuItem helpMenu41;
	
	JPanel    jPanel;


	public RentMainWindow() {
		f           = new JFrame("렌트카 예약 시스템");
		menuBar     = new JMenuBar();
		// 메인 메뉴 항목 객체 생성
		carMenu     = new JMenu("차량 관리");
		memberMenu  = new JMenu("회원관리");
		reserveMenu = new JMenu("예약관리");
		helpMenu    = new JMenu("도움말");
	}

	// 서브메뉴 생성 메소드
	protected void startFrame() {
		f.setJMenuBar(menuBar); // Frame에 메뉴바를 단다.
		menuBar.add(carMenu); // 메뉴바에 "파일"항목을 단다.
		// 차량 메뉴 관련 서브 메뉴 항목
		carMenu.add(carMenu11     = new JMenuItem("차량등록"));
		carMenu.add(carMenu12     = new JMenuItem("차량조회"));
		carMenu.addSeparator(); // 분리선 설정하기
		carMenu.add(carMenu13     = new JMenuItem("차량수정"));
		carMenu.add(carMenu14     = new JMenuItem("차량삭제"));

		menuBar.add(memberMenu); // 메뉴바에 "회원관리"항목을 단다.
		// 회원 관리 메뉴 관련 서브 메뉴 항목
		memberMenu.add(memMenu21  = new JMenuItem("회원등록"));
		memberMenu.add(memMenu22  = new JMenuItem("회원조회"));
		memberMenu.addSeparator(); 
		memberMenu.add(memMenu23  = new JMenuItem("회원수정"));
		memberMenu.add(memMenu24  = new JMenuItem("회원삭제"));
		
		menuBar.add(reserveMenu); // 메뉴바에 "회원관리"항목을 단다.
		// 예약 관리 메뉴 관련 서브 메뉴 항목
		reserveMenu.add(resMenu31 = new JMenuItem("예약등록"));
		reserveMenu.add(resMenu32 = new JMenuItem("예약조회"));
		reserveMenu.addSeparator(); 
		reserveMenu.add(resMenu33 = new JMenuItem("예약수정"));
		reserveMenu.add(resMenu34 = new JMenuItem("예약삭제"));

		menuBar.add(helpMenu);
		helpMenu.add(helpMenu41   = new JMenuItem("버전"));

		jPanel = new JPanel();
//		lCarName = new JLabel("차량명");
//		tf = new JTextField(10);
//		searchBtn = new JButton("차량 조회하기");
	
//		jPanel.add(lCarName);
//		jPanel.add(tf);
//		jPanel.add(searchBtn);

		Container con = f.getContentPane(); // contentPane 을 가지고 올 때 사용하는 메소드
		con.add(jPanel, "North");

		f.setLocation(200, 100);
		f.setSize(800, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon normalIcon = new ImageIcon("D:\\se_work\\rentCar6_230502\\이미지\\즐겁다(즐겁지않음).png");
	
		JButton btn = new JButton(normalIcon);
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showMessage("♚♚히어로즈 오브 더 스☆톰♚♚가입시$$전원 카드팩☜☜뒷면100%증정※\n ♜월드오브 워크래프트♜펫 무료증정￥\n 특정조건 §§디아블로3§§★공허의유산★초상화획득기회@@@ \n즉시이동http://kr.battle.net/heroes/ko/");
         
				
			}
    	});
	
		f.add(btn);
		f.setSize(1300, 800);
		f.setVisible(true);
		
		
		// 메뉴 항목 선택 이벤트와 이벤트 리스너를 연결한다.
		carMenu11.addActionListener(new CarHandler());
		carMenu12.addActionListener(new CarHandler());
		carMenu13.addActionListener(new CarHandler());
		carMenu14.addActionListener(new CarHandler());

		memMenu21.addActionListener(new MemberHandler());
		memMenu22.addActionListener(new MemberHandler());
		memMenu23.addActionListener(new MemberHandler());
		memMenu24.addActionListener(new MemberHandler());
		
		resMenu31.addActionListener(new ReserveHandler());
		resMenu32.addActionListener(new ReserveHandler());
		resMenu33.addActionListener(new ReserveHandler());
		resMenu34.addActionListener(new ReserveHandler());

		helpMenu41.addActionListener(new HelpHandler());
	}
	private class CarHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			  if      (e.getSource() == carMenu11) {
				new RegCarDialog("차량등록창");
			} else if (e.getSource() == carMenu12) {
				new SearchCarDialog("차량조회창");
			} else if (e.getSource() == carMenu13) {
				new ModCarDialog("차량정보수정창");
			}else if  (e.getSource() == carMenu14) {
				new DelCarDialog("차량정보삭제창");
			}		
			}	
		}
	private class MemberHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			  if      (e.getSource() == memMenu21) {
				new RegMemDialog("회원 등록창");
			} else if (e.getSource() == memMenu22) {
				new SearchMemDialog("회원 조회창");
			} else if (e.getSource() == memMenu23) {
				new ModMemDialog("회원정보수정창");
			}else if  (e.getSource() == memMenu24) {
				new DelMemDialog("회원정보삭제창");
			}
		}
	}
	private class ReserveHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			  if      (e.getSource() == resMenu31) {
				new RegResDialog("예약 등록창");
			} else if (e.getSource() == resMenu32) {
				new SearchResDialog("예약 조회창");
			} else if (e.getSource() == resMenu33) {
				new ModResDialog("예약정보수정창");
			}else if  (e.getSource() == resMenu34) {
				new DelResDialog("예약정보삭제창");
			}
		}
	}
	private class HelpHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			version();
		}
	}
	// 버전 메뉴 클릭 시 수행하는 메소드
	public void version() { // 버전관리창 생성
		final JDialog d        = new JDialog(this, "버전관리");
		      JLabel  jbver    = new JLabel("       버전1.24");
		      JLabel  jbdate   = new JLabel("       2024.05.02");
		      JLabel  jbauthor = new JLabel("       제작: Yonsai 3Team");
		d.setLayout(new FlowLayout());
		d.add(jbver);
		d.add(jbdate);
		d.add(jbauthor);
		d.setLocation(250, 230);
		d.setSize(200, 100);
		d.setVisible(true);
		d.addWindowListener(new WindowAdapter() { // 버전관리창 종료
			public void windowClosing(WindowEvent e) {
				d.dispose();
				d.setVisible(false);
			}
		});
	}
	public static void main(String[] args) {
		RentMainWindow test = new RentMainWindow();
		test.startFrame();
	}
	private void showMessage(String msg) {JOptionPane.showMessageDialog(this, msg, "시공의 폭풍은 정말 최고야!!", JOptionPane.WARNING_MESSAGE);}
}



