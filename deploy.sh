#!/bin/bash
####################################
#
# Deploy NVQS
#
####################################

deploy(){
  echo "Stating Deploy NghiaVuQuanSu System"
  cd NVQS/
  mvn clean
  mvn package

  sh /home/ec2-user/tomcat/apache-tomcat-7.0.79/bin/shutdown.sh
  rm -rf /home/ec2-user/tomcat/apache-tomcat-7.0.79/webapps/ROOT
  mv /home/ec2-user/NVQS/target/nghiavuquansu-0.0.1-SNAPSHOT.war /home/ec2-user/tomcat/apache-tomcat-7.0.79/webapps/ROOT.war
  sh /home/ec2-user/tomcat/apache-tomcat-7.0.79/bin/startup.sh
  echo "Finish..!"
}

main(){
  deploy
}

main

exit 0