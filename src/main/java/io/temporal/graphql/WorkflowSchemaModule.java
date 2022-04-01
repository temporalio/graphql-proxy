package io.temporal.graphql;

import com.google.api.graphql.rejoiner.Query;
import io.temporal.api.workflowservice.v1.WorkflowServiceGrpc;
import io.temporal.api.workflowservice.v1.ListNamespacesRequest;
import io.temporal.api.workflowservice.v1.ListNamespacesResponse;

/** A GraphQL {@link com.google.api.graphql.rejoiner.SchemaModule} backed by a gRPC service. */
final class WorkflowSchemaModule extends com.google.api.graphql.rejoiner.SchemaModule {
  @Query("listNamespaces")
  ListNamespacesResponse listNamespaces(ListNamespacesRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.listNamespaces(request);
  }
}
