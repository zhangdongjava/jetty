package com.sven.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {

    public static final int PORT = 8080;
    public static final String contentPath = "/";

    private static  String BASE_URL ;
    private static  String DEFAULT_WEBAPP_PATH;

    /**
     * 创建用于开发运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
     */
    public  void createServerInSource() throws Exception {
        BASE_URL = this.getClass().getResource("/").getPath().toString().substring(1);
        DEFAULT_WEBAPP_PATH = BASE_URL +"webapp";
        Server server = new Server();//设置端口号
        try {

            // 设置在JVM退出时关闭Jetty的钩子。
            server.setStopAtShutdown(true);

            //这是http的连接器
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(80);
            // 解决Windows下重复启动Jetty居然不报告端口冲突的问题.
            connector.setReuseAddress(false);
            server.setConnectors(new Connector[] { connector });

            WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contentPath);
            //webContext.setContextPath("/");
            webContext.setDescriptor(DEFAULT_WEBAPP_PATH+"/WEB-INF/web.xml");
            // 设置webapp的位置
            webContext.setResourceBase(DEFAULT_WEBAPP_PATH);
            webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
            server.setHandler(webContext);
            server.stop();
            server.start();
            server.join();
        } catch (Exception e) {

            e.printStackTrace();

        }
    }



    public static void main(String[] args) throws Exception {
        new Launcher().createServerInSource();
        System.out.println("启动完毕!!!!!");
    }
}