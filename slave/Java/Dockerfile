FROM jenkins/jnlp-slave:alpine

ARG ant_version
ARG mvn_version

ENV ANT_HOME=/home/jenkins/ant
ENV MAVEN_HOME=/home/jenkins/maven
ENV PATH=$PATH:$ANT_HOME/bin:$MAVEN_HOME/bin
ENV ANT_ARGS='-logger org.apache.tools.ant.listener.AnsiColorLogger'

#Installing Maven
RUN mkdir -p $MAVEN_HOME \
    && wget -nv -P /tmp http://apache.panu.it/maven/maven-3/$mvn_version/binaries/apache-maven-$mvn_version-bin.tar.gz \
    && tar -xzf /tmp/apache-maven-$mvn_version-bin.tar.gz -C $MAVEN_HOME --strip-components=1 \
    && rm -f /tmp/apache-maven-$mvn_version-bin.tar.gz

#Installing Ant
RUN  mkdir $ANT_HOME \
    && wget -nv -P /tmp https://archive.apache.org/dist/ant/binaries/apache-ant-$ant_version-bin.tar.gz \
    && tar -xzf /tmp/apache-ant-$ant_version-bin.tar.gz -C $ANT_HOME --strip-components=1 \
    && rm -f /tmp/apache-ant-$ant_version-bin.tar.gz

#Copy the entrypoint script and the wait-for-it script
COPY ./wait-for /usr/share
COPY ./docker-entrypoint.sh /usr/share