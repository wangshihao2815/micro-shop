package com.leyou.auth.test;

import com.leyou.auth.pojo.UserInfo;
import com.leyou.auth.util.JwtUtils;
import com.leyou.auth.util.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    /**
     * 存放公钥(yuè)的路径
     */
    private static final String pubKeyPath = "F:\\wangshihao\\rsa.pub";

    /**
     * 存放私钥(yuè)的路径
     */
    private static final String priKeyPath = "F:\\wangshihao\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        //（公钥路径，私钥路径，服务密钥）
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4NDg0NDUzMH0.cTJS_pNFP9I7Gz0YDf5v00UsQj-bYpHwTG6Lp0RvryMUEFImTLVCODJEAcxGd494lc343B5eu_f9OWzO-SAOwa-0pvvFLLnBIoTPCp9jxMJwhTtiQol-Uty7ajfmGMlIcx26loaCdcUz2-vXrKTkx813unUsXWTVqLZHs6RhLYc";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
