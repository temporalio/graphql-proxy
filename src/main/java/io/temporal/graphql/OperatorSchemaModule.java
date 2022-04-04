package io.temporal.graphql;

import com.google.api.graphql.rejoiner.Query;
import com.google.api.graphql.rejoiner.Mutation;
import io.temporal.api.operatorservice.v1.OperatorServiceGrpc;
import io.temporal.api.operatorservice.v1.ListSearchAttributesRequest;
import io.temporal.api.operatorservice.v1.ListSearchAttributesResponse;
import io.temporal.api.operatorservice.v1.AddSearchAttributesRequest;
import io.temporal.api.operatorservice.v1.AddSearchAttributesResponse;
import io.temporal.api.operatorservice.v1.DeleteNamespaceRequest;
import io.temporal.api.operatorservice.v1.DeleteNamespaceResponse;
import io.temporal.api.operatorservice.v1.RemoveSearchAttributesRequest;
import io.temporal.api.operatorservice.v1.RemoveSearchAttributesResponse;

/** A GraphQL {@link com.google.api.graphql.rejoiner.SchemaModule} backed by a gRPC service. */
final class OperatorSchemaModule extends com.google.api.graphql.rejoiner.SchemaModule {
  @Query("listSearchAttributes")
  ListSearchAttributesResponse listSearchAttributes(ListSearchAttributesRequest request, OperatorServiceGrpc.OperatorServiceBlockingStub client) {
    return client.listSearchAttributes(request);
  }

  @Mutation("addSearchAttributes")
  AddSearchAttributesResponse addSearchAttributes(AddSearchAttributesRequest request, OperatorServiceGrpc.OperatorServiceBlockingStub client) {
    return client.addSearchAttributes(request);
  }

  @Mutation("removeSearchAttributes")
  RemoveSearchAttributesResponse removeSearchAttributes(RemoveSearchAttributesRequest request, OperatorServiceGrpc.OperatorServiceBlockingStub client) {
    return client.removeSearchAttributes(request);
  }

  @Mutation("deleteNamespace")
  DeleteNamespaceResponse deleteNamespace(DeleteNamespaceRequest request, OperatorServiceGrpc.OperatorServiceBlockingStub client) {
    return client.deleteNamespace(request);
  }
}