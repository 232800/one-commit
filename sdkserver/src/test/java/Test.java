//import com.qycloud.SDK.client.DecodeAndEnCodeClient;
//import com.qycloud.SDK.client.ServerInfo;
//import com.qycloud.SDK.client.UserClient;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Test {
//
//    public static void main(String[] args){
//        try {
//            ServerInfo client = new ServerInfo("127.0.0.1",7910);
//            String loginUserId = "ccb1";
//            String password = DecodeAndEnCodeClient.encode3DES("4444", client);
//            password = "34b7b082523fc613";
//            String token = "32323231";
//            String ip = "192.192.1.11";
//            Map<String,Object> params = new HashMap<>();
//            params.put("loginU1serId",loginUserId);
//            params.put("password",password);
//            params.put("token",token);
//            params.put("ip",ip);
//            DecodeAndEnCodeClient decodeAndEnCodeClient = new DecodeAndEnCodeClient();
//            System.out.println(decodeAndEnCodeClient.encodeMD5("aaa", client));
//
//            String result = UserClient.login(loginUserId,password,"aadfasdf",ip,client);
//
////            String result = CommonClient.login(params,client);
////            LoginServer.Client loginClient = (LoginServer.Client)client.getClient(LoginServer.class.getName());
////            String result = loginClient.login(loginUserId.toString(),password.toString(),token.toString(),ip.toString());
//
//
////            JSONObject json = JSONObject.parseObject(result);
////            String code = json.getString("code");
////            String data = json.getString("data");
//            System.out.println("The result is11: "+result);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
