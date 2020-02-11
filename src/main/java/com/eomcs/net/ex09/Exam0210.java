// Apache HttpClient 라이브러리를 이용하여 웹서버 만들기 II
package com.eomcs.net.ex09;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.http.ConnectionClosedException;
import org.apache.http.ExceptionLogger;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

public class Exam0210 {
  
  public static void main(String[] args) throws Exception {
    SocketConfig socketConfig = SocketConfig.custom()
        .setSoTimeout(15000)
        .setTcpNoDelay(true)
        .build();

    final HttpServer server = ServerBootstrap.bootstrap()
        .setListenerPort(8888)
        .setServerInfo("Bitcamp/1.1")
        .setSocketConfig(socketConfig)
        .setSslContext(null)
        .setExceptionLogger(new StdErrorExceptionLogger())
        .registerHandler("*", new MyHttpRequestHandler())
        .create();

    server.start();
    System.out.println("서버 실행 중...");
    
    server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        server.shutdown(5, TimeUnit.SECONDS);
      }
    });
  }

  static class StdErrorExceptionLogger implements ExceptionLogger {

    @Override
    public void log(final Exception ex) {
      if (ex instanceof SocketTimeoutException) {
        System.err.println("Connection timed out");
      } else if (ex instanceof ConnectionClosedException) {
        System.err.println(ex.getMessage());
      } else {
        ex.printStackTrace();
      }
    }

  }

  static class MyHttpRequestHandler implements HttpRequestHandler  {

    public void handle(
        final HttpRequest request,
        final HttpResponse response,
        final HttpContext context) throws HttpException, IOException {

      // 클라이언트가 요청한 방식을 알아 낸다.
      String method = request.getRequestLine().getMethod().toUpperCase(Locale.ROOT);
      if (!method.equals("GET")) { // GET 요청이 아니라면 오류 내용을 응답한다.
        throw new MethodNotSupportedException(method + " method not supported");
      }
      
      response.setStatusCode(HttpStatus.SC_OK);
      StringEntity entity = new StringEntity(
          "<html><body><h1>Hello!</h1></body></html>",
          ContentType.create("text/html", "UTF-8"));
      response.setEntity(entity);
    }
  }
}