FROM jenkins/jnlp-slave:alpine

ARG sonar_scanner_version

ENV SCANNER_HOME=/home/jenkins/sonar-scanner

RUN mkdir -p $SCANNER_HOME \
    && wget -nv -P /tmp https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-$sonar_scanner_version-linux.zip \
    && cd $SCANNER_HOME && unzip /tmp/sonar-scanner-cli-$sonar_scanner_version-linux.zip \
    && rm -f /tmp/sonar-scanner-cli-$sonar_scanner_version-linux.zip

#Copy the wait-for-it script
COPY ./wait-for /usr/share