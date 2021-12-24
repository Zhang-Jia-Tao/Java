package com.one.util;

public class PathUtils {

    private static final String P_PATH="/Volumes/T7/PE_Client/PE/images/";

    public static String getRealPath(String relativePath){

        return P_PATH+relativePath;
    }

}
