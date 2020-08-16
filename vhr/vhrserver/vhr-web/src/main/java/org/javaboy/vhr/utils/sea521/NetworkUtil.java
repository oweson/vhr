/**
 * <p>Title: NetworkUtil.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) AKE 2019<／p>
 * <p>Company: AKE<／p>
 *
 * @author GuoJM
 * @date 2019年3月22日
 * @version 1.0
 */
package org.javaboy.vhr.utils.sea521;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author GuoJM
 *
 */
public class NetworkUtil {

    /**
     * Logger for this class
     */
    private static Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

    /**
     * 1  获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        /**问题背景
         在Web应用开发中，经常会需要获取客户端IP地址。一个典型的例子就是投票系统，为了防止刷票，
         需要限制每个IP地址只能投票一次。

         二：如何获取客户端IP
         在Java中，获取客户端IP最直接的方式就是使用request.getRemoteAddr()。这种方式能获取到连接服务器的客户端IP，在中间没有代理的情况下，的确是最简单有效的方式。但是目前互联网Web应用很少会将应用服务器直接对外提供服务，一般都会有一层Nginx做反向代理和负载均衡，有的甚至可能有多层代理。在有反向代理的情况下，直接使用request.getRemoteAddr()获取到的IP地址是Nginx所在服务器的IP地址，而不是客户端的IP。

         HTTP协议是基于TCP协议的，由于request.getRemoteAddr()默认获取到的是TCP层直接连接的客户端的IP，对于Web应用服务器来说直接连接它的客户端实际上是Nginx，也就是TCP层是拿不到真实客户端的IP。

         为了解决上面的问题，很多HTTP代理会在HTTP协议头中添加X-Forwarded-For头，用来追踪请求的来源。X-Forwarded-For的格式如下：

         X-Forwarded-For: client1, proxy1, proxy2

         三：伪造X-Forwarded-For
         一般的客户端（例如浏览器）发送HTTP请求是没有X-Forwarded-For头的，当请求到达第一个代理服务器时，
         代理服务器会加上X-Forwarded-For请求头，并将值设为客户端的IP地址（也就是最左边第一个值），后面如果还有多个代理，会依次将IP追加到X-Forwarded-For头最右边，最终请求到达Web应用服务器，应用通过获取X-Forwarded-For头取左边第一个IP即为客户端真实IP。

         但是如果客户端在发起请求时，请求头上带上一个伪造的X-Forwarded-For，由于后续每层代理只会追加而不会覆盖，
         那么最终到达应用服务器时，获取的左边第一个IP地址将会是客户端伪造的IP。也就是上面的Java代码中getClientIp()方法获取的IP地址很有可能是伪造的IP地址，如果一个投票系统用这种方式做的IP限制，那么很容易会被刷票。*/

        String ip = request.getHeader("X-Forwarded-For");
        if (logger.isInfoEnabled()) {
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    public static String getLocalIP() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }

    public static String getBaseUrl(HttpServletRequest req) {
        String baseUrl = req.getScheme() + "://" + req.getServerName();
        if (req.getServerPort() != 80) {
            baseUrl += ":" + req.getServerPort();
        }
        return baseUrl;
    }
}
