package com.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;  

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon; 



public class FiveChessFrame extends JFrame{

	//获取屏幕宽度
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	//获取屏幕高度
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
/*	//背景图片
	BufferedImage bgimg = null;
	BufferedImage blackc = null;
	BufferedImage whitec = null;
	//存储所有棋子的位置，0为空，1为黑子，2为白子
	int[][] allchess = new int[15][15];
	int x = 0;
	int y = 0;
*/	
	
	
	
	public FiveChessFrame(){
		//设置标题
		this.setTitle("五子棋游戏");
		//设置窗体大小
		this.setSize(500, 550);
		//设置窗体位置
		this.setLocation((width-500)/2, (height-500)/2);
		//设置窗体大小不可变
		this.setResizable(false);
		//设置窗体的关闭方式为关闭程序自动结束
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//建立主界面panel，用于画棋盘和棋子
		majorpanel panel = new majorpanel();
		//加入主界面panel鼠标监听器
		this.addMouseListener(panel);
		//将主界面panel放入JFrame，位置正中
		getContentPane().add(panel, BorderLayout.CENTER);
		//建立button的panel，用于存放底端的3个button，设置背景图片。
		JPanel bpanel = new JPanel(){
			@Override  
            protected void paintComponent(Graphics g) {  
                ImageIcon icon = new ImageIcon("./jpg/background.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());  
            } 
		};
		//将底端bpanel放入JFrame，位置为下
		getContentPane().add(bpanel, BorderLayout.SOUTH);
		
		//建立3个按纽,将3个button添入bpanel
		JButton button1 = new JButton("人先手");
		JButton button2 = new JButton("机器先手");
		JButton button3 = new JButton("开始");
		JButton button4 = new JButton("重新开始");
 		bpanel.add(button1);
		bpanel.add(button2);
		bpanel.add(button3);
		bpanel.add(button4);
		button3.setEnabled(false);
		button4.setEnabled(false);
		//button1点击事件，设置isperson为true，并将button1置为不可用,button2为可用
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 button1.setEnabled(false);
					 button2.setEnabled(true);
					 button3.setEnabled(true);
					 panel.isperson = true;
			}
		});
		//button2点击事件，设置isperson为not，并将button2置为不可用,button1为可用
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 button2.setEnabled(false);
					 button1.setEnabled(true);
					 button3.setEnabled(true);
					 panel.isperson = false;
			}
		});
		
		//button3点击事件，并将button1和button2置为不可用,isbegin为true
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 button2.setEnabled(false);
					 button1.setEnabled(false);
					 button3.setEnabled(false);
					 button4.setEnabled(true);
					 panel.isbegin = true;
					 if(!panel.isperson)
					 {
						 panel.allchess[7][7]=1;
						 repaint();
					 }
			}
		});
		//button4点击事件，并将button1和button2置为可用,isbegin为false
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 button2.setEnabled(true);
					 button1.setEnabled(true);
					 button3.setEnabled(false);
					 button4.setEnabled(false);
					 panel.isbegin = false;
					 panel.allchess = new int[15][15];
					 repaint();
			}
		});
		
		//显示出窗体
		this.setVisible(true);
		
	}
	


	
	
	//判断是否胜利
		
}


class majorpanel extends JPanel implements MouseListener{
	//背景图片
		BufferedImage bgimg = null;
		BufferedImage blackc = null;
		BufferedImage whitec = null;
		//存储所有棋子的位置，0为空，1为黑子，2为白子
		int[][] allchess = new int[15][15];
		int x = 0;
		int y = 0;
		boolean isperson = true;//定义哪方先手，默认true为人先手，false为机器先手
		boolean isbegin = false;
		boolean isBlack = true;//是否可以下黑子

