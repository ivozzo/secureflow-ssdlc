FROM java:8u111-jdk-alpine

ARG ant_version
ARG mvn_version

ENV ANT_HOME=/opt/ant
ENV MAVEN_HOME=/opt/maven
ENV PATH=$PATH:$ANT_HOME/bin:$MAVEN_HOME/bin
ENV ANT_ARGS='-logger org.apache.tools.ant.listener.AnsiColorLogger'

RUN apk update && apk add --update unzip \
        tar \
        wget \
        curl \
        sudo

#Installing maven
RUN mkdir -p $MAVEN_HOME \
    && curl -fsSL -o /tmp/apache-maven.tar.gz http://apache.panu.it/maven/maven-3/$mvn_version/binaries/apache-maven-$mvn_version-bin.tar.gz \
    && tar -xzf /tmp/apache-maven.tar.gz -C $MAVEN_HOME --strip-components=1 \
    && rm -f /tmp/apache-maven.tar.gz

#Ant
RUN  mkdir $ANT_HOME \
    && curl -fsSL -o /tmp/apache-ant.tar.gz https://archive.apache.org/dist/ant/binaries/apache-ant-$ant_version-bin.tar.gz \
    && tar -xzf /tmp/apache-ant.tar.gz -C $ANT_HOME --strip-components=1 \
    && rm -f /tmp/apache-ant.tar.gz

#Add user for jenkins with sudo nopassword
RUN adduser -u 1002 -D jenkins && echo "jenkins ALL = NOPASSWD : ALL" >> /etc/sudoers