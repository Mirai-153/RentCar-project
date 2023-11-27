package ex4.car;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class RegCarDialog  extends JDialog{
	JPanel     jPanel;
	JLabel     lCarNum, lCarName, lSize, lColor, lMaker, lMoney, lCc;
	JTextField tfCarNum, tfMoney, tfCarName, tfMaker, tfCc;
	String     sColor, sSize;

    JButton btnReg;    
    JComboBox<String> comColor;
    JComboBox<String> comSize;
  
    Car carController; 
    String[] CarSizes  = {"소형" ,"준중형" ,"중형" ,"준대형" ,"대형"};
    String[] CarColors = {"레드","블루","그린","옐로우","핑크","그레이","화이트","블랙","퍼플"};
    public RegCarDialog(String str){
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
        tfCarNum      = new HintTextField("00가0000");
        tfCarName     = new HintTextField("소나타");
        tfMaker       = new HintTextField("현대");
    	comColor      = new JComboBox<String>();
   	    for(int i=0;i<CarColors.length;i++) {comColor.addItem(CarColors[i]);}
   	    sColor=CarColors[0];
   	    comColor.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		   JComboBox cb        = (JComboBox)e .getSource();
           int       deptIndex = cb.getSelectedIndex();
           System.out.println(deptIndex);
                     sColor    = CarColors[deptIndex];}
	    });
   	    
   	    comSize=new JComboBox<String>();
        for(int i=0;i<CarSizes.length;i++) {comSize.addItem(CarSizes[i]);}
        sSize=CarSizes[0];
     	comSize.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		   JComboBox cb        = (JComboBox)e .getSource();
           int       deptIndex = cb.getSelectedIndex();
           System.out.println(deptIndex);
                     sSize     = CarSizes[deptIndex];}
	    });
 
    	tfMoney       = new HintTextField("10000");
    	tfCc          = new HintTextField("10000");
    	btnReg        = new JButton("등록");
    	
   	 	btnReg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean CarNumRsult    = tfCarNum.getText().matches("^[0-9]{2,3}[가-힣]{1}[0-9]{4}$");
				boolean CarNameResult  = tfCarName.getText().matches("^[가-힣]{1,10}$");
				boolean CarMakerResult = tfMaker.getText().matches("^[가-힣]{1,10}$");
				boolean CarMoneyResult = tfMoney.getText().matches("^[1-9]{1}[0-9]{0,5}$");
				boolean CarCcResult    = tfCc.getText().matches("^[1-9]{1}[0-9]{0,5}$");
				if(tfCarNum.getText().equals("")||tfCarName.getText().equals("")||tfMaker.getText().equals("")||tfMoney.getText().equals("")||tfCc.getText().equals("")) {
					showMessage("차량등록창에 공백이 있습니다.");
					
				}else {
					if      (!CarNumRsult) {
						tfCarNum.setText("");
						showMessage("차량번호를 잘못 입력하셨습니다.\n 00가0000 OR 000가0000\n형태로 입력해주세요.");
						
					}else if(!CarNameResult) {
						tfCarName.setText("");
						showMessage("차량명을 잘못 입력하셨습니다.\n 한글만 1~10글자\n내로 입력해주세요.");	
						
					}else if(!CarMakerResult) {
						tfMaker.setText("");
						showMessage("차제조사를 잘못 입력하셨습니다.\n 한글만 1~10글자\n내로 입력해주세요.");
						
					}else if(!CarMoneyResult) {
						tfMoney.setText("");
						showMessage("렌트비를 잘못 입력하셨습니다.\n 숫자만 1~6글자\n내로 입력해주세요.");
						
					}else if(!CarCcResult) {
						tfCc.setText("");
						showMessage("배기량을 잘못 입력하셨습니다.\n 숫자만 1~6글자\n내로 입력해주세요.");
						
					}else {
							String carNum   = tfCarNum.getText().trim();
							String carName  = tfCarName.getText().trim();
							String carSize  = sSize;
							String carColor = sColor;
							String carMaker = tfMaker.getText().trim();
							int    carMoney = Integer.parseInt(tfMoney.getText().trim());
							int    carCc    = Integer.parseInt(tfCc.getText().trim());
							CarVo vo=new CarVo(carNum,carName,carColor,carSize,carMaker,carMoney,carCc);
							try {
								carController.regCarInfo(vo);
								SearchCarDialog.btnSearch.doClick();
								showMessage("차량을  등록했습니다.");
								tfCarNum.setText("");
								tfCarName.setText("");
								tfMaker.setText("");
								tfMoney.setText("");
								tfCc.setText("");} 
							catch (Exception e1) {
								e1.printStackTrace();
								showMessage("오류가 발생했습니다.\n다시 등록해 주세요.");}
						}
					}	
				}
   	 });
   	 	
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
        setModal(true); //�׻� �θ�â ���� ���̰� �Ѵ�.
        setVisible(true);
    }    
    private void showMessage(String msg){JOptionPane.showMessageDialog(this,msg, "메시지 박스",JOptionPane.INFORMATION_MESSAGE);}}
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