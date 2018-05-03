package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommonUtils {
    public static String getIpAddress(){
        InetAddress local=null;
        try {
            local = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
        }
        return local.getHostAddress();
    }
}
