#!/bin/bash
KEYWORD="vehicle-traffic-violation-import"
pid=`ps aux | grep ${KEYWORD} | grep -v grep | awk '{print $2}'`
echo 进程pid：${pid}
if [ -n "${pid}" ]
then
  kill -9 ${pid}
  sleep 5
  cd /home/project/
  nohup java -jar ${KEYWORD}-0.0.1.jar > vtvi_log.txt 2>&1 &
  echo 新进程pid：`ps aux | grep ${KEYWORD} | grep -v grep | awk '{print $2}'`
fi

