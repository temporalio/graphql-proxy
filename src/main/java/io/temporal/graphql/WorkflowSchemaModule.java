package io.temporal.graphql;

import com.google.api.graphql.rejoiner.Query;
import com.google.api.graphql.rejoiner.Mutation;
import io.temporal.api.workflowservice.v1.WorkflowServiceGrpc;
import io.temporal.api.workflowservice.v1.RegisterNamespaceRequest;
import io.temporal.api.workflowservice.v1.RegisterNamespaceResponse;
import io.temporal.api.workflowservice.v1.DescribeNamespaceRequest;
import io.temporal.api.workflowservice.v1.DescribeNamespaceResponse;
import io.temporal.api.workflowservice.v1.UpdateNamespaceRequest;
import io.temporal.api.workflowservice.v1.UpdateNamespaceResponse;
import io.temporal.api.workflowservice.v1.DeprecateNamespaceRequest;
import io.temporal.api.workflowservice.v1.DeprecateNamespaceResponse;
import io.temporal.api.workflowservice.v1.StartWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.StartWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.GetWorkflowExecutionHistoryRequest;
import io.temporal.api.workflowservice.v1.GetWorkflowExecutionHistoryResponse;
import io.temporal.api.workflowservice.v1.GetWorkflowExecutionHistoryReverseRequest;
import io.temporal.api.workflowservice.v1.GetWorkflowExecutionHistoryReverseResponse;
import io.temporal.api.workflowservice.v1.PollWorkflowTaskQueueRequest;
import io.temporal.api.workflowservice.v1.PollWorkflowTaskQueueResponse;
import io.temporal.api.workflowservice.v1.RespondWorkflowTaskCompletedRequest;
import io.temporal.api.workflowservice.v1.RespondWorkflowTaskCompletedResponse;
import io.temporal.api.workflowservice.v1.RespondWorkflowTaskFailedRequest;
import io.temporal.api.workflowservice.v1.RespondWorkflowTaskFailedResponse;
import io.temporal.api.workflowservice.v1.PollActivityTaskQueueRequest;
import io.temporal.api.workflowservice.v1.PollActivityTaskQueueResponse;
import io.temporal.api.workflowservice.v1.RecordActivityTaskHeartbeatRequest;
import io.temporal.api.workflowservice.v1.RecordActivityTaskHeartbeatResponse;
import io.temporal.api.workflowservice.v1.RecordActivityTaskHeartbeatByIdRequest;
import io.temporal.api.workflowservice.v1.RecordActivityTaskHeartbeatByIdResponse;
import io.temporal.api.workflowservice.v1.RespondActivityTaskCompletedRequest;
import io.temporal.api.workflowservice.v1.RespondActivityTaskCompletedResponse;
import io.temporal.api.workflowservice.v1.RespondActivityTaskCompletedByIdRequest;
import io.temporal.api.workflowservice.v1.RespondActivityTaskCompletedByIdResponse;
import io.temporal.api.workflowservice.v1.RespondActivityTaskFailedRequest;
import io.temporal.api.workflowservice.v1.RespondActivityTaskFailedResponse;
import io.temporal.api.workflowservice.v1.RespondActivityTaskFailedByIdRequest;
import io.temporal.api.workflowservice.v1.RespondActivityTaskFailedByIdResponse;
import io.temporal.api.workflowservice.v1.RespondActivityTaskCanceledRequest;
import io.temporal.api.workflowservice.v1.RespondActivityTaskCanceledResponse;
import io.temporal.api.workflowservice.v1.RespondActivityTaskCanceledByIdRequest;
import io.temporal.api.workflowservice.v1.RespondActivityTaskCanceledByIdResponse;
import io.temporal.api.workflowservice.v1.RequestCancelWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.RequestCancelWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.SignalWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.SignalWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.SignalWithStartWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.SignalWithStartWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.ResetWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.ResetWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.TerminateWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.TerminateWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.ListOpenWorkflowExecutionsRequest;
import io.temporal.api.workflowservice.v1.ListOpenWorkflowExecutionsResponse;
import io.temporal.api.workflowservice.v1.ListClosedWorkflowExecutionsRequest;
import io.temporal.api.workflowservice.v1.ListClosedWorkflowExecutionsResponse;
import io.temporal.api.workflowservice.v1.ListWorkflowExecutionsRequest;
import io.temporal.api.workflowservice.v1.ListWorkflowExecutionsResponse;
import io.temporal.api.workflowservice.v1.ListArchivedWorkflowExecutionsRequest;
import io.temporal.api.workflowservice.v1.ListArchivedWorkflowExecutionsResponse;
import io.temporal.api.workflowservice.v1.ScanWorkflowExecutionsRequest;
import io.temporal.api.workflowservice.v1.ScanWorkflowExecutionsResponse;
import io.temporal.api.workflowservice.v1.CountWorkflowExecutionsRequest;
import io.temporal.api.workflowservice.v1.CountWorkflowExecutionsResponse;
import io.temporal.api.workflowservice.v1.GetSearchAttributesRequest;
import io.temporal.api.workflowservice.v1.GetSearchAttributesResponse;
import io.temporal.api.workflowservice.v1.RespondQueryTaskCompletedRequest;
import io.temporal.api.workflowservice.v1.RespondQueryTaskCompletedResponse;
import io.temporal.api.workflowservice.v1.ResetStickyTaskQueueRequest;
import io.temporal.api.workflowservice.v1.ResetStickyTaskQueueResponse;
import io.temporal.api.workflowservice.v1.QueryWorkflowRequest;
import io.temporal.api.workflowservice.v1.QueryWorkflowResponse;
import io.temporal.api.workflowservice.v1.DescribeWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.DescribeWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.DescribeTaskQueueRequest;
import io.temporal.api.workflowservice.v1.DescribeTaskQueueResponse;
import io.temporal.api.workflowservice.v1.GetClusterInfoRequest;
import io.temporal.api.workflowservice.v1.GetClusterInfoResponse;
import io.temporal.api.workflowservice.v1.GetSystemInfoRequest;
import io.temporal.api.workflowservice.v1.GetSystemInfoResponse;
import io.temporal.api.workflowservice.v1.ListTaskQueuePartitionsRequest;
import io.temporal.api.workflowservice.v1.ListTaskQueuePartitionsResponse;
import io.temporal.api.workflowservice.v1.ListNamespacesRequest;
import io.temporal.api.workflowservice.v1.ListNamespacesResponse;

