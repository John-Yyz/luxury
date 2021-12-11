package com.luxury.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/4 2:20
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private String _body;
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException
    {
        super(request);
        _body = "";
        try (BufferedReader bufferedReader = request.getReader())
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
                _body += line;
        }
    }
    public String getReqBody(){
        return _body;
    }
    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(_body.getBytes());
        return new ServletInputStream()
        {
            public int read() throws IOException
            {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0) {

            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}
