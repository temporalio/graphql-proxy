FROM openjdk:11-slim as builder

# Git is needed in order to update the dls submodule
RUN apt-get update && apt-get install -y wget protobuf-compiler git
COPY . /temporal-graphql
WORKDIR temporal-graphql
RUN ./gradlew --no-daemon installDist

FROM openjdk:11-slim
COPY --from=builder /temporal-graphql/build/install/temporal-graphql /temporal-graphql

ARG TEMPORAL_ENDPOINT="host.docker.internal:7233"
ENV TEMPORAL_GRPC_ENDPOINT=$TEMPORAL_ENDPOINT
CMD ["/temporal-graphql/bin/temporal-graphql-server"]
