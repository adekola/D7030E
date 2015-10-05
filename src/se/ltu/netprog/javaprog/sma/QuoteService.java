package se.ltu.netprog.javaprog.sma;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author ameerah
 */
public class QuoteService implements Deliverable{
    
  
    
    
	public static final int QUOTE_SERVICE_MESSAGE = 300;
	public static final int QUOTE_SERVICE_PORT = 2009;
    private Object DClass;
	public Message send(Message m) {
            
            
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        System.out.println(quotes[index]);
       m.setParam("quote", quotes[index]); 
        return m;
        }
        
        
    
      
    static String[] quotes = new String[]{
        "Promise me you’ll never forget me because if I thought you would I’d never leave - Winnie the Pooh",
        "Women have a wonderful instinct about things. They can discover everything except the obvious. - Oscar Wilde",
        "May the sun in his course visit no land more free, more happy, more lovely, than this our own country! - Daniel Webster",
        "When women are depressed, they eat or go shopping. Men invade another country. It’s a whole different way of thinking. - Elayne Boosler",
        "I told my psychiatrist that everyone hates me. He said I was being ridiculous - everyone hasn’t met me yet. - Rodney Dangerfield",
        "You can say I love you, I miss you and not be there. -	Ririchii",
        "A superior man is modest in his speech, but exceeds in his actions. - Confucius",
        "I hope some day to make you all a cup of coffee. Alright, peace. - Johnny Depp",
        "Love is not the dying moan of a distant violin .. it’s the triumphant twang of a bedspring - S. J. Perelman",
        "When you make a sacrifice in marriage, you’re sacrificing not to each other but to unity in a relationship.- Joseph Campbell",
        "A good marriage would be between a blind wife and a deaf husband. - Honore de Balzac",
        "Coming together is a beginning. Keeping together is progress. Working together is success. - Henry Ford",
        "Anger and intolerance are the twin enemies of correct understanding. - Mahatma Gandhi",
        "Any man who can drive safely while kissing a pretty girl is simply not giving the kiss the attention it deserves. - Albert Einstein"
       
    };
    
    public static void main(String args[]) {
		QuoteService qs = new QuoteService();
		MessageServer ms;
               // String inputAddress =args[2];
		try {
			ms = new MessageServer(QUOTE_SERVICE_PORT);
		} catch(Exception e) {
			System.err.println("Could not start Quote service " + e);
			return;
		}
		Thread msThread = new Thread(ms);
		ms.subscribe(QUOTE_SERVICE_MESSAGE, qs);
		msThread.start();
	}
    
    
    
}
