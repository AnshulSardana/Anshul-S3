package com.anshul.AnshulPOCs.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.logs.AWSLogs;
import com.amazonaws.services.logs.AWSLogsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class AWSConfiguration {

	@Value("${aws.accessKey}")
	private String accessKey;

	@Value("${aws.secretKey}")
	private String secretKey;

	@Value("${aws.region}")
	private String awsRegion;

	@Value("${aws.dynamodbendpoint}")
	private String dynamoDbEndPoint;

	@Bean
	public AWSLogs createAWSLogsClient() {

		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		return AWSLogsClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(awsRegion).build();
	}

	@Bean
	public AmazonS3 createAmazonS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(awsRegion).withAccelerateModeEnabled(false).withForceGlobalBucketAccessEnabled(true)
				.build();
	}
	
	@Bean
	public DynamoDBMapper createDynamoDBMapper() {
		 
		return new DynamoDBMapper(buildAmazonDynamoDB());
	}
	
	@Bean
	public AmazonSNSClient createSNSClientBuilder() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(awsRegion).build();
	}

	@Bean
	public QueueMessagingTemplate creatingQueueMessagingTemplate() {
		
		return new QueueMessagingTemplate(amazonSQSAsync());
	}
	
	public AmazonSQSAsync amazonSQSAsync() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsRegion)
                .build();
    }

	private AmazonDynamoDB buildAmazonDynamoDB() {
		
		return AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDbEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
	}

}