		public void paint(Graphics g){
			
			try {
				bgimg = ImageIO.read(new File("./jpg/background.jpg"));
				blackc = ImageIO.read(new File("./jpg/blackchess.png"));
				whitec = ImageIO.read(new File("./jpg/whitechess.png"));

			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		
			char bgl='A';
			g.drawImage(bgimg, 0, 0, this);
			//画横竖线
			for(int i=0;i<15;i++,bgl++)
			{
				g.drawLine(30, 20+30*i, 450, 20+30*i);
				g.drawLine(30+30*i, 20, 30+30*i, 440);
				g.drawString(""+bgl, 28+30*i, 15);
				g.drawString(""+(i+1), 15, 25+30*i);

			}
			//标注点位
			g.fillOval(236, 226, 8, 8);//8 H
			g.fillOval(117, 107, 6, 6);//4 D
			g.fillOval(357, 107, 6, 6);//4 L
			g.fillOval(117, 347, 6, 6);//12 D
			g.fillOval(357, 347, 6, 6);//12 L

			//画棋子
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					if(allchess[i][j] == 1)
					{
						int tempX= i*30+30;
						int tempY= j*30+20;
						g.drawImage(blackc, tempX-10, tempY-10, this);
						//g.fillOval(tempX-10, tempY-10, 20, 20);
					}
					else if(allchess[i][j] == 2)
					{
						int tempX= i*30+30;
						int tempY= j*30+20;
						g.drawImage(whitec, tempX-10, tempY-10, this);
					}
				}
			}

		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自动生成的方法存根
			if(isbegin == true)
			{
				x = e.getX();
				y = e.getY();
				
				if(x >= 30 && x <= 470 && y >= 40 && y <= 480)
				{
					x = (x-25)/30;
					y = (y-35)/30;
					//判断当前位置是否有棋子
					if(allchess[x][y] == 0)
					{
						if(isBlack == true)
						{
							if(isperson)
							{
								allchess[x][y] = 1;
								isBlack = false;
								
							}
							else
							{
								allchess[x][y] = 2;
								isBlack = false;
							}
						}
					}else{
						JOptionPane.showMessageDialog(this, "当前位置已经有棋子，请重新选择位置");
					}
					
					if(this.checkWin(x,y) == true)
					{
						JOptionPane.showMessageDialog(this,"游戏结束！"+(allchess[x][y]==1?"黑方胜出":"白方胜出"));
						isbegin = false;
					}
					else if(this.checkfull())
					{
						JOptionPane.showMessageDialog(this,"和棋，游戏结束！");
						isbegin = false;
					}
					else if(isBlack == false)
					{
						Algorithm();
						isBlack = true;					
					}
					this.repaint();	
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		
		//判断是否赢了
		private boolean checkWin(int x,int y){
			boolean flag = false;
			int count = 0;
			int color = allchess[x][y];
			
			//横向判断是否连成五子；
			for(int i=0;i<15;i++)
			{
				if(color == allchess[i][y])
				{
					count++;
					if(count == 5)
					{
						flag = true;
					}
				}else{
					count=0;
				}
			}
			
			//竖向判断是否连成五子；
			for(int i=0;i<15;i++)
			{
				if(color == allchess[x][i])
				{
					count++;
					if(count == 5)
					{
						flag = true;
					}
				}else{
					count=0;
				}
			}
			
			//斜向判断是否连成五子(左上、右下)；
			int i2=1;
			int count2=1;
			while((x+i2)<15 && (y+i2)<15 && (color == allchess[x+i2][y+i2])){
				count2++;
				i2++;
			}
			i2=1;
			while((x-i2)>=0 && (y-i2)>=0 && (color == allchess[x-i2][y-i2])){
				count2++;
				i2++;
			}
			if(count2>=5)
			{
				flag=true;
			}
			
			//斜向判断是否连成五子(左下、右上)；
			int i3=1;
			int count3=1;
			while((x+i3)<15 && (y-i3)>=0 && (color == allchess[x+i3][y-i3])){
				count3++;
				i3++;
			}
			
			i3=1;
			while((x-i3)>=0 && (y+i3)<15 && (color == allchess[x-i3][y+i3])){
				count3++;
				i3++;
			}
			
			if(count3>=5)
			{
				flag=true;
			}
		
			
			return flag;
		}

		//判断棋面上是否还有可下的子，以便判断是否和棋
		private boolean checkfull()
		{
			boolean full = false;
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					if(allchess[i][j]==0)
					{
						full = false;
					}
					else
					{
						full = true;
					}
				}
			}
			return full;
		}
	/*	
		//极小极大算法
		int val;
		step aaa = new step();	//记录最佳的步数
		step guji = new step();	//记录当前遍历的步数
		int best = Integer.MIN_VALUE;

		private int MinMax(int depth){
			if(depth <= 0)
			{
				return Score(guji.x[1],guji.y[1]);
			}
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++){
					if((allchess[i][j] == 0))
					{
						if(depth == 3)
						{
							guji.x[3-depth]=i;
							guji.y[3-depth]=j;
							val = MinMax(depth - 1);
							if(val >= best){
								best=val;
								aaa.x[3-depth]=i;
								aaa.y[3-depth]=j;
							}
						}
						//else if((depth == 2) && (guji.x[0]!=i) && (guji.y[0]!=j))
						if(depth == 2)	
						{
							guji.x[2-depth]=i;
							guji.y[2-depth]=j;
							val = MinMax(depth - 1);
							if(val >= best){
								best=val;
								aaa.x[2-depth]=i;
								aaa.y[2-depth]=j;
							}
						}
						//else if((depth == 1) && (guji.x[0]!=i) && (guji.y[0]!=j) && (guji.x[1]!=i) && (guji.y[1]!=j))
						else if((depth == 1) && (guji.x[0]!=i) && (guji.y[0]!=j))
						{
							guji.x[2-depth]=i;
							guji.y[2-depth]=j;
							val = MinMax(depth - 1);
							if(val >= best){
								best=val;
								aaa.x[2-depth]=i;
								aaa.y[2-depth]=j;
							}
						}
					}
				}
			}
			return best;
		}
	*/

