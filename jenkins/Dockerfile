FROM jenkins/jenkins:lts-alpine

#Copy init.d groovy scripts
COPY docker-plugin-config.groovy /var/jenkins_home/init.groovy.d
COPY setup-users.groovy /var/jenkins_home/init.groovy.d

#Install plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt