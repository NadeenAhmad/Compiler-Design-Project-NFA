import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


public class NFA {
	public static String sortString(String inputString) {
		// convert input string to char array
		char tempArray[] = inputString.toCharArray();

		// sort tempArray
		Arrays.sort(tempArray);

		// return new sorted string
		return new String(tempArray);
	}

	public static String zeroTrans(String state, ArrayList<String> zeroTrans2,
			ArrayList<String> epsilonTrans2) {
		// System.out.println("State " + state);
		String zero = "";
		for (int x = 0; x < state.length(); x++) {
			for (int j = 0; j < zeroTrans2.size(); j++) {
				if (zeroTrans2.get(j).charAt(0) == state.charAt(x)
						&& !zero.contains("" + zeroTrans2.get(j).charAt(2))) {

					zero += "" + zeroTrans2.get(j).charAt(2) + "";
				}
			}
		}

		
	
//		for (int x = 0; x < state.length(); x++) {
//			for (int y = 0; y < epsilonTrans2.size(); y++) {
//				if (epsilonTrans2.get(y).charAt(0) == state.charAt(x)
//						&& !zero.contains("" + epsilonTrans2.get(y).charAt(2))) {
//					zero += "" + epsilonTrans2.get(y).charAt(2) + "";
//				}
//			}
//
//		}
//		
//		
		
		for (int x = 0; x < zero.length(); x++) {
			for (int y = 0; y < epsilonTrans2.size(); y++) {
				if (epsilonTrans2.get(y).charAt(0) == zero.charAt(x)
						&& !zero.contains("" + epsilonTrans2.get(y).charAt(2))) {
					zero += "" + epsilonTrans2.get(y).charAt(2) + "";
				}
			}

		}

		
		
		
		
		zero = sortString(zero);
		if (zero.isEmpty()) {
			zero = "d";
		}
		return zero;
	}

	public static String oneTrans(String state, ArrayList<String> oneTrans2,
			ArrayList<String> epsilonTrans2) {

		String one = "";
		for (int x = 0; x < state.length(); x++) {
			for (int j = 0; j < oneTrans2.size(); j++) {
				if (oneTrans2.get(j).charAt(0) == state.charAt(x)
						&& !one.contains("" + oneTrans2.get(j).charAt(2))) {

					one += "" + oneTrans2.get(j).charAt(2) + "";
				}
			}
		}


		
		
		
		for (int x = 0; x < state.length(); x++) {
			for (int y = 0; y < epsilonTrans2.size(); y++) {
				if (epsilonTrans2.get(y).charAt(0) == state.charAt(x)
						&& !one.contains("" + epsilonTrans2.get(y).charAt(2))) {
					one += "" + epsilonTrans2.get(y).charAt(2) + "";
				}
			}

		}
		
		
		
		
		for (int x = 0; x < one.length(); x++) {
			for (int y = 0; y < epsilonTrans2.size(); y++) {
				if (epsilonTrans2.get(y).charAt(0) == one.charAt(x)
						&& !one.contains("" + epsilonTrans2.get(y).charAt(2))) {
					one += "" + epsilonTrans2.get(y).charAt(2) + "";
				}
			}

		}
		one = sortString(one);
		if (one.isEmpty()) {
			one = "d";
		}
		return one;
	}

