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

package io.temporal.graphql;

import com.google.inject.AbstractModule;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.temporal.api.workflowservice.v1.WorkflowServiceGrpc;

/** Binds a stub for the {@link GreeterGrpc} service. */
final class ClientModule extends AbstractModule {

  @Override
  protected void configure() {
    ManagedChannel channel = ManagedChannelBuilder.forTarget(System.getenv("TEMPORAL_GRPC_ENDPOINT")).usePlaintext().build();
    bind(WorkflowServiceGrpc.WorkflowServiceBlockingStub.class).toInstance(WorkflowServiceGrpc.newBlockingStub(channel));
  }
}
