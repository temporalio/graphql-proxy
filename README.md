# Temporal GraphQL API

A GraphQL server that proxies to Temporal's gRPC API (see [protos](https://github.com/temporalio/api/) and [server](https://github.com/temporalio/temporal)) using [`google/rejoiner`](https://github.com/google/rejoiner) and [GraphQL Java](https://www.graphql-java.com/).

## Get started

Build and run the GraphQL server:

```
git clone https://github.com/temporalio/graphql.git
cd graphql
./gradlew installDist
TEMPORAL_GRPC_ENDPOINT="localhost:7233" ./build/install/examples/bin/helloworld-graphqlserver
```

Open [localhost:8081](http://localhost:8081/) or point your GraphQL IDE (like [GraphQL Studio](https://studio.apollographql.com/sandbox/explorer)) at `http://localhost:8081/graphql`.