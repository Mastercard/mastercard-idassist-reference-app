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
import com.mastercard.developer.model.id.verification.DocumentVerificationExtractedDataDocumentData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * DocumentVerificationExtractedData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-02-08T13:32:52.448-06:00[America/Chicago]")
public class DocumentVerificationExtractedData {
  public static final String SERIALIZED_NAME_DOCUMENT_DATA = "documentData";
  @SerializedName(SERIALIZED_NAME_DOCUMENT_DATA)
  private DocumentVerificationExtractedDataDocumentData documentData;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private String status;

  public static final String SERIALIZED_NAME_TRANSACTION_ID = "transactionId";
  @SerializedName(SERIALIZED_NAME_TRANSACTION_ID)
  private String transactionId;


  public DocumentVerificationExtractedData documentData(DocumentVerificationExtractedDataDocumentData documentData) {
    
    this.documentData = documentData;
    return this;
  }

   /**
   * Get documentData
   * @return documentData
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public DocumentVerificationExtractedDataDocumentData getDocumentData() {
    return documentData;
  }


  public void setDocumentData(DocumentVerificationExtractedDataDocumentData documentData) {
    this.documentData = documentData;
  }


  public DocumentVerificationExtractedData status(String status) {
    
    this.status = status;
    return this;
  }

   /**
   * The status of the Status API, possible values are SUCCESS / PENDING
   * @return status
  **/
  @ApiModelProperty(example = "SUCCESS", required = true, value = "The status of the Status API, possible values are SUCCESS / PENDING")

  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  public DocumentVerificationExtractedData transactionId(String transactionId) {
    
    this.transactionId = transactionId;
    return this;
  }

   /**
   * A random 128-bit UUID representing the transaction
   * @return transactionId
  **/
  @ApiModelProperty(example = "1ec14310-e85c-11ea-adc1-0242ac120002", required = true, value = "A random 128-bit UUID representing the transaction")

  public String getTransactionId() {
    return transactionId;
  }


  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentVerificationExtractedData documentVerificationExtractedData = (DocumentVerificationExtractedData) o;
    return Objects.equals(this.documentData, documentVerificationExtractedData.documentData) &&
        Objects.equals(this.status, documentVerificationExtractedData.status) &&
        Objects.equals(this.transactionId, documentVerificationExtractedData.transactionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documentData, status, transactionId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentVerificationExtractedData {\n");
    sb.append("    documentData: ").append(toIndentedString(documentData)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
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

