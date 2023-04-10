package misc;

import java.util.ArrayList;
import java.util.Comparator;

public class main_misc2 {
	
	//private static HashMap<Integer,Integer> cache = new HashMap<>();
	public static void main(String[] args) {
		//(a+b)%k = ((a % k)+(b % k)) % k
		
		int n = 5;
		int x = 4;
		int y = 3;
		int k = 3;
		int[][]v = {{5,5},{4,2},{2,3}};
		System.out.println(queens_attack(n,k,x,y,v));
		//System.out.println(total_moves(n,x,y));
		
		
	
		
	
	
	
	}
	private static int mod(int g) {
		if(g < 0) {
			return -g;
		}
		return g;
	}
	private static int define_type(int[]v,int x,int y) {
		//type 1
		if(v[1] == y) {
			return 1;
		}
		
		//type 2
		if(v[0] == x) {
			return 2;
		}
		
		//type 3
		if(x == y && v[0] == v[1]) {
			return 3;
		}
		if(x >= y+1 && v[0] >= v[1]+1) {
			return 3;
		}
		if(y >= x+1 && v[1] >= v[0]+1) {
			return 3;
		}
			
    	//type 0
		return 0;
	}
	
	private static int type_1(int[]v,int x,int n) {
		//
		if(v[0] < x) {
			return -v[0];
		}
		return n-v[0]+1;
	}
	private static int type_2(int[]v,int y,int n) {
		if(v[1] < y) {
			return -v[1];
		}
		return n-v[1]+1;
	}
	private static int type_3(int[]v,int x,int y,int n) {
		
		if(x == y) {
			if(v[0] < x) {
				//v[0] or v[1]
				return -v[0];
			}
			return  n-v[0]+1;
		}
		if(x >= y +1) {
			if(v[1] < y) {
				return -v[1];
			}
			return n-v[1]+1;
		}
		if(y >= x+1) {
			if(v[0] < x) {
				return -v[0];
			}
			return n-v[0]+1;
		}
		return 0;
		
		
	}
			
	private static int  total_moves(int n,int y,int x) {
		//
		int x_y = 2*(n-1);
		/* diag_1 = 0;
		if(x >=n/2 || y >= n/2) {
			diag_1 = (n-x) + (n-y);
		
		}
		if(x < n/2 || y < n/2) {
			diag_1 = x+y-2;
		}
		
		int diag_2 = 0;
		if(x >= y) {
			diag_2 = x + (n-y)-y;
			
		}

		if(x < y){
			diag_2 = n-(n-x)+y;
		}
		return x_y + diag_1 + diag_2;*/
		
		/*Diagonal 1*/
		int d1 = 0;
		int a = x;
		int b = y;
		for(int i = x;i < n;i++){
			if(a+1 > n || b+1 > n) {
				break;
			}
			a++;
			b++;
			d1++;
		}
		//System.out.println(d1);
		
		/*Diagonal 2*/
		int d2 = 0;
		a = x;
		b = y;
		for(int i = x;i >= 1;i--){
			b++;
			if(b == n) {
				d2++;
				break;
			}
			d2++;
		}
		//System.out.println(d2);
		
		/*Diagonal 3*/
		int d3 = 0;
		a = x;
		b = y;
		for(int i = x;i <= n;i++){
			b--;
			a++;
			if(b == 1 || a == n) {
				d3++;
				break;
			}
			d3++;
		}
		//System.out.println(d3);
		
		/*Diagonal 4*/
		int d4 = 0;
		a = x;
		b = y;
		for(int i = x;i <= n;i++){
			b--;
			a--;
			if(b == 1 || a == 1) {
				d4++;
				break;
			}
			d4++;
		}
		//System.out.println(d4);
		
		return x_y + d1 +d2+d3+d4;
		
	}
	
	private static int queens_attack(int n,int k,int x,int y,int[][]v) {
		//n:length of the board sides
		//k: number of obstacles
		//x:row position
		//y:columns position
		//v: obstacles coordinates
		
		ArrayList<Integer> one = new ArrayList<>();
		ArrayList<Integer> two = new ArrayList<>();
		ArrayList<Integer> three = new ArrayList<>();
		
		for(int i = 0;i < v.length;i++) {
			int type = define_type(v[i],x,y);
			if(type == 1) {
				one.add(type_1(v[i],x,n));
			}
			if(type == 2) {
				two.add(type_2(v[i],y,n));
			}
			if(type == 3) {
				three.add(type_3(v[i],x,y,n));
			}
		}
		
		one.sort(Comparator.naturalOrder());
		two.sort(Comparator.naturalOrder());
		three.sort(Comparator.naturalOrder());
		
		int blocked = 0;
		
		if(one.get(one.size()-1) >= 0) {
			blocked += mod(one.get(one.size()-1));
		}
		if(one.get(0) <= 0) {
			blocked += mod(one.get(0));
		}
		
		if(two.get(two.size()-1) >= 0) {
			blocked += mod(two.get(two.size()-1));
		}
		if(two.get(0) <= 0) {
			blocked += mod(two.get(0));
		}
		
		if(!three.isEmpty() && three.get(three.size()-1) >= 0) {
			blocked += mod(three.get(three.size()-1));
		}
		if(!three.isEmpty() && three.get(0) <= 0) {
			blocked += mod(three.get(0));
		}
		
		
		return total_moves(n,x,y) - blocked;
	}
	
	
	
	
	}

	
		

	
	
	

	
