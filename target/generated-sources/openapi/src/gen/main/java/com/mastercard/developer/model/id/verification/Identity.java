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
import com.mastercard.developer.model.id.verification.IdentityScopedFields;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * The fields to be scoped
 */
@ApiModel(description = "The fields to be scoped")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-02-08T13:32:52.448-06:00[America/Chicago]")
public class Identity {
  public static final String SERIALIZED_NAME_TRANSACTION_ID = "transactionId";
  @SerializedName(SERIALIZED_NAME_TRANSACTION_ID)
  private String transactionId;

  public static final String SERIALIZED_NAME_SCOPED_FIELDS = "scopedFields";
  @SerializedName(SERIALIZED_NAME_SCOPED_FIELDS)
  private IdentityScopedFields scopedFields;


  public Identity transactionId(String transactionId) {
    
    this.transactionId = transactionId;
    return this;
  }

   /**
   * A random 128-bit. UUID representing the MIDS transaction
   * @return transactionId
  **/
  @ApiModelProperty(example = "7f83-b0c4-90e0-90b3-11e10800200c9a66", required = true, value = "A random 128-bit. UUID representing the MIDS transaction")

  public String getTransactionId() {
    return transactionId;
  }


  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }


  public Identity scopedFields(IdentityScopedFields scopedFields) {
    
    this.scopedFields = scopedFields;
    return this;
  }

   /**
   * Get scopedFields
   * @return scopedFields
  **/
  @ApiModelProperty(required = true, value = "")

  public IdentityScopedFields getScopedFields() {
    return scopedFields;
  }


  public void setScopedFields(IdentityScopedFields scopedFields) {
    this.scopedFields = scopedFields;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Identity identity = (Identity) o;
    return Objects.equals(this.transactionId, identity.transactionId) &&
        Objects.equals(this.scopedFields, identity.scopedFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, scopedFields);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Identity {\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    scopedFields: ").append(toIndentedString(scopedFields)).append("\n");
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
