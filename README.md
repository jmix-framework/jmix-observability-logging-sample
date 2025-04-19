## Jmix Observability Logging Sample

This project is part of the **Jmix Observability Guide Series** and demonstrates how to integrate centralized logging across multiple Jmix applications.

The goal of the guide series is to showcase how Jmix applications can be monitored, traced, and analyzed using modern, open-source observability tooling. This includes the use of OpenTelemetry as the standard telemetry transport protocol and the Grafana stack (Grafana, Loki) as the target platform.

In this example, we use two Jmix applications – the Petclinic backend and the Petclinic Portal frontend – which interact with each other and produce structured log output. The log data is centrally collected using OpenTelemetry and visualized in Grafana.

Learn more in the full guide: [Observability with Jmix](https://github.com/jmix-framework/jmix-guides/tree/main/jmix-observability-logging-sample)

---

### Build the JAR Files

Before starting the applications using Docker Compose, you need to build the JAR files.

#### Build petclinic (backend)
```bash
./gradlew -Pvaadin.productionMode=true --include-build jmix-petclinic-2 :jmix-petclinic-2:clean :jmix-petclinic-2:bootJar -x test --no-build-cache
```


#### Build petclinic-portal (frontend)
```bash
./gradlew -Pvaadin.productionMode=true --include-build jmix-petclinic-portal :jmix-petclinic-portal:clean :jmix-petclinic-portal:bootJar -x test --no-build-cache
```

Once the JARs are built, Docker Compose will pick them up and copy them into the containers using the provided Dockerfiles.

### Start the Applications

To run both applications along with the observability stack (PostgreSQL, Loki, Grafana, etc.), use the provided Docker Compose configuration:

```bash
docker compose -f docker/docker-compose.yaml up -d
```

```bash
docker compose -f docker/docker-compose.yaml down
```