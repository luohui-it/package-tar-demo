package com.bestpay.start;

import com.google.common.util.concurrent.AbstractIdleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动类
 * User: luohui Date: 2016/05/05 ProjectName: package-tar-demo Version: 1.0
 */
@Slf4j
public class Bootstrap extends AbstractIdleService {
    private ClassPathXmlApplicationContext context;

    public static void main(String[] args) {
		Bootstrap bootstrap = new Bootstrap();
        bootstrap.startAsync();
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();
                }
            }
        } catch (InterruptedException ex) {
        	log.error("ignore interruption",ex);
        }
    }

    /**
     * Start the service.
     */
    @Override
    protected void startUp() throws Exception {
        context = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext.xml"});
        context.start();
        context.registerShutdownHook();
        log.info("package-tar-demo service started successfully");
    }

    /**
     * Stop the service.
     */
    @Override
    protected void shutDown() throws Exception {
        context.stop();
        log.info("package-tar-demo service stopped successfully");
    }
}
