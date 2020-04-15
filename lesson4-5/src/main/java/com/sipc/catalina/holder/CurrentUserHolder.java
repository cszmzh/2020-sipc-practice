package com.sipc.catalina.holder;

public class CurrentUserHolder {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String get(){
        return holder.get()==null?"null":holder.get();
    }

    public static void set(String username){
        holder.set(username);
    }

}
