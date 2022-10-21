/*
 * Mastercard Id Assist API
 * ID Assist provides the technology platform and operational service to allow the secure storage and transmission of Digital Identity data from the user to the Relying Party. The data is verified to a required level of assurance by an Identity Verification Provider.
 *
 * The version of the OpenAPI document: 1.1
 * Contact: apisupport@mastercard.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.mastercard.developer.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * SMSOTPVerification
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-02-08T13:32:51.698-06:00[America/Chicago]")
public class SMSOTPVerification {
  public static final String SERIALIZED_NAME_OTP_ID = "otpId";
  @SerializedName(SERIALIZED_NAME_OTP_ID)
  private String otpId;

  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_COUNTRY_CODE = "countryCode";
  @SerializedName(SERIALIZED_NAME_COUNTRY_CODE)
  private String countryCode;

  public static final String SERIALIZED_NAME_OPTED_IN_CONSENT_STATUS = "optedInConsentStatus";
  @SerializedName(SERIALIZED_NAME_OPTED_IN_CONSENT_STATUS)
  private Boolean optedInConsentStatus;


  public SMSOTPVerification otpId(String otpId) {
    
    this.otpId = otpId;
    return this;
  }

   /**
   * Returned from &#x60;/sms-otps&#x60;
   * @return otpId
  **/
  @ApiModelProperty(example = "4b24557f-50a2-4e01-898b-ab8575527cac", required = true, value = "Returned from `/sms-otps`")

  public String getOtpId() {
    return otpId;
  }


  public void setOtpId(String otpId) {
    this.otpId = otpId;
  }


  public SMSOTPVerification code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * This is the code received by sms after calling &#x60;/sms-otps&#x60;
   * @return code
  **/
  @ApiModelProperty(example = "123456", required = true, value = "This is the code received by sms after calling `/sms-otps`")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public SMSOTPVerification countryCode(String countryCode) {
    
    this.countryCode = countryCode;
    return this;
  }

   /**
   * ISO-3166 Alpha-2 standard.
   * @return countryCode
  **/
  @ApiModelProperty(example = "US", required = true, value = "ISO-3166 Alpha-2 standard.")

  public String getCountryCode() {
    return countryCode;
  }


  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }


  public SMSOTPVerification optedInConsentStatus(Boolean optedInConsentStatus) {
    
    this.optedInConsentStatus = optedInConsentStatus;
    return this;
  }

   /**
   * Should be true, validation exception will be thrown if it is false.
   * @return optedInConsentStatus
  **/
  @ApiModelProperty(example = "true", required = true, value = "Should be true, validation exception will be thrown if it is false.")

  public Boolean getOptedInConsentStatus() {
    return optedInConsentStatus;
  }


  public void setOptedInConsentStatus(Boolean optedInConsentStatus) {
    this.optedInConsentStatus = optedInConsentStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SMSOTPVerification smSOTPVerification = (SMSOTPVerification) o;
    return Objects.equals(this.otpId, smSOTPVerification.otpId) &&
        Objects.equals(this.code, smSOTPVerification.code) &&
        Objects.equals(this.countryCode, smSOTPVerification.countryCode) &&
        Objects.equals(this.optedInConsentStatus, smSOTPVerification.optedInConsentStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(otpId, code, countryCode, optedInConsentStatus);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SMSOTPVerification {\n");
    sb.append("    otpId: ").append(toIndentedString(otpId)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    optedInConsentStatus: ").append(toIndentedString(optedInConsentStatus)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

