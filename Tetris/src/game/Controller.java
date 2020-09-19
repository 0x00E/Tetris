package game;

public class Controller {
	private static Controller self = null;
	private static Thread thread;
	boolean noOver = true;

	Display display = new Display();

	int[][] map = new int[20][10];
	int[][] g = new int[4][2];
	int gy = -3;
	int gx = 3;

	int fraction = 0;

	private Controller() {

	}

	public static Controller GET() {
		if (self == null) {
			self = new Controller();
		}
		return self;
	}

	@SuppressWarnings("deprecation")
	public void clear() {
		map = new int[20][10];
		g = new int[4][2];
		gy = -3;
		gx = 3;
		thread.stop();
		display.setView(null);
		
	}

	public void start() {
		if (Display.getFlag()) {
			clear();
		}
		display.start();
		this.map = new int[20][10];
		display.setView(this.map);
		display.setFraction(0);

		this.g = Graphics.GET();
		int[][] gm = new int[4][2];
		int[][] tmpmap = new int[20][10];

		for (int i = 0; i < 4; i++) {
			gm[i][0] = this.g[i][0] + this.gy;
			gm[i][1] = this.g[i][1] + this.gx;
		}

		for (int i = 0; i < tmpmap.length; i++) {
			for (int j = 0; j < tmpmap[i].length; j++) {
				tmpmap[i][j] = this.map[i][j];
			}
		}
		for (int i = 0; i < 4; i++) {
			if (gm[i][0] >= 0 && gm[i][0] < 20 && gm[i][1] >= 0 && gm[i][1] < 20) {
				tmpmap[gm[i][0]][gm[i][1]] = 1;
			}

		}
		display.setView(tmpmap);
		thread = new Thread() {
			public void run() {

				while (true) {
					try {
						int in = 500 - fraction * 5;
						if (in < 10) {
							in = 10;
						}
						Thread.sleep(in);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					Controller.GET().bottom();
				}
			}
		};
		thread.start();

	}

	public void bottom() {

		boolean t = true;

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (map[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						if (i == this.g[k][0] + this.gy + 1 && j == this.g[k][1] + this.gx) {
							t = false;
						}
					}
				}
			}
		}
		int s = 0;
		for (int k = 0; k < 4; k++) {
			if (this.g[k][0] + this.gy >= s) {
				s = this.g[k][0] + this.gy + 1;
			}
		}
		if (s >= 20) {
			t = false;
		}

		if (t) {
			this.gy++;
		} else {
			for (int i = 0; i < 4; i++) {
				if (this.g[i][0] + this.gy >= 0 && this.g[i][0] + this.gy < 20 && this.g[i][1] + this.gx >= 0
						&& this.g[i][1] + this.gx < 10) {
					this.map[this.g[i][0] + this.gy][this.g[i][1] + this.gx] = 1;
				}
			}
			this.g = new int[4][2];
			int[][] tmpg = Graphics.GET();
			for (int i = 0; i < 4; i++) {
				this.g[i][0] = tmpg[i][0];
				this.g[i][1] = tmpg[i][1];
			}
			this.gy = -3;
			this.gx = 3;
			this.check();
		}

		int[][] gm = new int[4][2];
		int[][] tmpmap = new int[20][10];

		for (int i = 0; i < 4; i++) {
			gm[i][0] = this.g[i][0] + this.gy;
			gm[i][1] = this.g[i][1] + this.gx;

		}

