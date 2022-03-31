// Copyright 2017 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.api.graphql.examples.helloworld.graphqlserver;

import com.google.api.graphql.rejoiner.Query;
import com.google.api.graphql.rejoiner.SchemaModule;
import io.temporal.api.workflowservice.v1.WorkflowServiceGrpc;
import io.temporal.api.workflowservice.v1.ListNamespacesRequest;
import io.temporal.api.workflowservice.v1.ListNamespacesResponse;

/** A GraphQL {@link SchemaModule} backed by a gRPC service. */
final class HelloWorldSchemaModule extends SchemaModule {
  @Query("listNamespaces")
  ListNamespacesResponse listNamespaces(ListNamespacesRequest request, WorkflowServiceGrpc.WorkflowServiceBlockingStub client) {
    return client.listNamespaces(request);
  }
}
