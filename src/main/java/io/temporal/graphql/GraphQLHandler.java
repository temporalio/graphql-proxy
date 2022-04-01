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

import com.google.api.graphql.execution.GuavaListenableFutureSupport;
import com.google.api.graphql.rejoiner.Schema;
import com.google.api.graphql.rejoiner.SchemaProviderModule;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Key;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.schema.GraphQLSchema;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class GraphQLHandler extends AbstractHandler {
  private static final Gson GSON = new GsonBuilder().serializeNulls().create();
  private static final TypeToken<Map<String, Object>> MAP_TYPE_TOKEN = new TypeToken<>() {};

  private static final GraphQLSchema SCHEMA =
      Guice.createInjector(
              new SchemaProviderModule(),
              new ClientModule(),
              new SchemaModule())
          .getInstance(Key.get(GraphQLSchema.class, Schema.class));

  private static final Instrumentation INSTRUMENTATION =
      new ChainedInstrumentation(
          Arrays.asList(
              GuavaListenableFutureSupport.listenableFutureInstrumentation(),
              new TracingInstrumentation()));

  private static final GraphQL GRAPHQL =
      GraphQL.newGraphQL(SCHEMA).instrumentation(INSTRUMENTATION).build();

  @Override
  public void handle(String target, Request baseRequest, jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException, jakarta.servlet.ServletException {
    if ("/graphql".equals(target)) {
      baseRequest.setHandled(true);
      if (request.getMethod().equals("OPTIONS")) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setStatus(HttpServletResponse.SC_OK);
        return;
      }

      Map<String, Object> json = readJson(request);
      String query = (String) json.get("query");
      if (query == null) {
        response.setStatus(400);
        return;
      }
      String operationName = (String) json.get("operationName");
      Map<String, Object> variables = getVariables(json.get("variables"));

      ExecutionInput executionInput =
          ExecutionInput.newExecutionInput()
              .query(query)
              .operationName(operationName)
              .variables(variables)
              .context(new Object())
              .build();
      ExecutionResult executionResult = GRAPHQL.execute(executionInput);
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_OK);
      response.setHeader("access-control-allow-origin", "*");
      GSON.toJson(executionResult.toSpecification(), response.getWriter());
    }
  }

  private static Map<String, Object> getVariables(Object variables) {
    Map<String, Object> variablesWithStringKey = new HashMap<>();
    if (variables instanceof Map) {
      ((Map) variables).forEach((k, v) -> variablesWithStringKey.put(String.valueOf(k), v));
    }
    return variablesWithStringKey;
  }

  private static Map<String, Object> readJson(jakarta.servlet.http.HttpServletRequest request) {
    try {
      String json = CharStreams.toString(request.getReader());
      return jsonToMap(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static Map<String, Object> jsonToMap(String json) {
    if (Strings.isNullOrEmpty(json)) {
      return ImmutableMap.of();
    }
    return Optional.<Map<String, Object>>ofNullable(GSON.fromJson(json, MAP_TYPE_TOKEN.getType()))
        .orElse(ImmutableMap.of());
  }
}
