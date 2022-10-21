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


package com.mastercard.developer.model.id.verification;

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
 * Error
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-02-08T13:32:52.448-06:00[America/Chicago]")
public class Error {
  public static final String SERIALIZED_NAME_SOURCE = "Source";
  @SerializedName(SERIALIZED_NAME_SOURCE)
  private String source;

  public static final String SERIALIZED_NAME_REASON_CODE = "ReasonCode";
  @SerializedName(SERIALIZED_NAME_REASON_CODE)
  private String reasonCode;

  public static final String SERIALIZED_NAME_DESCRIPTION = "Description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_RECOVERABLE = "Recoverable";
  @SerializedName(SERIALIZED_NAME_RECOVERABLE)
  private Boolean recoverable;

  public static final String SERIALIZED_NAME_DETAILS = "Details";
  @SerializedName(SERIALIZED_NAME_DETAILS)
  private String details;


  public Error source(String source) {
    
    this.source = source;
    return this;
  }

   /**
   * Source of where the error occured
   * @return source
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "mids", value = "Source of where the error occured")

  public String getSource() {
    return source;
  }


  public void setSource(String source) {
    this.source = source;
  }


  public Error reasonCode(String reasonCode) {
    
    this.reasonCode = reasonCode;
    return this;
  }

   /**
   * Code of the error
   * @return reasonCode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "USER_PROFILE_ID_NOT_FOUND", value = "Code of the error")

  public String getReasonCode() {
    return reasonCode;
  }


  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }


  public Error description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * The cause of the error
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "The provided userProfileId does not exist", value = "The cause of the error")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public Error recoverable(Boolean recoverable) {
    
    this.recoverable = recoverable;
    return this;
  }

   /**
   * Indiciates if the error can be recovered from
   * @return recoverable
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "false", value = "Indiciates if the error can be recovered from")

  public Boolean getRecoverable() {
    return recoverable;
  }


  public void setRecoverable(Boolean recoverable) {
    this.recoverable = recoverable;
  }


  public Error details(String details) {
    
    this.details = details;
    return this;
  }

   /**
   * Contains information about the error
   * @return details
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "IOException Occured", value = "Contains information about the error")

  public String getDetails() {
    return details;
  }


  public void setDetails(String details) {
    this.details = details;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.source, error.source) &&
        Objects.equals(this.reasonCode, error.reasonCode) &&
        Objects.equals(this.description, error.description) &&
        Objects.equals(this.recoverable, error.recoverable) &&
        Objects.equals(this.details, error.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, reasonCode, description, recoverable, details);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    reasonCode: ").append(toIndentedString(reasonCode)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    recoverable: ").append(toIndentedString(recoverable)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
