/*
 * Mastercard Id Verification API
 * ID Verification provides the technology platform and operational service to perform real-time user identity verification for various use cases including financial services, healthcare, travel and education. The solution is offered via APIs to prove the identity of users based on their verified phone number and social security number.
 *
 * The version of the OpenAPI document: 1.0
 * Contact: apisupport@mastercard.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.mastercard.developer.api;

import com.mastercard.developer.ApiCallback;
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.ApiResponse;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.Pair;
import com.mastercard.developer.ProgressRequestBody;
import com.mastercard.developer.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.mastercard.developer.model.id.verification.ApiError;
import com.mastercard.developer.model.id.verification.DeviceAuthenticationVerificationUrl;
import com.mastercard.developer.model.id.verification.DeviceIpAddress;
import com.mastercard.developer.model.id.verification.DevicePhoneNumber;
import com.mastercard.developer.model.id.verification.DeviceVerificationFingerprint;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdVerifyDeviceAuthenticationApi {
    private ApiClient localVarApiClient;

    public IdVerifyDeviceAuthenticationApi() {
        this(Configuration.getDefaultApiClient());
    }

    public IdVerifyDeviceAuthenticationApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for deviceAuthentication
     * @param deviceIpAddress  (required)
     * @param xEncryptedPayload Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  * X-Transaction-ID -  <br>  </td></tr>
        <tr><td> 400 </td><td> Something was wrong with the request. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Consent not given. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Request didn&#39;t match an existing resource. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deviceAuthenticationCall(DeviceIpAddress deviceIpAddress, Boolean xEncryptedPayload, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = deviceIpAddress;

        // create path and map variables
        String localVarPath = "/device-authentications";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xEncryptedPayload != null) {
            localVarHeaderParams.put("X-Encrypted-Payload", localVarApiClient.parameterToString(xEncryptedPayload));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deviceAuthenticationValidateBeforeCall(DeviceIpAddress deviceIpAddress, Boolean xEncryptedPayload, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'deviceIpAddress' is set
        if (deviceIpAddress == null) {
            throw new ApiException("Missing the required parameter 'deviceIpAddress' when calling deviceAuthentication(Async)");
        }
        

        okhttp3.Call localVarCall = deviceAuthenticationCall(deviceIpAddress, xEncryptedPayload, _callback);
        return localVarCall;

    }

    /**
     * Provide Redirect URL for Device Authentication Verification
     * This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;
     * @param deviceIpAddress  (required)
     * @param xEncryptedPayload Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  (optional)
     * @return DeviceAuthenticationVerificationUrl
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  * X-Transaction-ID -  <br>  </td></tr>
        <tr><td> 400 </td><td> Something was wrong with the request. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Consent not given. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Request didn&#39;t match an existing resource. </td><td>  -  </td></tr>
     </table>
     */
    public DeviceAuthenticationVerificationUrl deviceAuthentication(DeviceIpAddress deviceIpAddress, Boolean xEncryptedPayload) throws ApiException {
        ApiResponse<DeviceAuthenticationVerificationUrl> localVarResp = deviceAuthenticationWithHttpInfo(deviceIpAddress, xEncryptedPayload);
        return localVarResp.getData();
    }

    /**
     * Provide Redirect URL for Device Authentication Verification
     * This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;
     * @param deviceIpAddress  (required)
     * @param xEncryptedPayload Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  (optional)
     * @return ApiResponse&lt;DeviceAuthenticationVerificationUrl&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  * X-Transaction-ID -  <br>  </td></tr>
        <tr><td> 400 </td><td> Something was wrong with the request. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Consent not given. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Request didn&#39;t match an existing resource. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DeviceAuthenticationVerificationUrl> deviceAuthenticationWithHttpInfo(DeviceIpAddress deviceIpAddress, Boolean xEncryptedPayload) throws ApiException {
        okhttp3.Call localVarCall = deviceAuthenticationValidateBeforeCall(deviceIpAddress, xEncryptedPayload, null);
        Type localVarReturnType = new TypeToken<DeviceAuthenticationVerificationUrl>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Provide Redirect URL for Device Authentication Verification (asynchronously)
     * This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;
     * @param deviceIpAddress  (required)
     * @param xEncryptedPayload Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  * X-Transaction-ID -  <br>  </td></tr>
        <tr><td> 400 </td><td> Something was wrong with the request. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Consent not given. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Request didn&#39;t match an existing resource. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deviceAuthenticationAsync(DeviceIpAddress deviceIpAddress, Boolean xEncryptedPayload, final ApiCallback<DeviceAuthenticationVerificationUrl> _callback) throws ApiException {

        okhttp3.Call localVarCall = deviceAuthenticationValidateBeforeCall(deviceIpAddress, xEncryptedPayload, _callback);
        Type localVarReturnType = new TypeToken<DeviceAuthenticationVerificationUrl>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deviceAuthenticationVerification
     * @param deviceVerificationFingerprint  (required)
     * @param xEncryptedPayload Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  * X-Transaction-ID -  <br>  </td></tr>
        <tr><td> 400 </td><td> Something was wrong with the request. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Consent not given. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Request didn&#39;t match an existing resource. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deviceAuthenticationVerificationCall(DeviceVerificationFingerprint deviceVerificationFingerprint, Boolean xEncryptedPayload, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = deviceVerificationFingerprint;

        // create path and map variables
        String localVarPath = "/device-authentication-verifications";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xEncryptedPayload != null) {
            localVarHeaderParams.put("X-Encrypted-Payload", localVarApiClient.parameterToString(xEncryptedPayload));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deviceAuthenticationVerificationValidateBeforeCall(DeviceVerificationFingerprint deviceVerificationFingerprint, Boolean xEncryptedPayload, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'deviceVerificationFingerprint' is set
        if (deviceVerificationFingerprint == null) {
            throw new ApiException("Missing the required parameter 'deviceVerificationFingerprint' when calling deviceAuthenticationVerification(Async)");
        }
        

        okhttp3.Call localVarCall = deviceAuthenticationVerificationCall(deviceVerificationFingerprint, xEncryptedPayload, _callback);
        return localVarCall;

    }

    /**
     * Provide Phone Number using verificationFingerprint
     * This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;
     * @param deviceVerificationFingerprint  (required)
     * @param xEncryptedPayload Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  (optional)
     * @return DevicePhoneNumber
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  * X-Transaction-ID -  <br>  </td></tr>
        <tr><td> 400 </td><td> Something was wrong with the request. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Consent not given. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Request didn&#39;t match an existing resource. </td><td>  -  </td></tr>
     </table>
     */
    public DevicePhoneNumber deviceAuthenticationVerification(DeviceVerificationFingerprint deviceVerificationFingerprint, Boolean xEncryptedPayload) throws ApiException {
        ApiResponse<DevicePhoneNumber> localVarResp = deviceAuthenticationVerificationWithHttpInfo(deviceVerificationFingerprint, xEncryptedPayload);
        return localVarResp.getData();
    }

    /**
     * Provide Phone Number using verificationFingerprint
     * This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;
     * @param deviceVerificationFingerprint  (required)
     * @param xEncryptedPayload Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  (optional)
     * @return ApiResponse&lt;DevicePhoneNumber&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  * X-Transaction-ID -  <br>  </td></tr>
        <tr><td> 400 </td><td> Something was wrong with the request. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Consent not given. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Request didn&#39;t match an existing resource. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DevicePhoneNumber> deviceAuthenticationVerificationWithHttpInfo(DeviceVerificationFingerprint deviceVerificationFingerprint, Boolean xEncryptedPayload) throws ApiException {
        okhttp3.Call localVarCall = deviceAuthenticationVerificationValidateBeforeCall(deviceVerificationFingerprint, xEncryptedPayload, null);
        Type localVarReturnType = new TypeToken<DevicePhoneNumber>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Provide Phone Number using verificationFingerprint (asynchronously)
     * This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;
     * @param deviceVerificationFingerprint  (required)
     * @param xEncryptedPayload Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  * X-Transaction-ID -  <br>  </td></tr>
        <tr><td> 400 </td><td> Something was wrong with the request. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized request. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Consent not given. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Request didn&#39;t match an existing resource. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deviceAuthenticationVerificationAsync(DeviceVerificationFingerprint deviceVerificationFingerprint, Boolean xEncryptedPayload, final ApiCallback<DevicePhoneNumber> _callback) throws ApiException {

        okhttp3.Call localVarCall = deviceAuthenticationVerificationValidateBeforeCall(deviceVerificationFingerprint, xEncryptedPayload, _callback);
        Type localVarReturnType = new TypeToken<DevicePhoneNumber>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}