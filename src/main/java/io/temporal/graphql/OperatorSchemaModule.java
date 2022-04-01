package io.temporal.graphql;

import com.google.api.graphql.rejoiner.Query;
import io.temporal.api.operatorservice.v1.OperatorServiceGrpc;
import io.temporal.api.operatorservice.v1.ListSearchAttributesRequest;
import io.temporal.api.operatorservice.v1.ListSearchAttributesResponse;

/** A GraphQL {@link com.google.api.graphql.rejoiner.SchemaModule} backed by a gRPC service. */
final class OperatorSchemaModule extends com.google.api.graphql.rejoiner.SchemaModule {
  @Query("listSearchAttributes")
  ListSearchAttributesResponse listSearchAttributes(ListSearchAttributesRequest request, OperatorServiceGrpc.OperatorServiceBlockingStub client) {
    return client.listSearchAttributes(request);
  }
}
