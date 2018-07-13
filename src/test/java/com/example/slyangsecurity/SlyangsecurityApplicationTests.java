//package com.example.slyangsecurity;
//
//import org.hyperledger.fabric.protos.peer.Chaincode;
//import org.hyperledger.fabric.sdk.*;
//import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
//import org.hyperledger.fabric.sdk.exception.ProposalException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.UnsupportedEncodingException;
//import java.util.*;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//import static java.nio.charset.StandardCharsets.UTF_8;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SlyangsecurityApplicationTests {
//
//	private Logger logger = LoggerFactory.getLogger(SlyangsecurityApplicationTests.class);
//
//	private static final String CHAIN_CODE_ID = "mychainller";
//
//	private ChaincodeID chaincodeID = ChaincodeID.newBuilder()
//			.setName(CHAIN_CODE_ID)
//			.setVersion("1")
//			.setPath("/opt/gopath")
//			.build();
//
//
//	@Test
//	public void contextLoads() throws InvalidArgumentException, InterruptedException, ExecutionException, TimeoutException, UnsupportedEncodingException, ProposalException {
//
//		// 职能合约调用
//		/// Send transaction proposal to all peers
//		HFClient hfClient = HFClient.createNewInstance();
//
//		Channel channel = hfClient.newChannel(CHAIN_CODE_ID);
//
//		TransactionProposalRequest transactionProposalRequest = hfClient.newTransactionProposalRequest();
//		transactionProposalRequest.setChaincodeID(chaincodeID);
//		transactionProposalRequest.setFcn(fcn);
//		transactionProposalRequest.setArgs(args);
//		transactionProposalRequest.setProposalWaitTime(1000 * 10);
//
//		Map<String, byte[]> tm2 = new HashMap<>();
//		tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8));
//		tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8));
//		tm2.put("result", ":)".getBytes(UTF_8));
//
//		transactionProposalRequest.setTransientMap(tm2);
//
//		long currentStart = System.currentTimeMillis();
//
//		Collection<ProposalResponse> transactionProposalResponses = channel.sendTransactionProposal(transactionProposalRequest, channel.getPeers());
//
//		logger.info("chaincode invoke transaction proposal time = " + (System.currentTimeMillis() - currentStart));
//
//		toOrdererResponse(transactionProposalResponses, channel);
//
//	}
//
//
//	@Test
//	public void contextLoads2() throws InvalidArgumentException {
//
//		//智能合约查询
////		QueryByChaincodeRequest queryByChaincodeRequest = org.getClient().newQueryProposalRequest();
////		queryByChaincodeRequest.setArgs(args);
////		queryByChaincodeRequest.setFcn(fcn);
////		queryByChaincodeRequest.setChaincodeID(chaincodeID);
////		queryByChaincodeRequest.setProposalWaitTime(proposalWaitTime);
////
////		Map<String, byte[]> tm2 = new HashMap<>();
////		tm2.put("HyperLedgerFabric", "QueryByChaincodeRequest:JavaSDK".getBytes(UTF_8));
////		tm2.put("method", "QueryByChaincodeRequest".getBytes(UTF_8));
////		queryByChaincodeRequest.setTransientMap(tm2);
////
////		long currentStart = System.currentTimeMillis();
////		Collection<ProposalResponse> queryProposalResponses = org.getChannel().get().queryByChaincode(queryByChaincodeRequest, org.getChannel().get().getPeers());
////		log.info("chaincode query transaction proposal time = " + (System.currentTimeMillis() - currentStart));
////		return toPeerResponse(queryProposalResponses, true);
//
//	}
//
//	/**
//	 * 获取实例化合约、升级合约以及invoke合约的返回结果集合
//	 *
//	 * @param proposalResponses 请求返回集合
//	 * @param org               中继组织对象
//	 */
//	private Map<String, String> toOrdererResponse(Collection<ProposalResponse> proposalResponses,
//												  Channel channel) throws InvalidArgumentException, UnsupportedEncodingException, InterruptedException, ExecutionException, TimeoutException {
//		Map<String, String> resultMap = new HashMap<>();
//		Collection<ProposalResponse> successful = new LinkedList<>();
//		Collection<ProposalResponse> failed = new LinkedList<>();
//		for (ProposalResponse response : proposalResponses) {
//			if (response.getStatus() == ProposalResponse.Status.SUCCESS) {
//				successful.add(response);
//			} else {
//				failed.add(response);
//			}
//		}
//
//		Collection<Set<ProposalResponse>> proposalConsistencySets = SDKUtils.getProposalConsistencySets(proposalResponses);
//		if (proposalConsistencySets.size() != 1) {
//			logger.error("Expected only one set of consistent proposal responses but got " + proposalConsistencySets.size());
//		}
//		if (failed.size() > 0) {
//			ProposalResponse firstTransactionProposalResponse = failed.iterator().next();
//			logger.error("Not enough endorsers for inspect:" + failed.size() + " endorser error: " + firstTransactionProposalResponse.getMessage() + ". Was verified: "
//					+ firstTransactionProposalResponse.isVerified());
//			resultMap.put("code", "error");
//			resultMap.put("data", firstTransactionProposalResponse.getMessage());
//			return resultMap;
//		} else {
//			logger.info("Successfully received transaction proposal responses.");
//			ProposalResponse resp = proposalResponses.iterator().next();
//			logger.debug("TransactionID: " + resp.getTransactionID());
//			byte[] x = resp.getChaincodeActionResponsePayload();
//			String resultAsString = null;
//			if (x != null) {
//				resultAsString = new String(x, "UTF-8");
//			}
//			logger.info("resultAsString = " + resultAsString);
//
//			channel.sendTransaction(successful).get(10, TimeUnit.SECONDS);
//
//			resultMap.put("code", "success");
//			resultMap.put("data", resultAsString);
//			resultMap.put("txid", resp.getTransactionID());
//
//			return resultMap;
//		}
////        channel.sendTransaction(successful).thenApply(transactionEvent -> {
////            if (transactionEvent.isValid()) {
////                log.info("Successfully send transaction proposal to orderer. Transaction ID: " + transactionEvent.getTransactionID());
////            } else {
////                log.info("Failed to send transaction proposal to orderer");
////            }
////            // chain.shutdown(true);
////            return transactionEvent.getTransactionID();
////        }).get(chaincode.getInvokeWatiTime(), TimeUnit.SECONDS);
//	}
//
//
//}
