package com.mastercard.developer.interceptor;

import com.mastercard.developer.signers.OkHttpSigner;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.security.PrivateKey;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
 class OkHttpOAuth1InterceptorCustomTest {

    @Mock
    private  PrivateKey privateKey;

    @Mock
    private OkHttpSigner signerIdVerify;

    @Mock
    private OkHttpSigner signerMcAssist ;

    @Mock
    Interceptor.Chain chain ;

    @InjectMocks
    OkHttpOAuth1InterceptorCustom okHttpOAuth1InterceptorCustom;

    @Test
   void isOAuth1RequiredSignerMcAssistTest() throws IOException {
            Request request = create_request_mcidassist();
            Response  responseCreated = create_response();
            doReturn(responseCreated).when(chain).proceed(any());
            doReturn(request).when(chain).request();
            Response response = okHttpOAuth1InterceptorCustom.intercept(chain);
            Assertions.assertNotNull(response);
   }

    @Test
    void isOAuth1RequiredSignerIdVerifyTest() throws IOException {
        Request request = create_request_signerIdVerify();
        Response  responseCreated = create_response();
        doReturn(responseCreated).when(chain).proceed(any());
        doReturn(request).when(chain).request();
        Response response = okHttpOAuth1InterceptorCustom.intercept(chain);
        Assertions.assertNotNull(response);
    }

    @Test
    void isOAuth1RequiredSignerNoneTest() throws IOException {
        Request request = create_request_noOption();
        Response  responseCreated = create_response();
        doReturn(responseCreated).when(chain).proceed(any());
        doReturn(request).when(chain).request();
        Response response = okHttpOAuth1InterceptorCustom.intercept(chain);
        Assertions.assertNotNull(response);
    }

    @Test
    void instantiate_OAuth_IdVerify()  {
          okHttpOAuth1InterceptorCustom.initSignerIdVerify("123",privateKey);
          OkHttpOAuth1InterceptorCustom okHttpOAuth1InterceptorCustomMock = mock(OkHttpOAuth1InterceptorCustom.class);
          doNothing().when(okHttpOAuth1InterceptorCustomMock).initSignerIdVerify(anyString(),any(PrivateKey.class));
          okHttpOAuth1InterceptorCustomMock.initSignerIdVerify("123",privateKey);
          verify(okHttpOAuth1InterceptorCustomMock, times(1)).initSignerIdVerify("123",privateKey);
    }

    @Test
    void instantiate_OAuth_McAssist(){
        okHttpOAuth1InterceptorCustom.initSignerAssist("123",privateKey);
        OkHttpOAuth1InterceptorCustom okHttpOAuth1InterceptorCustomMock = mock(OkHttpOAuth1InterceptorCustom.class);
        doNothing().when(okHttpOAuth1InterceptorCustomMock).initSignerAssist(anyString(),any(PrivateKey.class));
        okHttpOAuth1InterceptorCustomMock.initSignerAssist("123",privateKey);
        verify(okHttpOAuth1InterceptorCustomMock, times(1)).initSignerAssist("123",privateKey);


    }

     private Request create_request_mcidassist() throws IOException {

         Request request = new Request.Builder()
                 .url("https://system.api.mastercard.com/mcidassist/user-identities")
                 .header("User-Agent", "OkHttp Example")
                 .build();
         return request;
    }


    private Request create_request_signerIdVerify() throws IOException {

        Request request = new Request.Builder()
                .url("https://system.api.mastercard.com/idverify/user-verifications")
                .header("User-Agent", "OkHttp Example")
                .build();
        return request;
    }


    private Request create_request_noOption() throws IOException {

        Request request = new Request.Builder()
                .url("https://system.api.mastercard.com/abc/none")
                .header("User-Agent", "OkHttp Example")
                .build();
        return request;
    }

    private Response create_response (){

        Request request = new Request.Builder()
                .url("https://google.com")
                .header("User-Agent", "OkHttp Example")
                .build();

        return new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_2)
                .code(401) // status code
                .message("mock")
                .body(ResponseBody.create("application/json; charset=utf-8", MediaType.parse("{}")))
                .build();
    }

}
