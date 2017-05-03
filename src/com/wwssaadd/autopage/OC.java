package com.wwssaadd.autopage;

/**
 * Created by Hocean on 2017/4/28.
 */
public class OC {
    
     public static void main(String[] args){
         short[] s = toShort("11A001");
          for(short item : s){
              System.out.println(item);
           }
     }
    public static short[] toShort(String str){
        int len = str.length();
        short[] s = new short[len / 2];
        for (int i = 0; i < s.length; i++) {
            String val = str.substring(2 * i,2 * i+2);
            //s[i] = Short.parseShort(val,16);
            s[i] = parseShort(val,16); //自定义方法
        }
        return s;
    }
    //输入要转换的 暂时只支持大写     字符串  和    进制
     public static short parseShort(String text, int radix){
         String[] check = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
         short sum = 0;
         for(int i = text.length(),b = 0 ; i > 0 ; i--,b++){
             String num = text.substring(i-1, i);
             for (int j = 0; j < check.length; j++) {
                 if(check[j].equals(num)){
                     sum +=j * Math.pow(radix,b);
                 }
             }
         }
         return sum;
     }
}



