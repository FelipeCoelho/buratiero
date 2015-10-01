limit=$(ulimit -u)
case $limit in
256)   # 1X Dyno
  heap=384
;;
512)   # 2X Dyno
  heap=768
;;
32768) # PX Dyno
  heap=6144
;;
*)
  heap=384
;;
esac
export JAVA_TOOL_OPTIONS="-Xmx${heap}m $JAVA_TOOL_OPTIONS -Djava.rmi.server.useCodebaseOnly=true"
