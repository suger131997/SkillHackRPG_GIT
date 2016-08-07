package jp.naclo.skillhack;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import jp.naclo.skillhack.Sequence.RootSequence;

public class SkillHackRPG {					//https://github.com/suger131997/SkillHackRPG_GIT.git

	public static void main(String[] args) {
		SkillHackRPG shr = new SkillHackRPG();
		shr.start();
	}

	JFrame mainwindow;
	BufferStrategy strategy;
	BufferedImage backgraundImage;				//全体の背景
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();	//全画面のため
	RootSequence display;
	private int windowWidth, windowHeight;

	//コンストラクタ
	SkillHackRPG(){
		this.mainwindow = new JFrame("魔王の凱旋");
		this.mainwindow.setUndecorated(true);
		this.device.setFullScreenWindow(this.mainwindow);
		this.mainwindow.
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowWidth = mainwindow.getWidth();
		windowHeight = mainwindow.getHeight();
		/*this.mainwindow.setSize(800, 720);
		this.mainwindow.setLocationRelativeTo(null);
		this.mainwindow.setVisible(true);
		this.mainwindow.setResizable(false);*/
		//バッファストラテジー
		this.mainwindow.setIgnoreRepaint(true);
		this.mainwindow.createBufferStrategy(2);
		this.strategy = this.mainwindow.getBufferStrategy();
		//キーアダプター
		this.mainwindow.addKeyListener(new MyKeyAdapter());
		//背景画面の画像ロード
		URL Imgurl = getClass().getClassLoader().getResource("data/common/img/CommonBackgraund.png");
		try {
			backgraundImage = ImageIO.read(Imgurl);
		} catch (IOException e) {
			System.out.print("-------------------");
			e.printStackTrace();
		}
		//ゲーム開始処理
		display = new RootSequence(null);
	}

	void start(){
		Timer t = new Timer();
		t.schedule(new RenderTask(), 0, 16);
	}


	//boolean waiting_for_key;					//キー入力許可
	boolean[] keystate = new boolean[KEY_STATE.NUMBER];
	void render(){
		//時間計測
		long time = System.currentTimeMillis();
		display.sinfo.currenttime = time;

		/*//キー入力待ち 不採用？
		waiting_for_key = true;
		while(time - display.sinfo.currenttime < 1){
			time = System.currentTimeMillis();
		}
		waiting_for_key = false;*/

		//キーの入力反映
		for(int i = 0; i < KEY_STATE.NUMBER ;i++){	//0:現フレーム 1:前フレーム
			display.sinfo.keystate[1][i] = display.sinfo.keystate[0][i];
			display.sinfo.keystate[0][i] = this.keystate[i];
		}
		//ロード待ち
		boolean loading = true;
		while(time - display.sinfo.currenttime < display.sinfo.loadSecond && loading){	//一定時間経過もしくはロードするものがないとき終
			time = System.currentTimeMillis();
			loading = display.sinfo.myLoader.loadFile();
		}


		Graphics2D g = (Graphics2D)this.strategy.getDrawGraphics();
		/*g.setBackground(Color.black);
		g.clearRect(0, 0,
				this.mainwindow.getWidth(), this.mainwindow.getHeight());*/
		clearBackground(g);
		g.translate((windowWidth - 960) / 2, (windowHeight - 720) / 2);

		display.sinfo.g = g;

		this.display.show();
		/*for(int i = 0; i < KEY_STATE.NUMBER ;i++){	//0:現フレーム 1:前フレーム
			this.keystate[i] = false;
		}*/
		g.dispose();
		this.strategy.show();
	}
	//背景初期化用
	private void clearBackground(Graphics2D g){
		int i = 0, j = 0;
		int width, height;
		width = backgraundImage.getWidth();
		height = backgraundImage.getHeight();
		while(windowWidth >= i * width){
			while(windowHeight >= j * height){
				g.drawImage(backgraundImage, i * width, j * height, null);
				j++;
			}
			j = 0;
			i++;
		}
	}

	class RenderTask extends TimerTask{
		@Override
		public void run() {
			SkillHackRPG.this.render();
		}
	}

	class MyKeyAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			this.setValue(e.getKeyCode(), true);
		}

		@Override
		public void keyReleased(KeyEvent e) {
				this.setValue(e.getKeyCode(), false);
		}

		private void setValue(int keycode, boolean b){
			//boolean[] keystate = display.sinfo.keystate;
			switch(keycode){
			case KeyEvent.VK_LEFT:
				keystate[KEY_STATE.LEFT_A] = b;
				break;
			case KeyEvent.VK_A:
				keystate[KEY_STATE.LEFT_A] = b;
				break;
			case KeyEvent.VK_RIGHT:
				keystate[KEY_STATE.RIGHT_D] = b;
				break;
			case KeyEvent.VK_D:
				keystate[KEY_STATE.RIGHT_D] = b;
				break;
			case KeyEvent.VK_UP:
				keystate[KEY_STATE.UP_W] = b;
				break;
			case KeyEvent.VK_W:
				keystate[KEY_STATE.UP_W] = b;
				break;
			case KeyEvent.VK_DOWN:
				keystate[KEY_STATE.DOWN_S] = b;
				break;
			case KeyEvent.VK_S:
				keystate[KEY_STATE.DOWN_S] = b;
				break;
			case KeyEvent.VK_E:
				keystate[KEY_STATE.E_1] = b;
				break;
			case KeyEvent.VK_1:
				keystate[KEY_STATE.E_1] = b;
				break;
			case KeyEvent.VK_R:
				keystate[KEY_STATE.R_2] = b;
				break;
			case KeyEvent.VK_2:
				keystate[KEY_STATE.R_2] = b;
				break;
			case KeyEvent.VK_SPACE:
				keystate[KEY_STATE.SPACE] = b;
				break;
			case KeyEvent.VK_ESCAPE:
				keystate[KEY_STATE.ESC] = b;
				break;
			}
		}

	}
}
