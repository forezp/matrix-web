package io.github.forezp.common.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * Created by fangzhipeng on 2017/7/18.
 */
public class MD5Utils {
    private final static String salt = "s2w3dce31sw";

    public static String encrypt(String rawStr) {
        rawStr = rawStr + salt;
        return Hashing.md5().newHasher().putString( rawStr, Charsets.UTF_8 ).hash().toString();
    }

//    public static void main(String[] args) {
//        System.out.println(MD5Utils.encrypt("123456"));
//    }

}