		for (int i = 0; i < tmpmap.length; i++) {
			for (int j = 0; j < tmpmap[i].length; j++) {
				tmpmap[i][j] = this.map[i][j];
			}
		}
		for (int i = 0; i < 4; i++) {
			if (gm[i][0] >= 0 && gm[i][0] < 20 && gm[i][1] >= 0 && gm[i][1] < 10) {
				tmpmap[gm[i][0]][gm[i][1]] = 1;
			}
		}
		display.setView(tmpmap);

	}

	public void left() {

		boolean t = true;

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (map[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						if (i == this.g[k][0] + this.gy && j == this.g[k][1] + this.gx - 1) {
							t = false;
						}
					}
				}
			}
		}
		for (int k = 0; k < 4; k++) {
			if (this.g[k][0] + this.gx < 1) {
				t = false;
			}
		}

		if (t) {
			this.gx--;
		}

		int[][] gm = new int[4][2];
		int[][] tmpmap = new int[20][10];

		for (int i = 0; i < 4; i++) {
			gm[i][0] = this.g[i][0] + this.gy;
			gm[i][1] = this.g[i][1] + this.gx;

		}

		for (int i = 0; i < tmpmap.length; i++) {
			for (int j = 0; j < tmpmap[i].length; j++) {
				tmpmap[i][j] = this.map[i][j];
			}
		}
		for (int i = 0; i < 4; i++) {
			if (gm[i][0] >= 0 && gm[i][0] < 20 && gm[i][1] >= 0 && gm[i][1] < 10) {
				tmpmap[gm[i][0]][gm[i][1]] = 1;
			}
		}
		display.setView(tmpmap);
	}

	public void right() {

		boolean t = true;

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (map[i][j] == 1) {
					for (int k = 0; k < 4; k++) {

						if (i == this.g[k][0] + this.gy && j == this.g[k][1] + this.gx + 1) {
							t = false;
						}
					}
				}
			}
		}
		int s = 0;
		for (int k = 0; k < 4; k++) {
			if (this.g[k][1] + this.gx >= s) {
				s = this.g[k][1] + this.gx + 1;

			}
		}
		if (s >= 10) {
			t = false;
		}

		if (t) {
			this.gx++;
		}

		int[][] gm = new int[4][2];
		int[][] tmpmap = new int[20][10];

		for (int i = 0; i < 4; i++) {
			gm[i][0] = this.g[i][0] + this.gy;
			gm[i][1] = this.g[i][1] + this.gx;

		}

		for (int i = 0; i < tmpmap.length; i++) {
			for (int j = 0; j < tmpmap[i].length; j++) {
				tmpmap[i][j] = this.map[i][j];
			}
		}
		for (int i = 0; i < 4; i++) {
			if (gm[i][0] >= 0 && gm[i][0] < 20 && gm[i][1] >= 0 && gm[i][1] < 10) {
				tmpmap[gm[i][0]][gm[i][1]] = 1;
			}

		}
		display.setView(tmpmap);
	}

	public void top() {
		this.g = new int[4][2];
		int[][] tmpg = Graphics.REGET();

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (map[i][j] == 1) {
					for (int k = 0; k < 4; k++) {

						if (i == tmpg[k][0] + this.gy && j == tmpg[k][1] + this.gx + 1) {
							this.gx--;
						}
					}
				}
			}
		}
		int s = 0;
		for (int k = 0; k < 4; k++) {
			if (this.g[k][1] + this.gx >= s) {
				s = this.g[k][1] + this.gx + 1;

			}
		}
		if (s >= 7) {
			this.gx -= (s - 6);

		}

		for (int i = 0; i < 4; i++) {
			this.g[i][0] = tmpg[i][0];
			this.g[i][1] = tmpg[i][1];
		}

		int[][] gm = new int[4][2];
		int[][] tmpmap = new int[20][10];

		for (int i = 0; i < 4; i++) {
			gm[i][0] = this.g[i][0] + this.gy;
			gm[i][1] = this.g[i][1] + this.gx;

		}

		for (int i = 0; i < tmpmap.length; i++) {
			for (int j = 0; j < tmpmap[i].length; j++) {
				tmpmap[i][j] = this.map[i][j];
			}
		}
		for (int i = 0; i < 4; i++) {
			if (gm[i][0] >= 0 && gm[i][0] < 20 && gm[i][1] >= 0 && gm[i][1] < 10) {
				tmpmap[gm[i][0]][gm[i][1]] = 1;
			}

		}
		display.setView(tmpmap);

	}

	public void check() {
		int nf = 0;
		for (int i = 0; i < this.map.length; i++) {
			int s = 0;
			for (int j = 0; j < this.map[this.map.length - i - 1].length; j++) {
				s += this.map[this.map.length - i - 1][j];
			}
			if (s == 10) {
				for (int j = 0; j < this.map[this.map.length - i - 1].length; j++) {
					this.map[this.map.length - i - 1][j] = 0;
				}
				nf++;
			}
			s = 0;
		}
		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < this.map.length; i++) {
				int s = 0;
				for (int j = 0; j < this.map[this.map.length - i - 1].length; j++) {
					s += this.map[this.map.length - i - 1][j];
				}
				if (s == 0 && i < 19) {
					for (int j = 0; j < this.map[this.map.length - i - 1].length; j++) {
						this.map[this.map.length - i - 1][j] = this.map[this.map.length - i - 2][j];
						this.map[this.map.length - i - 2][j] = 0;
					}
				}
				s = 0;
			}
		}
		switch (nf) {
		case 1:
			this.fraction += 1;
			break;
		case 2:
			this.fraction += 3;
			break;
		case 3:
			this.fraction += 5;
			break;
		case 4:
			this.fraction += 7;
			break;
		}
		this.display.setFraction(this.fraction);

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (map[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						if (i == this.g[k][0] + this.gy + 1 && j == this.g[k][1] + this.gx) {
							this.noOver = false;
							display.over();
						}
					}
				}
			}
		}

		this.display.setView(this.map);
	}

	public static void main(String[] args) {
		Controller.GET();
	}
}
