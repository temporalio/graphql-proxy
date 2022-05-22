# `graphql-full`

A GraphQL server that proxies to Temporal's gRPC API (see [protos](https://github.com/temporalio/api/) and [server](https://github.com/temporalio/temporal)) using [`google/rejoiner`](https://github.com/google/rejoiner) and [GraphQL Java](https://www.graphql-java.com/).

The current downside to this solution is how binary data like Payloads are represented. For a GraphQL server that decodes Payloads, see [`temporalio/graphql`](https://github.com/temporalio/graphql).

## Get started

Build and run the GraphQL server:

```
docker build . -t temporaltest/graphql
docker run -p 8081:8081 temporaltest/graphql
```

Open [localhost:8081](http://localhost:8081/) or point your GraphQL IDE (like [GraphQL Studio](https://studio.apollographql.com/sandbox/explorer)) at `http://localhost:8081/graphql`

Example operation:

```gql
{
  listNamespaces(input: { pageSize: 10 }) {
    namespaces {
      namespaceInfo {
        id
        name
        state
        description
        ownerEmail
        data {
          key
          value
        }
      }
      isGlobalNamespace
    }
  }
}
```

## Development

```
git clone https://github.com/temporalio/graphql-full.git
cd graphql-full
git submodule init
git submodule update
./gradlew installDist
TEMPORAL_GRPC_ENDPOINT="localhost:7233" ./build/install/temporal-graphql/bin/temporal-graphql-server
```


