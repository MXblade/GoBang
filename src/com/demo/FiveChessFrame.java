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

	//��ȡ��Ļ���
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	//��ȡ��Ļ�߶�
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
/*	//����ͼƬ
	BufferedImage bgimg = null;
	BufferedImage blackc = null;
	BufferedImage whitec = null;
	//�洢�������ӵ�λ�ã�0Ϊ�գ�1Ϊ���ӣ�2Ϊ����
	int[][] allchess = new int[15][15];
	int x = 0;
	int y = 0;
*/	
	
	
	
	public FiveChessFrame(){
		//���ñ���
		this.setTitle("��������Ϸ");
		//���ô����С
		this.setSize(500, 550);
		//���ô���λ��
		this.setLocation((width-500)/2, (height-500)/2);
		//���ô����С���ɱ�
		this.setResizable(false);
		//���ô���Ĺرշ�ʽΪ�رճ����Զ�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����������panel�����ڻ����̺�����
		majorpanel panel = new majorpanel();
		//����������panel��������
		this.addMouseListener(panel);
		//��������panel����JFrame��λ������
		getContentPane().add(panel, BorderLayout.CENTER);
		//����button��panel�����ڴ�ŵ׶˵�3��button�����ñ���ͼƬ��
		JPanel bpanel = new JPanel(){
			@Override  
            protected void paintComponent(Graphics g) {  
                ImageIcon icon = new ImageIcon("./jpg/background.jpg");  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());  
            } 
		};
		//���׶�bpanel����JFrame��λ��Ϊ��
		getContentPane().add(bpanel, BorderLayout.SOUTH);
		
		//����3����Ŧ,��3��button����bpanel
		JButton button1 = new JButton("������");
		JButton button2 = new JButton("��������");
		JButton button3 = new JButton("��ʼ");
		JButton button4 = new JButton("���¿�ʼ");
 		bpanel.add(button1);
		bpanel.add(button2);
		bpanel.add(button3);
		bpanel.add(button4);
		button3.setEnabled(false);
		button4.setEnabled(false);
		//button1����¼�������ispersonΪtrue������button1��Ϊ������,button2Ϊ����
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 button1.setEnabled(false);
					 button2.setEnabled(true);
					 button3.setEnabled(true);
					 panel.isperson = true;
			}
		});
		//button2����¼�������ispersonΪnot������button2��Ϊ������,button1Ϊ����
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 button2.setEnabled(false);
					 button1.setEnabled(true);
					 button3.setEnabled(true);
					 panel.isperson = false;
			}
		});
		
		//button3����¼�������button1��button2��Ϊ������,isbeginΪtrue
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
		//button4����¼�������button1��button2��Ϊ����,isbeginΪfalse
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
		
		//��ʾ������
		this.setVisible(true);
		
	}
	


	
	
	//�ж��Ƿ�ʤ��
		
}


class majorpanel extends JPanel implements MouseListener{
	//����ͼƬ
		BufferedImage bgimg = null;
		BufferedImage blackc = null;
		BufferedImage whitec = null;
		//�洢�������ӵ�λ�ã�0Ϊ�գ�1Ϊ���ӣ�2Ϊ����
		int[][] allchess = new int[15][15];
		int x = 0;
		int y = 0;
		boolean isperson = true;//�����ķ����֣�Ĭ��trueΪ�����֣�falseΪ��������
		boolean isbegin = false;
		boolean isBlack = true;//�Ƿ�����º���

		public void paint(Graphics g){
			
			try {
				bgimg = ImageIO.read(new File("./jpg/background.jpg"));
				blackc = ImageIO.read(new File("./jpg/blackchess.png"));
				whitec = ImageIO.read(new File("./jpg/whitechess.png"));

			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		
			char bgl='A';
			g.drawImage(bgimg, 0, 0, this);
			//��������
			for(int i=0;i<15;i++,bgl++)
			{
				g.drawLine(30, 20+30*i, 450, 20+30*i);
				g.drawLine(30+30*i, 20, 30+30*i, 440);
				g.drawString(""+bgl, 28+30*i, 15);
				g.drawString(""+(i+1), 15, 25+30*i);

			}
			//��ע��λ
			g.fillOval(236, 226, 8, 8);//8 H
			g.fillOval(117, 107, 6, 6);//4 D
			g.fillOval(357, 107, 6, 6);//4 L
			g.fillOval(117, 347, 6, 6);//12 D
			g.fillOval(357, 347, 6, 6);//12 L

			//������
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
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO �Զ����ɵķ������
			if(isbegin == true)
			{
				x = e.getX();
				y = e.getY();
				
				if(x >= 30 && x <= 470 && y >= 40 && y <= 480)
				{
					x = (x-25)/30;
					y = (y-35)/30;
					//�жϵ�ǰλ���Ƿ�������
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
						JOptionPane.showMessageDialog(this, "��ǰλ���Ѿ������ӣ�������ѡ��λ��");
					}
					
					if(this.checkWin(x,y) == true)
					{
						JOptionPane.showMessageDialog(this,"��Ϸ������"+(allchess[x][y]==1?"�ڷ�ʤ��":"�׷�ʤ��"));
						isbegin = false;
					}
					else if(this.checkfull())
					{
						JOptionPane.showMessageDialog(this,"���壬��Ϸ������");
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
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO �Զ����ɵķ������
			
		}
		
		//�ж��Ƿ�Ӯ��
		private boolean checkWin(int x,int y){
			boolean flag = false;
			int count = 0;
			int color = allchess[x][y];
			
			//�����ж��Ƿ��������ӣ�
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
			
			//�����ж��Ƿ��������ӣ�
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
			
			//б���ж��Ƿ���������(���ϡ�����)��
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
			
			//б���ж��Ƿ���������(���¡�����)��
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

		//�ж��������Ƿ��п��µ��ӣ��Ա��ж��Ƿ����
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
		//��С�����㷨
		int val;
		step aaa = new step();	//��¼��ѵĲ���
		step guji = new step();	//��¼��ǰ�����Ĳ���
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

			//����λ���㷨
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
				JOptionPane.showMessageDialog(this,"��Ϸ������"+(allchess[x][y]==1?"�ڷ�ʤ��":"�׷�ʤ��"));
				isbegin = false;
			}
			else if(this.checkfull())
			{
				JOptionPane.showMessageDialog(this,"���壬��Ϸ������");
				isbegin = false;
			}


		}
		

		//�����㷨������õ��ܷ�
		private int Score(int x,int y){
			int Score = 0; //��ǰ���������Ԫ��ķ���֮��
			int yn = 0;	//��Ԫ��з����ӣ�����������Ϊ���壩����
			int mn = 0;	//��Ԫ�鼺�����ӣ�������������Ϊ���壩����
			int yc = 1;	//����з����ӵ���ɫ��1Ϊ��ɫ��2Ϊ��ɫ
			int mc = 2;
			if(!isperson)//����������
			{
				yc = 2;
				mc = 1;
			}
			
			//����
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
			
			//����
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

			//б�ϣ����£����ϣ�
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
			
			//б�£����ϣ����£�
			
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
		
		//�жϸõ��Ƿ���������
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

		//�����ֱ�Աȵõ�����
		private int SingleArray(int yn,int mn)//yn����з���mn������
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
