/**
*
* @author Sümeyye Yaþar 
* @since  30.03.2021
* <p>
* regex,if,while,trim(),
* split() vb.. kullandým.
* </p>
*/

package paket;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
public class Program {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String yeni=null,sonhali=null;;//yardýmcý stringler.
		String data;//satýr okumak için.
        String[] kelime=null; 
        String[] kelime2=null;
        String[] kelime3=null;
        int publicmi=1;//public oldugunu kontrol etmek için
        int sinificimi=1;//sinifin içinde oldugunu koontrol etmek için
		 File myObj = new File("src/Program.cpp");//okunacak dosyanýn adý.
	     Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        data = myReader.nextLine();
	        
	        Pattern pattern = Pattern.compile("(?<=class)( *?\\w+)");
	        Matcher matcher = pattern.matcher(data);
	        while (matcher.find()) {	
	        	        sinificimi=1;
		                System.out.println("Sýnýf : " + matcher.group());		           
	        }
	        if(data.contains("};")) {
		    	  sinificimi=0;
		    }	
	        if(data.contains("public")) {
	        	publicmi=1;
	        }                                      //publicmi kontrol ediyor.
	    	  
		    if(data.contains("private")||data.contains("protected")) {
		    	data=data.replaceAll("\\s{2,}", "");
		    	if(data.contains(":")||myReader.nextLine().contains(":")) {
		    	  publicmi=0;}
		    }	
	       
