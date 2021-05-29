/**
 * This class takes a standard infix equation string 
 * and converts it to postfix so that the computer can 
 * more easily and accurately comppute the equation 
 * through the use of stacks
 * @author Myles Cagle
 * @date March 13, 2020
 */

import java.util.EmptyStackException;
import java.util.Stack;

public class Infix {
	private String infix;

	public Infix(String in) {
		infix = clean(in);
	}
	
	private String clean(String in) {
		char[] ops = "+-*/()[]^".toCharArray();

		for (char c : ops) {
			in = in.replace("" + c, " " + c + " ");
		}

		in = in.replaceAll("\\s+"," "); // replace all white spaces with a single space
		in = in.trim();
		
		return in;
	}
	
	private boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	
	public String toString() {
		return infix;
	}
	
	private boolean parenBalanced() {
		Stack<Character> parenthesis = new Stack<Character>();
		char[] equation = infix.toCharArray();
		
		try {
		for(char c : equation) {
			if(c == '(') parenthesis.push(c);
			if(c == ')' && parenthesis.peek() == '(') parenthesis.pop();
			if(c == '[') parenthesis.push(c);
			if(c == ']' && parenthesis.peek() == '[') parenthesis.pop();
		} } catch (EmptyStackException e) {
			return false;
		}
		
		
		return parenthesis.isEmpty();
	}
	
	public String toPostFix() {
		Stack<String> operatorStack = new Stack<String>();
		String postFix = "";
		String[] equation = infix.split(" ");
		
		if(!parenBalanced()) throw new IllegalArgumentException();
		
		for (String s : equation) {
	        if (!isNumber(s)) {
	            if (")".equals(s)) {
	                while (!operatorStack.isEmpty() && !"(".equals(operatorStack.peek())) {
	                    postFix += operatorStack.pop();
	                }
	                if (!operatorStack.isEmpty()) {
	                    operatorStack.pop();
	                }
	            } else {
	                if (!operatorStack.isEmpty() && !precedence(s, operatorStack.peek())) {
	                    operatorStack.push(s);
	                } else {
	                    while (!operatorStack.isEmpty() && precedence(s, operatorStack.peek())) {
	                        String top = operatorStack.pop();
	                        if (!"(".equals(top)) {
	                            postFix += top;
	                        }
	                    }
	                    operatorStack.push(s);
	                }
	            }
	        } else {
	            postFix += s + " ";
	        }
	    }
	    while (!operatorStack.isEmpty()) {
	        postFix += operatorStack.pop() + " ";
	    }

	    return clean(postFix);
	}

	private boolean precedence(String s, String s1) {
		boolean result = false;
		
	    switch (s) {
	    case "+":
	        result = !("+".equals(s1) || "(".equals(s1));
	        break;
	    case "-":
	        result = !("-".equals(s1) || "(".equals(s1));
	        break;

	    case "*":
	        result = "/".equals(s1) || "^".equals(s1) || "(".equals(s1);
	        break;
	    case "/":
	        result = "*".equals(s1) || "^".equals(s1) || "(".equals(s1);
	        break;

	    case "^":
	        result = "(".equals(s1);
	        break;

	    case "(":
	        result = false;
	        break;

	    default:
	        return false;
	    }
	    
	    return result;

	}
	
	public int compute() {
		String equation = toPostFix();
		String[] components = equation.split(" ");
		Stack<Integer> calculate = new Stack<Integer>();
		
		for(String s : components) {
			int x, y, result;
			
			if(isNumber(s)) {
				int temp = Integer.parseInt(s);
				calculate.push(temp);
			}
			else if(s.equals("+")) {
				y = calculate.pop();
				x = calculate.pop();
				
				result = x + y;
				calculate.push(result);
			}
			else if(s.equals("-")) {
				y = calculate.pop();
				x = calculate.pop();
				
				result = x - y;
				calculate.push(result);			
			}
			else if(s.equals("*")) {
				y = calculate.pop();
				x = calculate.pop();
				
				result = x * y;
				calculate.push(result);
			}
			else if(s.equals("/")) {
				y = calculate.pop();
				x = calculate.pop();
				
				result = x / y;
				calculate.push(result);
			}
			else if(s.equals("^")) {
				y = calculate.pop();
				x = calculate.pop();
				
				result = (int)Math.pow(x, y);
				calculate.push(result);
			}
		}
		
		return calculate.pop();
	}

	
}
