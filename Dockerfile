FROM centos:7
RUN yum install wget -y
RUN wget https://packages.microsoft.com/yumrepos/edge/microsoft-edge-stable-95.0.1020.38-1.x86_64.rpm
RUN rpm -ivh microsoft-edge-stable-95.0.1020.38-1.x86_64.rpm

