FROM centos:7
RUN rm -rf /etc/yum.repo.d/*
COPY centos.repo /etc/yum.repo.d/centos.repo
RUN yum install wget dnf -y
RUN dnf install 'dnf-command(config-manager)' -y
RUN rpm --import https://packages.microsoft.com/keys/microsoft.asc
RUN dnf config-manager --add-repo https://packages.microsoft.com/yumrepos/edge
RUN mv /etc/yum.repos.d/packages.microsoft.com_yumrepos_edge.repo /etc/yum.repos.d/microsoft-edge-dev.repo
RUN dnf install microsoft-edge-dev -y
RUN wget https://packages.microsoft.com/yumrepos/edge/microsoft-edge-stable-95.0.1020.38-1.x86_64.rpm
RUN rpm -ivh microsoft-edge-stable-95.0.1020.38-1.x86_64.rpm
