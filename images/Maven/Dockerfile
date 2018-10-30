FROM java:8u111-jdk-alpine

ARG mvn_version

RUN apk update && apk add --update unzip \
        tar \
        wget \
        curl
        
ENV MAVEN_HOME=/opt/maven
ENV PATH=$PATH:$MAVEN_HOME/bin

#Installing Maven
RUN mkdir -p $MAVEN_HOME \
    && wget -q -P /tmp http://apache.panu.it/maven/maven-3/$mvn_version/binaries/apache-maven-$mvn_version-bin.tar.gz \
    && tar -xzf /tmp/apache-maven-$mvn_version-bin.tar.gz -C $MAVEN_HOME --strip-components=1 \
    && rm -f /tmp/apache-maven-$mvn_version-bin.tar.gz

#Copy the wait-for-it script
COPY ./wait-for /usr/share