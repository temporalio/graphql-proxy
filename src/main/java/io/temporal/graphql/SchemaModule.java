package io.temporal.graphql;

import com.google.inject.AbstractModule;

public final class SchemaModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new WorkflowSchemaModule());
    install(new OperatorSchemaModule());
  }
}

