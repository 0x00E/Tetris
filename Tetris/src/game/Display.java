package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Display implements ActionListener,KeyListener{
	private JFrame jf=new JFrame();
	private JPanel jp=new JPanel();
	private JPanel jpgame=new JPanel();
	private JPanel jpdata=new JPanel();
	private JButton[][] jbbox=new JButton[20][10];
	private JButton start=new JButton("开始");
	private JButton exit=new JButton("结束");
	private JLabel jl=new JLabel("0",JLabel.CENTER);
	private JLabel gameover=new JLabel("点击‘开始’开始游戏");
	private static boolean flag=false;
	Display(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.View();
	}
	
	public static boolean getFlag() {
		return flag;
	}
	
	private void View(){
		
		jpgame.setLayout(new GridLayout(20, 10));
		
		for(int i=0;i<jbbox.length;i++){
			for(int j=0;j<jbbox[i].length;j++){
				jbbox[i][j]=new JButton();
				jbbox[i][j].setBackground(Color.WHITE);
				jbbox[i][j].setEnabled(false);
				jpgame.add(jbbox[i][j]);
			}
		}
		
		jpgame.setBackground(Color.WHITE);

		jpdata.add(start);
		start.setBounds(10, 50, 180, 30);
		start.addActionListener(this);
		jpdata.add(exit);
		exit.setBounds(10, 90, 180, 30);
		exit.addActionListener(this);
		jpdata.add(jl);
		jl.setBounds(10, 150, 180, 60);
		jl.setFont(new Font("微软雅黑",Font.BOLD,72));
		jpdata.add(gameover);
		gameover.setBounds(10, 10, 180, 30);
		
		
		
		jpdata.setLayout(null);
		
		jp.add(jpgame);
		jpgame.setBounds(0, 0, 400, 800);
		jp.add(jpdata);
		jpdata.setBounds(400, 0, 200, 800);
		
		jp.setLayout(null);
		jf.getContentPane().add(jp);
		jf.setTitle("Tetris 20180302");
		
		jf.setSize(600, 830);
		
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.addKeyListener(this);
		jf.setVisible(true);
		jf.requestFocusInWindow();
		
	}
	public void setView(int[][] map){
		if(map==null) {
			for(int i=0;i<20;i++){
				for(int j=0;j<10;j++){
					jbbox[i][j].setBackground(Color.WHITE);
				}
				
			}
			return;
		}else 
			for(int i=0;i<20;i++)
				for(int j=0;j<10;j++)
					if(map[i][j]==1)
						jbbox[i][j].setBackground(Color.BLACK);
					else
						jbbox[i][j].setBackground(Color.WHITE);
		this.jpgame.repaint();
		this.jpgame.updateUI();
	}
	public void setFraction(int f){
		jl.setText(String.valueOf(f));
	}
	public void over(){
		gameover.setText("游戏结束");
	}
	public void start(){
		gameover.setText("游戏中。。。");
		flag=true;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start){
			Controller.GET().start();
		}else if(e.getSource()==exit){
			System.exit(0);
		}
		jf.requestFocusInWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==38||e.getKeyCode()==87){
			Controller.GET().top();
		}else if(e.getKeyCode()==40||e.getKeyCode()==83){
			Controller.GET().bottom();
		}else if(e.getKeyCode()==37||e.getKeyCode()==65){
			Controller.GET().left();
		}else if(e.getKeyCode()==39||e.getKeyCode()==68){
			Controller.GET().right();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
}
