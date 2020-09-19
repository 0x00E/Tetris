package game;

import java.util.Random;

public class Graphics {
	static int n1=0;
	static int n2=0;
	static int[][][][] g=
		{
			{
				{
					{1,1},{1,2},{1,3},{1,4}
				},
				{
					{1,1},{2,1},{3,1},{4,1}
				}
			},
			{
				{
					{2,1},{2,2},{2,3},{1,3}
				},
				{
					{1,1},{1,2},{2,2},{3,2}
				},
				{
					{1,1},{2,1},{1,2},{1,3}
				},
				{
					{1,1},{2,1},{3,1},{3,2}
				}
			},
			{
				{
					{1,1},{1,2},{1,3},{2,3}
				},
				{
					{1,1},{2,1},{3,1},{1,2}
				},
				{
					{1,1},{2,1},{2,2},{2,3}
				},
				{
					{3,1},{1,2},{2,2},{3,2}
				}
			},
			{
				{
					{2,1},{3,1},{1,2},{2,2}
				},
				{
					{1,1},{1,2},{2,2},{2,3}
				}
			},
			{
				{
					{1,1},{2,1},{2,2},{3,2}
				},
				{
					{2,1},{2,2},{1,2},{1,3}
				}
			},
			{
				{
					{1,1},{2,1},{1,2},{2,2}
				}
			},
			{
				{
					{1,1},{2,1},{3,1},{2,2}
				},
				{
					{1,1},{1,2},{1,3},{2,2}
				},
				{
					{2,1},{1,2},{2,2},{3,2}
				},
				{
					{2,1},{1,2},{2,2},{2,3}
				}
			}
		};
	
	
	private Graphics(){
		
	}
	public static int[][] GET(){
		
		Random r=new Random();
		try{
			n1=r.nextInt(g.length);
			n2=r.nextInt(g[n1].length);
			
			return g[n1][n2];
		}catch(Exception e){
			return Graphics.GET();
		}
	}
	public static int[][] REGET(){
		
		
		try{
			
			if(n2!=g[n1].length-1){
				n2++;
			}else{
				n2=0;
			}
			
			
			return g[n1][n2];
		}catch(Exception e){
			return Graphics.GET();
		}
	}
}
