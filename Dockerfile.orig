FROM openliberty/open-liberty:kernel-java11-openj9-ubi

EXPOSE 9080

COPY --chown=1001:0  target/*.war /config/apps/
COPY --chown=1001:0 src/main/liberty/config/*.jar /config/
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/