			//白棋位置算法
		private void Algorithm(){
			
		
			int[][] AllScore = new int[15][15];
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					if(allchess[i][j]==0)
					{
						AllScore[i][j] = Score(i,j);
					}else{
						AllScore[i][j]=0;
					}
				}
			}
			int max = 0;
			int x = 0;
			int y = 0;
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					if(AllScore[i][j] > max)
					{
						max = AllScore[i][j];
						x = i;
						y = j;
					}
				}
			}
			if(isperson == true)
			{
				allchess[x][y] = 2;
			
			}
			else{
				allchess[x][y] = 1;

			}
			if(checkWin(x,y) == true)
			{
				JOptionPane.showMessageDialog(this,"游戏结束！"+(allchess[x][y]==1?"黑方胜出":"白方胜出"));
				isbegin = false;
			}
			else if(this.checkfull())
			{
				JOptionPane.showMessageDialog(this,"和棋，游戏结束！");
				isbegin = false;
			}


		}
		

		//评分算法，计算该点总分
		private int Score(int x,int y){
			int Score = 0; //当前点的所有五元组的分数之和
			int yn = 0;	//五元组敌方棋子（若人先手则为黑棋）个数
			int mn = 0;	//五元组己方棋子（若机器先手则为黑棋）个数
			int yc = 1;	//定义敌方棋子的颜色，1为黑色，2为白色
			int mc = 2;
			if(!isperson)//若机器先手
			{
				yc = 2;
				mc = 1;
			}
			
			//横向
			for(int i=0;i<5;i++)
			{
				yn=mn=0;
				for(int j=0;j<=i;j++)
				{
					if(PointInP(x-j, y))
					{
						if(allchess[x-j][y] == yc)
						{yn++;}
						else if(allchess[x-j][y] == mc)
						{mn++;}
					}
				}
				for(int j=0;j<5-i;j++)
				{
					if(PointInP(x+j, y))
					{
						if(allchess[x+j][y] == yc)
						{yn++;}
						else if(allchess[x+j][y] == mc)
						{mn++;}
					}
				}
				int tpScore = 0;
				tpScore = SingleArray(yn, mn);
				Score += tpScore;
			}
			
			//竖向
			for(int i=0;i<5;i++)
			{
				yn=mn=0;
				for(int j=0;j<=i;j++)
				{
					if(PointInP(x, y-j))
					{
						if(allchess[x][y-j] == yc)
						{yn++;}
						else if(allchess[x][y-j] == mc)
						{mn++;}
					}
				}
				for(int j=0;j<5-i;j++)
				{
					if(PointInP(x, y+j))
					{
						if(allchess[x][y+j] == yc)
						{yn++;}
						else if(allchess[x][y+j] == mc)
						{mn++;}
					}
				}
				int tpScore = 0;
				tpScore = SingleArray(yn, mn);
				Score += tpScore;
			}

			//斜上（左下，右上）
			for(int i=0;i<5;i++)
			{
				yn=mn=0;
				for(int j=0;j<=i;j++)
				{
					if(PointInP(x-j, y+j))
					{
						if(allchess[x-j][y+j] == yc)
						{yn++;}
						else if(allchess[x-j][y+j] == mc)
						{mn++;}
					}
				}
				for(int j=0;j<5-i;j++)
				{
					if(PointInP(x+j, y-j))
					{
						if(allchess[x+j][y-j] == yc)
						{yn++;}
						else if(allchess[x+j][y-j] == mc)
						{mn++;}
					}
				}
				int tpScore = 0;
				tpScore = SingleArray(yn, mn);
				Score += tpScore;
			}
			
			//斜下（左上，右下）
			
			for(int i=0;i<5;i++)
			{
				yn=mn=0;
				for(int j=0;j<=i;j++)
				{
					if(PointInP(x-j, y-j))
					{
						if(allchess[x-j][y-j] == yc)
						{yn++;}
						else if(allchess[x-j][y-j] == mc)
						{mn++;}
					}
				}
				for(int j=0;j<5-i;j++)
				{
					if(PointInP(x+j, y+j))
					{
						if(allchess[x+j][y+j] == yc)
						{yn++;}
						else if(allchess[x+j][y+j] == mc)
						{mn++;}
					}
				}
				int tpScore = 0;
				tpScore = SingleArray(yn, mn);
				Score += tpScore;
			}
			
			return Score;
			
		
		}
		
		//判断该点是否在棋盘上
		private boolean PointInP(int x,int y)
		{
			if(x>=0 && x<15 && y>=0 && y<15)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		//与评分表对比得到分数
		private int SingleArray(int yn,int mn)//yn代表敌方，mn代表己方
		{
			int temp = 0;
			if(yn == 0)
			{
				switch(mn)
				{
				case 0: temp = 7;break;
				case 1: temp = 35;break;
				case 2: temp = 800;break;
				case 3: temp = 15000;break;
				case 4: temp = 800000;break;
				}
			}else if(mn == 0)
			{
				switch(yn)
				{
				case 0: temp = 7;break;
				case 1: temp = 15;break;
				case 2: temp = 400;break;
				case 3: temp = 1800;break;
				case 4: temp = 100000;break;
				}
			}
			return temp;
			/*	    // tuple is empty
		    Blank,
		    // tuple contains a black chess
		    W,
		    // tuple contains two black chesses
		    WW,
		    // tuple contains three black chesses
		    WWW,
		    // tuple contains four black chesses
		    WWWW,
		    // tuple contains a white chess
		    B,
		    // tuple contains two white chesses
		    BB,
		    // tuple contains three white chesses
		    BBB,
		    // tuple contains four white chesses
		    BBBB,
		    // tuple does not exist
		    Virtual,
		    // tuple contains at least one black and at least one white
		    Polluted

		    
		    tupleScoreTable[0] = 7;
		    tupleScoreTable[1] = 35;
		    tupleScoreTable[2] = 800;
		    tupleScoreTable[3] = 15000;
		    tupleScoreTable[4] = 800000;
		    tupleScoreTable[5] = 15;
		    tupleScoreTable[6] = 400;
		    tupleScoreTable[7] = 1800;
		    tupleScoreTable[8] = 100000;
		    tupleScoreTable[9] = 0;
		    tupleScoreTable[10] = 0;

			*/	
		}


}