	      if(publicmi==1 && sinificimi==1 && !(data.contains("private"))&& !(data.contains("protected"))) {
	    	  if(data.contains("{")) {
	        		 pattern=Pattern.compile("^( *)?(const)?(.*)(?=\\{)");
	    	         matcher=pattern.matcher(data);
	    	         while(matcher.find()) { 
	    	        	 data=matcher.group();
	    	         }
	        	 }if(data.contains(":")) {
	        		 pattern=Pattern.compile("^( *)?(const)?(.*)(?=\\:)");
	    	         matcher=pattern.matcher(data);
	    	         while(matcher.find()) { 
	    	        	 data=matcher.group();
	    	         }
	        	 }
	    	  if(!(data.contains(";"))) {
	          pattern=Pattern.compile("^( *)?(const)?(.*)(?=\\(\\))");//"fonksiyon()" þeklindeki fonksiyonlarý buluyor.
	          matcher =pattern.matcher(data);
	          while (matcher.find()) {
		   
		        		 yeni=matcher.group();
		        		 yeni=yeni.replaceAll("const ", "");
		        		 yeni=yeni.trim();
		        		kelime=yeni.split(" ");//aþagýda if'lerle fonksiyon adý,donuþ turu ve parametreleri yazdý
		        		if(kelime.length==2 && !(kelime[0].contains("("))&&!(kelime[0].contains("<<"))&& !(kelime[1].contains("("))&&!(kelime[1].contains("<<"))) {
		        			System.out.println('\t'+kelime[1]+"\n\t\tDönüþ Türü="+kelime[0]+'\n'+"\t\tParametre=0");
		        		}
		        		if(kelime.length==1 && !(kelime[0].contains("."))&&kelime[0]!="if"&&kelime[0]!="for"&&kelime[0]!="while"&&!(kelime[0].contains("("))){
		        			
		        			System.out.println('\t'+kelime[0]+"\n\t\tDönüþ Türü=Nesne Adresi"+"\n\t\tParametre=0");
		        		}
		        		if(kelime.length==3 && !(kelime[2].contains("("))&&!(kelime[2].contains("<<"))&& !(kelime[1].contains("("))&&!(kelime[1].contains("<<"))&& !(kelime[1].contains("."))&& !(kelime[2].contains("."))) {
		        			System.out.println('\t'+kelime[2]+"\n\t\tDönüþ Türü="+kelime[1]+'\n'+"\t\tParametre=0");
		        		}
	        	  }kelime=null;
	    	  }
	        	 if(data.contains("{")) {
	        		 pattern=Pattern.compile("^( *)?(const)?(.*)(?=\\{)");
	    	         matcher=pattern.matcher(data);
	    	         while(matcher.find()) { 
	    	        	 data=matcher.group();
	    	         }
	        	 }
	         pattern=Pattern.compile("^( *)?(const)?(.*)(?=\\))");//parametreli fonksiyonlarý aramak için
	         matcher=pattern.matcher(data);
	         while(matcher.find()) { 
	        	
	        	 yeni=matcher.group();
	        	 pattern=Pattern.compile("(.*)(?=\\()");//fonksiyonun ismi ve dönüþ türünü aramak için.
		         matcher=pattern.matcher(yeni);
		         while(matcher.find()) {
		        	 
		        	 sonhali=matcher.group().replaceAll("\\s{2,}", " ");//fazla boþluklarý temizliyor
		        	 sonhali=sonhali.replaceAll("public:", "");
		        	 sonhali=sonhali.replaceAll("const", "");
		        	 sonhali=sonhali.trim();	
		        	 
		        	 kelime3=sonhali.split(" ");
		        	
	        	 pattern=Pattern.compile("(?<=\\()(.*)");//parametreleri aramak için.
		         matcher=pattern.matcher(yeni);
	        	 	while(matcher.find()) {
    	 			    sonhali=matcher.group();
	        	 		sonhali=sonhali.replaceAll("const ", "");	        	 	
	        	 		if(sonhali.contains(" ")&&!(sonhali.contains(":"))&&!(sonhali.contains(";"))) {
	        	 			int isim=0;//fonksiyon ismi yazýldýmý kontrol etmek için.
	        	 			
	        	 			if(kelime3.length==1 && kelime3[0]!="for"&& !(kelime3[0].contains("switch"))&& !(kelime3[0].contains("if")) && !(kelime3[0].contains("while")))
				        	 {isim=1;
				        		 System.out.println('\t'+kelime3[0]+"\n\t\tDönüþ Türü=Nesne Adresi");
				        	 }
				        	 if(kelime3.length==2 && !(kelime3[0].contains("("))&& !(kelime3[1].contains("("))&& !(kelime3[0].contains("switch"))&& !(kelime3[0].contains("while")))
				        	 {   isim=1;
				        		 System.out.println('\t'+kelime3[1]+"\n\t\tDönüþ Türü="+kelime3[0]);
				        	 }
				        	 if(kelime3.length==3 && !(kelime3[0].contains("("))&& !(kelime3[1].contains("(")))
				        	 {	isim=1;
				        		 System.out.println('\t'+kelime3[2]+"\n\t\tDönüþ Türü="+kelime3[1]);
				        	 }
				        	 if(kelime3.length>3 && !(kelime3[0].contains("for"))&& !(kelime3[0].contains("switch"))&& !(kelime3[0].contains("if")) && !(kelime3[0].contains("while"))&& !(kelime3[0].contains("."))&& !(kelime3[0].contains("-")) )
				        	 {	isim=1;
				        		 System.out.println('\t'+kelime3[0]+"\n\t\tDönüþ Türü=Nesne Adresi");
				        	 }
				        	 if(sonhali.contains(",") && isim==1) {
				        	 kelime=sonhali.split(",");
				        	 System.out.print("\t\tParametre="+kelime.length+"(");
		        	 			for(int i=0;i<kelime.length;i++) { //parametreleri yazýyor
		        	 				kelime[i]=kelime[i].trim();
		        	 			    kelime[i]=kelime[i].replaceAll("\\s{2,}", " ");
		        	 				kelime2=kelime[i].split(" ");
		        	 	          System.out.print(kelime2[0]);
		        	 	          if(i==kelime.length-1) {
		        	 	         System.out.print(")");}
		        	 	          else {System.out.print(",");}
		        		 			                             }
		        	 			
	                            System.out.println();            }
	        	 	
				        	 if(!(sonhali.contains(",")) && isim==1){ //ifadede virgül bulunmuyorsa 1 tane parametre vardýr.
				        		 kelime2=sonhali.split(" ");
				        		 System.out.println("\t\tParametre=1 ("+kelime2[0]+")");
				        	                                      }
				        	                  }
	        	 		
	        	 	                          }
	        	 	
		                                     }
	                           }
	                          }
	    	kelime2=null;
	    	kelime3=null;
	       
	      }
	               
	      myReader.close();
}
	}