/** A GraphQL {@link com.google.api.graphql.rejoiner.SchemaModule} backed by a gRPC service. */
final class WorkflowSchemaModule extends com.google.api.graphql.rejoiner.SchemaModule {
  @Query("listNamespaces")
  ListNamespacesResponse listNamespaces(ListNamespacesRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.listNamespaces(request);
  }
  @Query("listTaskQueuePartitions")
  ListTaskQueuePartitionsResponse listTaskQueuePartitions(ListTaskQueuePartitionsRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.listTaskQueuePartitions(request);
  }
  @Query("getSystemInfo")
  GetSystemInfoResponse getSystemInfo(GetSystemInfoRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.getSystemInfo(request);
  }
  @Query("getClusterInfo")
  GetClusterInfoResponse getClusterInfo(GetClusterInfoRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.getClusterInfo(request);
  }
  @Query("describeTaskQueue")
  DescribeTaskQueueResponse describeTaskQueue(DescribeTaskQueueRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.describeTaskQueue(request);
  }
  @Query("describeWorkflowExecution")
  DescribeWorkflowExecutionResponse describeWorkflowExecution(DescribeWorkflowExecutionRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.describeWorkflowExecution(request);
  }
  @Query("queryWorkflow")
  QueryWorkflowResponse queryWorkflow(QueryWorkflowRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.queryWorkflow(request);
  }
  @Mutation("resetStickyTaskQueue")
  ResetStickyTaskQueueResponse resetStickyTaskQueue(ResetStickyTaskQueueRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.resetStickyTaskQueue(request);
  }
  @Mutation("respondQueryTaskCompleted")
  RespondQueryTaskCompletedResponse respondQueryTaskCompleted(RespondQueryTaskCompletedRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondQueryTaskCompleted(request);
  }
  @Query("getSearchAttributes")
  GetSearchAttributesResponse getSearchAttributes(GetSearchAttributesRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.getSearchAttributes(request);
  }
  @Query("countWorkflowExecutions")
  CountWorkflowExecutionsResponse countWorkflowExecutions(CountWorkflowExecutionsRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.countWorkflowExecutions(request);
  }
  @Query("scanWorkflowExecutions")
  ScanWorkflowExecutionsResponse scanWorkflowExecutions(ScanWorkflowExecutionsRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.scanWorkflowExecutions(request);
  }
  @Query("listArchivedWorkflowExecutions")
  ListArchivedWorkflowExecutionsResponse listArchivedWorkflowExecutions(ListArchivedWorkflowExecutionsRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.listArchivedWorkflowExecutions(request);
  }
  @Query("listWorkflowExecutions")
  ListWorkflowExecutionsResponse listWorkflowExecutions(ListWorkflowExecutionsRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.listWorkflowExecutions(request);
  }
  @Query("listClosedWorkflowExecutions")
  ListClosedWorkflowExecutionsResponse listClosedWorkflowExecutions(ListClosedWorkflowExecutionsRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.listClosedWorkflowExecutions(request);
  }
  @Query("listOpenWorkflowExecutions")
  ListOpenWorkflowExecutionsResponse listOpenWorkflowExecutions(ListOpenWorkflowExecutionsRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.listOpenWorkflowExecutions(request);
  }
  @Mutation("terminateWorkflowExecution")
  TerminateWorkflowExecutionResponse terminateWorkflowExecution(TerminateWorkflowExecutionRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.terminateWorkflowExecution(request);
  }
  @Mutation("resetWorkflowExecution")
  ResetWorkflowExecutionResponse resetWorkflowExecution(ResetWorkflowExecutionRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.resetWorkflowExecution(request);
  }
  @Mutation("signalWithStartWorkflowExecution")
  SignalWithStartWorkflowExecutionResponse signalWithStartWorkflowExecution(SignalWithStartWorkflowExecutionRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.signalWithStartWorkflowExecution(request);
  }
  @Mutation("signalWorkflowExecution")
  SignalWorkflowExecutionResponse signalWorkflowExecution(SignalWorkflowExecutionRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.signalWorkflowExecution(request);
  }
  @Mutation("requestCancelWorkflowExecution")
  RequestCancelWorkflowExecutionResponse requestCancelWorkflowExecution(RequestCancelWorkflowExecutionRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.requestCancelWorkflowExecution(request);
  }
  @Mutation("respondActivityTaskCanceledById")
  RespondActivityTaskCanceledByIdResponse respondActivityTaskCanceledById(RespondActivityTaskCanceledByIdRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondActivityTaskCanceledById(request);
  }
  @Mutation("respondActivityTaskCanceled")
  RespondActivityTaskCanceledResponse respondActivityTaskCanceled(RespondActivityTaskCanceledRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondActivityTaskCanceled(request);
  }
  @Mutation("respondActivityTaskFailedById")
  RespondActivityTaskFailedByIdResponse respondActivityTaskFailedById(RespondActivityTaskFailedByIdRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondActivityTaskFailedById(request);
  }
  @Mutation("respondActivityTaskFailed")
  RespondActivityTaskFailedResponse respondActivityTaskFailed(RespondActivityTaskFailedRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondActivityTaskFailed(request);
  }
  @Mutation("respondActivityTaskCompletedById")
  RespondActivityTaskCompletedByIdResponse respondActivityTaskCompletedById(RespondActivityTaskCompletedByIdRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondActivityTaskCompletedById(request);
  }
  @Mutation("respondActivityTaskCompleted")
  RespondActivityTaskCompletedResponse respondActivityTaskCompleted(RespondActivityTaskCompletedRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondActivityTaskCompleted(request);
  }
  @Mutation("recordActivityTaskHeartbeatById")
  RecordActivityTaskHeartbeatByIdResponse recordActivityTaskHeartbeatById(RecordActivityTaskHeartbeatByIdRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.recordActivityTaskHeartbeatById(request);
  }
  @Mutation("recordActivityTaskHeartbeat")
  RecordActivityTaskHeartbeatResponse recordActivityTaskHeartbeat(RecordActivityTaskHeartbeatRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.recordActivityTaskHeartbeat(request);
  }
  @Mutation("pollActivityTaskQueue")
  PollActivityTaskQueueResponse pollActivityTaskQueue(PollActivityTaskQueueRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.pollActivityTaskQueue(request);
  }
  @Mutation("respondWorkflowTaskFailed")
  RespondWorkflowTaskFailedResponse respondWorkflowTaskFailed(RespondWorkflowTaskFailedRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondWorkflowTaskFailed(request);
  }
  @Mutation("respondWorkflowTaskCompleted")
  RespondWorkflowTaskCompletedResponse respondWorkflowTaskCompleted(RespondWorkflowTaskCompletedRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.respondWorkflowTaskCompleted(request);
  }
  @Mutation("pollWorkflowTaskQueue")
  PollWorkflowTaskQueueResponse pollWorkflowTaskQueue(PollWorkflowTaskQueueRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.pollWorkflowTaskQueue(request);
  }
  @Query("getWorkflowExecutionHistoryReverse")
  GetWorkflowExecutionHistoryReverseResponse getWorkflowExecutionHistoryReverse(GetWorkflowExecutionHistoryReverseRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.getWorkflowExecutionHistoryReverse(request);
  }
  @Query("getWorkflowExecutionHistory")
  GetWorkflowExecutionHistoryResponse getWorkflowExecutionHistory(GetWorkflowExecutionHistoryRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.getWorkflowExecutionHistory(request);
  }
  @Mutation("startWorkflowExecution")
  StartWorkflowExecutionResponse startWorkflowExecution(StartWorkflowExecutionRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.startWorkflowExecution(request);
  }
  @Mutation("deprecateNamespace")
  DeprecateNamespaceResponse deprecateNamespace(DeprecateNamespaceRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.deprecateNamespace(request);
  }
  @Mutation("updateNamespace")
  UpdateNamespaceResponse updateNamespace(UpdateNamespaceRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.updateNamespace(request);
  }
  @Query("describeNamespace")
  DescribeNamespaceResponse describeNamespace(DescribeNamespaceRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.describeNamespace(request);
  }
  @Mutation("registerNamespace")
  RegisterNamespaceResponse registerNamespace(RegisterNamespaceRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.registerNamespace(request);
  }
}