	public static void toDFA(String nfa, String test) {
		// Finding Accept States
		int hashes = 0;
		int acceptIndex = 0;

		for (int i = 0; i < nfa.length() - 1; i++) {
			if (nfa.charAt(i) == '#') {
				hashes++;
			}
			if (hashes == 3) {
				acceptIndex = i;
				break;
			}
		}
		String AcceptStates = nfa.substring(acceptIndex + 1);
		//System.out.println("Accept States");

		ArrayList<String> AcceptStates2 = new ArrayList<>(
				Arrays.asList(AcceptStates.split(",")));
		//System.out.println(AcceptStates2);
		// Saving zero transitions
		int hashes2 = 0;
		int zeroIndex = 0;

		for (int i = 0; i < nfa.length() - 1; i++) {
			if (nfa.charAt(i) == '#') {
				hashes2++;

			}
			if (hashes2 == 1) {
				zeroIndex = i;
				break;
			}
		}
		String zeroTrans = nfa.substring(0, zeroIndex);
		//System.out.println("zero Trans");
		ArrayList<String> zeroTrans2 = new ArrayList<>(Arrays.asList(zeroTrans
				.split(";")));
		//System.out.println(zeroTrans2);
		// Saving one transitions
		int hashes3 = 0;
		int oneIndex = 0;

		for (int i = 0; i < nfa.length() - 1; i++) {
			if (nfa.charAt(i) == '#') {
				hashes3++;

			}
			if (hashes3 == 2) {
				oneIndex = i;
				break;
			}
		}
		String oneTrans = nfa.substring(zeroIndex + 1, oneIndex);
	//	System.out.println("one Trans");

		ArrayList<String> oneTrans2 = new ArrayList<>(Arrays.asList(oneTrans
				.split(";")));
		//System.out.println(oneTrans2);
		// Saving epsilon transitions
		int hashes4 = 0;
		int epsilonIndex = 0;

		for (int i = 0; i < nfa.length() - 1; i++) {
			if (nfa.charAt(i) == '#') {
				hashes4++;

			}
			if (hashes4 == 3) {
				epsilonIndex = i;
				break;
			}
		}
		String epsilonTrans = nfa.substring(oneIndex + 1, epsilonIndex);
	//	System.out.println("epsilon Trans");

		ArrayList<String> epsilonTrans2 = new ArrayList<>(
				Arrays.asList(epsilonTrans.split(";")));
//		System.out.println(epsilonTrans2);

		boolean check1 = false;
		boolean check2 = false;
		ArrayList<String> states = new ArrayList<String>();
		String tempState = "0";
		states.add(tempState);
		while (!check1 && !check2) {
			for (int i = 0; i < states.size(); i++) {
				//if(!states.get(i).contains("d")){
						String zero = zeroTrans(states.get(i), zeroTrans2,
						epsilonTrans2);
			
				if (!states.contains(zero)) {
					check1 = false;
					states.add(zero);
				} else {
					check1 = true;
				}
				String one = oneTrans(states.get(i), oneTrans2, epsilonTrans2);
				
				if (!states.contains(one)) {
					check2 = false;
					states.add(one);
				} else {
					check2 = true;
				}
				//}
			}
		}
//		System.out.println("states");
//		System.out.println(states);

		ArrayList<String> zeros = new ArrayList<String>();
		ArrayList<String> ones = new ArrayList<String>();
		for (int n = 0; n < states.size(); n++) {
			//if(!states.get(n).contains("d")){
				
			zeros.add(zeroTrans(states.get(n), zeroTrans2, epsilonTrans2));
			ones.add(oneTrans(states.get(n), oneTrans2, epsilonTrans2));
			//}
			}
//		System.out.println("zeros");
//		System.out.println(zeros);
//		System.out.println("ones");
//		System.out.println(ones);
//
		ArrayList<String> newAcceptStates = new ArrayList<String>();
		for (int i = 0; i < AcceptStates2.size(); i++) {
			for (int j = 0; j < states.size(); j++) {
				if (states.get(j).contains("" + AcceptStates2.get(i))) {
					newAcceptStates.add(states.get(j));
				}
			}
		}

//		System.out.println("accept");
//		System.out.println(newAcceptStates);
//		
		
		String dfa = "";
		for (int q = 0; q < states.size(); q++) {
			//if(!states.get(q).contains("d")){
				
			if (q == states.size() - 1) {
				dfa += "" + states.get(q) + "," + zeros.get(q) + ","
						+ ones.get(q) + "#";

			} else
				dfa += "" + states.get(q) + "," + zeros.get(q) + ","
						+ ones.get(q) + ";";
			//}
			}
		for (int u = 0; u < newAcceptStates.size(); u++) {
			if (u == newAcceptStates.size() - 1) {
				dfa += "" + newAcceptStates.get(u);

			} else
				dfa += "" + newAcceptStates.get(u) + ",";
		}
		System.out.println(dfa);
		
		
		
		
		
		
		//test string
		
		String currentState = "0";
		
		for (int i = 0 ; i < test.length() ; i++){
			if(test.charAt(i) == '0'){
			  String tmp = zeros.get(states.indexOf(currentState));
			 // System.out.println("nadoo"+ currentState);
			  currentState = tmp;
			}
			else if (test.charAt(i) == '1'){

				  String tmp = ones.get(states.indexOf(currentState));
					//System.out.println("nadoo"+ currentState);	  
				  currentState = tmp;
				  }
		
		}
		boolean contains = false;
		for (String c : newAcceptStates) {
		    if (c.equals(currentState)) {
		        contains = true;
		        break;
		    }
		}
		System.out.println(contains);

		
		
		
		
	}

	public static void main(String[] args) {
	toDFA("0,0;0,1;1,3#0,1;1,2;2,3#1,2;3,2#3", "000111");
	toDFA("0,0;0,1;1,3#0,1;1,2;2,3#1,2;3,2#3", "10110");
	toDFA("0,0;0,1;1,3#0,1;1,2;2,3#1,2;3,2#3", "00110");
	toDFA("0,0;0,1;1,3#0,1;1,2;2,3#1,2;3,2#3", "101010");
	toDFA("0,0;0,1;1,3#0,1;1,2;2,3#1,2;3,2#3", "1111");
	
	
	
	

	toDFA("0,1;1,2;2,3#1,3;3,4;4,2#0,2;3,1;2,4#2", "0000");
	toDFA("0,1;1,2;2,3#1,3;3,4;4,2#0,2;3,1;2,4#2", "011011");
	toDFA("0,1;1,2;2,3#1,3;3,4;4,2#0,2;3,1;2,4#2", "01010");
	toDFA("0,1;1,2;2,3#1,3;3,4;4,2#0,2;3,1;2,4#2", "101010");
	toDFA("0,1;1,2;2,3#1,3;3,4;4,2#0,2;3,1;2,4#2", "11100");
	
	
	toDFA("0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3", "11100");


	}
}
