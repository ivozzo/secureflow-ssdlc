FROM java:8u111-jdk-alpine

ARG ant_version

RUN apk update && apk add --update unzip \
        tar \
        wget \
        curl

ENV ANT_HOME=/opt/ant
ENV PATH=$PATH:$ANT_HOME/bin
ENV ANT_ARGS='-logger org.apache.tools.ant.listener.AnsiColorLogger'

#Installing Ant
RUN  mkdir -p $ANT_HOME \
    && wget -q -P /tmp https://archive.apache.org/dist/ant/binaries/apache-ant-$ant_version-bin.tar.gz \
    && tar -xzf /tmp/apache-ant-$ant_version-bin.tar.gz -C $ANT_HOME --strip-components=1 \
    && rm -f /tmp/apache-ant-$ant_version-bin.tar.gz

#Copy the wait-for-it script
COPY ./wait-for /usr/share