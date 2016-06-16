package jp.naclo.skillhack;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import jp.naclo.skillhack.Sequence.RootSequence;

public class SkillHackRPG {

	public static void main(String[] args) {
		SkillHackRPG shr = new SkillHackRPG();
		shr.start();
	}

	JFrame mainwindow;
	BufferStrategy strategy;
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	RootSequence display;

	//コンストラクタ
	SkillHackRPG(){
		this.mainwindow = new JFrame("魔王の凱旋");
		this.mainwindow.setUndecorated(true);
		this.device.setFullScreenWindow(this.mainwindow);
		this.mainwindow.
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		//ゲーム開始処理
		display = new RootSequence(null);
	}

	void start(){
		Timer t = new Timer();
		t.schedule(new RenderTask(), 0, 16);
	}


	boolean waiting_for_key;					//キー入力許可
	void render(){
		//時間計測
		long time = System.currentTimeMillis();
		display.sinfo.currenttime = time;

		//キー入力待ち
		waiting_for_key = true;
		while(time - display.sinfo.currenttime < 1){
			time = System.currentTimeMillis();
		}
		waiting_for_key = false;

		//ロード待ち
		boolean loading = true;
		while(time - display.sinfo.currenttime < 6 && loading){	//一定時間経過もしくはロードするものがないとき終
			time = System.currentTimeMillis();
			loading = display.sinfo.myLoader.loadFile();
		}


		Graphics2D g = (Graphics2D)this.strategy.getDrawGraphics();
		g.setBackground(Color.black);

		g.clearRect(0, 0,
				this.mainwindow.getWidth(), this.mainwindow.getHeight());
		display.sinfo.g = g;

		this.display.show();
		g.dispose();
		this.strategy.show();
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
			if(waiting_for_key){
				this.setValue(e.getKeyCode(), true);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(waiting_for_key){
				this.setValue(e.getKeyCode(), false);
			}
		}

		private void setValue(int keycode, boolean b){
			boolean[] keystate = display.sinfo.keystate;
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
